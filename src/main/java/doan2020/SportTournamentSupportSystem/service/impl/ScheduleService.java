package doan2020.SportTournamentSupportSystem.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import doan2020.SportTournamentSupportSystem.config.Const;
import doan2020.SportTournamentSupportSystem.entity.CompetitionEntity;
import doan2020.SportTournamentSupportSystem.model.ContainerCollection.RankingTable;
import doan2020.SportTournamentSupportSystem.model.Entity.BoxDescription;
import doan2020.SportTournamentSupportSystem.model.Entity.Match;
import doan2020.SportTournamentSupportSystem.model.LogicBox.MatchSlot;
import doan2020.SportTournamentSupportSystem.model.Schedule.Format.DoubleEliminationTree;
import doan2020.SportTournamentSupportSystem.model.Schedule.Format.RoundRobinTable;
import doan2020.SportTournamentSupportSystem.model.Schedule.Format.SingleEliminationTree;
import doan2020.SportTournamentSupportSystem.model.Struct.BTree;
import doan2020.SportTournamentSupportSystem.model.Struct.Node;
import doan2020.SportTournamentSupportSystem.service.ICompetitionService;
import doan2020.SportTournamentSupportSystem.service.IFileStorageService;
import doan2020.SportTournamentSupportSystem.service.IScheduleService;

@Service
public class ScheduleService implements IScheduleService {

	@Autowired
	private IFileStorageService fileService;
	
	@Autowired
	private ICompetitionService competitionService;

	@SuppressWarnings("unchecked")
	public HashMap<String, Object> getSchedule(Long competitionId) {
		String fileName = Const.COMPETITION_SCHEDULING;
		String absFolderPath = null;
		String absFilePath = null;
		HashMap<String, Object> schedule = new HashMap<String, Object>();

		try {

			String folder = Const.COMPETITION_FILESYSTEM + Const.COMPETITION_FOLDER_NAMING + competitionId;
			absFolderPath = fileService.getFileStorageLocation(folder).toString();
			absFilePath = absFolderPath + "\\" + fileName;
			schedule = (HashMap<String, Object>) fileService.getObjectFromFile(absFilePath);

		} catch (Exception e) {
		}

		return schedule;
	}

