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

import doan2020.SportTournamentSupportSystem.converter.CompetitionFormatConverter;
import doan2020.SportTournamentSupportSystem.dto.CompetitionFormatDTO;
import doan2020.SportTournamentSupportSystem.response.Response;

@RestController
@CrossOrigin
@RequestMapping("/competitionFormat")
public class CompetitionFormatAPI {

	@Autowired
	private CompetitionFormatConverter converter;

	@GetMapping("/getOne")
	public ResponseEntity<Response> getOneCompetitionFormat(@RequestParam(value = "id") Long id) {
		System.out.println("CompetitionFormatAPI - getOneCompetitionFormat");
		HttpStatus httpStatus = null;
		httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();

		try {
			System.out.println("CompetitionFormatAPI - cp1");
			result.put("CompetitionFormat", null);
			config.put("global", 0);
			error.put("messageCode", 0);
			error.put("message", "");
			System.out.println("CompetitionFormatAPI - cp2");
		} catch (Exception e) {
			System.out.println("CompetitionFormatAPI - exception");
			result.put("CompetitionFormat", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "");
		}

		System.out.println("ApiAPI - cp3");
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("ApiAPI - cp pass");
		return new ResponseEntity<Response>(response, httpStatus);

	}
	
	@GetMapping("/getAll")
	public ResponseEntity<Response> getAllCompetitionFormat(@RequestParam(value = "page") Integer page) {
		System.out.println("CompetitionFormatAPI - getAllCompetitionFormatAPI");
		HttpStatus httpStatus = null;
		httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();

		try {
			System.out.println("CompetitionFormatAPI - cp1");
			result.put("CompetitionFormat", null);
			config.put("global", 0);
			error.put("messageCode", 0);
			error.put("message", "");
			System.out.println("CompetitionFormatAPI - cp2");
		} catch (Exception e) {
			System.out.println("CompetitionFormatAPI - exception");
			result.put("AllCompetitionFormat", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "");
		}

		System.out.println("CompetitionFormatAPI - cp3");
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("CompetitionFormatAPI - cp pass");
		return new ResponseEntity<Response>(response, httpStatus);

	}
	
	@PostMapping("")
	public ResponseEntity<Response> createCompetitionFormat(@RequestBody CompetitionFormatDTO competitionFormatDTO) {
		System.out.println("CompetitionFormatAPI - createCompetitionFormat");
		HttpStatus httpStatus = null;
		httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();

		try {
			System.out.println("CompetitionFormatAPI - cp1");
			result.put("CompetitionFormat", null);
			config.put("global", 0);
			error.put("messageCode", 0);
			error.put("message", "");
			System.out.println("CompetitionFormatAPI - cp2");
		} catch (Exception e) {
			System.out.println("CompetitionFormatAPI - exception");
			result.put("CompetitionFormat", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "");
		}

		System.out.println("CompetitionFormatAPI - cp3");
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("CompetitionFormatAPI - cp pass");
		return new ResponseEntity<Response>(response, httpStatus);

	}
	
	@PutMapping("")
	public ResponseEntity<Response> editCompetitionFormat(@RequestParam(value = "id") Long id) {
		System.out.println("CompetitionFormatAPI - editCompetitionFormat");
		HttpStatus httpStatus = null;
		httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();

		try {
			System.out.println("CompetitionFormatAPI - cp1");
			result.put("CompetitionFormat", null);
			config.put("global", 0);
			error.put("messageCode", 0);
			error.put("message", "");
			System.out.println("CompetitionFormatAPI - cp2");
		} catch (Exception e) {
			System.out.println("CompetitionFormatAPI - exception");
			result.put("CompetitionFormat", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "");
		}

		System.out.println("CompetitionFormatAPI - cp3");
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("CompetitionFormatAPI - cp pass");
		return new ResponseEntity<Response>(response, httpStatus);

	}
	
	@DeleteMapping("")
	public ResponseEntity<Response> deleteCompetitionFormat(@RequestParam(value = "id") Long id) {
		System.out.println("CompetitionFormatAPI - deleteCompetitionFormat");
		HttpStatus httpStatus = null;
		httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();

		try {
			System.out.println("CompetitionFormatAPI - cp1");
			result.put("CompetitionFormat", null);
			config.put("global", 0);
			error.put("messageCode", 0);
			error.put("message", "");
			System.out.println("CompetitionFormatAPI - cp2");
		} catch (Exception e) {
			System.out.println("CompetitionFormatAPI - exception");
			result.put("CompetitionFormat", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "");
		}

		System.out.println("CompetitionFormatAPI - cp3");
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("CompetitionFormatAPI - cp pass");
		return new ResponseEntity<Response>(response, httpStatus);

	}
}
