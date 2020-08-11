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
import doan2020.SportTournamentSupportSystem.converter.ScoringUnitConverter;
import doan2020.SportTournamentSupportSystem.dto.ScoringUnitDTO;
import doan2020.SportTournamentSupportSystem.dto.ScoringUnitDTO;
import doan2020.SportTournamentSupportSystem.entity.ScoringUnitEntity;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.IScoringUnitService;

@RestController
@CrossOrigin
@RequestMapping("/scoringUnit")
public class ScoringUnitAPI {

	@Autowired
	private ScoringUnitConverter converter;
	
	@Autowired
	private IScoringUnitService service;
	
	
	@GetMapping("")
	public ResponseEntity<Response> getScoringUnit(@RequestParam(value = "id", required = false) Long id) {
		System.out.println("ScoringUnitAPI: getScoringUnit: no exception");
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		ScoringUnitEntity scoringUnitEntity = new ScoringUnitEntity();
		ScoringUnitDTO scoringUnitDTO = new ScoringUnitDTO();
		try {
			if (id == null) { // id null
				result.put("ScoringUnit", null);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "Required param id");
			} else { // id not null
				
				scoringUnitEntity = service.findOneById(id);
				
				if (scoringUnitEntity == null) { // not found
					result.put("ScoringUnit", null);
					config.put("Global", 0);
					error.put("MessageCode", 1);
					error.put("Message", "Not found");
				} else { // found
					
					scoringUnitDTO = converter.toDTO(scoringUnitEntity);
					
					result.put("ScoringUnit", scoringUnitDTO);
					config.put("Global", 0);
					error.put("MessageCode", 0);
					error.put("Message", "Found");
				}
			}
			System.out.println("ScoringUnitAPI: getScoringUnit: no exception");
		} catch (Exception e) {
			System.out.println("ScoringUnitAPI: getScoringUnit: has exception");
			result.put("ScoringUnit", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("ScoringUnitAPI: getScoringUnit: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

	/*
	 * Tao moi mot ScoringUnit
	 * 
	 */
	@PostMapping
	@CrossOrigin
	public ResponseEntity<Response> createScoringUnit(@RequestBody ScoringUnitDTO newScoringUnit) {
		System.out.println("ScoringUnitAPI: createScoringUnit: start");
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		ScoringUnitEntity scoringUnitEntity = new ScoringUnitEntity();
		
		try {
			scoringUnitEntity = converter.toEntity(newScoringUnit);
			
			scoringUnitEntity = service.create(scoringUnitEntity);
			
			ScoringUnitDTO dto = converter.toDTO(scoringUnitEntity);

			result.put("ScoringUnit", dto);
			config.put("Global", 0);
			error.put("MessageCode", 0);
			error.put("Message", "ScoringUnit create successfuly");
			System.out.println("ScoringUnitAPI: createScoringUnit: no exception");
		} catch (Exception e) {
			System.out.println("ScoringUnitAPI: createScoringUnit: has exception");
			result.put("ScoringUnit", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("ScoringUnitAPI: createScoringUnit: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

	/*
	 * Edit mot ScoringUnit
	 * 
	 */
	@PutMapping
	@CrossOrigin
	public ResponseEntity<Response> editScoringUnit(
			@RequestBody ScoringUnitDTO scoringUnit,
			@RequestParam Long id) {
		System.out.println("ScoringUnitAPI: editScoringUnit: start");
		
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		ScoringUnitEntity scoringUnitEntity = new ScoringUnitEntity();
		
		try {
			scoringUnitEntity = converter.toEntity(scoringUnit);
			
			scoringUnitEntity = service.update(id, scoringUnitEntity);
			
			ScoringUnitDTO dto = converter.toDTO(scoringUnitEntity);

			result.put("ScoringUnit", dto);
			config.put("Global", 0);
			error.put("MessageCode", 0);
			error.put("Message", "ScoringUnit update successfuly");
			System.out.println("ScoringUnitAPI: editScoringUnit: no exception");
		} catch (Exception e) {
			System.out.println("ScoringUnitAPI: editScoringUnit: has exception");
			result.put("ScoringUnit", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("ScoringUnitAPI: editScoringUnit: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

}
