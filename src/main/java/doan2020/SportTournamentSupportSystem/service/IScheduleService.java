
package doan2020.SportTournamentSupportSystem.service;

import java.util.HashMap;

import doan2020.SportTournamentSupportSystem.entity.TournamentEntity;
import doan2020.SportTournamentSupportSystem.entity.MatchEntity;
import doan2020.SportTournamentSupportSystem.model.Schedule.DTO.ScheduleDTO;

public interface IScheduleService {

	public ScheduleDTO getSchedule(TournamentEntity Tournament);

	public ScheduleDTO createSchedule(TournamentEntity Tournament);

	public void createMatchesInDatabase(TournamentEntity Tournament);

	public ScheduleDTO finishMatch(MatchEntity match);

	public ScheduleDTO changeMatchInfo(TournamentEntity Tournament, Integer nodeId, Integer degree, Integer location,
			Integer tableId, HashMap<String, Object> newInfo);

}