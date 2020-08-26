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
import doan2020.SportTournamentSupportSystem.entity.GroupStageSettingEntity;
import doan2020.SportTournamentSupportSystem.entity.MatchEntity;
import doan2020.SportTournamentSupportSystem.entity.TeamEntity;
import doan2020.SportTournamentSupportSystem.model.ContainerCollection.RankingTable;
import doan2020.SportTournamentSupportSystem.model.LogicStruct.TeamDescription;
import doan2020.SportTournamentSupportSystem.model.ScheduleFormat.RoundRobinTable;
import doan2020.SportTournamentSupportSystem.model.ScheduleFormat.SingleEliminationTree;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.ICompetitionService;
import doan2020.SportTournamentSupportSystem.service.IFileStorageService;
import doan2020.SportTournamentSupportSystem.service.IFinalStageSettingService;
import doan2020.SportTournamentSupportSystem.service.IGroupStageSettingService;
import doan2020.SportTournamentSupportSystem.service.IScheduleService;
import doan2020.SportTournamentSupportSystem.service.ITeamService;

@RestController
@CrossOrigin
@RequestMapping("/schedule")
public class ScheduleAPI {

	@Autowired
	private ICompetitionService competitionService;

	@Autowired
	private IScheduleService scheduleService;

	@Autowired
	private ITeamService teamService;

	@Autowired
	private IFinalStageSettingService finalStageService;

	@Autowired
	private IGroupStageSettingService groupStageService;

	/*
	 * Get schedule for a competition
	 */

