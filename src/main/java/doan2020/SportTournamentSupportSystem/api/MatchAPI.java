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

import doan2020.SportTournamentSupportSystem.converter.MatchConverter;
import doan2020.SportTournamentSupportSystem.dtOut.MatchDtOut;
import doan2020.SportTournamentSupportSystem.entity.MatchEntity;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.IMatchService;

@RestController
@RequestMapping("/matches")
public class MatchAPI {
	@Autowired
	private IMatchService service;

	@Autowired
	private MatchConverter converter;

	/*
	 * Get match theo id
	 */
	@GetMapping
	public ResponseEntity<Response> getMatch(@RequestParam(value = "id", required = true) Long id) {
		System.out.println("getMatch");
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
//		System.out.println("2");

		if (id == null) {
			result.put("match", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "Required Match id");
			httpStatus = HttpStatus.OK;
			response.setConfig(config);
			response.setResult(result);
			response.setError(error);
			return new ResponseEntity<Response>(response, httpStatus);
		}

		MatchEntity res;


		res = service.findById(id);

		try {
			MatchDtOut resDTO = converter.toDTO(res);

			result.put("Match", resDTO);
			config.put("global", 0);
			error.put("messageCode", 0);
			error.put("message", "Found");

		} catch (Exception e) {
			result.put("Match", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "Tournament is not exist");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);

		return new ResponseEntity<Response>(response, httpStatus);
	}

	/*
	 * Tao moi mot Tournament
	 * 
	 */
	@PostMapping
	@CrossOrigin
	public ResponseEntity<Response> createMatch(@RequestBody Map<String, Object> newMatch) {
		System.out.println("createMatch");
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		try {
			MatchEntity matchEntity = converter.toEntity(newMatch);
			System.out.println("convert OK");
			service.addOne(matchEntity);
			System.out.println("add OK");
			MatchDtOut dto = converter.toDTO(matchEntity);
			System.out.println("convert OK");
			result.put("match", dto);
			config.put("global", 0);
			error.put("messageCode", 0);
			error.put("message", "Match create successfuly");
		} catch (Exception e) {
			result.put("Match", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "Match create fail");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		return new ResponseEntity<Response>(response, httpStatus);
	}

	/*
	 * Edit mot Match
	 * 
	 */
	@PutMapping
	@CrossOrigin
	public ResponseEntity<Response> editMatch(@RequestBody Map<String, Object> Match, @RequestParam Long id) {
		System.out.println("editMatch");
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		try {
			MatchEntity MatchEntity = converter.toEntity(Match);
			System.out.println("convert OK");
			MatchEntity newMatch = service.update(id, MatchEntity);
			System.out.println("add OK");
			MatchDtOut dto = converter.toDTO(newMatch);
			System.out.println("convert OK");
			result.put("match", dto);
			config.put("global", 0);
			error.put("messageCode", 0);
			error.put("message", "Match update successfuly");
		} catch (Exception e) {
			result.put("match", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "Match update fail");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		return new ResponseEntity<Response>(response, httpStatus);
	}
}
