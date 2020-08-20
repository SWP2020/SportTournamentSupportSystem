package doan2020.SportTournamentSupportSystem.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import doan2020.SportTournamentSupportSystem.converter.TeamConverter;
import doan2020.SportTournamentSupportSystem.dto.TeamDTO;
import doan2020.SportTournamentSupportSystem.entity.TeamEntity;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.ITeamService;


@RestController
@CrossOrigin
@RequestMapping("/team")
public class TeamAPI {
	@Autowired
	private ITeamService service;

	@Autowired
	private TeamConverter converter;
     
	
	/*
	 * Get team theo id
	 */
	@GetMapping("")
	public ResponseEntity<Response> getOneTeam(@RequestParam(value = "id", required = true) Long id) {
		System.out.println("TeamAPI - getOneTeam - start");
		System.out.println(id);
		HttpStatus httpStatus = null;
		httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		TeamEntity teamEntity = new TeamEntity();
		TeamDTO dto = new TeamDTO();
		try {

			if (id == null) {// id not exist
				System.out.println("TeamAPI - getOneTeam - cp1");
				result.put("Team", dto);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "Requried id");
			} else {// id exist
				teamEntity = service.findOneById(id);
				System.out.println("TeamAPI - getOneTeam - cp1");
				if (teamEntity == null) {// competition is not exist
					System.out.println("TeamAPI - getOneTeam - cp2");
					result.put("Team", dto);
					config.put("Global", 0);
					error.put("MessageCode", 1);
					error.put("Message", "Team is not exist");
				} else {// competition is exist
					System.out.println("TeamAPI - getOneTeam - cp3");
					dto = converter.toDTO(teamEntity);
					System.out.println("TeamAPI - getOneTeam - cp4");
					result.put("Team", dto);
					config.put("Global", 0);
					error.put("MessageCode", 0);
					error.put("Message", "get Team Successfully");
				}
			}

		} catch (Exception e) {
			System.out.println("TeamAPI - getOneTeam - cp5");
			result.put("Team", dto);
			config.put("Global", 0);
			error.put("MessageCode", 0);
			error.put("Message", "exception");
		}
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("TeamAPI - getOneTeam - cp pass");
		return new ResponseEntity<Response>(response, httpStatus);
	}

	/*
	 * Create one Team
	 * 
	 */
	@PostMapping
	@CrossOrigin
	public ResponseEntity<Response> createTeam(@RequestBody TeamDTO teamDTO) {
		System.out.println("Team API - createTeam - start");
		HttpStatus httpStatus = null;
		httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		TeamEntity teamEntity = new TeamEntity();
		TeamDTO resDTO = new TeamDTO();

		try {
			teamEntity = converter.toEntity(teamDTO);

			if (teamEntity == null) {// convert false
				System.out.println("Team API - createTeam - cp1");
				result.put("Team", resDTO);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "create new Team fail");
			} else {// convert ok
				System.out.println("Team API - createTeam - cp2: creator: "+ teamDTO.getCreatorId());
				service.create(teamEntity);
				resDTO = converter.toDTO(teamEntity);
				result.put("Team", resDTO);
				config.put("Global", 0);
				error.put("MessageCode", 0);
				error.put("Message", "create new Team successfull");
				System.out.println("Team API - createTeam - cp3");
			}

		} catch (Exception e) {
			System.out.println("Team API - createTeam - exception");
			result.put("Team", resDTO);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "server error");
		}
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("Team API - createTeam - pass");
		return new ResponseEntity<Response>(response, httpStatus);
	}

	/*
	 * Edit one Team
	 * 
	 */
	@PutMapping
	@CrossOrigin
	public ResponseEntity<Response> editTeam(@RequestBody TeamDTO teamDTO, @RequestParam Long id) {
		System.out.println("Team API - editTeam - start");
		HttpStatus httpStatus = null;
		httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		TeamEntity teamEntity = new TeamEntity();
		TeamDTO resDTO = new TeamDTO();

		try {

			if (id == null) {// id is not exist
				System.out.println("Team API - editTeam - cp1");
				result.put("Team", null);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "Required Id");
			} else {// id is exist

				teamEntity = converter.toEntity(teamDTO);

				if (teamEntity == null) {// convert false
					System.out.println("Team API - editTeam - cp2");
					result.put("Team", resDTO);
					config.put("Global", 0);
					error.put("MessageCode", 1);
					error.put("Message", "edit new Team fail");
				} else {// convert ok
					System.out.println("Team API - editTeam - cp3");
					service.update(id, teamEntity);
					resDTO = converter.toDTO(teamEntity);
					result.put("Team", resDTO);
					config.put("Global", 0);
					error.put("MessageCode", 0);
					error.put("Message", "edit new Team successfull");
					System.out.println("Team API - editTeam - cp4");
				}
			}
			System.out.println("Team API - editTeam - has no exception");
		} catch (Exception e) {
			System.out.println("Team API - editTeam - has exception");
			result.put("Team", resDTO);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "server error");
		}
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("Team API - editTeam - pass");
		return new ResponseEntity<Response>(response, httpStatus);
	}
	
	/* delete one Team */
	@DeleteMapping("")
	public ResponseEntity<Response> deleteTeam(@RequestParam("id") Long id) {
		System.out.println("Team API - deleteTeam - start");
		HttpStatus httpStatus = null;
		httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		TeamEntity teamEntity = new TeamEntity();
		TeamDTO resDTO = new TeamDTO();
		try {

			if (id == null) {// id is not exist
				System.out.println("Team API - deleteTeam - cp1");
				result.put("Team", resDTO);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "Required Id");
			} else {// id is exist
				service.delete(id);
				resDTO = converter.toDTO(teamEntity);
				result.put("Team", resDTO);
				config.put("Global", 0);
				error.put("MessageCode", 0);
				error.put("Message", "Delete Team successfull");
				System.out.println("Team API - deleteTeam - cp2");
			}

			System.out.println("Team API - deleteTeam - has no exception");
		} catch (Exception e) {
			System.out.println("Team API - deleteTeam - has exception");
			result.put("Team", resDTO);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "server error");
		}
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("Team API - deleteTeam - pass");
		return new ResponseEntity<Response>(response, httpStatus);
	}
}
