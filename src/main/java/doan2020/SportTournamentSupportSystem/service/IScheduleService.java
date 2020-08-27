
package doan2020.SportTournamentSupportSystem.service;

import java.util.ArrayList;
import java.util.HashMap;

import doan2020.SportTournamentSupportSystem.config.Const;
import doan2020.SportTournamentSupportSystem.model.Entity.BoxDescription;
import doan2020.SportTournamentSupportSystem.model.Schedule.Format.RoundRobinTable;
import doan2020.SportTournamentSupportSystem.model.Schedule.Format.SingleEliminationTree;

public interface IScheduleService {

	public HashMap<String, Object> getSchedule(Long competitionId);

	public String saveSchedule(HashMap<String, Object> schedule, Long competitionId);

	public HashMap<String, Object> finalStageScheduling(int totalTeam, String formatName, boolean hasHomeMatch,
			int tableId, ArrayList<BoxDescription> descriptions, int firstSeed);

	public HashMap<String, Object> groupStageScheduling(int totalTeam, String formatName, boolean hasHomeMatch,
			int maxTeamPerTable, int advanceTeamPerTable, int totalTable, int totalTeamInFinalTable);
}