
package doan2020.SportTournamentSupportSystem.service;

import java.util.ArrayList;

import doan2020.SportTournamentSupportSystem.model.Naming.BoxDescription;
import doan2020.SportTournamentSupportSystem.model.Schedule.DTO.FinalStageScheduleDTO;
import doan2020.SportTournamentSupportSystem.model.Schedule.DTO.GroupStageScheduleDTO;
import doan2020.SportTournamentSupportSystem.model.Schedule.DTO.ScheduleDTO;

public interface IScheduleService {

	public ScheduleDTO getSchedule(Long competitionId);

	public String saveSchedule(ScheduleDTO schedule, Long competitionId);

	public FinalStageScheduleDTO finalStageScheduling(int totalTeam, String formatName, boolean hasHomeMatch,
			int tableId, ArrayList<BoxDescription> descriptions, int firstSeed);

	public GroupStageScheduleDTO groupStageScheduling(int totalTeam, String formatName, boolean hasHomeMatch,
			int maxTeamPerTable, int advanceTeamPerTable, int totalTable, int totalTeamInFinalTable);
}