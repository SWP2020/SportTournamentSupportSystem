package doan2020.SportTournamentSupportSystem.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import doan2020.SportTournamentSupportSystem.config.Const;
import doan2020.SportTournamentSupportSystem.entity.CompetitionEntity;
import doan2020.SportTournamentSupportSystem.entity.FinalStageSettingEntity;
import doan2020.SportTournamentSupportSystem.entity.FormatEntity;
import doan2020.SportTournamentSupportSystem.entity.GroupStageSettingEntity;
import doan2020.SportTournamentSupportSystem.entity.MatchEntity;
import doan2020.SportTournamentSupportSystem.entity.ResultEntity;
import doan2020.SportTournamentSupportSystem.entity.TeamEntity;
import doan2020.SportTournamentSupportSystem.model.BoxCollection.RankingTable;
import doan2020.SportTournamentSupportSystem.model.Entity.Match;
import doan2020.SportTournamentSupportSystem.model.Indexing.BoxDescription;
import doan2020.SportTournamentSupportSystem.model.Indexing.RevertMapping;
import doan2020.SportTournamentSupportSystem.model.Schedule.DTO.DoubleEliminationScheduleDTO;
import doan2020.SportTournamentSupportSystem.model.Schedule.DTO.FinalStageScheduleDTO;
import doan2020.SportTournamentSupportSystem.model.Schedule.DTO.GroupStageScheduleDTO;
import doan2020.SportTournamentSupportSystem.model.Schedule.DTO.RoundRobinScheduleDTO;
import doan2020.SportTournamentSupportSystem.model.Schedule.DTO.ScheduleDTO;
import doan2020.SportTournamentSupportSystem.model.Schedule.DTO.SingleEliminationScheduleDTO;
import doan2020.SportTournamentSupportSystem.model.Schedule.DTO.UnknownScheduleDTO;
import doan2020.SportTournamentSupportSystem.model.Schedule.Format.DoubleEliminationTree;
import doan2020.SportTournamentSupportSystem.model.Schedule.Format.RoundRobinTable;
import doan2020.SportTournamentSupportSystem.model.Schedule.Format.SingleEliminationTree;
import doan2020.SportTournamentSupportSystem.model.Struct.Node;
import doan2020.SportTournamentSupportSystem.service.ICompetitionService;
import doan2020.SportTournamentSupportSystem.service.IFileStorageService;
import doan2020.SportTournamentSupportSystem.service.IMatchService;
import doan2020.SportTournamentSupportSystem.service.IScheduleService;
import doan2020.SportTournamentSupportSystem.service.ITeamService;
import doan2020.SportTournamentSupportSystem.validator.Validator;

@Service
public class ScheduleService implements IScheduleService {

	@Autowired
	private IFileStorageService fileService;

	@Autowired
	private ICompetitionService competitionService;

	@Autowired
	private ITeamService teamService;

	@Autowired
	private Validator validator;

	@Autowired
	private IMatchService matchService;

	@Override
	public ScheduleDTO getSchedule(CompetitionEntity thisCompetition) {
		System.out.println("ScheduleService: getSchedule: start");
		ScheduleDTO schedule;
		Long competitionId = thisCompetition.getId();
		try {
			schedule = getScheduleFromFile(competitionId);
			// Check for changes in team numbers
			int dbTeamNumber = teamService.countByCompetitionIdAndStatus(competitionId, Const.TEAM_STATUS_JOINED)
					.intValue();
			int cfTeamNumber = schedule.getTotalTeam();

			boolean checkTeamNumber = (dbTeamNumber == cfTeamNumber);

			String dbFinalStageFormat = thisCompetition.getFinalStageSetting().getFormat().getName();
			String cfFinalStageFormat = schedule.getFinalStageSchedule().getFormatName();

			boolean checkFinalStageFormat = (dbFinalStageFormat.contains(cfFinalStageFormat));
			boolean checkGroupStageFormat = true;
			if (thisCompetition.isHasGroupStage()) {
				String dbGroupStageFormat = thisCompetition.getGroupStageSetting().getFormat().getName();
				String cfGroupStageFormat = schedule.getGroupStageSchedule().getFormatName();
				checkGroupStageFormat = (dbGroupStageFormat.contains(cfGroupStageFormat));
			}

			if (checkFinalStageFormat && checkGroupStageFormat && checkTeamNumber) {
				System.out.println("ScheduleService: getSchedule: finish");
				return schedule;
			} else {
				System.out.println("setting has change");
				System.out.println("ScheduleService: getSchedule: finish -> ScheduleService: createSchedule:");
				return createSchedule(thisCompetition);
			}
		} catch (Exception e) {
			System.out.println("schedule not yet created");
			System.out.println("ScheduleService: getSchedule: finish -> ScheduleService: createSchedule:");
			return createSchedule(thisCompetition);
		}
	}

