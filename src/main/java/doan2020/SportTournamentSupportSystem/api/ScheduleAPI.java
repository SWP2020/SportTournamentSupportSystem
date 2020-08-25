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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import doan2020.SportTournamentSupportSystem.config.Const;
import doan2020.SportTournamentSupportSystem.entity.CompetitionEntity;
import doan2020.SportTournamentSupportSystem.entity.FinalStageSettingEntity;
import doan2020.SportTournamentSupportSystem.entity.FormatEntity;
import doan2020.SportTournamentSupportSystem.entity.MatchEntity;
import doan2020.SportTournamentSupportSystem.entity.TeamEntity;
import doan2020.SportTournamentSupportSystem.model.ScheduleStruct.EliminationTree;
import doan2020.SportTournamentSupportSystem.model.ScheduleStruct.RoundRobinTable;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.ICompetitionService;
import doan2020.SportTournamentSupportSystem.service.IFileStorageService;
import doan2020.SportTournamentSupportSystem.service.impl.ScheduleService;

@RestController
@CrossOrigin
@RequestMapping("/schedule")
public class ScheduleAPI {

	@Autowired
	private ICompetitionService competitionService;

	@Autowired
	private IFileStorageService fileService;
	
	private ScheduleService scheduleService;

	/*
	 * Get schedule for a competition
	 */

