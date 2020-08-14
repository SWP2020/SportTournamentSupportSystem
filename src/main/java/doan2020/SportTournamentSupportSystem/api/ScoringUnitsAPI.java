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
import org.springframework.web.bind.annotation.RestController;

import doan2020.SportTournamentSupportSystem.converter.ScoringUnitConverter;
import doan2020.SportTournamentSupportSystem.dto.ScoringUnitDTO;
import doan2020.SportTournamentSupportSystem.entity.ScoringUnitEntity;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.IScoringUnitService;

@RestController
@CrossOrigin
@RequestMapping("/scoringUnits")
public class ScoringUnitsAPI {

	@Autowired
	private ScoringUnitConverter converter;
	
	@Autowired
	private IScoringUnitService service;
	
	@GetMapping("")
	public ResponseEntity<Response> getAllScoringUnit() {
		System.out.println("ScoringUnitsAPI: getAllScoringUnit: no exception");
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		Collection<ScoringUnitEntity> scoringUnitEntites = new ArrayList<ScoringUnitEntity>();
		ScoringUnitDTO dto = new ScoringUnitDTO();
		List<ScoringUnitDTO> matchDTOs = new ArrayList<ScoringUnitDTO>();
		try {

			scoringUnitEntites = service.findAll();

			if (scoringUnitEntites.isEmpty()) { // not found
				result.put("ScoringUnits", null);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "Not found");
			} else { // found

				for (ScoringUnitEntity entity : scoringUnitEntites) {
					dto = converter.toDTO(entity);
					matchDTOs.add(dto);
				}

				result.put("ScoringUnits", matchDTOs);
				config.put("Global", 0);
				error.put("MessageCode", 0);
				error.put("Message", "Found");
			}
			System.out.println("ScoringUnitsAPI: getAllScoringUnit: no exception");
		} catch (Exception e) {
			System.out.println("ScoringUnitsAPI: getAllScoringUnit: has exception");
			result.put("ScoringUnit", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("ScoringUnitsAPI: getAllScoringUnit: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

}
