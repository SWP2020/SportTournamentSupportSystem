package doan2020.SportTournamentSupportSystem.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

import doan2020.SportTournamentSupportSystem.converter.TournamentConverter;
import doan2020.SportTournamentSupportSystem.dtOut.TournamentDtOut;
import doan2020.SportTournamentSupportSystem.dto.TournamentDTO;
import doan2020.SportTournamentSupportSystem.dto.TournamentDTO;
import doan2020.SportTournamentSupportSystem.entity.TournamentEntity;
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
		List<TournamentDTO> tournamentDTOs = new ArrayList<TournamentDTO>();
		List<TournamentEntity> tournamentEntities = new ArrayList<TournamentEntity>();
//		System.out.println("2");
		
		if (limit == null || limit <= 0)
			limit = 3;
		
		if (page == null || page <= 0)
			page = 1;
		
		if (userId == null) {// userId null
			result.put("Tournaments", tournamentDTOs);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Required param userId");
		} else {//userId not null
//			Sort sortable = Sort.by("id").ascending();
			try {
				Pageable pageable = PageRequest.of(page - 1, limit);
				tournamentEntities = (List<TournamentEntity>) service.findByCreatorId(pageable, userId);
				int totalPage = 0;
				UserEntity creator = userService.findOneById(userId);
				int totalEntity = creator.getTournaments().size();
				totalPage = totalEntity / limit;
				if (totalEntity % limit != 0)
					totalPage++;
				
				for (TournamentEntity entity: tournamentEntities) {
					TournamentDTO dto = converter.toDTO(entity);
					tournamentDTOs.add(dto);
				}
				
				result.put("TotalPage", totalPage);
				result.put("Tournaments", tournamentDTOs);
				config.put("Global", 0);
				error.put("MessageCode", 0);
				error.put("Message", "Found");
				System.out.println("TournamentsAPI: getTournamentsPagingByUserId: no exception");
			} catch (Exception e) {
				System.out.println("TournamentsAPI: getTournamentsPagingByUserId: has exception");
				result.put("TotalPage", null);
				result.put("Tournaments", tournamentDTOs);
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
//
//	/*
//	 * Tim kiem tournament theo id hoac name Yeu cau id hoac name phai duoc nhap Neu
//	 * ca 2 deu duoc nhap vao thi uu tien id
//	 */
//	@GetMapping("/getOne")
//	public ResponseEntity<Response> getTournament(@RequestParam(value = "id", required = false) Long id,
//			@RequestParam(value = "name", required = false) String name) {
//		System.out.println("getTournament");
//		HttpStatus httpStatus = HttpStatus.OK;
//		Response response = new Response();
//		Map<String, Object> config = new HashMap<String, Object>();
//		Map<String, Object> result = new HashMap<String, Object>();
//		Map<String, Object> error = new HashMap<String, Object>();
////		System.out.println("2");
//		System.out.println(id);
//		System.out.println(name);
//		if (id == null && name == null) {
//			result.put("tournament", null);
//			config.put("Global", 0);
//			error.put("MessageCode", 1);
//			error.put("Message", "Required tournament's id or name!");
//			httpStatus = HttpStatus.OK;
//			response.setConfig(config);
//			response.setResult(result);
//			response.setError(error);
//			return new ResponseEntity<Response>(response, httpStatus);
//		}
//
//		TournamentEntity res;
//
//		if (id == null) {
//			System.out.println("Find by name");
//			res = service.findByName(name);
//		} else {
//			System.out.println("Find by Id");
//			res = service.findOneById(id);
//		}
//
//		if (res == null) {
//			result.put("tournament", null);
//			config.put("Global", 0);
//			error.put("MessageCode", 1);
//			error.put("Message", "tournament is not exist");
//			response.setConfig(config);
//			response.setResult(result);
//			response.setError(error);
//			return new ResponseEntity<Response>(response, httpStatus);
//		}
//
//		try {
//
//			TournamentDtOut resDTO = converter.toDTO(res);
//			System.out.println("Convert OK");
//
//			result.put("tournament", resDTO);
//			config.put("Global", 0);
//			error.put("MessageCode", 0);
//			error.put("Message", "Found");
//
//		} catch (Exception e) {
//			result.put("tournament", null);
//			config.put("Global", 0);
//			error.put("MessageCode", 1);
//			error.put("Message", "Tournament is not exist");
//		}
//
//		response.setConfig(config);
//		response.setResult(result);
//		response.setError(error);
//
//		return new ResponseEntity<Response>(response, httpStatus);
//	}
//
//	/*
//	 * Tao moi mot Tournament
//	 * 
//	 */
//	@PostMapping
//	@CrossOrigin
//	public ResponseEntity<Response> createTournament(@RequestBody Map<String, Object> newTournament) {
//		System.out.println("createTournament");
//		HttpStatus httpStatus = HttpStatus.OK;
//		Response response = new Response();
//		Map<String, Object> config = new HashMap<String, Object>();
//		Map<String, Object> result = new HashMap<String, Object>();
//		Map<String, Object> error = new HashMap<String, Object>();
//		try {
//			TournamentEntity tournamentEntity = converter.toEntity(newTournament);
//			System.out.println("convert OK");
//			service.addOne(tournamentEntity);
//			System.out.println("add OK");
//			TournamentDtOut dto = converter.toDTO(tournamentEntity);
//			System.out.println("convert OK");
//			result.put("tournament", dto);
//			config.put("Global", 0);
//			error.put("MessageCode", 0);
//			error.put("Message", "Tournament create successfuly");
//		} catch (Exception e) {
//			result.put("tournament", null);
//			config.put("Global", 0);
//			error.put("MessageCode", 1);
//			error.put("Message", "Tournament create fail");
//		}
//
//		response.setConfig(config);
//		response.setResult(result);
//		response.setError(error);
//		return new ResponseEntity<Response>(response, httpStatus);
//	}
//
//	/*
//	 * Edit mot Tournament
//	 * 
//	 */
//	@PutMapping
//	@CrossOrigin
//	public ResponseEntity<Response> editTournament(@RequestBody Map<String, Object> tournament, @RequestParam Long id) {
//		System.out.println("editTournament");
//		HttpStatus httpStatus = HttpStatus.OK;
//		Response response = new Response();
//		Map<String, Object> config = new HashMap<String, Object>();
//		Map<String, Object> result = new HashMap<String, Object>();
//		Map<String, Object> error = new HashMap<String, Object>();
//		try {
//			TournamentEntity tournamentEntity = converter.toEntity(tournament);
//			System.out.println("convert OK");
//			TournamentEntity newTournament = service.update(id, tournamentEntity);
//			System.out.println("add OK");
//			TournamentDtOut dto = converter.toDTO(newTournament);
//			System.out.println("convert OK");
//			result.put("tournament", dto);
//			config.put("Global", 0);
//			error.put("MessageCode", 0);
//			error.put("Message", "Tournament create successfuly");
//		} catch (Exception e) {
//			result.put("tournament", null);
//			config.put("Global", 0);
//			error.put("MessageCode", 1);
//			error.put("Message", "Tournament create fail");
//		}
//
//		response.setConfig(config);
//		response.setResult(result);
//		response.setError(error);
//		return new ResponseEntity<Response>(response, httpStatus);
//	}

}
