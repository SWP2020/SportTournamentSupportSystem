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

import doan2020.SportTournamentSupportSystem.converter.CompetitionSettingConverter;
import doan2020.SportTournamentSupportSystem.dto.CompetitionSettingDTO;
import doan2020.SportTournamentSupportSystem.response.Response;

@RestController
@CrossOrigin
@RequestMapping("/competitionSetting")
public class CompetitionSettingAPI {

	@Autowired
	private CompetitionSettingConverter converter;
	
	@GetMapping("/getOne")
	public ResponseEntity<Response> getOneCompetitionSetting(@RequestParam(value = "id") Long id) {
		System.out.println("CompetitionSettingAPI - getOneCompetitionSetting");
		HttpStatus httpStatus = null;
		httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();

		try {
			System.out.println("CompetitionSettingAPI - cp1");
			result.put("CompetitionSetting", null);
			config.put("global", 0);
			error.put("messageCode", 0);
			error.put("message", "");
			System.out.println("CompetitionSettingAPI - cp2");
		} catch (Exception e) {
			System.out.println("CompetitionSettingAPI - exception");
			result.put("CompetitionSetting", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "");
		}

		System.out.println("CompetitionSettingAPI - cp3");
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("CompetitionSettingAPI - cp pass");
		return new ResponseEntity<Response>(response, httpStatus);

	}
	
	@GetMapping("/getAll")
	public ResponseEntity<Response> getAllCompetitionSetting(@RequestParam(value = "page") Integer page) {
		System.out.println("CompetitionSettingAPI - getAllCompetitionSetting");
		HttpStatus httpStatus = null;
		httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();

		try {
			System.out.println("CompetitionSettingAPI - cp1");
			result.put("CompetitionSetting", null);
			config.put("global", 0);
			error.put("messageCode", 0);
			error.put("message", "");
			System.out.println("CompetitionSettingAPI - cp2");
		} catch (Exception e) {
			System.out.println("CompetitionSettingAPI - exception");
			result.put("CompetitionSetting", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "");
		}

		System.out.println("CompetitionSettingAPI - cp3");
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("CompetitionSettingAPI - cp pass");
		return new ResponseEntity<Response>(response, httpStatus);

	}
	
	@PostMapping("")
	public ResponseEntity<Response> createCompetitionSetting(@RequestBody CompetitionSettingDTO competitionSettingDTO) {
		System.out.println("CompetitionSettingAPI - createCompetitionSetting");
		HttpStatus httpStatus = null;
		httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();

		try {
			System.out.println("CompetitionSettingAPI - cp1");
			result.put("CompetitionSetting", null);
			config.put("global", 0);
			error.put("messageCode", 0);
			error.put("message", "");
			System.out.println("CompetitionSettingAPI - cp2");
		} catch (Exception e) {
			System.out.println("CompetitionSettingAPI - exception");
			result.put("CompetitionSetting", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "");
		}

		System.out.println("CompetitionSettingAPI - cp3");
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("CompetitionSettingAPI - cp pass");
		return new ResponseEntity<Response>(response, httpStatus);

	}
	
	@DeleteMapping("")
	public ResponseEntity<Response> editCompetitionSetting(@RequestParam(value = "id") Long id) {
		System.out.println("CompetitionSettingAPI - editCompetitionSetting");
		HttpStatus httpStatus = null;
		httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();

		try {
			System.out.println("CompetitionSettingAPI - cp1");
			result.put("CompetitionSetting", null);
			config.put("global", 0);
			error.put("messageCode", 0);
			error.put("message", "");
			System.out.println("CompetitionSettingAPI - cp2");
		} catch (Exception e) {
			System.out.println("CompetitionSettingAPI - exception");
			result.put("CompetitionSetting", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "");
		}

		System.out.println("CompetitionSettingAPI - cp3");
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("CompetitionSettingAPI - cp pass");
		return new ResponseEntity<Response>(response, httpStatus);

	}
	
	@PutMapping("")
	public ResponseEntity<Response> deleteCompetitionFormat(@RequestParam(value = "id") Long id) {
		System.out.println("CompetitionSettingAPI - deleteCompetitionFormat");
		HttpStatus httpStatus = null;
		httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();

		try {
			System.out.println("CompetitionSettingAPI - cp1");
			result.put("CompetitionSetting", null);
			config.put("global", 0);
			error.put("messageCode", 0);
			error.put("message", "");
			System.out.println("CompetitionSettingAPI - cp2");
		} catch (Exception e) {
			System.out.println("CompetitionSettingAPI - exception");
			result.put("CompetitionSetting", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "");
		}

		System.out.println("CompetitionSettingAPI - cp3");
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("CompetitionSettingAPI - cp pass");
		return new ResponseEntity<Response>(response, httpStatus);

	}
}
