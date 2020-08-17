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
import doan2020.SportTournamentSupportSystem.model.MatchTree;
import doan2020.SportTournamentSupportSystem.model.LogicEntity.SeedTree;
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
			@RequestParam(value = "competitionId", required = false) Long competitionId) {
		System.out.println("ScheduleAPI: singleEliminationSchedule: start");
		Response response = new Response();
		HttpStatus httpStatus = HttpStatus.OK;
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();

		Collection<MatchEntity> matches = new ArrayList<>();

		try {
			CompetitionEntity thisCompetition = competitionService.findOneById(competitionId);
//			Collection<TeamEntity> teams =
//			MatchTree tree = new MatchTree(thisCompetition);

//			result.put("Schedule", tree.getMatchesStruct());
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Successful");
			System.out.println("ScheduleAPI: singleEliminationSchedule: no exception");
		} catch (Exception e) {
			System.out.println("ScheduleAPI: singleEliminationSchedule: has exception");
			result.put("Schedule", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("ScheduleAPI: singleEliminationSchedule: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

	@GetMapping("/test")
	public ResponseEntity<Response> singleEliminationScheduleTest(
			@RequestParam(value = "numOfTeam", required = false) Integer numOfTeam,
			@RequestParam(value = "formatId", required = true) Long formatId) {

		System.out.println("ScheduleAPI: singleEliminationScheduleTest: start");
		Response response = new Response();
		HttpStatus httpStatus = HttpStatus.OK;
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();

		Collection<MatchEntity> matches = new ArrayList<>();

		if (numOfTeam == null) {
			System.out.println("ScheduleAPI: singleEliminationScheduleTest: numOfTeam null");
			result.put("Schedule", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "numOfTeam is null");
		} else

			try {
				MatchTree tree = new MatchTree(numOfTeam, formatId);
				
				if (formatId == 1)
					result.put("Schedule", tree.getMatchesStruct());
				else {
					Map<String, Object> schedule = new HashMap<>();
					
					schedule.put("WinBranch", tree.getMatchesStruct().getLeft());
					schedule.put("LoseBranch", tree.getMatchesStruct().getRight());
					result.put("Schedule", schedule);
				}
				config.put("Global", 0);
				error.put("MessageCode", 0);
				error.put("Message", "Successful");
				System.out.println("ScheduleAPI: singleEliminationScheduleTest: no exception");
			} catch (Exception e) {
				System.out.println("ScheduleAPI: singleEliminationScheduleTest: has exception");
				result.put("Schedule", null);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "Server error");
			}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("ScheduleAPI: singleEliminationScheduleTest: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}
	
	@GetMapping("/testv2")
	public ResponseEntity<Response> singleEliminationScheduleTestv2(
			@RequestParam(value = "numOfTeam", required = false) Integer numOfTeam,
			@RequestParam(value = "formatId", required = true) Long formatId) {

		System.out.println("ScheduleAPI: singleEliminationScheduleTest: start");
		Response response = new Response();
		HttpStatus httpStatus = HttpStatus.OK;
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();

		Collection<MatchEntity> matches = new ArrayList<>();

		if (numOfTeam == null) {
			System.out.println("ScheduleAPI: singleEliminationScheduleTest: numOfTeam null");
			result.put("Schedule", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "numOfTeam is null");
		} else

			try {
				SeedTree tree = new SeedTree(numOfTeam);
				
				if (formatId == 1)
					result.put("Schedule", tree.getWinBranch());
				else {
					Map<String, Object> schedule = new HashMap<>();
					
//					schedule.put("WinBranch", tree.getMatchesStruct().getLeft());
//					schedule.put("LoseBranch", tree.getMatchesStruct().getRight());
					result.put("Schedule", schedule);
				}
				config.put("Global", 0);
				error.put("MessageCode", 0);
				error.put("Message", "Successful");
				System.out.println("ScheduleAPI: singleEliminationScheduleTest: no exception");
			} catch (Exception e) {
				System.out.println("ScheduleAPI: singleEliminationScheduleTest: has exception");
				result.put("Schedule", null);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "Server error");
			}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("ScheduleAPI: singleEliminationScheduleTest: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

}