	private ScheduleDTO getScheduleFromFile(Long competitionId) {
		String fileName = Const.COMPETITION_SCHEDULING;
		String absFolderPath = null;
		String absFilePath = null;
		ScheduleDTO schedule = new ScheduleDTO();

		try {

			String folder = Const.COMPETITION_FILESYSTEM + Const.COMPETITION_FOLDER_NAMING + competitionId;
			absFolderPath = fileService.getFileStorageLocation(folder).toString();
			absFilePath = absFolderPath + "\\" + fileName;
			schedule = (ScheduleDTO) fileService.getObjectFromFile(absFilePath);

		} catch (Exception e) {
		}

		return schedule;
	}

	private String saveScheduleToFile(ScheduleDTO schedule, Long competitionId) {
		String fileName = Const.COMPETITION_SCHEDULING;
		String absFolderPath = null;
		String absFilePath = null;

		try {
			String folder = Const.COMPETITION_FILESYSTEM + Const.COMPETITION_FOLDER_NAMING + competitionId;
			absFolderPath = fileService.getFileStorageLocation(folder).toString();
			absFilePath = absFolderPath + "\\" + fileName;
			absFilePath = fileService.saveObjectToFile(schedule, absFilePath);

			System.out.println("ScheduleService: saveSchedule: absFileName: \n[" + absFilePath + "]");
		} catch (Exception e) {
		}

		return absFilePath;

	}

	/*----------------------------------------------------------------------
	 * initialize matches in db
	 * include createMatchesInDatabase() - 3 overload
	 * 
	 */

	public void createMatchesInDatabase(CompetitionEntity thisCompetition) {

//		try {

		System.out.println("ScheduleService: createMatchesInDatabase: start");
		Long competitionId = thisCompetition.getId();
		System.out.println("get id done");
		ScheduleDTO schedule = getSchedule(thisCompetition);
		System.out.println("get schedule done");
		String finalStageFormat = schedule.getFinalStageSchedule().getFormatName();
		System.out.println("get final stage schedule done");
		ArrayList<TeamEntity> teams = new ArrayList<>();

		System.out.println("init OK");
		if (!schedule.isHasGroupStage()) {
			System.out.println("hasn\'t group stage");
			teams.addAll(teamService.findByCompetitionIdAndStatus(competitionId, Const.TEAM_STATUS_JOINED));

		} else {
			System.out.println("has group stage");
			GroupStageScheduleDTO groupStageSchedule = schedule.getGroupStageSchedule();
			String groupStageFormat = groupStageSchedule.getFormatName();
			ArrayList<FinalStageScheduleDTO> tables = groupStageSchedule.getTables();
			ArrayList<TeamEntity> realTeams = new ArrayList<>();

			realTeams.addAll(teamService.findByCompetitionIdAndStatus(competitionId, Const.TEAM_STATUS_JOINED));
			Collections.sort(realTeams, new TeamEntity());

			for (FinalStageScheduleDTO table : tables) {

				ArrayList<TeamEntity> tableTeams = new ArrayList<>();
				int firstSeed = table.getFirstSeed();
				int totalTeam = table.getTotalTeam();

				int seed = 0;
				while (seed < totalTeam) {
					tableTeams.add(realTeams.get(seed + firstSeed));
					seed++;
				}

				if (groupStageFormat.contains(Const.SINGLE_ELIMINATION_FORMAT)) {
					table = createMatchesInDatabase((SingleEliminationScheduleDTO) table, tableTeams, thisCompetition);
				}
				if (groupStageFormat.contains(Const.DOUBLE_ELIMINATION_FORMAT)) {
					table = createMatchesInDatabase((DoubleEliminationScheduleDTO) table, tableTeams, thisCompetition);
				}
				if (groupStageFormat.contains(Const.ROUND_ROBIN_FORMAT)) {
					table = createMatchesInDatabase((RoundRobinScheduleDTO) table, tableTeams, thisCompetition);
				}
				schedule.getMapping().putAll(table.getMapping());
			}

			schedule.setGroupStageSchedule(groupStageSchedule);
			//
		}

		System.out.println("save for final stage");

		if (finalStageFormat.contains(Const.SINGLE_ELIMINATION_FORMAT)) {
			SingleEliminationScheduleDTO finalStageSchedule = (SingleEliminationScheduleDTO) schedule
					.getFinalStageSchedule();
			schedule.setFinalStageSchedule(createMatchesInDatabase(finalStageSchedule, teams, thisCompetition));
			schedule.getMapping().putAll(finalStageSchedule.getMapping());
		}
		if (finalStageFormat.contains(Const.DOUBLE_ELIMINATION_FORMAT)) {
			DoubleEliminationScheduleDTO finalStageSchedule = (DoubleEliminationScheduleDTO) schedule
					.getFinalStageSchedule();
			schedule.setFinalStageSchedule(createMatchesInDatabase(finalStageSchedule, teams, thisCompetition));
			schedule.getMapping().putAll(finalStageSchedule.getMapping());
		}
		if (finalStageFormat.contains(Const.ROUND_ROBIN_FORMAT)) {
			RoundRobinScheduleDTO finalStageSchedule = (RoundRobinScheduleDTO) schedule.getFinalStageSchedule();
			schedule.setFinalStageSchedule(createMatchesInDatabase(finalStageSchedule, teams, thisCompetition));
			schedule.getMapping().putAll(finalStageSchedule.getMapping());
		}

		saveScheduleToFile(schedule, competitionId);

//		System.out.println("ScheduleService: createMatchesInDatabase: no exception");
//		} catch (Exception e) {
//			System.out.println("ScheduleService: createMatchesInDatabase: has exception");
//			System.out.println(e);
//		}
		System.out.println("ScheduleService: createMatchesInDatabase: finish");

	}

