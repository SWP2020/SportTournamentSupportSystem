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

import doan2020.SportTournamentSupportSystem.converter.CompetitionConverter;
import doan2020.SportTournamentSupportSystem.dto.CompetitionDTO;
import doan2020.SportTournamentSupportSystem.entity.CompetitionEntity;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.ICompetitionService;

@RestController
@CrossOrigin
@RequestMapping("/competitions")
public class CompetitionsAPI {

	@Autowired
	private ICompetitionService CompetitionService;

	@Autowired
	private CompetitionConverter competitionConverter;

	/*
	 * ---------------- find all Competition by TournamentId
	 * ------------------------
	 */
	@GetMapping("")
	public ResponseEntity<Response> getAllCompetiton(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit) {
		System.out.println("CompetitionsAPI: getAllCompetiton: start");

		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		HttpStatus httpStatus = HttpStatus.OK;

		Collection<CompetitionEntity> findPage = new ArrayList<>();
		List<CompetitionDTO> findPageDTO = new ArrayList<>();

		try {
			if (limit == null)
				limit = 3;
			if (limit == 0)
				limit = 3;
			if (page == null)
				page = 1;

			Pageable pageable = PageRequest.of(page - 1, limit);
			findPage = CompetitionService.findAll(pageable);

			for (CompetitionEntity entity : findPage) {
				CompetitionDTO dto = competitionConverter.toDTO(entity);
				findPageDTO.add(dto);
			}

			result.put("Competitions", findPageDTO);
			error.put("MessageCode", 0);
			error.put("Message", "Get page successfully");

			System.out.println("CompetitionsAPI: getAllCompetiton: no exception");
		} catch (Exception e) {
			System.out.println("CompetitionsAPI: getAllCompetiton: has exception");
			result.put("Users", findPageDTO);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("CompetitionsAPI: getAllCompetiton: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

	/*
	 * ---------------- find all Competition by TournamentId
	 * ------------------------
	 */
	@GetMapping("/getByTournamentId")
	public ResponseEntity<Response> getAllCompetitonByTournamentId(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit, @RequestParam("tournamentId") Long tournamentId,
			@RequestParam("sportId") Long sportId) {
		System.out.println("CompetitionsAPI: getAllCompetitonByTournamentId: start");

		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		HttpStatus httpStatus = HttpStatus.OK;

		Collection<CompetitionEntity> findPage = new ArrayList<>();
		List<CompetitionDTO> findPageDTO = new ArrayList<>();

		try {
			if (limit == null)
				limit = 3;
			if (limit == 0)
				limit = 3;
			if (page == null)
				page = 1;

			Pageable pageable = PageRequest.of(page - 1, limit);
			findPage = CompetitionService.findByTournamentId(pageable, tournamentId);

			for (CompetitionEntity entity : findPage) {
				CompetitionDTO dto = competitionConverter.toDTO(entity);
				findPageDTO.add(dto);
			}

			result.put("Competitions", findPageDTO);
			error.put("MessageCode", 0);
			error.put("Message", "Get page successfully");

			System.out.println("CompetitionsAPI: getAllCompetitonByTournamentId: no exception");
		} catch (Exception e) {
			System.out.println("CompetitionsAPI: getAllCompetitonByTournamentId: has exception");
			result.put("Users", findPageDTO);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("CompetitionsAPI: getAllCompetitonByTournamentId: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}
	
	/*
	 * ---------------- find all Competition by TournamentId and SportId
	 * ------------------------
	 */
	@GetMapping("/getByTournamentIdAndSportId")
	public ResponseEntity<Response> getAllByTournamentIdAndSportId(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit, @RequestParam("tournamentId") Long tournamentId,
			@RequestParam("sportId") Long sportId) {
		System.out.println("CompetitionsAPI: getAllCompetitonByTournamentId: start");

		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		HttpStatus httpStatus = HttpStatus.OK;

		Collection<CompetitionEntity> findPage = new ArrayList<>();
		List<CompetitionDTO> findPageDTO = new ArrayList<>();

		try {
			if (limit == null)
				limit = 3;
			if (limit == 0)
				limit = 3;
			if (page == null)
				page = 1;

			Pageable pageable = PageRequest.of(page - 1, limit);
			findPage = CompetitionService.findByTournamentIdAndSportId(pageable, tournamentId, sportId);

			for (CompetitionEntity entity : findPage) {
				CompetitionDTO dto = competitionConverter.toDTO(entity);
				findPageDTO.add(dto);
			}

			result.put("Competitions", findPageDTO);
			error.put("MessageCode", 0);
			error.put("Message", "Get page successfully");

			System.out.println("CompetitionsAPI: getAllCompetitonByTournamentId: no exception");
		} catch (Exception e) {
			System.out.println("CompetitionsAPI: getAllCompetitonByTournamentId: has exception");
			result.put("Users", findPageDTO);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("CompetitionsAPI: getAllCompetitonByTournamentId: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

}
