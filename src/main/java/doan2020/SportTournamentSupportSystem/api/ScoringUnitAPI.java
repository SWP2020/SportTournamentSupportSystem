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

import doan2020.SportTournamentSupportSystem.converter.ScoringUnitConverter;
import doan2020.SportTournamentSupportSystem.dto.ScoringUnitDTO;
import doan2020.SportTournamentSupportSystem.response.Response;

@RestController
@CrossOrigin
@RequestMapping("/scoringUni")
public class ScoringUnitAPI {

	@Autowired
	private ScoringUnitConverter converter;

	@GetMapping("/getOne")
	public ResponseEntity<Response> getOneScoringUni(@RequestParam(value = "id") Long id) {
		System.out.println("ScoringUniAPI - getOneScoringUni");
		HttpStatus httpStatus = null;
		httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();

		try {
			System.out.println("ScoringUniAPI - cp1");
			result.put("ScoringUni", null);
			config.put("global", 0);
			error.put("messageCode", 0);
			error.put("message", "");
			System.out.println("ScoringUniAPI - cp2");
		} catch (Exception e) {
			System.out.println("ScoringUniAPI - exception");
			result.put("ScoringUni", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "");
		}

		System.out.println("ScoringUniAPI - cp3");
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("ScoringUniAPI - cp pass");
		return new ResponseEntity<Response>(response, httpStatus);

	}
	
	@GetMapping("/getAll")
	public ResponseEntity<Response> getAllScoringUni(@RequestParam(value = "page") Integer page) {
		System.out.println("ScoringUniAPI - getAllScoringUni");
		HttpStatus httpStatus = null;
		httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();

		try {
			System.out.println("ScoringUniAPI - cp1");
			result.put("ScoringUni", null);
			config.put("global", 0);
			error.put("messageCode", 0);
			error.put("message", "");
			System.out.println("ScoringUniAPI - cp2");
		} catch (Exception e) {
			System.out.println("ScoringUniAPI - exception");
			result.put("ScoringUni", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "");
		}

		System.out.println("ScoringUniAPI - cp3");
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("ScoringUniAPI - cp pass");
		return new ResponseEntity<Response>(response, httpStatus);

	}
	
	@PostMapping("")
	public ResponseEntity<Response> createScoringUni(@RequestBody ScoringUnitDTO ScoringUniDTO) {
		System.out.println("ScoringUniAPI - createScoringUni");
		HttpStatus httpStatus = null;
		httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();

		try {
			System.out.println("ScoringUniAPI - cp1");
			result.put("ScoringUni", null);
			config.put("global", 0);
			error.put("messageCode", 0);
			error.put("message", "");
			System.out.println("ScoringUniAPI - cp2");
		} catch (Exception e) {
			System.out.println("ScoringUniAPI - exception");
			result.put("ScoringUni", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "");
		}

		System.out.println("ScoringUniAPI - cp3");
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("ScoringUniAPI - cp pass");
		return new ResponseEntity<Response>(response, httpStatus);

	}
	
	@PutMapping("")
	public ResponseEntity<Response> editScoringUni(@RequestParam(value = "id") Long id) {
		System.out.println("ScoringUniAPI - editAPI");
		HttpStatus httpStatus = null;
		httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();

		try {
			System.out.println("ScoringUniAPI - cp1");
			result.put("ScoringUni", null);
			config.put("global", 0);
			error.put("messageCode", 0);
			error.put("message", "");
			System.out.println("ScoringUniAPI - cp2");
		} catch (Exception e) {
			System.out.println("ScoringUniAPI - exception");
			result.put("ScoringUni", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "");
		}

		System.out.println("ScoringUniAPI - cp3");
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("ScoringUniAPI - cp pass");
		return new ResponseEntity<Response>(response, httpStatus);

	}
	
	@DeleteMapping("")
	public ResponseEntity<Response> deleteScoringUni(@RequestParam(value = "id") Long id) {
		System.out.println("ScoringUniAPI - deleteScoringUni");
		HttpStatus httpStatus = null;
		httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();

		try {
			System.out.println("ScoringUniAPI - cp1");
			result.put("ScoringUni", null);
			config.put("global", 0);
			error.put("messageCode", 0);
			error.put("message", "");
			System.out.println("ScoringUniAPI - cp2");
		} catch (Exception e) {
			System.out.println("ScoringUniAPI - exception");
			result.put("ScoringUni", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "");
		}

		System.out.println("ScoringUniAPI - cp3");
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("ScoringUniAPI - cp pass");
		return new ResponseEntity<Response>(response, httpStatus);

	}

}