	private SingleEliminationScheduleDTO createMatchesInDatabase(SingleEliminationScheduleDTO schedule,
			ArrayList<TeamEntity> teams, CompetitionEntity competition) {
		System.out.println("ScheduleService: SingleEliminationScheduleDTO createMatchesInDatabase: start");
		SingleEliminationTree tree = new SingleEliminationTree(schedule.getBracket(), schedule.getTotalTeam());
		tree.applyTeams(teams);
		System.out.println("match total: " + tree.getMatches().size());
		for (Match match : tree.getMatches()) {
			MatchEntity realMatch = new MatchEntity();
			realMatch.setCompetition(competition);

			realMatch.setName(match.getName());
			realMatch.setLocation(match.getLocation());
			realMatch.setTime(validator.formatStringToDate(match.getTime()));

			if (match.getStatus() == -1) {
				realMatch.setStatus(Const.MATCH_STATUS_FINISHED);
			}
			if (match.getStatus() == 0) {
				realMatch.setStatus(Const.MATCH_STATUS_PLAYING);
			}
			if (match.getStatus() > 0) {
				realMatch.setStatus(Const.MATCH_STATUS_PENDING);
			}

			try {
				realMatch.setTeam1(teamService.findOneById(match.getTeam1().getTeam().getId()));
				realMatch.setTeam2(teamService.findOneById(match.getTeam2().getTeam().getId()));
			} catch (Exception e) {
			}

			int nodeId = tree.getBracket().findNodeByData(match).getId();

			realMatch = matchService.create(realMatch);
			match.setId(realMatch.getId());

			RevertMapping map = new RevertMapping();
			map.setRealMatchId(match.getId());
			map.setLocation(0);
			map.setScheduleType(1);
			map.setTableId(schedule.getTableId());
			map.setNodeId(nodeId);
			System.out.println("map set OK");
			schedule.getMapping().put(match.getId(), map);
		}
		if (tree.getTotalTeam() >= 4) {
			Match match = schedule.getMatch34();
			MatchEntity realMatch = new MatchEntity();
			realMatch.setCompetition(competition);

			realMatch.setName(match.getName());
			realMatch.setLocation(match.getLocation());
			realMatch.setTime(validator.formatStringToDate(match.getTime()));

			realMatch.setStatus(Const.MATCH_STATUS_PENDING);

			realMatch = matchService.create(realMatch);
			match.setId(realMatch.getId());
			RevertMapping map = new RevertMapping();
			map.setRealMatchId(match.getId());
			map.setLocation(3);
			map.setScheduleType(1);
			map.setTableId(schedule.getTableId());
			map.setNodeId(-1);
			schedule.getMapping().put(match.getId(), map);
		}
		schedule.setBracket(tree.getBracket());

		System.out.println("ScheduleService: SingleEliminationScheduleDTO createMatchesInDatabase: finish");
		return schedule;
	}

