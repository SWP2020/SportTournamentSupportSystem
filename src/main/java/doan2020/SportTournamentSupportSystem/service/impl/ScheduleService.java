package doan2020.SportTournamentSupportSystem.service.impl;

import java.util.ArrayList;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import doan2020.SportTournamentSupportSystem.config.Const;
import doan2020.SportTournamentSupportSystem.entity.CompetitionEntity;
import doan2020.SportTournamentSupportSystem.entity.FinalStageSettingEntity;
import doan2020.SportTournamentSupportSystem.entity.FormatEntity;
import doan2020.SportTournamentSupportSystem.entity.GroupStageSettingEntity;
import doan2020.SportTournamentSupportSystem.entity.MatchEntity;
import doan2020.SportTournamentSupportSystem.entity.TeamEntity;
import doan2020.SportTournamentSupportSystem.model.BoxCollection.RankingTable;
import doan2020.SportTournamentSupportSystem.model.Entity.Match;
import doan2020.SportTournamentSupportSystem.model.Naming.BoxDescription;
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
		ScheduleDTO schedule;
		Long competitionId = thisCompetition.getId();
		try {
			schedule = getScheduleFromFile(competitionId);
			// Check for changes in team numbers
			int dbTeamNumber = teamService
					.countByCompetitionIdAndStatus(competitionId, Const.TEAM_STATUS_JOINED).intValue();
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
				return schedule;
			} else {
				return createSchedule(thisCompetition);
			}
		} catch (Exception e) {
			System.out.println("schedule not yet created");
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
		System.out.println("ScheduleService: saveScheduleToDatabase: start");
		Long competitionId = thisCompetition.getId();
		ScheduleDTO schedule = getSchedule(thisCompetition);
		String finalStageFormat = schedule.getFinalStageSchedule().getFormatName();
		ArrayList<TeamEntity> teams = new ArrayList<>();

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
			}

			schedule.setGroupStageSchedule(groupStageSchedule);
			//
		}

		System.out.println("save for final stage");

		if (finalStageFormat.contains(Const.SINGLE_ELIMINATION_FORMAT)) {
			SingleEliminationScheduleDTO finalStageSchedule = (SingleEliminationScheduleDTO) schedule
					.getFinalStageSchedule();
			schedule.setFinalStageSchedule(createMatchesInDatabase(finalStageSchedule, teams, thisCompetition));
		}
		if (finalStageFormat.contains(Const.DOUBLE_ELIMINATION_FORMAT)) {
			DoubleEliminationScheduleDTO finalStageSchedule = (DoubleEliminationScheduleDTO) schedule
					.getFinalStageSchedule();
			schedule.setFinalStageSchedule(createMatchesInDatabase(finalStageSchedule, teams, thisCompetition));
		}
		if (finalStageFormat.contains(Const.ROUND_ROBIN_FORMAT)) {
			RoundRobinScheduleDTO finalStageSchedule = (RoundRobinScheduleDTO) schedule.getFinalStageSchedule();
			schedule.setFinalStageSchedule(createMatchesInDatabase(finalStageSchedule, teams, thisCompetition));
		}

		saveScheduleToFile(schedule, competitionId);
		System.out.println("ScheduleService: saveScheduleToDatabase: finish");

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

			realMatch = matchService.create(realMatch);
			match.setId(realMatch.getId());
		}
		if (tree.getTotalTeam() >= 4) {
			Match match = schedule.getMatch34();
			MatchEntity realMatch = new MatchEntity();
			realMatch.setCompetition(competition);
			;
			realMatch.setName(match.getName());
			realMatch.setLocation(match.getLocation());
			realMatch.setTime(validator.formatStringToDate(match.getTime()));

			realMatch.setStatus(Const.MATCH_STATUS_PENDING);

			realMatch = matchService.create(realMatch);
			match.setId(realMatch.getId());
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
		for (Match match : tree.getMatches()) {
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
		}
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
		}

		schedule.setMatches(table.getMatches());

		return schedule;
	}
	// ----------------------------------------------------------------------

	/*
	 * --------------------------------------------------------------------------------create
	 */
	@Override
	public ScheduleDTO createSchedule(CompetitionEntity thisCompetition) {

		ScheduleDTO schedule = new ScheduleDTO();
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

		saveScheduleToFile(schedule, competitionId);
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
				SingleEliminationTree tree = new SingleEliminationTree(totalTeam);
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

				return dto;
			}
		} catch (Exception e) {
			System.out.println("ScheduleService: finalStageScheduling: has exception");
			System.out.println(e);
			System.out.println("-------");
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

		GroupStageScheduleDTO dto = new GroupStageScheduleDTO();

		dto.setTotalTeam(totalTeam);
		dto.setFormatName(formatName);
		dto.setHasHomeMatch(hasHomeMatch);
		dto.setMaxTeamPerTable(maxTeamPerTable);
		dto.setAdvanceTeamPerTable(advanceTeamPerTable);
		dto.setTotalTable(totalTable);
		dto.setTotalTeamInFinalTable(totalTeamInFinalTable);

		ArrayList<FinalStageScheduleDTO> tables = new ArrayList<>();

		ArrayList<BoxDescription> descriptions = new ArrayList<>();

		for (int tableId = 0; tableId < totalTable - 1; tableId++) {

			int firstSeed = tableId * maxTeamPerTable + 1;
			FinalStageScheduleDTO table = finalStageScheduling(maxTeamPerTable, formatName, hasHomeMatch, tableId,
					descriptions, firstSeed);
			tables.add(table);
		}

		int firstSeed = (totalTable - 1) * maxTeamPerTable;

		tables.add(finalStageScheduling(totalTeamInFinalTable, formatName, hasHomeMatch, totalTable - 1, descriptions,
				firstSeed));
		dto.setTables(tables);
		dto.setStatus(Const.STAGE_INITIALIZING);

		return dto;
	}

	
	//--------------------
	
	/*
	 * --------------------------------------------------------------------- update
	 * result for a match after finished synchronize db and schedule
	 */
	public void finishMatch(MatchEntity realMatch) {
		if (!realMatch.getStatus().contains(Const.MATCH_STATUS_FINISHED)) {
			return;
		}
		CompetitionEntity thisCompetition = realMatch.getCompetition();
		Long competitionId = thisCompetition.getId();

		ScheduleDTO schedule = getSchedule(thisCompetition);
		if (schedule.isHasGroupStage()
				&& schedule.getGroupStageSchedule().getStatus().contains(Const.STAGE_PROCESSING)) {
			GroupStageScheduleDTO gssDTO = schedule.getGroupStageSchedule();
		} else {

		}
	}

	@Override
	public ScheduleDTO updateMatchWinner(MatchEntity match, Long winnerId) {
		
		CompetitionEntity thisCompetition = match.getCompetition();
		Long competitionId = thisCompetition.getId();
		ScheduleDTO schedule = getScheduleFromFile(competitionId);
		
		match.setWinnner(teamService.findOneById(winnerId));
		match = matchService.update(match.getId(), match);
		
		
		
		
		return schedule;
	}
	
	// ----------------------------------------------------------------------
}
