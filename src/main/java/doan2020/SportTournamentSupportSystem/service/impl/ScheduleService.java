package doan2020.SportTournamentSupportSystem.service.impl;

import java.util.ArrayList;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import doan2020.SportTournamentSupportSystem.config.Const;
import doan2020.SportTournamentSupportSystem.entity.CompetitionEntity;
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

	@SuppressWarnings("unchecked")
	public ScheduleDTO getSchedule(Long competitionId) {
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

	public String saveSchedule(ScheduleDTO schedule, Long competitionId) {
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

	public void saveScheduleToDatabase(Long competitionId) {
		CompetitionEntity thisComp = competitionService.findOneById(competitionId);
		ScheduleDTO schedule = getSchedule(competitionId);
		String finalStageFormat = schedule.getFinalStageSchedule().getFormatName();
		ArrayList<TeamEntity> teams = new ArrayList<>();
		
		if (!schedule.isHasGroupStage()) {
			teams.addAll(teamService.findByCompetitionIdAndStatus(competitionId, Const.TEAM_STATUS_JOINED));
			
		} else {
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
					saveFinalScheduleToDatabase((SingleEliminationScheduleDTO) table, tableTeams, thisComp);
				}
				if (groupStageFormat.contains(Const.DOUBLE_ELIMINATION_FORMAT)) {
					saveFinalScheduleToDatabase((DoubleEliminationScheduleDTO) table, tableTeams, thisComp);
				}
				if (groupStageFormat.contains(Const.ROUND_ROBIN_FORMAT)) {
					saveFinalScheduleToDatabase((RoundRobinScheduleDTO) table, tableTeams, thisComp);
				}
			}
			
			// 
		}
		
		if (finalStageFormat.contains(Const.SINGLE_ELIMINATION_FORMAT)) {
			SingleEliminationScheduleDTO finalStageSchedule = (SingleEliminationScheduleDTO) schedule.getFinalStageSchedule();
			saveFinalScheduleToDatabase(finalStageSchedule, teams, thisComp);
		}
		if (finalStageFormat.contains(Const.DOUBLE_ELIMINATION_FORMAT)) {
			DoubleEliminationScheduleDTO finalStageSchedule = (DoubleEliminationScheduleDTO) schedule.getFinalStageSchedule();
			saveFinalScheduleToDatabase(finalStageSchedule, teams, thisComp);
		}
		if (finalStageFormat.contains(Const.ROUND_ROBIN_FORMAT)) {
			RoundRobinScheduleDTO finalStageSchedule = (RoundRobinScheduleDTO) schedule.getFinalStageSchedule();
			saveFinalScheduleToDatabase(finalStageSchedule, teams, thisComp);
		}

	}

	private void saveFinalScheduleToDatabase(SingleEliminationScheduleDTO schedule, ArrayList<TeamEntity> teams, CompetitionEntity competition) {
		SingleEliminationTree tree = new SingleEliminationTree(schedule.getBracket(), schedule.getTotalTeam());
		tree.applyTeams(teams);
		for (Match match : tree.getMatches()) {
			MatchEntity realMatch = new MatchEntity();
			realMatch.setCompetition(competition);;
			realMatch.setName(match.getName());
			realMatch.setLocation(match.getLocation());
			realMatch.setTime(validator.formatStringToDate(match.getTime()));
			
			if (match.getStatus() == -1) {
				realMatch.setStatus("finished");
			}
			if (match.getStatus() == 0) {
				realMatch.setStatus("ready");
			}
			if (match.getStatus() > 0) {
				realMatch.setStatus("pending");
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
			realMatch.setCompetition(competition);;
			realMatch.setName(match.getName());
			realMatch.setLocation(match.getLocation());
			realMatch.setTime(validator.formatStringToDate(match.getTime()));

			realMatch.setStatus("pending");

			realMatch = matchService.create(realMatch);
			match.setId(realMatch.getId());
		}
	}

	private void saveFinalScheduleToDatabase(DoubleEliminationScheduleDTO schedule, ArrayList<TeamEntity> teams, CompetitionEntity competition) {
		DoubleEliminationTree tree = new DoubleEliminationTree(schedule.getWinBranch(), schedule.getLoseBranch(), schedule.getTotalTeam());
		tree.applyTeams(teams);
		for (Match match : tree.getMatches()) {
			MatchEntity realMatch = new MatchEntity();
			realMatch.setCompetition(competition);;
			realMatch.setName(match.getName());
			realMatch.setLocation(match.getLocation());
			realMatch.setTime(validator.formatStringToDate(match.getTime()));
			
			if (match.getStatus() == -1) {
				realMatch.setStatus("finished");
			}
			if (match.getStatus() == 0) {
				realMatch.setStatus("ready");
			}
			if (match.getStatus() > 0) {
				realMatch.setStatus("pending");
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
			realFinalMatch.setCompetition(competition);;
			realFinalMatch.setName(finalMatch.getName());
			realFinalMatch.setLocation(finalMatch.getLocation());
			realFinalMatch.setTime(validator.formatStringToDate(finalMatch.getTime()));

			realFinalMatch.setStatus("pending");

			realFinalMatch = matchService.create(realFinalMatch);
			finalMatch.setId(realFinalMatch.getId());
			
			Match optionMatch = tree.getSummaryFinal();
			MatchEntity realOptionMatch = new MatchEntity();
			realOptionMatch.setCompetition(competition);;
			realOptionMatch.setName(optionMatch.getName());
			realOptionMatch.setLocation(optionMatch.getLocation());
			realOptionMatch.setTime(validator.formatStringToDate(optionMatch.getTime()));

			realOptionMatch.setStatus("pending");

			realOptionMatch = matchService.create(realOptionMatch);
			optionMatch.setId(realOptionMatch.getId());
		}
	}

	private void saveFinalScheduleToDatabase(RoundRobinScheduleDTO schedule, ArrayList<TeamEntity> teams, CompetitionEntity competition) {
		RoundRobinTable table = new RoundRobinTable((long)schedule.getTableId(), schedule.getMatches(), schedule.getTotalTeam(), schedule.isHasHomeMatch());
		table.applyTeams(teams);
		for (Match match : table.getMatches()) {
			MatchEntity realMatch = new MatchEntity();
			realMatch.setCompetition(competition);;
			realMatch.setName(match.getName());
			realMatch.setLocation(match.getLocation());
			realMatch.setTime(validator.formatStringToDate(match.getTime()));
			
			if (match.getStatus() == -1) {
				realMatch.setStatus("finished");
			}
			if (match.getStatus() == 0) {
				realMatch.setStatus("ready");
			}
			if (match.getStatus() > 0) {
				realMatch.setStatus("pending");
			}
			
			try {
				realMatch.setTeam1(teamService.findOneById(match.getTeam1().getTeam().getId()));
				realMatch.setTeam2(teamService.findOneById(match.getTeam2().getTeam().getId()));
			} catch (Exception e) {
			}
				
			realMatch = matchService.create(realMatch);
			match.setId(realMatch.getId());
		}
	}

	public FinalStageScheduleDTO finalStageScheduling(int totalTeam, String formatName, boolean hasHomeMatch,
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

	public GroupStageScheduleDTO groupStageScheduling(int totalTeam, String formatName, boolean hasHomeMatch,
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

		return dto;
	}

}