	private DoubleEliminationScheduleDTO createMatchesInDatabase(DoubleEliminationScheduleDTO schedule,
			ArrayList<TeamEntity> teams, CompetitionEntity competition) {
		System.out.println("ScheduleService: saveFinalScheduleToDatabase: start");
		DoubleEliminationTree tree = new DoubleEliminationTree(schedule.getWinBranch(), schedule.getLoseBranch(),
				schedule.getTotalTeam());
		tree.applyTeams(teams);
		System.out.println("apply tree OK");
		for (Match match : tree.getMatches()) {
			MatchEntity realMatch = new MatchEntity();
			realMatch.setCompetition(competition);
			realMatch.setName(match.getName());
			realMatch.setLocation(match.getLocation());
			realMatch.setTime(validator.formatStringToDate(match.getTime()));

			if (match.getStatus() == -1) {
				realMatch.setStatus(Const.MATCH_STATUS_FINISHED);
			}
			if (match.getStatus() == 0) {
				realMatch.setStatus(Const.MATCH_STATUS_PLAYING);
			}
			if (match.getStatus() > 0) {
				realMatch.setStatus(Const.MATCH_STATUS_PENDING);
			}
			System.out.println("try to set team");
			try {
				realMatch.setTeam1(teamService.findOneById(match.getTeam1().getTeam().getId()));
				realMatch.setTeam2(teamService.findOneById(match.getTeam2().getTeam().getId()));
			} catch (Exception e) {
			}

			int nodeId;
			RevertMapping map = new RevertMapping();

			if (match.getName().contains(Const.LOSE_BRANCH_NAMING)) {
				Node<Match> node = tree.getLoseBranch().findNodeByData(match);
				nodeId = node.getId();
				map.setLocation(2);
				map.setDegree(node.getDegree() + 1);
			} else {
				Node<Match> node = tree.getWinBranch().findNodeByData(match);
				nodeId = node.getId();
				map.setLocation(1);
				map.setDegree(node.getDegree());
			}
			System.out.println("try to create in db");
			realMatch = matchService.create(realMatch);
			match.setId(realMatch.getId());

			System.out.println("try to mapping");
			map.setRealMatchId(match.getId());
			map.setScheduleType(2);
			map.setTableId(schedule.getTableId());
			map.setNodeId(nodeId);
			System.out.println("init map OK");
			schedule.getMapping().put(match.getId(), map);
			System.out.println("mapping OK");
		}
		System.out.println("solve summay final");
		if (tree.getTotalTeam() >= 3) {
			Match finalMatch = tree.getSummaryFinal();
			MatchEntity realFinalMatch = new MatchEntity();
			realFinalMatch.setCompetition(competition);
			;
			realFinalMatch.setName(finalMatch.getName());
			realFinalMatch.setLocation(finalMatch.getLocation());
			realFinalMatch.setTime(validator.formatStringToDate(finalMatch.getTime()));

			realFinalMatch.setStatus(Const.MATCH_STATUS_PENDING);

			realFinalMatch = matchService.create(realFinalMatch);
			finalMatch.setId(realFinalMatch.getId());

			RevertMapping map = new RevertMapping();
			map.setRealMatchId(finalMatch.getId());
			map.setLocation(4);
			map.setScheduleType(2);
			map.setTableId(schedule.getTableId());
			map.setNodeId(-1);
			map.setDegree(0);
			schedule.getMapping().put(finalMatch.getId(), map);

			Match optionMatch = tree.getOptionFinal();
			MatchEntity realOptionMatch = new MatchEntity();
			realOptionMatch.setCompetition(competition);
			;
			realOptionMatch.setName(optionMatch.getName());
			realOptionMatch.setLocation(optionMatch.getLocation());
			realOptionMatch.setTime(validator.formatStringToDate(optionMatch.getTime()));

			realOptionMatch.setStatus(Const.MATCH_STATUS_UNKNOWN);

			realOptionMatch = matchService.create(realOptionMatch);
			optionMatch.setId(realOptionMatch.getId());

			RevertMapping map2 = new RevertMapping();
			map2.setRealMatchId(optionMatch.getId());
			map2.setLocation(5);
			map2.setScheduleType(2);
			map2.setTableId(schedule.getTableId());
			map2.setNodeId(-1);
			map2.setDegree(0);
			schedule.getMapping().put(optionMatch.getId(), map2);
		}
		schedule.setWinBranch(tree.getWinBranch());
		schedule.setLoseBranch(tree.getLoseBranch());

		System.out.println("ScheduleService: saveFinalScheduleToDatabase: finish");
		return schedule;
	}

	private RoundRobinScheduleDTO createMatchesInDatabase(RoundRobinScheduleDTO schedule, ArrayList<TeamEntity> teams,
			CompetitionEntity competition) {
		RoundRobinTable table = new RoundRobinTable((long) schedule.getTableId(), schedule.getMatches(),
				schedule.getTotalTeam(), schedule.isHasHomeMatch());
		table.applyTeams(teams);
		for (Match match : table.getMatches()) {
			MatchEntity realMatch = new MatchEntity();
			realMatch.setCompetition(competition);
			;
			realMatch.setName(match.getName());
			realMatch.setLocation(match.getLocation());
			realMatch.setTime(validator.formatStringToDate(match.getTime()));

			if (match.getStatus() == -1) {
				realMatch.setStatus(Const.MATCH_STATUS_FINISHED);
			}
			if (match.getStatus() == 0) {
				realMatch.setStatus(Const.MATCH_STATUS_PLAYING);
			}
			if (match.getStatus() > 0) {
				realMatch.setStatus(Const.MATCH_STATUS_PENDING);
			}

			try {
				realMatch.setTeam1(teamService.findOneById(match.getTeam1().getTeam().getId()));
				realMatch.setTeam2(teamService.findOneById(match.getTeam2().getTeam().getId()));
			} catch (Exception e) {
			}

			realMatch = matchService.create(realMatch);
			match.setId(realMatch.getId());

			RevertMapping map = new RevertMapping();
			map.setRealMatchId(match.getId());
			map.setLocation(-1);
			map.setScheduleType(3);
			map.setTableId(schedule.getTableId());
			map.setNodeId(match.getMatchNo());
			map.setDegree(0);
			schedule.getMapping().put(match.getId(), map);
		}

		schedule.setMatches(table.getMatches());

		return schedule;
	}
	// ----------------------------------------------------------------------

