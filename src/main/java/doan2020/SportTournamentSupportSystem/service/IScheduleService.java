
package doan2020.SportTournamentSupportSystem.service;

import java.util.HashMap;

import doan2020.SportTournamentSupportSystem.config.Const;
import doan2020.SportTournamentSupportSystem.model.ScheduleFormat.RoundRobinTable;
import doan2020.SportTournamentSupportSystem.model.ScheduleFormat.SingleEliminationTree;

public interface IScheduleService {
	
	public HashMap<String, Object> getSchedule(Long competitionId);

	public String saveSchedule(HashMap<String, Object> schedule, Long competitionId);
	
	public HashMap<String, Object> finalStageScheduling(int totalTeam, String formatName, boolean hasHomeMatch);

	public HashMap<String, Object> groupStageScheduling(int totalTeam, String formatName, boolean hasHomeMatch,
			int maxTeamPerTable, int advanceTeamPerTable);
}