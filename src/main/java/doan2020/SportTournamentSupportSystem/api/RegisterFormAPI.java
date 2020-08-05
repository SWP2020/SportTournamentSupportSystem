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

import doan2020.SportTournamentSupportSystem.converter.RegisterFormConverter;
import doan2020.SportTournamentSupportSystem.dto.ApiDTO;
import doan2020.SportTournamentSupportSystem.response.Response;

@RestController
@CrossOrigin
@RequestMapping("/registerForm")
public class RegisterFormAPI {
	
	@Autowired
	private RegisterFormConverter converter;
	
	@GetMapping("/getOne")
	public ResponseEntity<Response> getOneRegisterForm(@RequestParam(value = "id") Long id) {
		System.out.println("RegisterFormAPI - getOneRegisterForm");
		HttpStatus httpStatus = null;
		httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();

		try {
			System.out.println("RegisterFormAPI - cp1");
			result.put("RegisterForm", null);
			config.put("global", 0);
			error.put("messageCode", 0);
			error.put("message", "");
			System.out.println("RegisterFormAPI - cp2");
		} catch (Exception e) {
			System.out.println("RegisterFormAPI - exception");
			result.put("RegisterForm", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "");
		}

		System.out.println("RegisterFormAPI - cp3");
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("RegisterFormAPI - cp pass");
		return new ResponseEntity<Response>(response, httpStatus);

	}
	
	@GetMapping("/getAll")
	public ResponseEntity<Response> getAllRegisterForm(@RequestParam(value = "page") Integer page) {
		System.out.println("RegisterFormAPI - getAllRegisterForm");
		HttpStatus httpStatus = null;
		httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();

		try {
			System.out.println("RegisterFormAPI - cp1");
			result.put("RegisterForm", null);
			config.put("global", 0);
			error.put("messageCode", 0);
			error.put("message", "");
			System.out.println("RegisterFormAPI - cp2");
		} catch (Exception e) {
			System.out.println("RegisterFormAPI - exception");
			result.put("RegisterForm", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "");
		}

		System.out.println("RegisterFormAPI - cp3");
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("RegisterFormAPI - cp pass");
		return new ResponseEntity<Response>(response, httpStatus);

	}
	
	@PostMapping("")
	public ResponseEntity<Response> createRegisterForm(@RequestBody ApiDTO apiDTO) {
		System.out.println("RegisterFormAPI - createRegisterForm");
		HttpStatus httpStatus = null;
		httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();

		try {
			System.out.println("RegisterFormAPI - cp1");
			result.put("RegisterForm", null);
			config.put("global", 0);
			error.put("messageCode", 0);
			error.put("message", "");
			System.out.println("RegisterFormAPI - cp2");
		} catch (Exception e) {
			System.out.println("RegisterFormAPI - exception");
			result.put("RegisterForm", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "");
		}

		System.out.println("RegisterFormAPI - cp3");
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("RegisterFormAPI - cp pass");
		return new ResponseEntity<Response>(response, httpStatus);

	}
	
	@PutMapping("")
	public ResponseEntity<Response> editRegisterForm(@RequestParam(value = "id") Long id) {
		System.out.println("RegisterFormAPI - editRegisterForm");
		HttpStatus httpStatus = null;
		httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();

		try {
			System.out.println("RegisterFormAPI - cp1");
			result.put("RegisterForm", null);
			config.put("global", 0);
			error.put("messageCode", 0);
			error.put("message", "");
			System.out.println("RegisterFormAPI - cp2");
		} catch (Exception e) {
			System.out.println("RegisterFormAPI - exception");
			result.put("RegisterForm", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "");
		}

		System.out.println("RegisterFormAPI - cp3");
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("RegisterFormAPI - cp pass");
		return new ResponseEntity<Response>(response, httpStatus);

	}
	
	@DeleteMapping("")
	public ResponseEntity<Response> deleteApi(@RequestParam(value = "id") Long id) {
		System.out.println("RegisterFormAPI - deleteRegisterForm");
		HttpStatus httpStatus = null;
		httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();

		try {
			System.out.println("RegisterFormAPI - cp1");
			result.put("RegisterForm", null);
			config.put("global", 0);
			error.put("messageCode", 0);
			error.put("message", "");
			System.out.println("RegisterFormAPI - cp2");
		} catch (Exception e) {
			System.out.println("RegisterFormAPI - exception");
			result.put("RegisterForm", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "");
		}

		System.out.println("RegisterFormAPI - cp3");
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("RegisterFormAPI - cp pass");
		return new ResponseEntity<Response>(response, httpStatus);

	}

}
