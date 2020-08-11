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

import doan2020.SportTournamentSupportSystem.converter.CompetitionConverter;
import doan2020.SportTournamentSupportSystem.dto.CompetitionDTO;
import doan2020.SportTournamentSupportSystem.entity.CompetitionEntity;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.ICompetitionService;

@RestController
@CrossOrigin
@RequestMapping("/competition")
public class CompetitionAPI {

	@Autowired
	private ICompetitionService competitionService;

	@Autowired
	private CompetitionConverter competitionConverter;

	/* ----------------Get One Competititon ------------------------ */
	@GetMapping("")
	public ResponseEntity<Response> GetCompetiton(@RequestParam("id") Long id) {
		System.out.println("Competition API - GetCompetiton - start");
		System.out.println(id);
		HttpStatus httpStatus = null;
		httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		CompetitionEntity competitionEntity = new CompetitionEntity();
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
					System.out.println("Competition API - GetCompetiton - competitionEntity not null");
					dto = competitionConverter.toDTO(competitionEntity);
					System.out.println("Competition API - GetCompetiton - convert to DTO ok");
					result.put("competition", dto);
					config.put("Global", 0);
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


	/* ---------------- add new Competition ------------------------ */
	@PostMapping
	public ResponseEntity<Response> createCompetition(@RequestBody CompetitionDTO competitionDTO) {
		System.out.println("Competition API - createCompetition - start");
		HttpStatus httpStatus = null;
		httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		CompetitionEntity competitionEntity = new CompetitionEntity();

		try {
			competitionEntity = competitionConverter.toEntity(competitionDTO);

			if (competitionEntity == null) {// convert false
				System.out.println("Competition API - createCompetition - cp1");
				result.put("Competition", null);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "create new Competition fail");
			} else {// convert ok
				System.out.println("Competition API - createCompetition - cp2");
				competitionService.create(competitionEntity);

				result.put("Competition", competitionEntity);
				config.put("Global", 0);
				error.put("MessageCode", 0);
				error.put("Message", "create new Competition successfull");
				System.out.println("Competition API - createCompetition - cp3");
			}
			System.out.println("Competition API - createCompetition - no exception");
		} catch (Exception e) {
			System.out.println("Competition API - createCompetition - has exception");
			result.put("Competition", competitionEntity);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "server error");
		}
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("Competition API - createCompetition - finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

	/* ---------------- Edit Competition ------------------------ */
	@PutMapping("")
	public ResponseEntity<Response> editCompetition(@RequestParam("id") Long id,
			@RequestBody CompetitionDTO competitionDTO) {
		System.out.println("Competition API - editCompetition - start");
		HttpStatus httpStatus = null;
		httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		CompetitionEntity competitionEntity = new CompetitionEntity();

		try {

			if (id == null) {// id is not exist
				System.out.println("Competition API - editCompetition - cp1");
				result.put("Competition", null);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "Required Id");
			} else {// id is exist

				competitionEntity = competitionConverter.toEntity(competitionDTO);

				if (competitionEntity == null) {// convert false
					System.out.println("Competition API - editCompetition - cp2");
					result.put("Competition", null);
					config.put("Global", 0);
					error.put("MessageCode", 1);
					error.put("Message", "edit new Competition fail");
				} else {// convert ok
					System.out.println("Competition API - editCompetition - cp3");
					competitionService.update(id, competitionEntity);

					result.put("Competition", competitionEntity);
					config.put("Global", 0);
					error.put("MessageCode", 0);
					error.put("Message", "edit new Competition successfull");
					System.out.println("Competition API - editCompetition - cp4");
				}
			}
			System.out.println("Competition API - editCompetition - has no exception");
		} catch (Exception e) {
			System.out.println("Competition API - editCompetition - has exception");
			result.put("Competition", competitionEntity);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "server error");
		}
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("Competition API - editCompetition - pass");
		return new ResponseEntity<Response>(response, httpStatus);
	}

	/* delete one Competition */
	@DeleteMapping("")
	public ResponseEntity<Response> deleteCompetition(@RequestParam("id") Long id) {
		System.out.println("Competition API - deleteCompetition - start");
		HttpStatus httpStatus = null;
		httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		CompetitionEntity competitionEntity = new CompetitionEntity();

		try {

			if (id == null) {// id is not exist
				System.out.println("Competition API - deleteCompetition - cp1");
				result.put("Competition", null);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "Required Id");
			} else {// id is exist
				competitionService.delete(id);

				result.put("Competition", competitionEntity);
				config.put("Global", 0);
				error.put("MessageCode", 0);
				error.put("Message", "Delete Competition successfull");
				System.out.println("Competition API - deleteCompetition - cp2");
			}

			System.out.println("Competition API - deleteCompetition - has no exception");
		} catch (Exception e) {
			System.out.println("Competition API - deleteCompetition - has exception");
			result.put("Competition", competitionEntity);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "server error");
		}
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("Competition API - deleteCompetition - pass");
		return new ResponseEntity<Response>(response, httpStatus);
	}
}