	public String saveSchedule(HashMap<String, Object> schedule, Long competitionId) {
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

	public void saveScheduleToDatabase(Long id) {
		CompetitionEntity thisComp = competitionService.findOneById(id);
		HashMap<String, Object> schedule = getSchedule(id);
		HashMap<String, Object> finalStageSchedule = (HashMap<String, Object>) schedule.get("FinalStageSchedule");
		
		if (thisComp.isHasGroupStage()) {
			HashMap<String, Object> groupStageSchedule = (HashMap<String, Object>) schedule.get("GroupStageSchedule");
			HashMap<String, Object> tables = (HashMap<String, Object>) groupStageSchedule.get("Tables");
			
		}

	}

	public HashMap<String, Object> finalStageScheduling(int totalTeam, String formatName, boolean hasHomeMatch,
			int tableId, ArrayList<BoxDescription> descriptions, int firstSeed) {
		System.out.println("ScheduleService: finalStageScheduling: start");

		System.out.println("ScheduleService: finalStageScheduling: totalTeam: " + totalTeam);
		System.out.println("ScheduleService: finalStageScheduling: formatName: " + formatName);
		System.out.println("ScheduleService: finalStageScheduling: tableId: " + tableId);

		HashMap<String, Object> schedule = new HashMap<>();
		schedule.put("TotalTeam", totalTeam);
		schedule.put("FormatName", formatName);
		if (tableId >= 0) {
			schedule.put("Table", Const.TABLE_NAMING.charAt(tableId));
		}

		boolean scheduled = false;

		try {
			if (formatName.contains(Const.SINGLE_ELIMINATION_FORMAT)) {
				if (totalTeam < 2) {
					schedule.put("TotalRound", 1);
					BTree<Match> winBranch = new BTree<>(new Node<Match>());
					Match match = new Match();
					BoxDescription des = new BoxDescription((long)firstSeed + 1);
					MatchSlot slot1 = new MatchSlot();
					MatchSlot slot2 = new MatchSlot();
					slot1.setDescription(des);
					slot2.setDescription(new BoxDescription());
					match.setTeam1(slot1);
					match.setTeam2(slot2);
					match.setRoundNo(1);
					winBranch.getRoot().setData(match);
					schedule.put("Bracket", winBranch);
					RankingTable rb;
					if (tableId < 0)
						rb = new RankingTable(totalTeam);
					else 
						rb = new RankingTable(totalTeam, tableId);
					schedule.put("RankingTable", rb);
				} else {
					SingleEliminationTree tree = new SingleEliminationTree(totalTeam);
					System.out.println("ScheduleService: finalStageScheduling: build tree OK");
					if (tableId < 0) {
						tree.applyDescriptions(descriptions);
						System.out.println("ScheduleService: finalStageScheduling: apply description OK");
					} else {
						tree.applyDescriptions(firstSeed);
					}
					
					tree.setTableId(tableId);
					System.out.println("ScheduleService: finalStageScheduling: set table id OK");
					schedule.put("Bracket", tree.getBracket());
					System.out.println("ScheduleService: finalStageScheduling: CP1");
					schedule.put("HasMatch34", hasHomeMatch);
					schedule.put("Match34", tree.getMatch34());
					System.out.println("ScheduleService: finalStageScheduling: CP2");
					schedule.put("RankingTable", tree.getRankingTable());
					System.out.println("ScheduleService: finalStageScheduling: CP3");
					scheduled = true;
				}
				
			}

			if (formatName.contains(Const.DOUBLE_ELIMINATION_FORMAT)) {
				if (totalTeam < 2) {
					schedule.put("TotalRound", 1);
					BTree<Match> winBranch = new BTree<>(new Node<Match>());
					Match match = new Match();
					BoxDescription des = new BoxDescription((long)firstSeed + 1);
					MatchSlot slot1 = new MatchSlot();
					MatchSlot slot2 = new MatchSlot();
					slot1.setDescription(des);
					slot2.setDescription(new BoxDescription());
					match.setTeam1(slot1);
					match.setTeam2(slot2);
					match.setRoundNo(1);
					winBranch.getRoot().setData(match);
					schedule.put("WinBranch", winBranch);
					RankingTable rb;
					if (tableId < 0)
						rb = new RankingTable(totalTeam);
					else 
						rb = new RankingTable(totalTeam, tableId);
					schedule.put("RankingTable", rb);
				} else {

					DoubleEliminationTree tree = new DoubleEliminationTree(totalTeam);
					if (tableId < 0)
						tree.applyDescriptions(descriptions);
					else {
						tree.applyDescriptions(firstSeed);
					}
					tree.setTableId(tableId);
					schedule.put("WinBranch", tree.getWinBranch());
					schedule.put("LoseBranch", tree.getLoseBranch());
					schedule.put("SummaryFinal", tree.getSummaryFinal());
					schedule.put("OptionFinal", tree.gettOptionFinal());
					schedule.put("RankingTable", tree.getRankingTable());
					scheduled = true;
				}
			}

			if (formatName.contains(Const.ROUND_ROBIN_FORMAT)) {
				if (totalTeam < 2) {
					schedule.put("TotalRound", 1);
					schedule.put("HasHomeMatch", hasHomeMatch);
					ArrayList<Match> bracket = new ArrayList<>();
					Match match = new Match();
					BoxDescription des = new BoxDescription((long)firstSeed + 1);
					MatchSlot slot1 = new MatchSlot();
					MatchSlot slot2 = new MatchSlot();
					slot1.setDescription(des);
					slot2.setDescription(new BoxDescription());
					match.setTeam1(slot1);
					match.setTeam2(slot2);
					match.setRoundNo(1);
					bracket.add(match);
					schedule.put("Bracket", bracket);
					RankingTable rb;
					if (tableId < 0)
						rb = new RankingTable(totalTeam);
					else 
						rb = new RankingTable(totalTeam, tableId);
					schedule.put("RankingTable", rb);
				} else {
					RoundRobinTable table;
					if (tableId >= 0) {
						table = new RoundRobinTable((long) tableId, totalTeam, hasHomeMatch);
						table.applyDescriptions(firstSeed);
//						System.out.println(table.getSeedList().toString());
					} else {
						table = new RoundRobinTable(totalTeam, hasHomeMatch);
						table.applyDescriptions(descriptions);
					}
					
					table.setTableId(tableId);
					schedule.put("Bracket", table.getMatches());
					schedule.put("HasHomeMatch", hasHomeMatch);
					schedule.put("TotalRound", table.getTotalRound());
					schedule.put("RankingTable", table.getRankingTable());
					scheduled = true;
				}

			}

			if (!scheduled) {
				System.out.println("ScheduleService: finalStageScheduling: unknown format");
				schedule.put("FormatName", Const.ANOTHER_FORMAT);
			}
			System.out.println("ScheduleService: finalStageScheduling: no exception");
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("ScheduleService: finalStageScheduling: has exception");
		}

		System.out.println("ScheduleService: finalStageScheduling: start");
		return schedule;
	}

	public HashMap<String, Object> groupStageScheduling(int totalTeam, String formatName, boolean hasHomeMatch,
			int maxTeamPerTable, int advanceTeamPerTable, int totalTable, int totalTeamInFinalTable) {
		HashMap<String, Object> schedule = new HashMap<>();
		schedule.put("TotalTeam", totalTeam);
		schedule.put("FormatName", formatName);
		schedule.put("HasHomeMatch", hasHomeMatch);
		schedule.put("MaxTeamPerTable", maxTeamPerTable);
		schedule.put("AdvanceTeamPerTable", advanceTeamPerTable);
		schedule.put("TotalTable", totalTable);
		schedule.put("TotalTeamInFinalTable", totalTeamInFinalTable);

		ArrayList<HashMap<String, Object>> tables = new ArrayList<>();

		ArrayList<BoxDescription> descriptions = new ArrayList<>();
		
		for (int tableId = 0; tableId < totalTable - 1; tableId++) {
			
			int firstSeed = tableId * maxTeamPerTable + 1;
			HashMap<String, Object> table = finalStageScheduling(maxTeamPerTable, formatName, hasHomeMatch, tableId, descriptions, firstSeed);
			tables.add(table);
		}
		
		int firstSeed = (totalTable - 1) * maxTeamPerTable;

		tables.add(finalStageScheduling(totalTeamInFinalTable, formatName, hasHomeMatch, totalTable - 1, descriptions, firstSeed));
		schedule.put("Tables", tables);
		
		return schedule;
	}

}
