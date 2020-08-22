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

import doan2020.SportTournamentSupportSystem.converter.CompetitionFormatConverter;
import doan2020.SportTournamentSupportSystem.dto.CompetitionFormatDTO;
import doan2020.SportTournamentSupportSystem.entity.CompetitionFormatEntity;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.ICompetitionFormatService;

@RestController
@CrossOrigin
@RequestMapping("/competitionFormats")
public class CompetitionFormatsAPI {

	@Autowired
	private CompetitionFormatConverter converter;
	
	@Autowired
	private ICompetitionFormatService service;
	
	@GetMapping("")
	public ResponseEntity<Response> getAllCompetitionFormat() {
		System.out.println("CompetitionFormatsAPI: getAllCompetitionFormat: start");

		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		HttpStatus httpStatus = HttpStatus.OK;

		Collection<CompetitionFormatEntity> findPage = new ArrayList<>();
		List<CompetitionFormatDTO> findPageDTO = new ArrayList<>();

		try {
			findPage = service.findAll();

			for (CompetitionFormatEntity entity : findPage) {
				CompetitionFormatDTO dto = converter.toDTO(entity);
				findPageDTO.add(dto);
			}

			result.put("CompetitionSettings", findPageDTO);
			error.put("MessageCode", 0);
			error.put("Message", "Get page successfully");

			System.out.println("CompetitionFormatsAPI: getAllCompetitionFormat: no exception");
		} catch (Exception e) {
			System.out.println("CompetitionFormatsAPI: getAllCompetitionFormat: has exception");
			result.put("Users", findPageDTO);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("CompetitionFormatsAPI: getAllCompetitionFormat: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}
}
