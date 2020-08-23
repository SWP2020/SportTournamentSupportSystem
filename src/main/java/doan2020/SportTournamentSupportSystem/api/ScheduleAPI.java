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
import doan2020.SportTournamentSupportSystem.entity.FormatEntity;
import doan2020.SportTournamentSupportSystem.entity.MatchEntity;
import doan2020.SportTournamentSupportSystem.entity.TeamEntity;
import doan2020.SportTournamentSupportSystem.model.LogicEntity.EliminationTree;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.ICompetitionService;
import doan2020.SportTournamentSupportSystem.service.IFileStorageService;

@RestController
@CrossOrigin
@RequestMapping("/schedule")
public class ScheduleAPI {

	@Autowired
	private ICompetitionService competitionService;
	
	@Autowired
	private IFileStorageService fileService;

	/*
	 * Get schedule for a competition
	 */
	
	@SuppressWarnings("unchecked")
	@GetMapping()
	public ResponseEntity<Response> getScheduleByCompetition(
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
				
				String fileName = "comp_" + competitionId + ".conf";
				String absFolderPath = fileService.getFileStorageLocation(Const.BRANCH_CONFIG_FOLDER).toString();
				HashMap<String, Object> schedule;
				try {
					System.out.println("ScheduleAPI: getScheduleByCompetition: try to get schedule");
					schedule = (HashMap<String, Object>) fileService.getObjectFromFile(absFolderPath + "\\" + fileName);
					// Check for changes in team numbers
					int dbTeamNumber = thisCompetition.getTeams().size();
					int cfTeamNumber = (int) schedule.get("TotalTeam");
					
					if (dbTeamNumber != cfTeamNumber)
						return scheduleByCompetitionId(competitionId);
				} catch (Exception e) {
					System.out.println("ScheduleAPI: getScheduleByCompetition: schedule not yet created");
					return scheduleByCompetitionId(competitionId);
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
	public ResponseEntity<Response> scheduleByCompetitionId(
			@RequestParam(value = "competitionId", required = false) Long competitionId) {
		System.out.println("ScheduleAPI: scheduleByCompetitionId: start");
		Response response = new Response();
		HttpStatus httpStatus = HttpStatus.OK;
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();

		Collection<MatchEntity> matches = new ArrayList<>();
		ArrayList<TeamEntity> teams = new ArrayList<>();
		Map<String, Object> schedule = new HashMap<>();
		EliminationTree tree = new EliminationTree();

		try {
			CompetitionEntity thisCompetition = competitionService.findOneById(competitionId);

			if (thisCompetition == null) {
				result.put("Schedule", null);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "Competition not found");
			} else {

				for (TeamEntity team : thisCompetition.getTeams()) {
					teams.add(team);
				}
				
				schedule.put("TotalTeam", teams.size());
				
				FormatEntity format;
				Long formatId;
				
				try {
					format = thisCompetition.getFinalStageSetting().getFormat();
					formatId = format.getId();
					schedule.put("Format", format.getName());
				} catch (Exception e) {
					format = null;
					formatId = -1l;
					schedule.put("Format", "Unknown format");
				}
				
				System.out.println("ScheduleAPI: scheduleByCompetitionId: format: " + format.getName());
				
				boolean err = false;

				switch (formatId.intValue()) {
				
				case Const.SINGLE_ELIMINATION_FORMAT:
					System.out.println("ScheduleAPI: scheduleByCompetitionId: case: SINGLE_ELIMINATION_FORMAT");
					if (teams.size() < 2) {
						System.out.println(
								"ScheduleAPI: scheduleByCompetitionId: case: SINGLE_ELIMINATION_FORMAT: Not enough teams");
						config.put("Global", 0);
						error.put("MessageCode", 1);
						error.put("Message", "Not enough teams");
						err= true;
					} else {
						tree = new EliminationTree(teams, format.getId());
						schedule.put("Bracket", tree.getWinBranch());
					}
					break;
					
				case Const.DOUBLE_ELIMINATION_FORMAT:
					System.out.println("ScheduleAPI: scheduleByCompetitionId: case: DOUBLE_ELIMINATION_FORMAT");
					if (teams.size() < 3) {
						System.out.println(
								"ScheduleAPI: scheduleByCompetitionId: case: DOUBLE_ELIMINATION_FORMAT: Not enough teams");
						config.put("Global", 0);
						error.put("MessageCode", 1);
						error.put("Message", "Not enough teams");
						err = true;
					} else {
						tree = new EliminationTree(teams, format.getId());
						System.out.println("ScheduleAPI: scheduleByCompetitionId: build tree complete");
						schedule.put("WinBranch", tree.getWinBranch());
						schedule.put("LoseBranch", tree.getLoseBranch());
						System.out.println("ScheduleAPI: scheduleByCompetitionId: set schedule complete");
					}
					break;

				default:
					System.out.println("ScheduleAPI: scheduleByCompetitionId: format not support ");
					config.put("Global", 0);
					error.put("MessageCode", 1);
					error.put("Message", "Not support format");
					err = true;
					break;
				}
				
				result.put("Schedule", schedule);
				
				if (!err) {
					config.put("Global", 0);
					error.put("MessageCode", 0);
					error.put("Message", "Successful");
				
				}
				
				String fileName = "comp_" + thisCompetition.getId() + ".conf";
				String absFolderPath = fileService.getFileStorageLocation(Const.BRANCH_CONFIG_FOLDER).toString();
				
				System.out.println("ScheduleAPI: scheduleByCompetitionId: write to file: absFolderPath: \n[" + absFolderPath + "]");
				
				String absFilePath = fileService.saveObjectToFile(schedule, absFolderPath + "\\" + fileName);
				
				System.out.println("ScheduleAPI: scheduleByCompetitionId: write to file: absFileName: \n[" + absFilePath + "]");

			}
		} catch (Exception e) {
			System.out.println("ScheduleAPI: scheduleByCompetitionId: has exception");
			result.put("Schedule", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("ScheduleAPI: scheduleByCompetitionId: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

}
