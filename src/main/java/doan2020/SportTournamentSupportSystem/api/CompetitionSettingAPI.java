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
import doan2020.SportTournamentSupportSystem.converter.CompetitionSettingConverter;
import doan2020.SportTournamentSupportSystem.dto.CompetitionSettingDTO;
import doan2020.SportTournamentSupportSystem.dto.CompetitionSettingDTO;
import doan2020.SportTournamentSupportSystem.entity.CompetitionSettingEntity;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.ICompetitionSettingService;

@RestController
@CrossOrigin
@RequestMapping("/competitionSetting")
public class CompetitionSettingAPI {

	@Autowired
	private CompetitionSettingConverter converter;
	
	@Autowired
	private ICompetitionSettingService service;
	
	
	@GetMapping("")
	public ResponseEntity<Response> getCompetitionSetting(@RequestParam(value = "id", required = false) Long id) {
		System.out.println("CompetitionSettingAPI: getCompetitionSetting: no exception");
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		CompetitionSettingEntity competitionSettingEntity = new CompetitionSettingEntity();
		CompetitionSettingDTO competitionSettingDTO = new CompetitionSettingDTO();
		try {
			if (id == null) { // id null
				result.put("CompetitionSetting", null);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "Required param id");
			} else { // id not null
				
				competitionSettingEntity = service.findOneById(id);
				
				if (competitionSettingEntity == null) { // not found
					result.put("CompetitionSetting", null);
					config.put("Global", 0);
					error.put("MessageCode", 1);
					error.put("Message", "Not found");
				} else { // found
					
					competitionSettingDTO = converter.toDTO(competitionSettingEntity);
					
					result.put("CompetitionSetting", competitionSettingDTO);
					config.put("Global", 0);
					error.put("MessageCode", 0);
					error.put("Message", "Found");
				}
			}
			System.out.println("CompetitionSettingAPI: getCompetitionSetting: no exception");
		} catch (Exception e) {
			System.out.println("CompetitionSettingAPI: getCompetitionSetting: has exception");
			result.put("CompetitionSetting", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("CompetitionSettingAPI: getCompetitionSetting: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

	/*
	 * Tao moi mot CompetitionSetting
	 * 
	 */
	@PostMapping
	@CrossOrigin
	public ResponseEntity<Response> createCompetitionSetting(@RequestBody CompetitionSettingDTO newCompetitionSetting) {
		System.out.println("CompetitionSettingAPI: createCompetitionSetting: start");
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		CompetitionSettingEntity competitionSettingEntity = new CompetitionSettingEntity();
		
		try {
			competitionSettingEntity = converter.toEntity(newCompetitionSetting);
			
			competitionSettingEntity = service.create(competitionSettingEntity);
			
			CompetitionSettingDTO dto = converter.toDTO(competitionSettingEntity);

			result.put("CompetitionSetting", dto);
			config.put("Global", 0);
			error.put("MessageCode", 0);
			error.put("Message", "CompetitionSetting create successfuly");
			System.out.println("CompetitionSettingAPI: createCompetitionSetting: no exception");
		} catch (Exception e) {
			System.out.println("CompetitionSettingAPI: createCompetitionSetting: has exception");
			result.put("CompetitionSetting", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("CompetitionSettingAPI: createCompetitionSetting: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

	/*
	 * Edit mot CompetitionSetting
	 * 
	 */
	@PutMapping
	@CrossOrigin
	public ResponseEntity<Response> editCompetitionSetting(
			@RequestBody CompetitionSettingDTO competitionSetting,
			@RequestParam Long id) {
		System.out.println("CompetitionSettingAPI: editCompetitionSetting: start");
		
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		CompetitionSettingEntity competitionSettingEntity = new CompetitionSettingEntity();
		
		try {
			competitionSettingEntity = converter.toEntity(competitionSetting);
			
			competitionSettingEntity = service.update(id, competitionSettingEntity);
			
			CompetitionSettingDTO dto = converter.toDTO(competitionSettingEntity);

			result.put("CompetitionSetting", dto);
			config.put("Global", 0);
			error.put("MessageCode", 0);
			error.put("Message", "CompetitionSetting update successfuly");
			System.out.println("CompetitionSettingAPI: editCompetitionSetting: no exception");
		} catch (Exception e) {
			System.out.println("CompetitionSettingAPI: editCompetitionSetting: has exception");
			result.put("CompetitionSetting", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("CompetitionSettingAPI: editCompetitionSetting: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}
}
