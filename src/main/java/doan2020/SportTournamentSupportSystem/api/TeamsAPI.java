package doan2020.SportTournamentSupportSystem.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import doan2020.SportTournamentSupportSystem.converter.TeamConverter;
import doan2020.SportTournamentSupportSystem.dto.TeamDTO;
import doan2020.SportTournamentSupportSystem.entity.CompetitionEntity;
import doan2020.SportTournamentSupportSystem.entity.TeamEntity;
import doan2020.SportTournamentSupportSystem.entity.UserEntity;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.ICompetitionService;
import doan2020.SportTournamentSupportSystem.service.ITeamService;
import doan2020.SportTournamentSupportSystem.service.IUserService;

@RestController
@CrossOrigin
@RequestMapping("/teams")
public class TeamsAPI {
	@Autowired
	private ITeamService service;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ICompetitionService competitionService;

	@Autowired
	private TeamConverter converter;

	/*
	 * Get all team Paging
	 */
	@GetMapping("")
	public ResponseEntity<Response> getTeams(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit) {
		System.out.println("TeamsAPI: getTeams: start");
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

			Sort sortable = Sort.by("id").ascending();
			Pageable pageable = PageRequest.of(page - 1, limit, sortable);

			list = (List<TeamEntity>) service.findAll(pageable);

			if (list.isEmpty()) {// list is not exist
				result.put("Total page", null);
				result.put("Teams", null);
				config.put("Global", 0);
				error.put("MessageCode", 0);
				error.put("Message", "Page Teams is not exist");

			} else {// list is exist

				for (TeamEntity teamEntity : list) {

					TeamDTO resDTO = converter.toDTO(teamEntity);
					teamDTOs.add(resDTO);
				}

				List<TeamEntity> totalLists = (List<TeamEntity>) service.findAll();

				int total = totalLists.size();
				int totalPage = total / limit;
				if (total % limit != 0) {
					totalPage++;
				}
				result.put("Total page", totalPage);
				result.put("Teams", teamDTOs);
				config.put("Global", 0);
				error.put("MessageCode", 0);
				error.put("Message", "get Page Teams successfully");

				System.out.println("TeamsAPI: getTeams: no exception");
			}
		} catch (Exception e) {
			System.out.println("TeamsAPI: getTeams: has exception");
			result.put("Teams", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("TeamsAPI: getTeams: pass");
		return new ResponseEntity<Response>(response, httpStatus);
	}

	/*
	 * Get all team Paging by UserId
	 */
	@GetMapping("/getByUserId")
	public ResponseEntity<Response> getTeamsByUserId(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "userId") Long userId) {
		System.out.println("TeamsAPI: getTeamByUserId: start");
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

			if (userId == null) {
				result.put("Total page", null);
				result.put("Teams", null);
				config.put("Global", 0);
				error.put("MessageCode", 0);
				error.put("Message", "required Userid");
			} else {

				Sort sortable = Sort.by("id").ascending();
				Pageable pageable = PageRequest.of(page - 1, limit, sortable);

				list = (List<TeamEntity>) service.findByCreatorId(pageable, userId);

				if (list.isEmpty()) {// list is not exist
					result.put("Total page", null);
					result.put("Teams", null);
					config.put("Global", 0);
					error.put("MessageCode", 0);
					error.put("Message", "Page Teams is not exist");

				} else {// list is exist

					for (TeamEntity teamEntity : list) {

						TeamDTO resDTO = converter.toDTO(teamEntity);
						teamDTOs.add(resDTO);
					}

					UserEntity userEntity = userService.findOneById(userId);

					int total = userEntity.getTeams().size();
					int totalPage = total / limit;
					if (total % limit != 0) {
						totalPage++;
					}
					result.put("Total page", totalPage);
					result.put("Teams", teamDTOs);
					config.put("Global", 0);
					error.put("MessageCode", 0);
					error.put("Message", "get Page Teams successfully");

					System.out.println("TeamsAPI: getTeamByUserId: no exception");
				}
			}
		} catch (Exception e) {
			System.out.println("TeamsAPI: getTeamByUserId: has exception");
			result.put("Teams", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("TeamsAPI: getTeams: pass");
		return new ResponseEntity<Response>(response, httpStatus);
	}

	/*
	 * Get all team Paging by CompetitionId
	 */
	@GetMapping("/getByCompetitionId")
	public ResponseEntity<Response> getTeamsByCompetitionId(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "competitionId") Long competitionId) {
		System.out.println("TeamsAPI: getByCompetitionId: start");
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
				result.put("Total page", null);
				result.put("Teams", null);
				config.put("Global", 0);
				error.put("MessageCode", 0);
				error.put("Message", "required competitionId");
			} else {

				Sort sortable = Sort.by("id").ascending();
				Pageable pageable = PageRequest.of(page - 1, limit, sortable);

				list = (List<TeamEntity>) service.findByCompetitionId(pageable, competitionId);

				if (list.isEmpty()) {// list is not exist
					result.put("Total page", null);
					result.put("Teams", null);
					config.put("Global", 0);
					error.put("MessageCode", 0);
					error.put("Message", "Page Teams is not exist");

				} else {// list is exist

					for (TeamEntity teamEntity : list) {

						TeamDTO resDTO = converter.toDTO(teamEntity);
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

					System.out.println("TeamsAPI: getTeams: no exception");
				}
			}
		} catch (Exception e) {
			System.out.println("TeamsAPI: getTeams: has exception");
			result.put("Teams", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("TeamsAPI: getTeams: pass");
		return new ResponseEntity<Response>(response, httpStatus);
	}

}
