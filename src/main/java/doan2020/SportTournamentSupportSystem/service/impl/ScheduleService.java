package doan2020.SportTournamentSupportSystem.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import doan2020.SportTournamentSupportSystem.config.Const;
import doan2020.SportTournamentSupportSystem.model.ScheduleFormat.DoubleEliminationTree;
import doan2020.SportTournamentSupportSystem.model.ScheduleFormat.RoundRobinTable;
import doan2020.SportTournamentSupportSystem.model.ScheduleFormat.SingleEliminationTree;
import doan2020.SportTournamentSupportSystem.service.IFileStorageService;
import doan2020.SportTournamentSupportSystem.service.IScheduleService;

@Service
public class ScheduleService implements IScheduleService{
	
	@Autowired
	private IFileStorageService fileService;
	
	
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
		HashMap<String, Object> schedule = getSchedule(id);
		String format = (String) schedule.get("Format");
		
		
	}
	
	public HashMap<String, Object> finalStageScheduling(int totalTeam, String formatName, boolean hasHomeMatch) {
		HashMap<String, Object> schedule = new HashMap<>();
		schedule.put("totalTeam", totalTeam);
		schedule.put("formatName", formatName);
		
		boolean scheduled = false;
		boolean err = false;
		
		try {
			if (formatName == Const.SINGLE_ELIMINATION_FORMAT) {
				if (totalTeam < 2) {
					err = true;
				} else {
					SingleEliminationTree tree = new SingleEliminationTree(totalTeam);
					schedule.put("Bracket", tree.getBracket());
					if (hasHomeMatch) {
//						schedule.put("Game34", tree.getMatch34());
					}
					scheduled = true;
				}
			}

			if (formatName == Const.DOUBLE_ELIMINATION_FORMAT) {
				if (totalTeam < 3) {
					err = true;
				} else {
					DoubleEliminationTree tree = new DoubleEliminationTree(totalTeam);
					schedule.put("WinBranch", tree.getWinBranch());
					schedule.put("LoseBranch", tree.getLoseBranch());
					scheduled = true;
				}
			}

			if (formatName == Const.ROUND_ROBIN_FORMAT) {
				if (totalTeam < 2) {
					err = true;
				} else {

					RoundRobinTable table = new RoundRobinTable(totalTeam, hasHomeMatch);
					schedule.put("Table", table.getMatches());
					scheduled = true;
				}

			}
			
			if (!scheduled) {
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return schedule;
	}

	public HashMap<String, Object> groupStageScheduling(int totalTeam, String formatName, boolean hasHomeMatch,
			int maxTeamPerTable, int advanceTeamPerTable) {
		HashMap<String, Object> schedule = new HashMap<>();
		schedule.put("totalTeam", totalTeam);
		schedule.put("formatName", formatName);
		schedule.put("hasHomeMatch", hasHomeMatch);
		schedule.put("maxTeamPerTable", maxTeamPerTable);
		schedule.put("advanceTeamPerTable", advanceTeamPerTable);
		try {

		} catch (Exception e) {
			// TODO: handle exception
		}

		return schedule;
	}

}
