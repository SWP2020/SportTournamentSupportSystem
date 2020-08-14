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

import doan2020.SportTournamentSupportSystem.converter.TournamentConverter;
import doan2020.SportTournamentSupportSystem.dto.TournamentDTO;
import doan2020.SportTournamentSupportSystem.entity.CompetitionEntity;
import doan2020.SportTournamentSupportSystem.entity.TournamentEntity;
import doan2020.SportTournamentSupportSystem.entity.UserEntity;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.ITournamentService;
import doan2020.SportTournamentSupportSystem.service.IUserService;

@RestController
@CrossOrigin
@RequestMapping("/tournaments")
public class TournamentsAPI {
	
	@Autowired
	private ITournamentService service;
	
	@Autowired
	private IUserService userService;

	@Autowired
	private TournamentConverter converter;

//	/*
//	 * Tim kiem tournament theo paging
//	 */
//	@GetMapping("/getAll")
//	public ResponseEntity<Response> getTournamentPaging(@RequestParam(value = "page") Integer page) {
//		System.out.println("getTournamentPaging");
//		HttpStatus httpStatus = HttpStatus.OK;
//		Response response = new Response();
//		Map<String, Object> config = new HashMap<String, Object>();
//		Map<String, Object> result = new HashMap<String, Object>();
//		Map<String, Object> error = new HashMap<String, Object>();
//		List<TournamentDtOut> tournamentDtOuts = new ArrayList<TournamentDtOut>();
//		List<TournamentEntity> list = new ArrayList<TournamentEntity>();
////		System.out.println("2");
//
//		if (page == null) {
//			result.put("tournament", null);
//			config.put("Global", 0);
//			error.put("MessageCode", 1);
//			error.put("Message", "Required tournament's page!");
//			httpStatus = HttpStatus.OK;
//			response.setConfig(config);
//			response.setResult(result);
//			response.setError(error);
//			return new ResponseEntity<Response>(response, httpStatus);
//		}
//
//		Sort sortable = Sort.by("id").ascending();
//		int limit = 3;
//		Pageable pageable = PageRequest.of(page - 1, limit, sortable);
//
//		list = (List<TournamentEntity>) service.findAll(pageable);
//
//		if (list.isEmpty()) {
//			result.put("Tournament", null);
//			config.put("Global", 0);
//			error.put("MessageCode", 1);
//			error.put("Message", "tournament is not exist");
//			response.setConfig(config);
//			response.setResult(result);
//			response.setError(error);
//			return new ResponseEntity<Response>(response, httpStatus);
//		}
//		try {
//			for (TournamentEntity tournamentEntity : list) {
//
//				TournamentDtOut resDTO = converter.toDTO(tournamentEntity);
//				tournamentDtOuts.add(resDTO);
//				System.out.println(tournamentDtOuts.get(0).getFullName());
//
//			}
//
//			
//			int total = tournamentDtOuts.size();
//			int totalPage = total / limit;
//			if (total % limit != 0) {
//				totalPage++;
//			}
//			result.put("total page", totalPage);
//			result.put("list tournament", tournamentDtOuts);
//			config.put("Global", 0);
//			error.put("MessageCode", 0);
//			error.put("Message", "Found");
//
//			System.out.println("true");
//
//		} catch (Exception e) {
//			result.put("tournament", null);
//			config.put("Global", 0);
//			error.put("MessageCode", 1);
//			error.put("Message", "Tournament is not exist");
//			System.out.println(e.getMessage().toString());
//		}
//
//		response.setConfig(config);
//		response.setResult(result);
//		response.setError(error);
//
//		return new ResponseEntity<Response>(response, httpStatus);
//	}
//
	/*
	 * Tim kiem tournament theo paging by UserId
	 */
	
