package doan2020.SportTournamentSupportSystem.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import doan2020.SportTournamentSupportSystem.entity.CompetitionEntity;
import doan2020.SportTournamentSupportSystem.entity.MatchEntity;
import doan2020.SportTournamentSupportSystem.entity.TeamEntity;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.ICompetitionService;

@RestController
@CrossOrigin
@RequestMapping("/schedule")
public class ScheduleAPI {
	
	@Autowired
	private ICompetitionService competitionService;
	
	@GetMapping("")
	public ResponseEntity<Response> singleEliminationSchedule(
			@RequestParam(value ="competitionId", required = false) Long competitionId) {
		System.out.println("ScheduleAPI: singleEliminationSchedule: start");
		Response response = new Response();
		HttpStatus httpStatus = HttpStatus.OK;
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		
		Collection<MatchEntity> matches = new ArrayList<>();
		
		try {
			CompetitionEntity thisCompetition = 
		} catch (Exception e) {
			// TODO: handle exception
		}
		Collection<TeamEntity> teams = 
		
		
		
		
		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("ScheduleAPI: singleEliminationSchedule: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}
	
}
