package doan2020.SportTournamentSupportSystem.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import doan2020.SportTournamentSupportSystem.config.Const;
import doan2020.SportTournamentSupportSystem.entity.TournamentEntity;
import doan2020.SportTournamentSupportSystem.entity.FinalStageSettingEntity;
import doan2020.SportTournamentSupportSystem.entity.GroupStageSettingEntity;
import doan2020.SportTournamentSupportSystem.entity.TournamentEntity;
import doan2020.SportTournamentSupportSystem.model.Schedule.DTO.ScheduleDTO;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.ITournamentService;
import doan2020.SportTournamentSupportSystem.service.IScheduleService;

@RestController
@CrossOrigin
@RequestMapping("/schedule")
public class ScheduleAPI {

	@Autowired
	private ITournamentService TournamentService;

	@Autowired
	private IScheduleService scheduleService;

	/*
	 * Get schedule for a Tournament
	 */

	@GetMapping()
	public ResponseEntity<Response> getScheduleByTournamentId(
			@RequestParam(value = "tournamentId", required = false) Long TournamentId) {
		System.out.println("ScheduleAPI: getScheduleByTournamentId: start");
		Response response = new Response();
		HttpStatus httpStatus = HttpStatus.OK;
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();

		try {

			TournamentEntity thisTournament = TournamentService.findOneById(TournamentId);
			if (thisTournament == null) {
				result.put("Schedule", null);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "Tournament not found");
			} else {
				System.out.println("ScheduleAPI: getScheduleByTournamentId: -> ScheduleService: getSchedule: ");
				ScheduleDTO schedule = scheduleService.getSchedule(thisTournament);
				try {
				System.out.println("check data send to front end: " + schedule.getGroupStageSchedule().getTables().get(0).getMatches().get(0));
				System.out.println("check data send to front end: " + schedule.getFinalStageSchedule().getRankingTable().get(0).getTeam().getId());
				System.out.println("check data send to front end: " + schedule.getFinalStageSchedule().getRankingTable().get(1).getTeam().getId());
				}catch (Exception e) {
					// TODO: handle exception
				}
				System.out.println("ScheduleService: getSchedule: -> ScheduleAPI: getScheduleByTournamentId:");
				if (schedule == null) {
					result.put("Schedule", null);
					config.put("Global", 0);
					error.put("MessageCode", 1);
					error.put("Message", "Not enough team");
				} else {
					result.put("Schedule", schedule);
					config.put("Global", 0);
					error.put("MessageCode", 0);
					error.put("Message", "Success");
				}
			}
			System.out.println("ScheduleAPI: getScheduleByTournamentId: no exception");
		} catch (Exception e) {
			System.out.println("ScheduleAPI: getScheduleByTournamentId: has exception");
			result.put("Schedule", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Đã có lỗi xảy ra, vui lòng thử lại");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("ScheduleAPI: getScheduleByTournamentId: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

	@PostMapping()
	public ResponseEntity<Response> schedulingByTournamentId(
			@RequestParam(value = "tournamentId", required = false) Long TournamentId) {
		System.out.println("ScheduleAPI: createFinalStageScheduleByTournamentId: start");
		Response response = new Response();
		HttpStatus httpStatus = HttpStatus.OK;
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();

		ScheduleDTO schedule = new ScheduleDTO();

		boolean err = false;

		try {
			TournamentEntity thisTournament = TournamentService.findOneById(TournamentId);

			if (thisTournament == null) {
				result.put("Schedule", null);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "Tournament not found");
			} else {
				if (thisTournament.getStatus().contains(Const.TOURNAMENT_STATUS_INITIALIZING)) {

					boolean hasGroupStage = thisTournament.isHasGroupStage();
					FinalStageSettingEntity fsse = thisTournament.getFinalStageSetting();
					GroupStageSettingEntity gsse = thisTournament.getGroupStageSetting();

					if (fsse == null) {
						error.put("Message", "Missing final stage setting");
						err = true;
					}

					if (hasGroupStage && gsse == null) {
						error.put("Message", "Missing group stage setting");
						err = true;
					}

					if (!err) {
						schedule = scheduleService.createSchedule(thisTournament);

						result.put("Schedule", schedule);
						config.put("Global", 0);
						error.put("MessageCode", 0);
						error.put("Message", "Success");

					} else {
						result.put("Schedule", null);
						config.put("Global", 0);
						error.put("MessageCode", 1);
					}
				} else {
					String message = "Unknown error";
					if (thisTournament.getStatus().contains(Const.TOURNAMENT_STATUS_REGISTRATION_OPENING)) {
						message = Const.TOURNAMENT_MESSAGE_REGISTRATION_OPENING;
					}
					if (thisTournament.getStatus().contains(Const.TOURNAMENT_STATUS_PROCESSING)) {
						message = Const.TOURNAMENT_MESSAGE_PROCESSING;
					}
					if (thisTournament.getStatus().contains(Const.TOURNAMENT_STATUS_FINISHED)) {
						message = Const.TOURNAMENT_MESSAGE_FINISHED;
					}
					if (thisTournament.getStatus().contains(Const.TOURNAMENT_STATUS_STOPPED)) {
						message = Const.TOURNAMENT_MESSAGE_STOPPED;
					}
					result.put("Tournament", null);
					config.put("Global", 0);
					error.put("MessageCode", 1);
					error.put("Message", message);
				}
			}

			System.out.println("ScheduleAPI: createFinalStageScheduleByTournamentId: no exception");
		} catch (Exception e) {
			System.out.println("ScheduleAPI: createFinalStageScheduleByTournamentId: has exception");
			System.out.println(e);
			result.put("Schedule", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Đã có lỗi xảy ra, vui lòng thử lại");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("ScheduleAPI: createFinalStageScheduleByTournamentId: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

	@PutMapping("/changeMatchInfo")
	public ResponseEntity<Response> changeMatchInfo(
			@RequestParam(value = "tournamentId", required = false) Long TournamentId,
			@RequestParam(value = "nodeId") Integer nodeId,
			@RequestParam(value = "degree") Integer degree,
			@RequestParam(value = "location") Integer location, // -1-RR, 0-SE, 1-Win branch, 2-Lose branch, 3-match34,
																// 4-summary final, 5-option
			@RequestParam(value = "tableId") Integer tableId, @RequestBody HashMap<String, Object> newInfo) {
		System.out.println("ScheduleAPI: changeMatchInfo: start");
		Response response = new Response();
		HttpStatus httpStatus = HttpStatus.OK;
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();

		try {

			TournamentEntity thisTournament = TournamentService.findOneById(TournamentId);
			if (thisTournament == null) {
				result.put("Schedule", null);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "Tournament not found");
			} else {

				ScheduleDTO schedule = scheduleService.changeMatchInfo(thisTournament, nodeId, degree, location,
						tableId, newInfo);

				result.put("Schedule", schedule);
				config.put("Global", 0);
				error.put("MessageCode", 0);
				error.put("Message", "Success");

			}
			System.out.println("ScheduleAPI: changeMatchInfo: no exception");
		} catch (Exception e) {
			System.out.println("ScheduleAPI: changeMatchInfo: has exception");
			result.put("Schedule", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Đã có lỗi xảy ra, vui lòng thử lại");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("ScheduleAPI: changeMatchInfo: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}
	
	@PutMapping("/swapTwoTeamInRankingTable")
	public ResponseEntity<Response> swapTwoTeamInRankingTable(
			@RequestParam(value = "tournamentId", required = false) Long TournamentId,
			@RequestParam(value = "tableId") Integer tableId,
			@RequestParam(value = "team1Id") Long team1Id,
			@RequestParam(value = "team2Id") Long team2Id) {
		System.out.println("ScheduleAPI: swapTwoTeamInRankingTable: start");
		Response response = new Response();
		HttpStatus httpStatus = HttpStatus.OK;
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();

		try {

			TournamentEntity thisTournament = TournamentService.findOneById(TournamentId);
			if (thisTournament == null) {
				result.put("Schedule", null);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "Tournament not found");
			} else {
				
                scheduleService.swapTwoTeamInRankingTable(thisTournament, tableId, team1Id, team2Id);

//				result.put("Schedule", schedule);
				config.put("Global", 0);
				error.put("MessageCode", 0);
				error.put("Message", "swapTwoTeamInRankingTable Success");

			}
			System.out.println("ScheduleAPI: swapTwoTeamInRankingTable: no exception");
		} catch (Exception e) {
			System.out.println("ScheduleAPI: swapTwoTeamInRankingTable: has exception");
			result.put("Schedule", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Đã có lỗi xảy ra, vui lòng thử lại");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("ScheduleAPI: swapTwoTeamInRankingTable: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}
	
	@PutMapping("/updateNote")
	public ResponseEntity<Response> UpdateNoteByTeamId(
			@RequestParam(value = "tournamentId", required = false) Long TournamentId,
			@RequestParam(value = "tableId") Integer tableId,
			@RequestParam(value = "teamId") Long teamId, 
			@RequestParam(value = "note") String note){
		System.out.println("ScheduleAPI: updateNote: start");
		Response response = new Response();
		HttpStatus httpStatus = HttpStatus.OK;
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();

		try {

			TournamentEntity thisTournament = TournamentService.findOneById(TournamentId);
			if (thisTournament == null) {
				result.put("Schedule", null);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "Tournament not found");
			} else {
				
                scheduleService.updateNote(thisTournament, tableId, teamId, note);

//				result.put("Schedule", schedule);
				config.put("Global", 0);
				error.put("MessageCode", 0);
				error.put("Message", "updateNote Success");

			}
			System.out.println("ScheduleAPI: updateNote: no exception");
		} catch (Exception e) {
			System.out.println("ScheduleAPI: updateNote: has exception");
			result.put("Schedule", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Đã có lỗi xảy ra, vui lòng thử lại");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("ScheduleAPI: updateNote: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}
}