	@GetMapping()
	public ResponseEntity<Response> getFinalStageScheduleByCompetition(
			@RequestParam(value = "competitionId", required = false) Long competitionId) {
		System.out.println("ScheduleAPI: getScheduleByCompetition: start");
		Response response = new Response();
		HttpStatus httpStatus = HttpStatus.OK;
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();

		Collection<MatchEntity> matches = new ArrayList<>();
		ArrayList<TeamEntity> teams = new ArrayList<>();

		try {

			System.out.println("ScheduleAPI: getScheduleByCompetition: competitionId: " + competitionId);
			CompetitionEntity thisCompetition = competitionService.findOneById(competitionId);
			System.out.println("ScheduleAPI: getScheduleByCompetition: thisCompetition: " + thisCompetition);
			if (thisCompetition == null) {
				result.put("Schedule", null);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "Competition not found");
			} else {
				HashMap<String, Object> schedule;
				try {
					System.out.println("ScheduleAPI: getScheduleByCompetition: try to get schedule");
					schedule = scheduleService.getSchedule(competitionId);
					// Check for changes in team numbers
					int dbTeamNumber = thisCompetition.getTeams().size();
					int cfTeamNumber = (int) schedule.get("TotalTeam");

					if (dbTeamNumber != cfTeamNumber)
						return createFinalStageScheduleByCompetitionId(competitionId);
				} catch (Exception e) {
					System.out.println("ScheduleAPI: getScheduleByCompetition: schedule not yet created");
					return createFinalStageScheduleByCompetitionId(competitionId);
				}

				result.put("Schedule", schedule);
				config.put("Global", 0);
				error.put("MessageCode", 0);
				error.put("Message", "Successful");

			}
			System.out.println("ScheduleAPI: getScheduleByCompetition: no exception");
		} catch (Exception e) {
			System.out.println("ScheduleAPI: getScheduleByCompetition: has exception");
			result.put("Schedule", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("ScheduleAPI: getScheduleByCompetition: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

	@PostMapping()
	public ResponseEntity<Response> createFinalStageScheduleByCompetitionId(
			@RequestParam(value = "competitionId", required = false) Long competitionId) {
		System.out.println("ScheduleAPI: createFinalStageScheduleByCompetitionId: start");
		Response response = new Response();
		HttpStatus httpStatus = HttpStatus.OK;
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();

		Collection<MatchEntity> matches = new ArrayList<>();
		ArrayList<TeamEntity> teams = new ArrayList<>();
		HashMap<String, Object> schedule = new HashMap<>();
		
		EliminationTree tree = new EliminationTree();
		RoundRobinTable table = new RoundRobinTable();

		try {
			CompetitionEntity thisCompetition = competitionService.findOneById(competitionId);

			if (thisCompetition == null) {
				result.put("Schedule", null);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "Competition not found");
			} else {

				for (TeamEntity team : thisCompetition.getTeams()) {
					if (team.getStatus() == Const.TEAM_STATUS_JOINED)
						teams.add(team);
				}

				schedule.put("TotalTeam", teams.size());

				FormatEntity format;
				String formatName;

				try {
					format = thisCompetition.getFinalStageSetting().getFormat();
					formatName = format.getName();
				} catch (Exception e) {
					format = null;
					formatName = Const.ANOTHER_FORMAT;

				}

				System.out.println("ScheduleAPI: createFinalStageScheduleByCompetitionId: format: " + formatName);
				schedule.put("Format", formatName);

				boolean err = false;

				if (formatName == Const.SINGLE_ELIMINATION_FORMAT) {
					System.out.println("ScheduleAPI: createFinalStageScheduleByCompetitionId: case: SINGLE_ELIMINATION_FORMAT");
					if (teams.size() < 2) {
						System.out.println(
								"ScheduleAPI: createFinalStageScheduleByCompetitionId: case: SINGLE_ELIMINATION_FORMAT: Not enough teams");
						config.put("Global", 0);
						error.put("MessageCode", 1);
						error.put("Message", "Not enough teams");
						err = true;
					} else {
						tree = new EliminationTree(teams.size(), format.getId());
						schedule.put("Bracket", tree.getWinBranch());
					}
				}

				if (formatName == Const.DOUBLE_ELIMINATION_FORMAT) {
					System.out.println("ScheduleAPI: createFinalStageScheduleByCompetitionId: case: DOUBLE_ELIMINATION_FORMAT");
					if (teams.size() < 3) {
						System.out.println(
								"ScheduleAPI: createFinalStageScheduleByCompetitionId: case: DOUBLE_ELIMINATION_FORMAT: Not enough teams");
						config.put("Global", 0);
						error.put("MessageCode", 1);
						error.put("Message", "Not enough teams");
						err = true;
					} else {
						tree = new EliminationTree(teams.size(), format.getId());
						System.out.println("ScheduleAPI: createFinalStageScheduleByCompetitionId: build tree complete");
						schedule.put("WinBranch", tree.getWinBranch());
						schedule.put("LoseBranch", tree.getLoseBranch());
						System.out.println("ScheduleAPI: createFinalStageScheduleByCompetitionId: set schedule complete");
					}
				}
				
				if (formatName == Const.ROUND_ROBIN_FORMAT) {
					System.out.println("ScheduleAPI: createFinalStageScheduleByCompetitionId: case: ROUND_ROBIN_FORMAT");
					if (teams.size() < 2) {
						config.put("Global", 0);
						error.put("MessageCode", 1);
						error.put("Message", "Not enough teams");
						err = true;
					} else {
						
						table = new RoundRobinTable(teams.size());
						schedule.put("Table", table.getMatches());
					}
					
				}

				if (formatName == Const.ANOTHER_FORMAT) {
					System.out.println("ScheduleAPI: createFinalStageScheduleByCompetitionId: format not support ");
					config.put("Global", 0);
					error.put("MessageCode", 1);
					error.put("Message", "Not support format");
					err = true;
				}

				result.put("Schedule", schedule);

				if (!err) {
					config.put("Global", 0);
					error.put("MessageCode", 0);
					error.put("Message", "Successful");

				}
				// save file
				scheduleService.saveSchedule(schedule, competitionId);

			}
			System.out.println("ScheduleAPI: createFinalStageScheduleByCompetitionId: no exception");
		} catch (Exception e) {
			System.out.println("ScheduleAPI: createFinalStageScheduleByCompetitionId: has exception");
			result.put("Schedule", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("ScheduleAPI: createFinalStageScheduleByCompetitionId: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}
	
	
	private HashMap<String, Object> finalStageScheduling(int totalTeam, String formatName, FinalStageSettingEntity setting){
		HashMap<String, Object> schedule = null;
		
		return schedule;
	}

}
