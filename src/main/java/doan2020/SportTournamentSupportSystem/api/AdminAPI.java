package doan2020.SportTournamentSupportSystem.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import doan2020.SportTournamentSupportSystem.config.Const;
import doan2020.SportTournamentSupportSystem.converter.CompetitionConverter;
import doan2020.SportTournamentSupportSystem.converter.PermissionConverter;
import doan2020.SportTournamentSupportSystem.converter.ReportConverter;
import doan2020.SportTournamentSupportSystem.converter.TeamConverter;
import doan2020.SportTournamentSupportSystem.converter.TournamentConverter;
import doan2020.SportTournamentSupportSystem.converter.UserConverter;
import doan2020.SportTournamentSupportSystem.dto.CompetitionDTO;
import doan2020.SportTournamentSupportSystem.dto.PermissionDTO;
import doan2020.SportTournamentSupportSystem.dto.ReportDTO;
import doan2020.SportTournamentSupportSystem.dto.TeamDTO;
import doan2020.SportTournamentSupportSystem.dto.TournamentDTO;
import doan2020.SportTournamentSupportSystem.dto.UserDTO;
import doan2020.SportTournamentSupportSystem.entity.CompetitionEntity;
import doan2020.SportTournamentSupportSystem.entity.FinalStageSettingEntity;
import doan2020.SportTournamentSupportSystem.entity.FormatEntity;
import doan2020.SportTournamentSupportSystem.entity.GroupStageSettingEntity;
import doan2020.SportTournamentSupportSystem.entity.MatchEntity;
import doan2020.SportTournamentSupportSystem.entity.PermissionEntity;
import doan2020.SportTournamentSupportSystem.entity.ReportEntity;
import doan2020.SportTournamentSupportSystem.entity.TeamEntity;
import doan2020.SportTournamentSupportSystem.entity.TournamentEntity;
import doan2020.SportTournamentSupportSystem.entity.UserEntity;
import doan2020.SportTournamentSupportSystem.model.ContainerCollection.RankingTable;
import doan2020.SportTournamentSupportSystem.model.Entity.BoxDescription;
import doan2020.SportTournamentSupportSystem.model.Entity.Player;
import doan2020.SportTournamentSupportSystem.model.Schedule.Format.RoundRobinTable;
import doan2020.SportTournamentSupportSystem.model.Schedule.Format.SingleEliminationTree;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.ICompetitionService;
import doan2020.SportTournamentSupportSystem.service.IFinalStageSettingService;
import doan2020.SportTournamentSupportSystem.service.IGroupStageSettingService;
import doan2020.SportTournamentSupportSystem.service.IPermissionService;
import doan2020.SportTournamentSupportSystem.service.IReportService;
import doan2020.SportTournamentSupportSystem.service.IScheduleService;
import doan2020.SportTournamentSupportSystem.service.ITeamService;
import doan2020.SportTournamentSupportSystem.service.ITournamentService;
import doan2020.SportTournamentSupportSystem.service.IUserService;
import doan2020.SportTournamentSupportSystem.service.impl.FileStorageService;
import doan2020.SportTournamentSupportSystem.service.impl.JwtService;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminAPI {

	@Autowired
	private ITournamentService tournamentService;

	@Autowired
	private IUserService userService;

	@Autowired
	private TournamentConverter tournamentconverter;

	@Autowired
	private UserConverter userConverter;

	@Autowired
	private PermissionConverter permissionConverter;

	@Autowired
	private FileStorageService fileStorageService;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private IPermissionService permissionService;

	@Autowired
	private ICompetitionService competitionService;

	@Autowired
	private CompetitionConverter competitionConverter;

	@Autowired
	private IScheduleService scheduleService;

	@Autowired
	private ITeamService teamService;

	@Autowired
	private TeamConverter teamConverter;

	@Autowired
	private IReportService reportService;

	@Autowired
	private ReportConverter reportConverter;

	@Autowired
	private IFinalStageSettingService finalStageService;

	@Autowired
	private IGroupStageSettingService groupStageService;

	@GetMapping("/searchTournament")
	public ResponseEntity<Response> searchTournament(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "searchString") String searchString) {
		System.out.println("AdminAPI: getBySearchString: start");

		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();

		List<TournamentEntity> entities = new ArrayList<TournamentEntity>();
		List<Map<String, Object>> tournaments = new ArrayList<Map<String, Object>>();
		Long totalPage = 0l;
		if (limit == null || limit <= 0)
			limit = 3;

		if (page == null || page <= 0)
			page = 1;

		if (searchString == null) {// searchString null
			result.put("Tournaments", tournaments);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Required param searchString");
		} else {// searchString not null
//			Sort sortable = Sort.by("id").ascending();
			try {
				Pageable pageable = PageRequest.of(page - 1, limit);
				entities = (List<TournamentEntity>) tournamentService.findBySearchString(pageable, searchString);

				Long totalEntity = tournamentService.countBySearchString(searchString);

				System.out.println("AdminAPI: getBySearchString: totalEntity: " + new Long(totalEntity).toString());

				totalPage = totalEntity / limit;
				if (totalEntity % limit != 0)
					totalPage++;

				for (TournamentEntity entity : entities) {
					TournamentDTO tournamentDTO = tournamentconverter.toDTO(entity);

					Map<String, Object> tournament = new HashMap<String, Object>();
					Map<String, Object> otherInformation = new HashMap<String, Object>();

					otherInformation = tournamentService.getOtherInformation(entity.getId());
					tournament.put("OtherInformation", otherInformation);
					tournament.put("Tournament", tournamentDTO);

					tournaments.add(tournament);
				}

				result.put("TotalPage", totalPage);
				result.put("Tournaments", tournaments);
				config.put("Global", 0);
				error.put("MessageCode", 0);
				error.put("Message", "Found");
				System.out.println("AdminAPI: getBySearchString: no exception");
			} catch (Exception e) {
				System.out.println("AdminAPI: getBySearchString: has exception");
				result.put("TotalPage", totalPage);
				result.put("Tournaments", tournaments);
				config.put("Global", 0);
				error.put("MessageCode", 0);
				error.put("Message", "Server error");
			}

		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("AdminAPI: getBySearchString: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

	@GetMapping("/searchUser")
	public ResponseEntity<Response> searchUser(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "searchString") String searchString) {
		System.out.println("AdminAPI: getBySearchString: start");

		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();

		List<UserDTO> dtos = new ArrayList<UserDTO>();
		List<UserEntity> entities = new ArrayList<UserEntity>();

		if (limit == null || limit <= 0)
			limit = 3;

		if (page == null || page <= 0)
			page = 1;

		if (searchString == null) {// searchString null
			result.put("Users", dtos);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Required param searchString");
		} else {// searchString not null
//			Sort sortable = Sort.by("id").ascending();
			try {
				Pageable pageable = PageRequest.of(page - 1, limit);
				entities = (List<UserEntity>) userService.findBySearchString(pageable, searchString);

				Long totalPage = 0l;

				Long totalEntity = userService.countBySearchString(searchString);

				totalPage = totalEntity / limit;
				if (totalEntity % limit != 0)
					totalPage++;

				for (UserEntity entity : entities) {
					UserDTO dto = userConverter.toDTO(entity);
					dtos.add(dto);
				}

				result.put("TotalPage", totalPage);
				result.put("Users", dtos);
				config.put("Global", 0);
				error.put("MessageCode", 0);
				error.put("Message", "Found");
				System.out.println("AdminAPI: getBySearchString: no exception");
			} catch (Exception e) {
				System.out.println("AdminAPI: getBySearchString: has exception");
				result.put("TotalPage", null);
				result.put("Users", dtos);
				config.put("Global", 0);
				error.put("MessageCode", 0);
				error.put("Message", "Server error");
			}

		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("AdminAPI: getBySearchString: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

	@GetMapping("/viewTournament")
	public ResponseEntity<Response> viewTournament(
			@RequestHeader(value = Const.TOKEN_HEADER, required = false) String jwt,
			@RequestParam(value = "id", required = false) Long id) {
		System.out.println("AdminAPI: getTournament: start");
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();

		TournamentEntity tournamentEntity = new TournamentEntity();
		TournamentDTO tournamentDTO = new TournamentDTO();
		UserEntity user = new UserEntity();
		PermissionEntity permissionEntity = new PermissionEntity();
		PermissionDTO permissionDTO = new PermissionDTO();
		Map<String, Object> otherInformation = new HashMap<String, Object>();

		try {
			if (id == null) { // id null
				result.put("Tournament", tournamentDTO);
				result.put("OtherInformation", otherInformation);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "Required param id");
			} else { // id not null

				tournamentEntity = tournamentService.findOneById(id);

				if (tournamentEntity == null) { // not found
					result.put("Tournament", tournamentDTO);
					result.put("OtherInformation", otherInformation);
					config.put("Global", 0);
					error.put("MessageCode", 1);
					error.put("Message", "Not found");
				} else { // found

					tournamentDTO = tournamentconverter.toDTO(tournamentEntity);

					otherInformation = tournamentService.getOtherInformation(id);

					Long curentUserId = -1l;

					try {
						String curentUserName = jwtService.getUserNameFromJwtToken(jwt);
						user = userService.findByUsername(curentUserName);
						curentUserId = user.getId();
					} catch (Exception e) {

					}

					if (curentUserId == tournamentEntity.getCreator().getId()) {
						permissionEntity = permissionService.findOneByName(Const.OWNER);

						permissionDTO = permissionConverter.toDTO(permissionEntity);
					} else {
						permissionEntity = permissionService.findOneByName(Const.MONITOR);

						permissionDTO = permissionConverter.toDTO(permissionEntity);
					}

					result.put("Tournament", tournamentDTO);
					result.put("OtherInformation", otherInformation);
					config.put("Global", permissionDTO);
					error.put("MessageCode", 0);
					error.put("Message", "Found");
				}
			}
			System.out.println("AdminAPI: getTournament: no exception");
		} catch (Exception e) {
			System.out.println("AdminAPI: getTournament: has exception");
			result.put("Tournament", null);
			result.put("OtherInformation", otherInformation);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("AdminAPI: getTournament: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

	/* ----------------Get One Competititon ------------------------ */
	@GetMapping("/viewCompetition")
	public ResponseEntity<Response> viewCompetition(
			@RequestHeader(value = Const.TOKEN_HEADER, required = false) String jwt, @RequestParam("id") Long id) {
		System.out.println("Competition API - GetCompetiton - start");
		System.out.println(id);
		HttpStatus httpStatus = null;
		httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		CompetitionEntity competitionEntity = new CompetitionEntity();
		UserEntity user = new UserEntity();
		PermissionEntity permissionEntity = new PermissionEntity();
		PermissionDTO permissionDTO = new PermissionDTO();
		CompetitionDTO dto = new CompetitionDTO();
		try {
			if (id == null) {// id not exist
				System.out.println("Competition API - GetCompetiton - id null");
				result.put("Competition", null);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "Requried id");
			} else {// id exist
				System.out.println("Competition API - GetCompetiton - id not null: " + id.toString());
				competitionEntity = competitionService.findOneById(id);
				System.out.println("Competition API - GetCompetiton - find OK");
				if (competitionEntity == null) {// competition is not exist
					System.out.println("Competition API - GetCompetiton - competitionEntity null");
					result.put("Competition", null);
					config.put("Global", 0);
					error.put("MessageCode", 1);
					error.put("Message", "Not found");
				} else {// competition is exist

					Long curentUserId = -1l;

					try {
						String curentUserName = jwtService.getUserNameFromJwtToken(jwt);
						user = userService.findByUsername(curentUserName);
						curentUserId = user.getId();
					} catch (Exception e) {

					}

					if (curentUserId == competitionEntity.getTournament().getCreator().getId()) {
						permissionEntity = permissionService.findOneByName(Const.OWNER);

						permissionDTO = permissionConverter.toDTO(permissionEntity);
					} else {
						permissionEntity = permissionService.findOneByName(Const.MONITOR);

						permissionDTO = permissionConverter.toDTO(permissionEntity);
					}

					System.out.println("Competition API - GetCompetiton - competitionEntity not null");
					dto = competitionConverter.toDTO(competitionEntity);
					System.out.println("Competition API - GetCompetiton - convert to DTO ok");
					result.put("competition", dto);
					config.put("Global", permissionDTO);
					error.put("MessageCode", 0);
					error.put("Message", "Found");
				}
			}
			System.out.println("Competition API - GetCompetiton - no exception");
		} catch (Exception e) {
			System.out.println("Competition API - GetCompetiton - has exception");
			result.put("competition", dto);
			config.put("Global", 0);
			error.put("MessageCode", 0);
			error.put("Message", "exception");
		}
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("Competition API - GetCompetiton - finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

	/* get One User */

	@GetMapping("/viewUserInformation")
	public ResponseEntity<Response> viewUserInformation(
			@RequestHeader(value = Const.TOKEN_HEADER, required = false) String jwt,
			@RequestParam(value = "id", required = false) Long id) {
		System.out.println("AdminAPI: getById: start");
		Response response = new Response();
		HttpStatus httpStatus = HttpStatus.OK;
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		UserEntity user = new UserEntity();
		UserDTO dto = new UserDTO();
		PermissionEntity permissionEntity = new PermissionEntity();
		PermissionDTO permissionDTO = new PermissionDTO();

		try {

			if (id == null) {// id null
				result.put("User", null);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "Required param id");
			} else {// id not null
				user = userService.findOneById(id);
				if (user == null) {// not found
					result.put("User", null);
					config.put("Global", 0);
					error.put("MessageCode", 1);
					error.put("Message", "Not found");
				} else {// found

					dto = userConverter.toDTO(user);
					System.out.println("AdminAPI: getById: CP1");
					Long curentUserId = -1l;

					try {
						System.out.println("AdminAPI: getById: jwt: " + jwt);
						String curentUserName = jwtService.getUserNameFromJwtToken(jwt);
						user = userService.findByUsername(curentUserName);
						curentUserId = user.getId();
					} catch (Exception e) {

					}

					if (curentUserId == id) {
						permissionEntity = permissionService.findOneByName(Const.OWNER);

						permissionDTO = permissionConverter.toDTO(permissionEntity);
					} else {
						permissionEntity = permissionService.findOneByName(Const.VIEWER);

						permissionDTO = permissionConverter.toDTO(permissionEntity);
					}
					result.put("User", dto);
					config.put("Global", permissionDTO);
					error.put("MessageCode", 0);
					error.put("Message", "Found");
				}
			}
			System.out.println("AdminAPI: getById: no exception");

		} catch (Exception e) {
			System.out.println("AdminAPI: getById: has exception");
			result.put("User", null);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("AdminAPI: getById: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

	/*
	 * Get schedule for a competition
	 */

	@GetMapping("/viewBracket")
	public ResponseEntity<Response> viewBracket(
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

	/*
	 * Get schedule for a competition
	 */

	@GetMapping("/viewSchedule")
	public ResponseEntity<Response> viewSchedule(
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

	// use to viewbracket and view shedule
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

				ArrayList<BoxDescription> descriptions = new ArrayList<>();

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

	// View ranking table
	@GetMapping("/viewRankingTable")
	public ResponseEntity<Response> viewRankingTable() {
		return null;

	}

	/*
	 * Get all participated team by CompetitionId
	 */
	@GetMapping("/viewParticipatedTeams")
	public ResponseEntity<Response> viewParticipatedTeams(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "competitionId") Long competitionId) {
		System.out.println("AdminAPI: viewParticipatedTeams: start");
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		List<TeamDTO> teamDTOs = new ArrayList<TeamDTO>();
		List<TeamEntity> list = new ArrayList<TeamEntity>();
		try {

			if (limit == null || limit <= 0)
				limit = 3;

			if (page == null || page <= 0)
				page = 1;

			if (competitionId == null) {
				result.put("Total page", 0);
				result.put("Teams", null);
				config.put("Global", 0);
				error.put("MessageCode", 0);
				error.put("Message", "required competitionId");
			} else {

				list = (List<TeamEntity>) teamService.findByCompetitionIdAndStatus(competitionId,
						Const.TEAM_STATUS_JOINED);

				if (list == null) {// list is not exist
					result.put("Total page", 0);
					result.put("Teams", null);
					config.put("Global", 0);
					error.put("MessageCode", 1);
					error.put("Message", "List is not exist");

				} else {// list is exist
					Collections.sort(list, new TeamEntity());

					for (TeamEntity teamEntity : list) {
						ArrayList<Player> players = (ArrayList<Player>) teamService
								.getTeamPlayerFromFile(teamEntity.getCompetition().getId(), teamEntity.getId());
						TeamDTO resDTO = teamConverter.toDTO(teamEntity);
						resDTO.setPlayers(players);
						teamDTOs.add(resDTO);
					}

					CompetitionEntity competitionEntity = competitionService.findOneById(competitionId);

					int total = competitionEntity.getTeams().size();
					int totalPage = total / limit;
					if (total % limit != 0) {
						totalPage++;
					}
					result.put("Total page", totalPage);
					result.put("Teams", teamDTOs);
					config.put("Global", 0);
					error.put("MessageCode", 0);
					error.put("Message", "get Page Teams successfully");

				}
			}
			System.out.println("AdminAPI: viewParticipatedTeams: no exception");
		} catch (Exception e) {
			System.out.println("AdminAPI: viewParticipatedTeams: has exception");
			result.put("Teams", teamDTOs);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("AdminAPI: viewParticipatedTeams: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

	/* ---------------- Edit Profile User ------------------------ */
	@PutMapping("/editUser")
	public ResponseEntity<Response> editUser(@RequestParam(value = "id") Long id, @RequestBody UserDTO dto) {
		System.out.println("AdminAPI: editUser: start");
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		try {
			UserEntity userEntity = new UserEntity();
			if (id != null) {
				userEntity = userConverter.toEntity(dto);
				userEntity = userService.update(id, userEntity);

				result.put("User", userConverter.toDTO(userEntity));
				error.put("MessageCode", 0);
				error.put("Message", "Edit Profile User Successfull");
			} else {
				error.put("MessageCode", 1);
				error.put("Message", "required user id");
			}
			System.out.println("AdminAPI: editUser: no exception");
		} catch (Exception ex) {
			System.out.println("AdminAPI: editUser: has exception");
			result.put("User", null);
			error.put("MessageCode", 1);
			error.put("Message", "edit  User fail");
		}
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("AdminAPI: editUser: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

	// Stop tournament
	@PutMapping("/stopTournament")
	public ResponseEntity<Response> stopTournament(@RequestParam Long id) {
		System.out.println("AdminAPI: stopTournament: start");
		Response response = new Response();
		HttpStatus httpStatus = HttpStatus.OK;
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();

		TournamentEntity thisTournament = new TournamentEntity();
		TournamentDTO thisTournamentDTO = new TournamentDTO();

		try {
			if (id == null) {// id null
				result.put("Tournament", null);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "Required param id");
			} else {// id not null

				thisTournament = tournamentService.findOneById(id);
				if (thisTournament == null) {
					result.put("Tournament", null);
					config.put("Global", 0);
					error.put("MessageCode", 1);
					error.put("Message", "Tournament is not exist");
				} else {

					thisTournament = tournamentService.updateStatus(thisTournament, Const.TOURNAMENT_STATUS_STOPPED);

					thisTournamentDTO = tournamentconverter.toDTO(thisTournament);

					Collection<CompetitionEntity> comps = thisTournament.getCompetitions();
					ArrayList<HashMap<String, Object>> tests = new ArrayList<>();
					for (CompetitionEntity comp : comps) {
						CompetitionEntity competitionEntity = competitionService.updateStatus(comp,
								Const.TOURNAMENT_STATUS_STOPPED);

					}

					result.put("Tournament", thisTournamentDTO);
					config.put("Global", 0);
					error.put("MessageCode", 0);
					error.put("Message", "Success");
				}

			}
			System.out.println("AdminAPI: stopTournament: no exception");
		} catch (Exception e) {
			System.out.println("AdminAPI: stopTournament: has exception");
			result.put("Tournament", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("AdminAPI: stopTournament: finish");
		return new ResponseEntity<Response>(response, httpStatus);

	}

	// Continue tournament
	@PutMapping("/continueTournament")
	public ResponseEntity<Response> continueTournament(@RequestParam Long id) {
		System.out.println("AdminAPI: continueTournament: start");
		Response response = new Response();
		HttpStatus httpStatus = HttpStatus.OK;
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();

		TournamentEntity thisTournament = new TournamentEntity();
		TournamentDTO thisTournamentDTO = new TournamentDTO();

		try {
			if (id == null) {// id null
				result.put("Tournament", null);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "Required param id");
			} else {// id not null

				thisTournament = tournamentService.findOneById(id);
				if (thisTournament == null) {
					result.put("Tournament", null);
					config.put("Global", 0);
					error.put("MessageCode", 1);
					error.put("Message", "Tournament is not exist");
				} else {

					thisTournament = tournamentService.updateStatus(thisTournament, Const.TOURNAMENT_STATUS_PROCESSING);

					thisTournamentDTO = tournamentconverter.toDTO(thisTournament);

					Collection<CompetitionEntity> comps = thisTournament.getCompetitions();
					ArrayList<HashMap<String, Object>> tests = new ArrayList<>();
					for (CompetitionEntity comp : comps) {
						CompetitionEntity competitionEntity = competitionService.updateStatus(comp,
								Const.TOURNAMENT_STATUS_PROCESSING);

					}

					result.put("Tournament", thisTournamentDTO);
					config.put("Global", 0);
					error.put("MessageCode", 0);
					error.put("Message", "Success");
				}

			}
			System.out.println("AdminAPI: continueTournament: no exception");
		} catch (Exception e) {
			System.out.println("AdminAPI: continueTournament: has exception");
			result.put("Tournament", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("AdminAPI: continueTournament: finish");
		return new ResponseEntity<Response>(response, httpStatus);

	}

	// Change account role
	@PutMapping("/changeAccountRole")
	public ResponseEntity<Response> changeAccountRole(@RequestParam Long id, @RequestParam String roleName) {
		System.out.println("AdminAPI: changeAccountRole: start");
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		UserEntity userEntity = new UserEntity();
		UserDTO userDTO = new UserDTO();
		try {
			if (id == null || roleName == null) {// id null or roleName null
				result.put("User", null);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "Required param id or roleName");
			} else {// id not null or roleName null

				userEntity = userService.findOneById(id);
				if (userEntity == null) {
					result.put("User", null);
					config.put("Global", 0);
					error.put("MessageCode", 1);
					error.put("Message", "User is not exist");
				} else {

					userEntity = userService.updateRole(userEntity, roleName);

					userDTO = userConverter.toDTO(userEntity);

					result.put("User", userDTO);
					config.put("Global", 0);
					error.put("MessageCode", 0);
					error.put("Message", "ChangeAccountRole success");
				}

			}
		} catch (Exception e) {
			System.out.println("AdminAPI: ChangeAccountRole: has exception");
			result.put("User", userDTO);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("AdminAPI: editUser: finish");
		return new ResponseEntity<Response>(response, httpStatus);

	}

	// Activate account
	@PutMapping("/activateAccount")
	public ResponseEntity<Response> activateAccount(@RequestParam Long id) {
		System.out.println("AdminAPI: activateAccount: start");
		Response response = new Response();
		HttpStatus httpStatus = HttpStatus.OK;
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();

		UserEntity thisUser = new UserEntity();
		UserDTO thisUserDTO = new UserDTO();

		try {
			if (id == null) {// id null
				result.put("User", null);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "Required param id");
			} else {// id not null

				thisUser = userService.findOneById(id);
				if (thisUser == null) {
					result.put("User", null);
					config.put("Global", 0);
					error.put("MessageCode", 1);
					error.put("Message", "User is not exist");
				} else {

					thisUser = userService.updateStatus(thisUser, Const.STATUS_ACTIVE);

					thisUserDTO = userConverter.toDTO(thisUser);

					result.put("User", thisUserDTO);
					config.put("Global", 0);
					error.put("MessageCode", 0);
					error.put("Message", "Success");
				}

			}
			System.out.println("AdminAPI: activateAccount: no exception");
		} catch (Exception e) {
			System.out.println("AdminAPI: activateAccount: has exception");
			result.put("User", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("AdminAPI: activateAccount: finish");
		return new ResponseEntity<Response>(response, httpStatus);

	}

	// Deactivate account
	@PutMapping("/deactivateAccount")
	public ResponseEntity<Response> deactivateAccount(@RequestParam Long id) {
		System.out.println("AdminAPI: deactivateAccount: start");
		Response response = new Response();
		HttpStatus httpStatus = HttpStatus.OK;
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();

		UserEntity thisUser = new UserEntity();
		UserDTO thisUserDTO = new UserDTO();

		try {
			if (id == null) {// id null
				result.put("User", null);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "Required param id");
			} else {// id not null

				thisUser = userService.findOneById(id);
				if (thisUser == null) {
					result.put("User", null);
					config.put("Global", 0);
					error.put("MessageCode", 1);
					error.put("Message", "User is not exist");
				} else {

					thisUser = userService.updateStatus(thisUser, Const.STATUS_DEACTIVE);

					thisUserDTO = userConverter.toDTO(thisUser);

					result.put("User", thisUserDTO);
					config.put("Global", 0);
					error.put("MessageCode", 0);
					error.put("Message", "Success");
				}

			}
			System.out.println("AdminAPI: deactivateAccount: no exception");
		} catch (Exception e) {
			System.out.println("AdminAPI: deactivateAccount: has exception");
			result.put("User", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("AdminAPI: deactivateAccount: finish");
		return new ResponseEntity<Response>(response, httpStatus);

	}

	// View system report
	@GetMapping("/ViewSystemReport")
	public ResponseEntity<Response> ViewSystemReports(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit) {
		System.out.println("AdminAPI: ViewSystemReport: start");

		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		List<ReportDTO> dtos = new ArrayList<ReportDTO>();
		List<ReportEntity> entities = new ArrayList<ReportEntity>();

		if (limit == null || limit <= 0)
			limit = 10;

		if (page == null || page <= 0)
			page = 1;

		try {
			Pageable pageable = PageRequest.of(page - 1, limit);
			entities = (List<ReportEntity>) reportService.findByType(pageable, Const.REPORT_SYSTEM_ERROR);
			int totalPage = 0;
			List<ReportEntity> entities2 = (List<ReportEntity>) reportService.findByType(Const.REPORT_SYSTEM_ERROR);
			System.out.println(entities2);
			int totalEntity = entities2.size();
			totalPage = totalEntity / limit;
			System.out.println(totalPage);
			if (totalEntity % limit != 0) {
				totalPage++;
				System.out.println(totalPage);
			}
			System.out.println(totalPage);
			for (ReportEntity entity : entities) {
				ReportDTO dto = reportConverter.toDTO(entity);
				dtos.add(dto);
			}

			result.put("TotalPage", totalPage);
			result.put("Reports", dtos);
			config.put("Global", 0);
			error.put("MessageCode", 0);
			error.put("Message", "Found");
			System.out.println("AdminAPI: ViewSystemReport: no exception");
		} catch (Exception e) {
			System.out.println("AdminAPI: ViewSystemReport: has exception");
			result.put("TotalPage", null);
			result.put("Reports", dtos);
			config.put("Global", 0);
			error.put("MessageCode", 0);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("AdminAPI: ViewSystemReport: finish");
		return new ResponseEntity<Response>(response, httpStatus);

	}

	// View violation report
	@GetMapping("/ViewViolationReport")
	public ResponseEntity<Response> ViewViolationReports(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit) {
		System.out.println("AdminAPI: ViewViolationReport: start");

		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		List<ReportDTO> dtos = new ArrayList<ReportDTO>();
		List<ReportEntity> entities = new ArrayList<ReportEntity>();

		if (limit == null || limit <= 0)
			limit = 10;

		if (page == null || page <= 0)
			page = 1;

		try {
			Pageable pageable = PageRequest.of(page - 1, limit);
			entities = (List<ReportEntity>) reportService.findByType(pageable, Const.REPORT_VIOLATION);
			int totalPage = 0;
			List<ReportEntity> entities2 = (List<ReportEntity>) reportService.findByType(Const.REPORT_VIOLATION);
			System.out.println(entities2);
			int totalEntity = entities2.size();
			totalPage = totalEntity / limit;
			System.out.println(totalPage);
			if (totalEntity % limit != 0) {
				totalPage++;
				System.out.println(totalPage);
			}
			System.out.println(totalPage);
			for (ReportEntity entity : entities) {
				ReportDTO dto = reportConverter.toDTO(entity);
				dtos.add(dto);
			}

			result.put("TotalPage", totalPage);
			result.put("Reports", dtos);
			config.put("Global", 0);
			error.put("MessageCode", 0);
			error.put("Message", "Found");
			System.out.println("AdminAPI: ViewViolationReport: no exception");
		} catch (Exception e) {
			System.out.println("AdminAPI: ViewViolationReport: has exception");
			result.put("TotalPage", null);
			result.put("Reports", dtos);
			config.put("Global", 0);
			error.put("MessageCode", 0);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("AdminAPI: ViewViolationReport: finish");
		return new ResponseEntity<Response>(response, httpStatus);

	}

}
