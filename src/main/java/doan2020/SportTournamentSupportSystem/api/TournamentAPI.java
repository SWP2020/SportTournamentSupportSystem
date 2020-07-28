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
import doan2020.SportTournamentSupportSystem.entity.TournamentEntity;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.ITournamentService;

@RestController
@CrossOrigin
@RequestMapping("/tournaments")
public class TournamentAPI {
	@Autowired
	private ITournamentService service;

	@Autowired
	private TournamentConverter converter;

	
	/*
	 * Tim kiem tournament theo paging
	 */
	@GetMapping("/getAll")
	public ResponseEntity<Response> getTournamentPaging(@RequestParam(value = "page") Integer page) {
		System.out.println("getTournament");
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		List<TournamentDtOut> tournamentDtOuts = new ArrayList<TournamentDtOut>();
		List<TournamentEntity> list = new ArrayList<TournamentEntity>();
//		System.out.println("2");

		if (page == null) {
			result.put("tournament", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "Required tournament's page!");
			httpStatus = HttpStatus.OK;
			response.setConfig(config);
			response.setResult(result);
			response.setError(error);
			return new ResponseEntity<Response>(response, httpStatus);
		}
		
		Sort sortable = Sort.by("id").ascending();
		int limit = 3;
		Pageable pageable = PageRequest.of(page - 1, limit , sortable);

		list = (List<TournamentEntity>) service.findAll(pageable);
			
		try {
			for(TournamentEntity tournamentEntity :list) {
				
			TournamentDtOut resDTO = converter.toDTO(tournamentEntity);
			tournamentDtOuts.add(resDTO);
			System.out.println(tournamentDtOuts.get(0).getFullName());
			System.out.println("cong");
			}
			System.out.println(tournamentDtOuts.get(0).getFullName());
			System.out.println("a");
			result.put("list tournament", tournamentDtOuts);
			config.put("global", 0);
			error.put("messageCode", 0);
			error.put("message", "Found");
			
			System.out.println("true");

		} catch (Exception e) {
			result.put("tournament", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "Tournament is not exist");
			System.out.println(e.getMessage().toString());
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);

		return new ResponseEntity<Response>(response, httpStatus);
	}
	
	/*
	 * Tim kiem tournament theo paging by UserId
	 */
//	@GetMapping("/getAllbyUserId")
//	public ResponseEntity<Response> getTournamentPagingByUserId(@RequestParam(value = "page") Integer page, @RequestParam(value = "id") Long id) {
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
//		Pageable pageable = PageRequest.of(page - 1, limit , sortable);
//
//		list = (List<TournamentEntity>) service.findAllByCreator(pageable, id);
//			
//		try {
//			for(TournamentEntity tournamentEntity :list) {
//				
//			TournamentDtOut resDTO = converter.toDTO(tournamentEntity);
//			tournamentDtOuts.add(resDTO);
//			System.out.println(tournamentDtOuts.get(0).getFullName());
//			System.out.println("cong");
//			}
//			System.out.println(tournamentDtOuts.get(0).getFullName());
//			System.out.println("a");
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
	
	
	
	/*
	 * Tim kiem tournament theo id hoac name Yeu cau id hoac name phai duoc nhap Neu
	 * ca 2 deu duoc nhap vao thi uu tien id
	 */
	@GetMapping("/getOne")
	public ResponseEntity<Response> getTournament(@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "name", required = false) String name) {
		System.out.println("getTournament");
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
//		System.out.println("2");
		System.out.println(id);
		System.out.println(name);
		if (id == null && name == null) {
			result.put("tournament", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "Required tournament's id or name!");
			httpStatus = HttpStatus.OK;
			response.setConfig(config);
			response.setResult(result);
			response.setError(error);
			return new ResponseEntity<Response>(response, httpStatus);
		}

		TournamentEntity res;

		if (id == null) {
			System.out.println("Find by name");
			res = service.findByName(name);
		} else {
			System.out.println("Find by Id");
			res = service.findOneById(id);
		}
			
		try {
			
			
			TournamentDtOut resDTO = converter.toDTO(res);
			System.out.println("Convert OK");
			
			result.put("tournament", resDTO);
			config.put("global", 0);
			error.put("messageCode", 0);
			error.put("message", "Found");

		} catch (Exception e) {
			result.put("tournament", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "Tournament is not exist");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);

		return new ResponseEntity<Response>(response, httpStatus);
	}

	/*
	 * Tao moi mot Tournament
	 * 
	 */
	@PostMapping
	@CrossOrigin
	public ResponseEntity<Response> createTournament(@RequestBody Map<String, Object> newTournament) {
		System.out.println("createTournament");
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		try {
			TournamentEntity tournamentEntity = converter.toEntity(newTournament);
			System.out.println("convert OK");
			service.addOne(tournamentEntity);
			System.out.println("add OK");
			TournamentDtOut dto = converter.toDTO(tournamentEntity);
			System.out.println("convert OK");
			result.put("tournament", dto);
			config.put("global", 0);
			error.put("messageCode", 0);
			error.put("message", "Tournament create successfuly");
		} catch (Exception e) {
			result.put("tournament", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "Tournament create fail");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		return new ResponseEntity<Response>(response, httpStatus);
	}
	
	/*
	 * Edit mot Tournament
	 * 
	 */
	@PutMapping
	@CrossOrigin
	public ResponseEntity<Response> editTournament(@RequestBody Map<String, Object> tournament,
			@RequestParam Long id) {
		System.out.println("editTournament");
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		try {
			TournamentEntity tournamentEntity = converter.toEntity(tournament);
			System.out.println("convert OK");
			TournamentEntity newTournament = service.update(id, tournamentEntity);
			System.out.println("add OK");
			TournamentDtOut dto = converter.toDTO(newTournament);
			System.out.println("convert OK");
			result.put("tournament", dto);
			config.put("global", 0);
			error.put("messageCode", 0);
			error.put("message", "Tournament create successfuly");
		} catch (Exception e) {
			result.put("tournament", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "Tournament create fail");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		return new ResponseEntity<Response>(response, httpStatus);
	}

}
