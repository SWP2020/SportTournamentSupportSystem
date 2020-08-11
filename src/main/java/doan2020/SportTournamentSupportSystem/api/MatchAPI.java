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
import doan2020.SportTournamentSupportSystem.dto.MatchDTO;
import doan2020.SportTournamentSupportSystem.entity.MatchEntity;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.IMatchService;

@RestController
@CrossOrigin
@RequestMapping("/matches")
public class MatchAPI {
	@Autowired
	private MatchConverter converter;
	
	@Autowired
	private IMatchService service;
	
	
	@GetMapping("")
	public ResponseEntity<Response> getMatch(@RequestParam(value = "id", required = false) Long id) {
		System.out.println("MatchAPI: getMatch: no exception");
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		MatchEntity matchEntity = new MatchEntity();
		MatchDTO matchDTO = new MatchDTO();
		try {
			if (id == null) { // id null
				result.put("Match", null);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "Required param id");
			} else { // id not null
				
				matchEntity = service.findOneById(id);
				
				if (matchEntity == null) { // not found
					result.put("Match", null);
					config.put("Global", 0);
					error.put("MessageCode", 1);
					error.put("Message", "Not found");
				} else { // found
					
					matchDTO = converter.toDTO(matchEntity);
					
					result.put("Match", matchDTO);
					config.put("Global", 0);
					error.put("MessageCode", 0);
					error.put("Message", "Found");
				}
			}
			System.out.println("MatchAPI: getMatch: no exception");
		} catch (Exception e) {
			System.out.println("MatchAPI: getMatch: has exception");
			result.put("Match", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("MatchAPI: getMatch: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

	/*
	 * Tao moi mot Match
	 * 
	 */
	@PostMapping
	@CrossOrigin
	public ResponseEntity<Response> createMatch(@RequestBody MatchDTO newMatch) {
		System.out.println("MatchAPI: createMatch: start");
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		MatchEntity matchEntity = new MatchEntity();
		
		try {
			matchEntity = converter.toEntity(newMatch);
			
			matchEntity = service.create(matchEntity);
			
			MatchDTO dto = converter.toDTO(matchEntity);

			result.put("Match", dto);
			config.put("Global", 0);
			error.put("MessageCode", 0);
			error.put("Message", "Match create successfuly");
			System.out.println("MatchAPI: createMatch: no exception");
		} catch (Exception e) {
			System.out.println("MatchAPI: createMatch: has exception");
			result.put("Match", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("MatchAPI: createMatch: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

	/*
	 * Edit mot Match
	 * 
	 */
	@PutMapping
	@CrossOrigin
	public ResponseEntity<Response> editMatch(
			@RequestBody MatchDTO match,
			@RequestParam Long id) {
		System.out.println("MatchAPI: editMatch: start");
		
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		MatchEntity matchEntity = new MatchEntity();
		
		try {
			matchEntity = converter.toEntity(match);
			
			matchEntity = service.update(id, matchEntity);
			
			MatchDTO dto = converter.toDTO(matchEntity);

			result.put("Match", dto);
			config.put("Global", 0);
			error.put("MessageCode", 0);
			error.put("Message", "Match update successfuly");
			System.out.println("MatchAPI: editMatch: no exception");
		} catch (Exception e) {
			System.out.println("MatchAPI: editMatch: has exception");
			result.put("Match", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("MatchAPI: editMatch: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}
}
