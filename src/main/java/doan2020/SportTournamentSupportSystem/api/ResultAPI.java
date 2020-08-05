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

import doan2020.SportTournamentSupportSystem.converter.ResultConverter;
import doan2020.SportTournamentSupportSystem.dto.ResultDTO;
import doan2020.SportTournamentSupportSystem.response.Response;

@RestController
@CrossOrigin
@RequestMapping("/result")
public class ResultAPI {

	@Autowired
	private ResultConverter converter;

	@GetMapping("/getOne")
	public ResponseEntity<Response> getOneResult(@RequestParam(value = "id") Long id) {
		System.out.println("ResultAPI - getOneResult");
		HttpStatus httpStatus = null;
		httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();

		try {
			System.out.println("ResultAPI - cp1");
			result.put("Result", null);
			config.put("global", 0);
			error.put("messageCode", 0);
			error.put("message", "");
			System.out.println("ResultAPI - cp2");
		} catch (Exception e) {
			System.out.println("ResultAPI - exception");
			result.put("Result", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "");
		}

		System.out.println("ResultAPI - cp3");
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("ResultAPI - cp pass");
		return new ResponseEntity<Response>(response, httpStatus);

	}
	
	@GetMapping("/getAll")
	public ResponseEntity<Response> getAllResult(@RequestParam(value = "page") Integer page) {
		System.out.println("ResultAPI - getAllResult");
		HttpStatus httpStatus = null;
		httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();

		try {
			System.out.println("ResultAPI - cp1");
			result.put("Result", null);
			config.put("global", 0);
			error.put("messageCode", 0);
			error.put("message", "");
			System.out.println("ResultAPI - cp2");
		} catch (Exception e) {
			System.out.println("ResultAPI - exception");
			result.put("Result", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "");
		}

		System.out.println("ResultAPI - cp3");
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("ResultAPI - cp pass");
		return new ResponseEntity<Response>(response, httpStatus);

	}
	
	@PostMapping("")
	public ResponseEntity<Response> createResult(@RequestBody ResultDTO ResultDTO) {
		System.out.println("ResultAPI - createResult");
		HttpStatus httpStatus = null;
		httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();

		try {
			System.out.println("ResultAPI - cp1");
			result.put("Result", null);
			config.put("global", 0);
			error.put("messageCode", 0);
			error.put("message", "");
			System.out.println("ResultAPI - cp2");
		} catch (Exception e) {
			System.out.println("ResultAPI - exception");
			result.put("Result", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "");
		}

		System.out.println("ResultAPI - cp3");
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("ResultAPI - cp pass");
		return new ResponseEntity<Response>(response, httpStatus);

	}
	
	@PutMapping("")
	public ResponseEntity<Response> editResult(@RequestParam(value = "id") Long id) {
		System.out.println("ResultAPI - editAPI");
		HttpStatus httpStatus = null;
		httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();

		try {
			System.out.println("ResultAPI - cp1");
			result.put("Result", null);
			config.put("global", 0);
			error.put("messageCode", 0);
			error.put("message", "");
			System.out.println("ResultAPI - cp2");
		} catch (Exception e) {
			System.out.println("ResultAPI - exception");
			result.put("Result", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "");
		}

		System.out.println("ResultAPI - cp3");
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("ResultAPI - cp pass");
		return new ResponseEntity<Response>(response, httpStatus);

	}
	
	@DeleteMapping("")
	public ResponseEntity<Response> deleteResult(@RequestParam(value = "id") Long id) {
		System.out.println("ResultAPI - deleteResult");
		HttpStatus httpStatus = null;
		httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();

		try {
			System.out.println("ResultAPI - cp1");
			result.put("Result", null);
			config.put("global", 0);
			error.put("messageCode", 0);
			error.put("message", "");
			System.out.println("ResultAPI - cp2");
		} catch (Exception e) {
			System.out.println("ResultAPI - exception");
			result.put("Result", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "");
		}

		System.out.println("ResultAPI - cp3");
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("ResultAPI - cp pass");
		return new ResponseEntity<Response>(response, httpStatus);

	}

}