	@GetMapping()
	public ResponseEntity<Response> getScheduleByCompetitionId(
			@RequestParam(value = "competitionId", required = false) Long competitionId) {
		System.out.println("ScheduleAPI: getScheduleByCompetitionId: start");
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
				HashMap<String, Object> schedule;
				try {
					System.out.println("ScheduleAPI: getScheduleByCompetitionId: try to get schedule");
					schedule = scheduleService.getSchedule(competitionId);
					// Check for changes in team numbers
					int dbTeamNumber = thisCompetition.getTeams().size();
					int cfTeamNumber = (int) schedule.get("TotalTeam");

					if (dbTeamNumber != cfTeamNumber)
						return schedulingByCompetitionId(competitionId);
				} catch (Exception e) {
					System.out.println("ScheduleAPI: getScheduleByCompetition: schedule not yet created");
					return schedulingByCompetitionId(competitionId);
				}

				result.put("Schedule", schedule);
				config.put("Global", 0);
				error.put("MessageCode", 0);
				error.put("Message", "Successful");

			}
			System.out.println("ScheduleAPI: getScheduleByCompetitionId: no exception");
		} catch (Exception e) {
			System.out.println("ScheduleAPI: getScheduleByCompetitionId: has exception");
			result.put("Schedule", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("ScheduleAPI: getScheduleByCompetitionId: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

	@PostMapping()
	public ResponseEntity<Response> schedulingByCompetitionId(
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
		HashMap<String, Object> groupStageSchedule = new HashMap<>();
		HashMap<String, Object> finalStageSchedule = new HashMap<>();

		SingleEliminationTree tree = new SingleEliminationTree();
		RoundRobinTable table = new RoundRobinTable();

		try {
			CompetitionEntity thisCompetition = competitionService.findOneById(competitionId);

			if (thisCompetition == null) {
				result.put("Schedule", null);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "Competition not found");
			} else {

				int totalTeamBeforeGroupStage;
				int totalTeamAfterGroupStage;

				FormatEntity finalFormat;
				FormatEntity groupFormat;

				ArrayList<TeamDescription> descriptions = new ArrayList<>();

				totalTeamBeforeGroupStage = teamService
						.countByCompetitionIdAndStatus(competitionId, Const.TEAM_STATUS_JOINED).intValue();

				System.out.println("++++++++++++++++++++++++++++++++++++++ totalTeamBeforeGroupStage: "
						+ totalTeamBeforeGroupStage);

				boolean hasGroupStage = thisCompetition.isHasGroupStage();
				FinalStageSettingEntity finalStageSetting = thisCompetition.getFinalStageSetting();

				if (finalStageSetting == null) {
					result.put("Schedule", null);
					config.put("Global", 0);
					error.put("MessageCode", 1);
					error.put("Message", "Missing final stage setting");
				} else {

					// group
					if (hasGroupStage) {

						GroupStageSettingEntity groupStageSetting = thisCompetition.getGroupStageSetting();
						groupFormat = groupStageSetting.getFormat();
						boolean hasHomeMatch = groupStageSetting.isHasHomeMatch();
						int maxTeamPerTable = groupStageSetting.getMaxTeamPerTable();
						int advanceTeamPerTable = groupStageSetting.getAdvanceTeamPerTable();
						int totalTable = (int) (totalTeamBeforeGroupStage / maxTeamPerTable);

						int totalTeamInFinalTable;

						if (totalTable != 0) {
							totalTeamInFinalTable = totalTeamBeforeGroupStage % totalTable;
						} else {
							totalTeamInFinalTable = totalTeamBeforeGroupStage;
						}

						if (totalTeamInFinalTable == 0) {
							totalTeamInFinalTable = maxTeamPerTable;
						} else {
							totalTable++;
						}

						System.out.println("+++++++++++++++++++++++++++ maxTeamPerTable: " + maxTeamPerTable);
						System.out.println("+++++++++++++++++++++++++++ totalTable: " + totalTable);

						// schedule for group stage
						String formatName = groupFormat.getName();
						groupStageSchedule = scheduleService.groupStageScheduling(totalTeamBeforeGroupStage, formatName,
								hasHomeMatch, maxTeamPerTable, advanceTeamPerTable, totalTable, totalTeamInFinalTable);
						System.out.println("++++++++++++++++++++++++++++++++++++++ CP6 ");
						totalTeamAfterGroupStage = (totalTable - 1) * advanceTeamPerTable
								+ (int) Math.min(totalTeamInFinalTable, advanceTeamPerTable);

						// create descriptions
						ArrayList<RankingTable> rankingTables = new ArrayList<>();
						@SuppressWarnings("unchecked")
						ArrayList<HashMap<String, Object>> tables = (ArrayList<HashMap<String, Object>>) groupStageSchedule
								.get("Tables");
						for (HashMap<String, Object> tb : tables) {
							RankingTable rankingTable = (RankingTable) tb.get("RankingTable");
							rankingTables.add(rankingTable);
						}

						for (int i = 0; i < advanceTeamPerTable; i++) {
						
							for (RankingTable tb : rankingTables) {
					
								if (tb.size() > i) {
									descriptions.add(tb.get(i).getDescription());
									
								}
							}
						}

					} else {

						totalTeamAfterGroupStage = totalTeamBeforeGroupStage;
					}

					System.out.println("++++++++++++++++++++++++++++++++++++++ totalTeamAfterGroupStage: "
							+ totalTeamAfterGroupStage);

					// final

					finalFormat = finalStageSetting.getFormat();
					boolean hasHomeMatch = finalStageSetting.isHasHomeMatch();
					String formatName = finalFormat.getName();
					finalStageSchedule = scheduleService.finalStageScheduling(totalTeamAfterGroupStage, formatName,
							hasHomeMatch, -1, descriptions, 1);

				}
				
				schedule.put("FinalStageSchedule", finalStageSchedule);
				schedule.put("GroupStageSchedule", groupStageSchedule);

				result.put("Schedule", schedule);
				config.put("Global", 0);
				error.put("MessageCode", 0);
				error.put("Message", "Success");

				// save file
				scheduleService.saveSchedule(schedule, competitionId);

			}
			System.out.println("ScheduleAPI: createFinalStageScheduleByCompetitionId: no exception");
		} catch (Exception e) {
			System.out.println("ScheduleAPI: createFinalStageScheduleByCompetitionId: has exception");
			System.out.println(e);
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

}