	/*
	 * -----------------------------------------------------------------------------
	 * ---create
	 */
	@Override
	public ScheduleDTO createSchedule(CompetitionEntity thisCompetition) {
		System.out.println("ScheduleService: createSchedule: start");
		ScheduleDTO schedule = new ScheduleDTO();
		HashMap<Long, RevertMapping> map = new HashMap<Long, RevertMapping>();

		int totalTeamBeforeGroupStage;
		int totalTeamAfterGroupStage;

		Long competitionId = thisCompetition.getId();

		FormatEntity finalFormat;
		FormatEntity groupFormat;

		ArrayList<BoxDescription> descriptions = new ArrayList<>();

		totalTeamBeforeGroupStage = teamService.countByCompetitionIdAndStatus(competitionId, Const.TEAM_STATUS_JOINED)
				.intValue();

		boolean hasGroupStage = thisCompetition.isHasGroupStage();

		FinalStageSettingEntity fsse = thisCompetition.getFinalStageSetting();

		FinalStageScheduleDTO fss;
		GroupStageScheduleDTO gss = new GroupStageScheduleDTO();

		// group
		if (hasGroupStage) {

			GroupStageSettingEntity gsse = thisCompetition.getGroupStageSetting();
			groupFormat = gsse.getFormat();
			boolean hasHomeMatch = gsse.isHasHomeMatch();
			int maxTeamPerTable = gsse.getMaxTeamPerTable();
			int advanceTeamPerTable = gsse.getAdvanceTeamPerTable();
			int totalTable = (int) (totalTeamBeforeGroupStage / maxTeamPerTable);

			int totalTeamInFinalTable;

			if (totalTable != 0) {
				totalTeamInFinalTable = totalTeamBeforeGroupStage % totalTable;
			} else {
				totalTeamInFinalTable = totalTeamBeforeGroupStage;
			}

			if (totalTeamInFinalTable == 0) {
				totalTeamInFinalTable = maxTeamPerTable;
			} else {
				totalTable++;
			}

			// schedule for group stage
			String formatName = groupFormat.getName();
			gss = groupStageScheduling(totalTeamBeforeGroupStage, formatName, hasHomeMatch, maxTeamPerTable,
					advanceTeamPerTable, totalTable, totalTeamInFinalTable);

			totalTeamAfterGroupStage = (totalTable - 1) * advanceTeamPerTable
					+ (int) Math.min(totalTeamInFinalTable, advanceTeamPerTable);

			// create descriptions
			ArrayList<RankingTable> rankingTables = new ArrayList<>();

			ArrayList<FinalStageScheduleDTO> tables = gss.getTables();
			for (FinalStageScheduleDTO tb : tables) {
				RankingTable rankingTable = tb.getRankingTable();
				rankingTables.add(rankingTable);
			}

			for (int i = 0; i < advanceTeamPerTable; i++) {
				for (RankingTable tb : rankingTables) {
					if (tb.size() > i) {
						descriptions.add(tb.get(i).getDescription());
					}
				}
			}

			map.putAll(gss.getMapping());

		} else {

			totalTeamAfterGroupStage = totalTeamBeforeGroupStage;
		}

		// final

		finalFormat = fsse.getFormat();
		boolean hasHomeMatch = fsse.isHasHomeMatch();
		String formatName = finalFormat.getName();
		fss = finalStageScheduling(totalTeamAfterGroupStage, formatName, hasHomeMatch, -1, descriptions, 1);

		schedule.setTotalTeam(totalTeamBeforeGroupStage);
		schedule.setHasGroupStage(hasGroupStage);
		schedule.setFinalStageSchedule(fss);
		schedule.setGroupStageSchedule(gss);

		map.putAll(fss.getMapping());
		schedule.setMapping(map);

		saveScheduleToFile(schedule, competitionId);

		System.out.println("ScheduleService: createSchedule: finish");
		return schedule;
	}

