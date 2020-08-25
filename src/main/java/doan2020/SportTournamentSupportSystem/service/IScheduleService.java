
package doan2020.SportTournamentSupportSystem.service;

import java.util.HashMap;

public interface IScheduleService {
	
	public HashMap<String, Object> getSchedule(Long competitionId);

	public String saveSchedule(HashMap<String, Object> schedule, Long competitionId);
}