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

import doan2020.SportTournamentSupportSystem.config.Const;
import doan2020.SportTournamentSupportSystem.entity.CompetitionEntity;
import doan2020.SportTournamentSupportSystem.entity.CompetitionFormatEntity;
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
	@GetMapping("")
	public ResponseEntity<Response> scheduleByCompetition(
			@RequestParam(value = "competitionId", required = false) Long competitionId) {
		System.out.println("ScheduleAPI: scheduleByCompetition: start");
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

				for (TeamEntity team : thisCompetition.getTeams()) {
					teams.add(team);
				}

				CompetitionFormatEntity format = thisCompetition.getMainFormat();

				System.out.println("ScheduleAPI: scheduleByCompetition: format: " + format.getName());

				Long formatId = format.getId();

				EliminationTree tree = new EliminationTree();
				Map<String, Object> schedule = new HashMap<>();
				boolean err = false;

				switch (formatId.intValue()) {
				case Const.SINGLE_ELIMINATION_FORMAT:
					System.out.println("ScheduleAPI: scheduleByCompetition: case: SINGLE_ELIMINATION_FORMAT");
					if (teams.size() < 2) {
						System.out.println(
								"ScheduleAPI: scheduleByCompetition: case: SINGLE_ELIMINATION_FORMAT: Not enough teams");
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
					System.out.println("ScheduleAPI: scheduleByCompetition: case: DOUBLE_ELIMINATION_FORMAT");
					if (teams.size() < 3) {
						System.out.println(
								"ScheduleAPI: scheduleByCompetition: case: DOUBLE_ELIMINATION_FORMAT: Not enough teams");
						config.put("Global", 0);
						error.put("MessageCode", 1);
						error.put("Message", "Not enough teams");
						err = true;
					} else {
						tree = new EliminationTree(teams, format.getId());
						schedule.put("WinBranch", tree.getWinBranch());
						schedule.put("LoseBranch", tree.getLoseBranch());
					}
					break;

				default:
					System.out.println("ScheduleAPI: scheduleByCompetition: format not support ");
					config.put("Global", 0);
					error.put("MessageCode", 1);
					error.put("Message", "Not support format");
					err = true;
					break;
				}
				if (!err) {
					schedule.put("Format", format.getName());
					result.put("Schedule", schedule);
					config.put("Global", 0);
					error.put("MessageCode", 0);
					error.put("Message", "Successful");
					
					System.out.println("ScheduleAPI: scheduleByCompetition: write to file: start");
					String fileName = "comp_" + thisCompetition.getId() + ".conf";
					String absFilePath = fileService.saveObjectToFile(schedule, Const.BRANCH_CONFIG_FOLDER + fileName);
					System.out.println("ScheduleAPI: scheduleByCompetition: write to file: absFileName: " + absFilePath);
				}

			}
		} catch (Exception e) {
			System.out.println("ScheduleAPI: scheduleByCompetition: has exception");
			result.put("Schedule", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("ScheduleAPI: scheduleByCompetition: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

}