	private FinalStageScheduleDTO finalStageScheduling(int totalTeam, String formatName, boolean hasHomeMatch,
			int tableId, ArrayList<BoxDescription> descriptions, int firstSeed) {
		System.out.println("ScheduleService: finalStageScheduling: start");

		// debug -------------------------------------------------------
		System.out.println("ScheduleService: finalStageScheduling: totalTeam: " + totalTeam);
		System.out.println("ScheduleService: finalStageScheduling: formatName: " + formatName);
		System.out.println("ScheduleService: finalStageScheduling: tableId: " + tableId);
		// -------------------------------------------------------------

		try {
			if (formatName.contains(Const.SINGLE_ELIMINATION_FORMAT)) {
				SingleEliminationScheduleDTO dto = new SingleEliminationScheduleDTO();
				System.out.println("ScheduleService: finalStageScheduling: -> SingleElimination: Constructor:");
				SingleEliminationTree tree = new SingleEliminationTree(totalTeam);
				System.out.println("SingleElimination: Constructor: -> ScheduleService: finalStageScheduling:");
				System.out.println("ScheduleService: finalStageScheduling: build tree OK");
				if (tableId < 0) {
					tree.applyDescriptions(descriptions);
					System.out.println("ScheduleService: finalStageScheduling: apply description OK");
				} else {
					tree.applyDescriptions(firstSeed);
					System.out.println("ScheduleService: finalStageScheduling: apply description OK");
				}

				tree.setTableId(tableId);
				System.out.println("ScheduleService: finalStageScheduling: set table id OK");

				dto.setBracket(tree.getBracket());
				dto.setHasMatch34(hasHomeMatch);
				dto.setMatch34(tree.getMatch34());

				dto.setRankingTable(tree.getRankingTable());
				dto.setMatches(tree.getMatches());
				dto.setFormatName(formatName);
				dto.setTotalTeam(totalTeam);
				dto.setTableId(tableId);
				if (tableId >= 0)
					dto.setTableName("" + Const.TABLE_NAMING.charAt(tableId));
				else
					dto.setTableName("");
				dto.setFirstSeed(firstSeed);
				dto.setTotalRound(tree.getTotalRound());
				dto.setRoundsNaming();
				dto.setStatus(Const.STAGE_INITIALIZING);
				dto.setMapping(new HashMap<Long, RevertMapping>());

				System.out.println("ScheduleService: finalStageScheduling: finish");
				return dto;
			}

			if (formatName.contains(Const.DOUBLE_ELIMINATION_FORMAT)) {

				DoubleEliminationScheduleDTO dto = new DoubleEliminationScheduleDTO();

				DoubleEliminationTree tree = new DoubleEliminationTree(totalTeam);
				if (tableId < 0)
					tree.applyDescriptions(descriptions);
				else {
					tree.applyDescriptions(firstSeed);
				}
				tree.setTableId(tableId);

				dto.setWinBranch(tree.getWinBranch());
				dto.setLoseBranch(tree.getLoseBranch());
				System.out.println(tree.getLoseBranch().toString());
				dto.setSummaryFinal(tree.getSummaryFinal());
				dto.setOptionFinal(tree.getOptionFinal());
				dto.setTotalWinRound(tree.getTotalRound());
				dto.setTotalLoseRound(tree.getTotalLoseBranchRound());

				dto.setRankingTable(tree.getRankingTable());
				dto.setMatches(tree.getMatches());
				dto.setFormatName(formatName);
				dto.setTotalTeam(totalTeam);
				dto.setTableId(tableId);
				if (tableId >= 0)
					dto.setTableName("" + Const.TABLE_NAMING.charAt(tableId));
				else
					dto.setTableName("");
				dto.setFirstSeed(firstSeed);
				dto.setTotalRound(tree.getTotalRound());
				dto.setRoundsNaming();
				dto.setStatus(Const.STAGE_INITIALIZING);
				dto.setMapping(new HashMap<Long, RevertMapping>());

				System.out.println("ScheduleService: finalStageScheduling: finish");
				return dto;
			}

			if (formatName.contains(Const.ROUND_ROBIN_FORMAT)) {

				RoundRobinScheduleDTO dto = new RoundRobinScheduleDTO();
				RoundRobinTable table;

				if (tableId >= 0) {
					table = new RoundRobinTable((long) tableId, totalTeam, hasHomeMatch);
					table.applyDescriptions(firstSeed);

				} else {
					table = new RoundRobinTable(totalTeam, hasHomeMatch);
					table.applyDescriptions(descriptions);
				}

				table.setTableId(tableId);
				dto.setHasHomeMatch(hasHomeMatch);

				dto.setRankingTable(table.getRankingTable());
				dto.setMatches(table.getMatches());
				dto.setFormatName(formatName);
				dto.setTotalTeam(totalTeam);
				dto.setTableId(tableId);
				if (tableId >= 0)
					dto.setTableName("" + Const.TABLE_NAMING.charAt(tableId));
				else
					dto.setTableName("");
				dto.setFirstSeed(firstSeed);
				dto.setTotalRound(table.getTotalRound());
				dto.setRoundsNaming();
				dto.setStatus(Const.STAGE_INITIALIZING);
				dto.setMapping(new HashMap<Long, RevertMapping>());

				System.out.println("ScheduleService: finalStageScheduling: finish");
				return dto;
			}
		} catch (Exception e) {
			System.out.println("ScheduleService: finalStageScheduling: has exception");
			System.out.println(e);
			System.out.println("-------");
			System.out.println("ScheduleService: finalStageScheduling: finish");
			return null;
		}

		FinalStageScheduleDTO schedule = new UnknownScheduleDTO();

		schedule.setRankingTable(new RankingTable());
		schedule.setMatches(new ArrayList<>());
		schedule.setFormatName(Const.ANOTHER_FORMAT);
		schedule.setTotalTeam(totalTeam);
		schedule.setTableId(tableId);
		schedule.setFirstSeed(firstSeed);
		schedule.setTotalRound(0);
		System.out.println("ScheduleService: finalStageScheduling: finish");
		return schedule;
	}

