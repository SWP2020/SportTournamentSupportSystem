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
import doan2020.SportTournamentSupportSystem.entity.CompetitionFormatEntity;
import doan2020.SportTournamentSupportSystem.entity.CompetitionSettingEntity;
import doan2020.SportTournamentSupportSystem.entity.MatchEntity;
import doan2020.SportTournamentSupportSystem.entity.TeamEntity;
import doan2020.SportTournamentSupportSystem.model.LogicEntity.SeedTree;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.ICompetitionService;

@RestController
@CrossOrigin
@RequestMapping("/schedule")
public class ScheduleAPI {

	@Autowired
	private ICompetitionService competitionService;
	
	
	/*
	 * Get schedule for a competition
	 */
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
		ArrayList<TeamEntity> teams = new ArrayList<>();

		try {
			CompetitionEntity thisCompetition = competitionService.findOneById(competitionId);
			
			if (thisCompetition == null) {
				result.put("Schedule", null);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "Competition not found");
			} else {
				System.out.println("ScheduleAPI: singleEliminationSchedule: thisComp: " + thisCompetition.getTeams());
				for (TeamEntity team: thisCompetition.getTeams()) {
					teams.add(team);
				}
				System.out.println("ScheduleAPI: singleEliminationSchedule: teams: " + teams);
				CompetitionFormatEntity format = thisCompetition.getMainFormat();
				System.out.println("ScheduleAPI: singleEliminationSchedule: format: " + format.getName());
				if (teams.size() < 2 || (teams.size() < 3 && format.getId() == 2)) {
					System.out.println("ScheduleAPI: singleEliminationSchedule: schedule null: ");
					result.put("Schedule", null);
					config.put("Global", 0);
					error.put("MessageCode", 1);
					error.put("Message", "Not enough teams");
				} else {
					System.out.println("===============TEST===================");
					for (TeamEntity team: teams) {
						System.out.println(team.getId());
					}
					System.out.println("======================================");
					
					SeedTree tree = new SeedTree(teams, format.getId());
					
					if (format.getId() == 1)
						result.put("Schedule", tree.getWinBranch());
					else if (format.getId() == 2) {
						Map<String, Object> schedule = new HashMap<>();
						
						schedule.put("WinBranch", tree.getWinBranch());
						schedule.put("LoseBranch", tree.getLoseBranch());
						result.put("Schedule", schedule);
					}
					config.put("Global", 0);
					error.put("MessageCode", 0);
					error.put("Message", "Successful");
				}
			}
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
				SeedTree tree = new SeedTree(numOfTeam, formatId.intValue());
				
				if (formatId == 1)
					result.put("Schedule", tree.getWinBranch());
				else {
					Map<String, Object> schedule = new HashMap<>();
					
					schedule.put("WinBranch", tree.getWinBranch());
					schedule.put("LoseBranch", tree.getLoseBranch());
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
