package doan2020.SportTournamentSupportSystem.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import doan2020.SportTournamentSupportSystem.converter.SportConverter;
import doan2020.SportTournamentSupportSystem.dto.SportDTO;
import doan2020.SportTournamentSupportSystem.entity.SportEntity;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.ISportService;

@RestController
@CrossOrigin
@RequestMapping("/sports")
public class SportsAPI {
	
	@Autowired
	private SportConverter converter;
	
	@Autowired
	private ISportService service;
	
	@GetMapping("/getByScoringUnitId")
	public ResponseEntity<Response> getByScoringUnitId(@RequestParam(value = "scoringUnitId") Long scoringUnitId) {
		System.out.println("SportsAPI: getByScoringUnitId: no exception");
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		Collection<SportEntity> sportEntites = new ArrayList<SportEntity>();
		List<SportDTO> sportDTOs = new ArrayList<SportDTO>();
		try {

			if (scoringUnitId == null) {// userId null
				result.put("Sports", sportDTOs);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "Required param userId");
			} else {// userId not null

				sportEntites = service.findByScoringUnitId(scoringUnitId);

				if (sportEntites.isEmpty()) { // not found
					result.put("Sports", null);
					config.put("Global", 0);
					error.put("MessageCode", 1);
					error.put("Message", "Not found");
				} else { // found

					for (SportEntity entity : sportEntites) {
						SportDTO dto = converter.toDTO(entity);
						sportDTOs.add(dto);
					}

					result.put("Sports", sportDTOs);
					config.put("Global", 0);
					error.put("MessageCode", 0);
					error.put("Message", "Found");
				}
			}
			System.out.println("SportsAPI: getByScoringUnitId: no exception");
		} catch (Exception e) {
			System.out.println("SportsAPI: getByScoringUnitId: has exception");
			result.put("Sport", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("SportsAPI: getByScoringUnitId: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

	@GetMapping("")
	public ResponseEntity<Response> getAllSport() {
		System.out.println("SportsAPI: getAllSport: no exception");
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		Collection<SportEntity> sportEntites = new ArrayList<SportEntity>();
		SportDTO dto = new SportDTO();
		List<SportDTO> sportDTOs = new ArrayList<SportDTO>();
		try {

			sportEntites = service.findAll();

			if (sportEntites.isEmpty()) { // not found
				result.put("Sports", null);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "Not found");
			} else { // found

				for (SportEntity entity : sportEntites) {
					dto = converter.toDTO(entity);
					sportDTOs.add(dto);
				}

				result.put("Sports", sportDTOs);
				config.put("Global", 0);
				error.put("MessageCode", 0);
				error.put("Message", "Found");
			}
			System.out.println("SportsAPI: getAllSport: no exception");
		} catch (Exception e) {
			System.out.println("SportsAPI: getAllSport: has exception");
			result.put("Sport", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("SportsAPI: getAllSport: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

}
