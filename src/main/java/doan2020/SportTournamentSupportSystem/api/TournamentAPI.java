package doan2020.SportTournamentSupportSystem.api;

import java.util.ArrayList;
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
import doan2020.SportTournamentSupportSystem.entity.TournamentEntity;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.ITournamentService;

@RestController
@CrossOrigin
@RequestMapping("/tournament")
public class TournamentAPI {
	@Autowired
	private ITournamentService service;

	@Autowired
	private TournamentConverter converter;

//
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
//			config.put("global", 0);
//			error.put("messageCode", 1);
//			error.put("message", "Required tournament's page!");
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
//			config.put("global", 0);
//			error.put("messageCode", 1);
//			error.put("message", "tournament is not exist");
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
//			config.put("global", 0);
//			error.put("messageCode", 0);
//			error.put("message", "Found");
//
//			System.out.println("true");
//
//		} catch (Exception e) {
//			result.put("tournament", null);
//			config.put("global", 0);
//			error.put("messageCode", 1);
//			error.put("message", "Tournament is not exist");
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
//	/*
//	 * Tim kiem tournament theo paging by UserId
//	 */
//
//	@GetMapping("/getAllByUserId")
//	public ResponseEntity<Response> getTournamentPagingByUserId(@RequestParam(value = "page") Integer page,
//			@RequestParam(value = "id") Long id) {
//		System.out.println("getTournament");
//		HttpStatus httpStatus = HttpStatus.OK;
//		Response response = new Response();
//		Map<String, Object> config = new HashMap<String, Object>();
//		Map<String, Object> result = new HashMap<String, Object>();
//		Map<String, Object> error = new HashMap<String, Object>();
//		List<TournamentDtOut> tournamentDtOuts = new ArrayList<TournamentDtOut>();
//		List<TournamentEntity> list = new ArrayList<TournamentEntity>();
////		System.out.println("2");
//
//		if (page == null || id == null) {
//			result.put("tournaments", null);
//			config.put("global", 0);
//			error.put("messageCode", 1);
//			error.put("message", "Required tournament's page!");
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
//		list = (List<TournamentEntity>) service.findAllByCreator(pageable, id);
//
//		if (list.isEmpty()) {
//			result.put("tournamnent", null);
//			config.put("global", 0);
//			error.put("messageCode", 1);
//			error.put("message", "tournament is not exist");
//			response.setConfig(config);
//			response.setResult(result);
//			response.setError(error);
//			return new ResponseEntity<Response>(response, httpStatus);
//		}
//
//		try {
//			for (TournamentEntity tournamentEntity : list) {
//
//				TournamentDtOut resDTO = converter.toDTO(tournamentEntity);
//				tournamentDtOuts.add(resDTO);
//				System.out.println(tournamentDtOuts.get(0).getFullName());
//				System.out.println("cong");
//			}
//			int total = tournamentDtOuts.size();
//			int totalPage = total / limit;
//			if (total % limit != 0) {
//				totalPage++;
//			}
//			result.put("total page", totalPage);
//			result.put("list tournament", tournamentDtOuts);
//			config.put("global", 0);
//			error.put("messageCode", 0);
//			error.put("message", "Found");
//
//			System.out.println("true");
//
//		} catch (Exception e) {
//			result.put("tournaments", null);
//			config.put("global", 0);
//			error.put("messageCode", 1);
//			error.put("message", "Tournament is not exist");
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
//	/*
//	 * Tim kiem tournament theo id hoac name Yeu cau id hoac name phai duoc nhap Neu
//	 * ca 2 deu duoc nhap vao thi uu tien id
//	 */
	@GetMapping("")
	public ResponseEntity<Response> getTournament(@RequestParam(value = "id", required = false) Long id) {
		System.out.println("TournamentAPI: getTournament: no exception");
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		TournamentEntity tournamentEntity = new TournamentEntity();
		TournamentDTO tournamentDTO = new TournamentDTO();
		try {
			if (id == null) { // id null
				result.put("Tournament", null);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "Required param id");
			} else { // id not null
				
				tournamentEntity = service.findOneById(id);
				
				if (tournamentEntity == null) { // not found
					result.put("Tournament", null);
					config.put("Global", 0);
					error.put("MessageCode", 1);
					error.put("Message", "Not found");
				} else { // found
					
					tournamentDTO = converter.toDTO(tournamentEntity);
					
					result.put("Tournament", tournamentDTO);
					config.put("Global", 0);
					error.put("MessageCode", 0);
					error.put("Message", "Found");
				}
			}
			System.out.println("TournamentAPI: getTournament: no exception");
		} catch (Exception e) {
			System.out.println("TournamentAPI: getTournament: has exception");
			result.put("Tournament", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("TournamentAPI: getTournament: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

	/*
	 * Tao moi mot Tournament
	 * 
	 */
	@PostMapping
	@CrossOrigin
	public ResponseEntity<Response> createTournament(@RequestBody TournamentDTO newTournament) {
		System.out.println("TournamentAPI: createTournament: start");
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		TournamentEntity tournamentEntity = new TournamentEntity();
		
		try {
			tournamentEntity = converter.toEntity(newTournament);
			
			tournamentEntity = service.create(tournamentEntity);
			
			TournamentDTO dto = converter.toDTO(tournamentEntity);

			result.put("Tournament", dto);
			config.put("Global", 0);
			error.put("MessageCode", 0);
			error.put("Message", "Tournament create successfuly");
			System.out.println("TournamentAPI: createTournament: no exception");
		} catch (Exception e) {
			System.out.println("TournamentAPI: createTournament: has exception");
			result.put("Tournament", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("TournamentAPI: createTournament: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

	/*
	 * Edit mot Tournament
	 * 
	 */
	@PutMapping
	@CrossOrigin
	public ResponseEntity<Response> editTournament(
			@RequestBody TournamentDTO tournament,
			@RequestParam Long id) {
		System.out.println("TournamentAPI: editTournament: start");
		
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		TournamentEntity tournamentEntity = new TournamentEntity();
		
		try {
			tournamentEntity = converter.toEntity(tournament);
			
			tournamentEntity = service.update(id, tournamentEntity);
			
			TournamentDTO dto = converter.toDTO(tournamentEntity);

			result.put("Tournament", dto);
			config.put("Global", 0);
			error.put("MessageCode", 0);
			error.put("Message", "Tournament update successfuly");
			System.out.println("TournamentAPI: editTournament: no exception");
		} catch (Exception e) {
			System.out.println("TournamentAPI: editTournament: has exception");
			result.put("Tournament", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("TournamentAPI: editTournament: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

}