	@GetMapping("")
	public ResponseEntity<Response> getAllTournament(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit) {
		System.out.println("TournamentsAPI: getAllTournament: start");

		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		HttpStatus httpStatus = HttpStatus.OK;

		Collection<TournamentEntity> findPage = new ArrayList<>();
		List<TournamentDTO> findPageDTO = new ArrayList<>();

		try {
			if (limit == null)
				limit = 10;
			if (limit == 0)
				limit = 10;
			if (page == null)
				page = 1;

			Pageable pageable = PageRequest.of(page - 1, limit);
			findPage = service.findAll(pageable);

			for (TournamentEntity entity : findPage) {
				Collection<String> sportNames = new ArrayList<>();
				Collection<CompetitionEntity> competitions = entity.getCompetitions();
				for (CompetitionEntity entity2 : competitions) {
					String sportName = entity2.getSport().getFullName();
					sportNames.add(sportName);
				}
				result.put("Sports in "  + entity.getFullName(), sportNames);
				TournamentDTO dto = converter.toDTO(entity);
				findPageDTO.add(dto);
			}

			result.put("Tournaments", findPageDTO);
			error.put("MessageCode", 0);
			error.put("Message", "Get page successfully");

			System.out.println("TournamentsAPI: getAllTournament: no exception");
		} catch (Exception e) {
			System.out.println("TournamentsAPI: getAllTournament: has exception");
			result.put("Users", findPageDTO);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("TournamentsAPI: getAllTournament: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

	@GetMapping("/getByUserId")
	public ResponseEntity<Response> getTournamentsPagingByUserId(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "userId") Long userId) {
		
		System.out.println("TournamentsAPI: getTournamentsPagingByUserId: start");
		
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		
		List<TournamentDTO> dtos = new ArrayList<TournamentDTO>();
		List<TournamentEntity> entities = new ArrayList<TournamentEntity>();
		
		if (limit == null || limit <= 0)
			limit = 3;
		
		if (page == null || page <= 0)
			page = 1;
		
		if (userId == null) {// userId null
			result.put("Tournaments", dtos);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Required param userId");
		} else {//userId not null
//			Sort sortable = Sort.by("id").ascending();
			try {
				Pageable pageable = PageRequest.of(page - 1, limit);
				entities = (List<TournamentEntity>) service.findByCreatorId(pageable, userId);
				int totalPage = 0;
				UserEntity creator = userService.findOneById(userId);
				int totalEntity = creator.getTournaments().size();
				totalPage = totalEntity / limit;
				if (totalEntity % limit != 0)
					totalPage++;
				
				for (TournamentEntity entity: entities) {
					TournamentDTO dto = converter.toDTO(entity);
					dtos.add(dto);
				}
				
				result.put("TotalPage", totalPage);
				result.put("Tournaments", dtos);
				config.put("Global", 0);
				error.put("MessageCode", 0);
				error.put("Message", "Found");
				System.out.println("TournamentsAPI: getTournamentsPagingByUserId: no exception");
			} catch (Exception e) {
				System.out.println("TournamentsAPI: getTournamentsPagingByUserId: has exception");
				result.put("TotalPage", null);
				result.put("Tournaments", dtos);
				config.put("Global", 0);
				error.put("MessageCode", 0);
				error.put("Message", "Server error");
			}
			
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("TournamentsAPI: getTournamentsPagingByUserId: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}
	
	@GetMapping("/getBySearchString")
	public ResponseEntity<Response> getBySearchString(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "searchString") String searchString) {
		System.out.println("TournamentsAPI: getBySearchString: start");
		
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		
		List<TournamentDTO> dtos = new ArrayList<TournamentDTO>();
		List<TournamentEntity> entities = new ArrayList<TournamentEntity>();
		
		if (limit == null || limit <= 0)
			limit = 3;
		
		if (page == null || page <= 0)
			page = 1;
		
		if (searchString == null) {// searchString null
			result.put("Tournaments", dtos);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Required param searchString");
		} else {//searchString not null
//			Sort sortable = Sort.by("id").ascending();
			try {
				Pageable pageable = PageRequest.of(page - 1, limit);
				entities = (List<TournamentEntity>) service.findBySearchString(pageable, searchString);
				
				int totalPage = 0;
				
				int totalEntity = entities.size();
				totalPage = totalEntity / limit;
				if (totalEntity % limit != 0)
					totalPage++;
				
				for (TournamentEntity entity: entities) {
					TournamentDTO dto = converter.toDTO(entity);
					dtos.add(dto);
				}
				
				result.put("TotalPage", totalPage);
				result.put("Tournaments", dtos);
				config.put("Global", 0);
				error.put("MessageCode", 0);
				error.put("Message", "Found");
				System.out.println("TournamentsAPI: getBySearchString: no exception");
			} catch (Exception e) {
				System.out.println("TournamentsAPI: getBySearchString: has exception");
				result.put("TotalPage", null);
				result.put("Tournaments", dtos);
				config.put("Global", 0);
				error.put("MessageCode", 0);
				error.put("Message", "Server error");
			}
			
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("TournamentsAPI: getBySearchString: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}


}