	private GroupStageScheduleDTO groupStageScheduling(int totalTeam, String formatName, boolean hasHomeMatch,
			int maxTeamPerTable, int advanceTeamPerTable, int totalTable, int totalTeamInFinalTable) {
		System.out.println("ScheduleService: groupStageScheduling: start");
		GroupStageScheduleDTO dto = new GroupStageScheduleDTO();

		dto.setTotalTeam(totalTeam);
		dto.setFormatName(formatName);
		dto.setHasHomeMatch(hasHomeMatch);
		dto.setMaxTeamPerTable(maxTeamPerTable);
		dto.setAdvanceTeamPerTable(advanceTeamPerTable);
		dto.setTotalTable(totalTable);
		dto.setTotalTeamInFinalTable(totalTeamInFinalTable);

		HashMap<Long, RevertMapping> map = new HashMap<Long, RevertMapping>();
		ArrayList<FinalStageScheduleDTO> tables = new ArrayList<>();
		ArrayList<BoxDescription> descriptions = new ArrayList<>();

		for (int tableId = 0; tableId < totalTable - 1; tableId++) {

			int firstSeed = tableId * maxTeamPerTable + 1;
			System.out.println("ScheduleService: groupStageScheduling: -> ScheduleService: finalStageScheduling");
			FinalStageScheduleDTO table = finalStageScheduling(maxTeamPerTable, formatName, hasHomeMatch, tableId,
					descriptions, firstSeed);
			System.out.println("ScheduleService: finalStageScheduling -> ScheduleService: groupStageScheduling");
			map.putAll(table.getMapping());
			tables.add(table);
		}

		int firstSeed = (totalTable - 1) * maxTeamPerTable;
		System.out.println("ScheduleService: groupStageScheduling: -> ScheduleService: finalStageScheduling");
		tables.add(finalStageScheduling(totalTeamInFinalTable, formatName, hasHomeMatch, totalTable - 1, descriptions,
				firstSeed));
		System.out.println("ScheduleService: finalStageScheduling -> ScheduleService: groupStageScheduling");
		dto.setTables(tables);
		dto.setStatus(Const.STAGE_INITIALIZING);
		dto.setMapping(map);

		System.out.println("ScheduleService: groupStageScheduling: finish");
		return dto;
	}

	// --------------------

	/*
	 * --------------------------------------------------------------------- update
	 * result for a match after finished synchronize db and schedule
	 */

