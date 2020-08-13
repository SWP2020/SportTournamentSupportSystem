package doan2020.SportTournamentSupportSystem.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import doan2020.SportTournamentSupportSystem.converter.ResultConverter;
import doan2020.SportTournamentSupportSystem.dto.ResultDTO;
import doan2020.SportTournamentSupportSystem.entity.ResultEntity;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.IResultService;

@RestController
@CrossOrigin
@RequestMapping("/results")
public class ResultsAPI {

	@Autowired
	private ResultConverter converter;
	
	@Autowired
	private IResultService service;

	
	@GetMapping("")
	public ResponseEntity<Response> getAllResult(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit) {
		System.out.println("ResultsAPI: getAllResult: start");

		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		HttpStatus httpStatus = HttpStatus.OK;

		Collection<ResultEntity> findPage = new ArrayList<>();
		List<ResultDTO> findPageDTO = new ArrayList<>();

		try {
			if (limit == null)
				limit = 10;
			if (limit == 0)
				limit = 10;
			if (page == null)
				page = 1;

			Pageable pageable = PageRequest.of(page - 1, limit);
			findPage = service.findAll(pageable);

			for (ResultEntity entity : findPage) {
				ResultDTO dto = converter.toDTO(entity);
				findPageDTO.add(dto);
			}

			result.put("Results", findPageDTO);
			error.put("MessageCode", 0);
			error.put("Message", "Get page successfully");

			System.out.println("ResultsAPI: getAllResult: no exception");
		} catch (Exception e) {
			System.out.println("ResultsAPI: getAllResult: has exception");
			result.put("Users", findPageDTO);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("ResultsAPI: getAllResult: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}
	
	@GetMapping("/getByMatchId")
	public ResponseEntity<Response> getByMatchId(@RequestParam(value = "matchId") Long matchId) {
		System.out.println("ResultsAPI: getByMatchId: no exception");
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		Collection<ResultEntity> resultEntites = new ArrayList<ResultEntity>();
		List<ResultDTO> resultDTOs = new ArrayList<ResultDTO>();
		try {

			if (matchId == null) {// matchId null
				result.put("Results", resultDTOs);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "Required param matchId");
			} else {// matchId not null

				resultEntites = service.findByMatchId(matchId);
				if (resultEntites.isEmpty()) { // not found
					result.put("Results", null);
					config.put("Global", 0);
					error.put("MessageCode", 1);
					error.put("Message", "Not found");
				} else { // found

					for (ResultEntity entity : resultEntites) {
						ResultDTO dto = converter.toDTO(entity);
						resultDTOs.add(dto);
					}

					result.put("Results", resultDTOs);
					config.put("Global", 0);
					error.put("MessageCode", 0);
					error.put("Message", "Found");
				}
			}
			System.out.println("ResultsAPI: getByMatchId: no exception");
		} catch (Exception e) {
			System.out.println("ResultsAPI: getByMatchId: has exception");
			result.put("Result", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("ResultsAPI: getByMatchId: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}
	
	@GetMapping("/getByTeamId")
	public ResponseEntity<Response> getByTeamId(@RequestParam(value = "teamId") Long teamId) {
		System.out.println("ResultsAPI: getByTeamId: no exception");
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		Collection<ResultEntity> resultEntites = new ArrayList<ResultEntity>();
		List<ResultDTO> resultDTOs = new ArrayList<ResultDTO>();
		try {

			if (teamId == null) {// teamId null
				result.put("Results", resultDTOs);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "Required param teamId");
			} else {// teamId not null

				resultEntites = service.findByTeamId(teamId);

				if (resultEntites.isEmpty()) { // not found
					result.put("Results", null);
					config.put("Global", 0);
					error.put("MessageCode", 1);
					error.put("Message", "Not found");
				} else { // found

					for (ResultEntity entity : resultEntites) {
						ResultDTO dto = converter.toDTO(entity);
						resultDTOs.add(dto);
					}

					result.put("Results", resultDTOs);
					config.put("Global", 0);
					error.put("MessageCode", 0);
					error.put("Message", "Found");
				}
			}
			System.out.println("ResultsAPI: getByTeamId: no exception");
		} catch (Exception e) {
			System.out.println("ResultsAPI: getByTeamId: has exception");
			result.put("Result", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("ResultsAPI: getByTeamId: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}
}
