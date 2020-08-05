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

import doan2020.SportTournamentSupportSystem.converter.PermissionConverter;
import doan2020.SportTournamentSupportSystem.dto.PermissionDTO;
import doan2020.SportTournamentSupportSystem.response.Response;

@RestController
@CrossOrigin
@RequestMapping("/permission")
public class PermissionAPI {
	
	@Autowired
	private PermissionConverter converter;
	
	@GetMapping("/getOne")
	public ResponseEntity<Response> getOnePermission(@RequestParam(value = "id") Long id) {
		System.out.println("PermissionAPI - getOnePermission");
		HttpStatus httpStatus = null;
		httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();

		try {
			System.out.println("PermissionAPI - cp1");
			result.put("Permission", null);
			config.put("global", 0);
			error.put("messageCode", 0);
			error.put("message", "");
			System.out.println("PermissionAPI - cp2");
		} catch (Exception e) {
			System.out.println("PermissionAPI - exception");
			result.put("Permission", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "");
		}

		System.out.println("PermissionAPI - cp3");
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("PermissionAPI - cp pass");
		return new ResponseEntity<Response>(response, httpStatus);

	}
	
	@GetMapping("/getAll")
	public ResponseEntity<Response> getAllApi(@RequestParam(value = "page") Integer page) {
		System.out.println("PermissionAPI - getAllPermission");
		HttpStatus httpStatus = null;
		httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();

		try {
			System.out.println("PermissionAPI - cp1");
			result.put("Permission", null);
			config.put("global", 0);
			error.put("messageCode", 0);
			error.put("message", "");
			System.out.println("PermissionAPI - cp2");
		} catch (Exception e) {
			System.out.println("PermissionAPI - exception");
			result.put("Permission", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "");
		}

		System.out.println("PermissionAPI - cp3");
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("PermissionAPI - cp pass");
		return new ResponseEntity<Response>(response, httpStatus);

	}
	
	@PostMapping("")
	public ResponseEntity<Response> createPermission(@RequestBody PermissionDTO PermissionDTO) {
		System.out.println("PermissionAPI - createPermission");
		HttpStatus httpStatus = null;
		httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();

		try {
			System.out.println("PermissionAPI - cp1");
			result.put("Permission", null);
			config.put("global", 0);
			error.put("messageCode", 0);
			error.put("message", "");
			System.out.println("ApiAPI - cp2");
		} catch (Exception e) {
			System.out.println("PermissionAPI - exception");
			result.put("Permission", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "");
		}

		System.out.println("PermissionAPI - cp3");
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("PermissionAPI - cp pass");
		return new ResponseEntity<Response>(response, httpStatus);

	}
	
	@PutMapping("")
	public ResponseEntity<Response> editPermission(@RequestParam(value = "id") Long id) {
		System.out.println("PermissionAPI - editPermission");
		HttpStatus httpStatus = null;
		httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();

		try {
			System.out.println("PermissionAPI - cp1");
			result.put("Permission", null);
			config.put("global", 0);
			error.put("messageCode", 0);
			error.put("message", "");
			System.out.println("PermissionAPI - cp2");
		} catch (Exception e) {
			System.out.println("PermissionAPI - exception");
			result.put("Permission", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "");
		}

		System.out.println("PermissionAPI - cp3");
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("PermissionAPI - cp pass");
		return new ResponseEntity<Response>(response, httpStatus);

	}
	
	@DeleteMapping("")
	public ResponseEntity<Response> deletePermission(@RequestParam(value = "id") Long id) {
		System.out.println("PermissionAPI - deletePermission");
		HttpStatus httpStatus = null;
		httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();

		try {
			System.out.println("PermissionAPI - cp1");
			result.put("Permission", null);
			config.put("global", 0);
			error.put("messageCode", 0);
			error.put("message", "");
			System.out.println("PermissionAPI - cp2");
		} catch (Exception e) {
			System.out.println("PermissionAPI - exception");
			result.put("Permission", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "");
		}

		System.out.println("PermissionAPI - cp3");
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("PermissionAPI - cp pass");
		return new ResponseEntity<Response>(response, httpStatus);

	}

}
