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
import doan2020.SportTournamentSupportSystem.converter.CompetitionFormatConverter;
import doan2020.SportTournamentSupportSystem.dto.CompetitionFormatDTO;
import doan2020.SportTournamentSupportSystem.dto.CompetitionFormatDTO;
import doan2020.SportTournamentSupportSystem.entity.CompetitionFormatEntity;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.ICompetitionFormatService;

@RestController
@CrossOrigin
@RequestMapping("/competitionFormat")
public class CompetitionFormatAPI {

	@Autowired
	private CompetitionFormatConverter converter;
	
	@Autowired
	private ICompetitionFormatService service;
	
	
	@GetMapping("")
	public ResponseEntity<Response> getCompetitionFormat(@RequestParam(value = "id", required = false) Long id) {
		System.out.println("CompetitionFormatAPI: getCompetitionFormat: no exception");
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		CompetitionFormatEntity competitionFormatEntity = new CompetitionFormatEntity();
		CompetitionFormatDTO competitionFormatDTO = new CompetitionFormatDTO();
		try {
			if (id == null) { // id null
				result.put("CompetitionFormat", null);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "Required param id");
			} else { // id not null
				
				competitionFormatEntity = service.findOneById(id);
				
				if (competitionFormatEntity == null) { // not found
					result.put("CompetitionFormat", null);
					config.put("Global", 0);
					error.put("MessageCode", 1);
					error.put("Message", "Not found");
				} else { // found
					
					competitionFormatDTO = converter.toDTO(competitionFormatEntity);
					
					result.put("CompetitionFormat", competitionFormatDTO);
					config.put("Global", 0);
					error.put("MessageCode", 0);
					error.put("Message", "Found");
				}
			}
			System.out.println("CompetitionFormatAPI: getCompetitionFormat: no exception");
		} catch (Exception e) {
			System.out.println("CompetitionFormatAPI: getCompetitionFormat: has exception");
			result.put("CompetitionFormat", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("CompetitionFormatAPI: getCompetitionFormat: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

	/*
	 * Tao moi mot CompetitionFormat
	 * 
	 */
	@PostMapping
	@CrossOrigin
	public ResponseEntity<Response> createCompetitionFormat(@RequestBody CompetitionFormatDTO newCompetitionFormat) {
		System.out.println("CompetitionFormatAPI: createCompetitionFormat: start");
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		CompetitionFormatEntity competitionFormatEntity = new CompetitionFormatEntity();
		
		try {
			competitionFormatEntity = converter.toEntity(newCompetitionFormat);
			
			competitionFormatEntity = service.create(competitionFormatEntity);
			
			CompetitionFormatDTO dto = converter.toDTO(competitionFormatEntity);

			result.put("CompetitionFormat", dto);
			config.put("Global", 0);
			error.put("MessageCode", 0);
			error.put("Message", "CompetitionFormat create successfuly");
			System.out.println("CompetitionFormatAPI: createCompetitionFormat: no exception");
		} catch (Exception e) {
			System.out.println("CompetitionFormatAPI: createCompetitionFormat: has exception");
			result.put("CompetitionFormat", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("CompetitionFormatAPI: createCompetitionFormat: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

	/*
	 * Edit mot CompetitionFormat
	 * 
	 */
	@PutMapping
	@CrossOrigin
	public ResponseEntity<Response> editCompetitionFormat(
			@RequestBody CompetitionFormatDTO competitionFormat,
			@RequestParam Long id) {
		System.out.println("CompetitionFormatAPI: editCompetitionFormat: start");
		
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		CompetitionFormatEntity competitionFormatEntity = new CompetitionFormatEntity();
		
		try {
			competitionFormatEntity = converter.toEntity(competitionFormat);
			
			competitionFormatEntity = service.update(id, competitionFormatEntity);
			
			CompetitionFormatDTO dto = converter.toDTO(competitionFormatEntity);

			result.put("CompetitionFormat", dto);
			config.put("Global", 0);
			error.put("MessageCode", 0);
			error.put("Message", "CompetitionFormat update successfuly");
			System.out.println("CompetitionFormatAPI: editCompetitionFormat: no exception");
		} catch (Exception e) {
			System.out.println("CompetitionFormatAPI: editCompetitionFormat: has exception");
			result.put("CompetitionFormat", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("CompetitionFormatAPI: editCompetitionFormat: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}
}