	@Override
	public ScheduleDTO finishMatch(MatchEntity match) {

		ArrayList<ResultEntity> results = new ArrayList<>();
		results.addAll(match.getResults());
		
		CompetitionEntity thisCompetition = match.getCompetition();
		Long competitionId = thisCompetition.getId();
		ScheduleDTO schedule = getScheduleFromFile(competitionId);

		HashMap<Long, RevertMapping> allMap = schedule.getMapping();

		RevertMapping thisMap = allMap.get(match.getId());
		int nodeId = thisMap.getNodeId();
		int tableId = thisMap.getTableId();
		int degree = thisMap.getDegree();

		Match thisMatch = new Match();

		switch (thisMap.getLocation()) {
		case -1:
			if (tableId >= 0) {
				GroupStageScheduleDTO gss = schedule.getGroupStageSchedule();
				RoundRobinScheduleDTO thisTable = (RoundRobinScheduleDTO) gss.getTables().get(thisMap.getTableId());
				thisMatch = thisTable.getMatches().get(nodeId);
				

			} else {
				RoundRobinScheduleDTO fss = (RoundRobinScheduleDTO) schedule.getFinalStageSchedule();
				thisMatch = fss.getMatches().get(nodeId);
			}

			break;
		case 0:
			if (tableId >= 0) {
				GroupStageScheduleDTO gss = schedule.getGroupStageSchedule();
				SingleEliminationScheduleDTO thisTable = (SingleEliminationScheduleDTO)gss.getTables().get(thisMap.getTableId());
				thisMatch = thisTable.getBracket().getById(nodeId).getData();
				
			} else {
				SingleEliminationScheduleDTO fss = (SingleEliminationScheduleDTO)schedule.getFinalStageSchedule();
				thisMatch = fss.getBracket().getById(nodeId).getData();
			}
			break;
		case 1:
			if (tableId >= 0) {
				GroupStageScheduleDTO gss = schedule.getGroupStageSchedule();
				DoubleEliminationScheduleDTO thisTable = (DoubleEliminationScheduleDTO)gss.getTables().get(thisMap.getTableId());
				thisMatch = thisTable.getWinBranch().getById(nodeId).getData();

			} else {
				DoubleEliminationScheduleDTO fss = (DoubleEliminationScheduleDTO)schedule.getFinalStageSchedule();
				thisMatch = fss.getWinBranch().getById(nodeId).getData();
			}
			break;
		case 2:
			if (tableId >= 0) {
				GroupStageScheduleDTO gss = schedule.getGroupStageSchedule();
				DoubleEliminationScheduleDTO thisTable = (DoubleEliminationScheduleDTO)gss.getTables().get(thisMap.getTableId());
				thisMatch = thisTable.getLoseBranch().getByIdAndDegree(nodeId, degree).getData();

			} else {
				DoubleEliminationScheduleDTO fss = (DoubleEliminationScheduleDTO)schedule.getFinalStageSchedule();
				thisMatch = fss.getLoseBranch().getByIdAndDegree(nodeId, degree).getData();
			}
			break;
		case 3:
			if (tableId >= 0) {
				GroupStageScheduleDTO gss = schedule.getGroupStageSchedule();
				SingleEliminationScheduleDTO thisTable = (SingleEliminationScheduleDTO)gss.getTables().get(thisMap.getTableId());
				thisMatch = thisTable.getMatch34();

			} else {
				SingleEliminationScheduleDTO fss = (SingleEliminationScheduleDTO)schedule.getFinalStageSchedule();
				thisMatch = fss.getMatch34();
			}
			break;
		case 4:
			if (tableId >= 0) {
				GroupStageScheduleDTO gss = schedule.getGroupStageSchedule();
				DoubleEliminationScheduleDTO thisTable = (DoubleEliminationScheduleDTO)gss.getTables().get(thisMap.getTableId());
				thisMatch = thisTable.getSummaryFinal();

			} else {
				DoubleEliminationScheduleDTO fss = (DoubleEliminationScheduleDTO)schedule.getFinalStageSchedule();
				thisMatch = fss.getSummaryFinal();
			}
			break;
		case 5:
			if (tableId >= 0) {
				GroupStageScheduleDTO gss = schedule.getGroupStageSchedule();
				DoubleEliminationScheduleDTO thisTable = (DoubleEliminationScheduleDTO)gss.getTables().get(thisMap.getTableId());
				thisMatch = thisTable.getOptionFinal();

			} else {
				DoubleEliminationScheduleDTO fss = (DoubleEliminationScheduleDTO)schedule.getFinalStageSchedule();
				thisMatch = fss.getOptionFinal();
			}
			break;

		}

		updateMatch(match, thisMatch, results);
		saveScheduleToFile(schedule, match.getCompetition().getId());
		
		return schedule;
	}
	
	private void updateMatch(MatchEntity match, Match thisMatch, ArrayList<ResultEntity> results) {
		
		Integer team1Score = 0;
		Integer team2Score = 0;

		Double team1Diff = 0.0;
		Double team2Diff = 0.0;

		for (ResultEntity r : results) {
			team1Diff += r.getTeam1Score() - r.getTeam2Score();
			team2Diff += 0 - team1Diff;
			if (team1Diff > 0)
				team1Score++;

			if (team2Diff > 0)
				team2Score++;
		}
		
		if (match.getWinnner().getId() == match.getTeam1().getId()) {
			thisMatch.getWinner().setTeam(thisMatch.getTeam1().getTeam());
			thisMatch.getLoser().setTeam(thisMatch.getTeam2().getTeam());
		} else {
			thisMatch.getWinner().setTeam(thisMatch.getTeam2().getTeam());
			thisMatch.getLoser().setTeam(thisMatch.getTeam1().getTeam());
		}

		thisMatch.getTeam1().setScore(team1Score);
		thisMatch.getTeam2().setScore(team2Score);
		thisMatch.getTeam1().setDifference(team1Diff);
		thisMatch.getTeam2().setDifference(team2Diff);
		
	}

	// ----------------------------------------------------------------------
}
