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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import doan2020.SportTournamentSupportSystem.converter.CompetitionConverter;
import doan2020.SportTournamentSupportSystem.dtIn.CreateCompetitionDtIn;
import doan2020.SportTournamentSupportSystem.dtIn.EditCompetitionDtIn;
import doan2020.SportTournamentSupportSystem.dtOut.CompetitionDtOut;
import doan2020.SportTournamentSupportSystem.entity.CompetitionEntity;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.ICompetitionService;

@RestController
@CrossOrigin
@RequestMapping("/competitions")
public class CompetitionAPI {

//	@Autowired
//	private ICompetitionService CompetitionService;
//
//	@Autowired
//	private CompetitionConverter competitionConverter;
//
//	/* ----------------Get One Competititon ------------------------ */
//	@GetMapping("/getOne")
//	public ResponseEntity<Response> GetCompetiton(@RequestParam("id") Long id) {
//		HttpStatus httpStatus = null;
//		httpStatus = HttpStatus.OK;
//		Response response = new Response();
//		Map<String, Object> config = new HashMap<String, Object>();
//		Map<String, Object> result = new HashMap<String, Object>();
//		Map<String, Object> error = new HashMap<String, Object>();
//		CompetitionEntity competitionEntity = new CompetitionEntity();
//		CompetitionDtOut competitionDtOut = new CompetitionDtOut();
//		try {
//			competitionEntity = CompetitionService.findOneById(id);
//
//			if (competitionEntity == null) {
//				result.put("Competition", null);
//				config.put("global", 0);
//				error.put("messageCode", 1);
//				error.put("message", "Competition is not exist");
//				response.setConfig(config);
//				response.setResult(result);
//				response.setError(error);
//				return new ResponseEntity<Response>(response, httpStatus);
//			}
//			
//			competitionDtOut = competitionConverter.toDTO(competitionEntity);
//
//			result.put("competition", competitionDtOut);
//
//			error.put("messageCode", 0);
//			error.put("message", "get Competition Successfully");
//
//			httpStatus = HttpStatus.OK;
//
//		} catch (Exception e) {
//			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
//		}
//		response.setError(error);
//		response.setResult(result);
//		response.setConfig(config);
//		return new ResponseEntity<Response>(response, httpStatus);
//	}
//
//	/*
//	 * ---------------- find all Competition by TournamentId
//	 * ------------------------
//	 */
//	@GetMapping("/getAllByTournamentId")
//	public ResponseEntity<Response> GetAllCompetiton(@RequestParam("id") Long id) {
//		HttpStatus httpStatus = null;
//		httpStatus = HttpStatus.OK;
//		Response response = new Response();
//		Map<String, Object> config = new HashMap<String, Object>();
//		Map<String, Object> result = new HashMap<String, Object>();
//		Map<String, Object> error = new HashMap<String, Object>();
//		List<CompetitionEntity> competitionEntitys = new ArrayList<CompetitionEntity>();
//		List<CompetitionDtOut> competitionDtOuts = new ArrayList<CompetitionDtOut>();
//
//		if (id == null) {
//			result.put("Competition", null);
//			config.put("global", 0);
//			error.put("messageCode", 1);
//			error.put("message", "Required Competition's id!");
//
//			response.setConfig(config);
//			response.setResult(result);
//			response.setError(error);
//			return new ResponseEntity<Response>(response, httpStatus);
//		}
//
//		competitionEntitys = CompetitionService.findByTournamentId(id);
//		
//		if (competitionEntitys.isEmpty()) {
//			result.put("Competition", null);
//			config.put("global", 0);
//			error.put("messageCode", 1);
//			error.put("message", "Competition is not exist");
//			response.setConfig(config);
//			response.setResult(result);
//			response.setError(error);
//			return new ResponseEntity<Response>(response, httpStatus);
//		}
//		
//		try {
//			for (CompetitionEntity competitionEntity : competitionEntitys) {
//				CompetitionDtOut competitionDtOut = competitionConverter.toDTO(competitionEntity);
//				competitionDtOuts.add(competitionDtOut);
//			}
//			
//			result.put("list Competitions", competitionDtOuts);
//			config.put("global", 0);
//			error.put("messageCode", 0);
//			error.put("message", "Found");
//
//			System.out.println("true");
//
//		} catch (Exception e) {
//			result.put("Competition", null);
//			config.put("global", 0);
//			error.put("messageCode", 1);
//			error.put("message", "Competition is not exist");
//		}
//		response.setError(error);
//		response.setResult(result);
//		response.setConfig(config);
//		return new ResponseEntity<Response>(response, httpStatus);
//	}
//
//	/*
//	 * ---------------- find all Competition by TournamenId
//	 * WithPaging------------------------
//	 */
//	@GetMapping("/getAllByTournamentIdWithPaging")
//	public ResponseEntity<Response> GetAllCompetititonByTournamenId(@RequestParam("page") Integer page,
//			@RequestParam("id") Long id) {
//		HttpStatus httpStatus = null;
//		httpStatus = HttpStatus.OK;
//		Response response = new Response();
//		Map<String, Object> config = new HashMap<String, Object>();
//		Map<String, Object> result = new HashMap<String, Object>();
//		Map<String, Object> error = new HashMap<String, Object>();
//		List<CompetitionEntity> competitionEntitys = new ArrayList<CompetitionEntity>();
//		List<CompetitionDtOut> competitionDtOuts = new ArrayList<CompetitionDtOut>();
//
//		if (page == null || id == null) {
//			result.put("Competition", null);
//			config.put("global", 0);
//			error.put("messageCode", 1);
//			error.put("message", "Required Competition's page or id!");
//
//			response.setConfig(config);
//			response.setResult(result);
//			response.setError(error);
//			return new ResponseEntity<Response>(response, httpStatus);
//		}
//
//		Sort sortable = Sort.by("id").ascending();
//		int limit = 3;
//		Pageable pageable = PageRequest.of(page - 1, limit, sortable);
//		competitionEntitys = CompetitionService.findByTournamentId(pageable, id);
//		
//		if (competitionEntitys.isEmpty()) {
//			result.put("Competition", null);
//			config.put("global", 0);
//			error.put("messageCode", 1);
//			error.put("message", "Competition is not exist");
//			response.setConfig(config);
//			response.setResult(result);
//			response.setError(error);
//			return new ResponseEntity<Response>(response, httpStatus);
//		}
//		
//		try {
//			for (CompetitionEntity competitionEntity : competitionEntitys) {
//				CompetitionDtOut competitionDtOut = competitionConverter.toDTO(competitionEntity);
//				competitionDtOuts.add(competitionDtOut);
//			}
//			int total = competitionDtOuts.size();
//			int totalPage = total / limit;
//			if (total % limit != 0) {
//				totalPage++;
//			}
//			result.put("total page", totalPage);
//			result.put("list Competitions", competitionDtOuts);
//			config.put("global", 0);
//			error.put("messageCode", 0);
//			error.put("message", "Found");
//
//			System.out.println("true");
//
//		} catch (Exception e) {
//			result.put("Competition", null);
//			config.put("global", 0);
//			error.put("messageCode", 1);
//			error.put("message", "Competition is not exist");
//		}
//		response.setError(error);
//		response.setResult(result);
//		response.setConfig(config);
//		return new ResponseEntity<Response>(response, httpStatus);
//	}
//
//	/* ---------------- add new Competition ------------------------ */
//	@PostMapping
//	public ResponseEntity<Response> createCompetition(@RequestBody CreateCompetitionDtIn competitionDtIn) {
//		HttpStatus httpStatus = null;
//		httpStatus = HttpStatus.OK;
//		Response response = new Response();
//		Map<String, Object> config = new HashMap<String, Object>();
//		Map<String, Object> result = new HashMap<String, Object>();
//		Map<String, Object> error = new HashMap<String, Object>();
//		CompetitionEntity competitionEntity = new CompetitionEntity();
//
//		try {
//			competitionEntity = competitionConverter.toEntity(competitionDtIn);
//
//			CompetitionService.addCompetition(competitionEntity);
//
//			config.put("global", 0);
//			error.put("messageCode", 0);
//			error.put("message", "Add new Competition successfull");
//
//			System.out.println("true");
//
//		} catch (Exception e) {
//			httpStatus = HttpStatus.NOT_FOUND;
//			error.put("messageCode", 1);
//			error.put("message", "CompetitionId is fail");
//		}
//		response.setError(error);
//		response.setResult(result);
//		response.setConfig(config);
//		return new ResponseEntity<Response>(response, httpStatus);
//	}
//
//	/* ---------------- Edit Competition ------------------------ */
//	@PutMapping("")
//	public ResponseEntity<Response> editCompetition(@RequestParam("id") Long id,
//			@RequestBody EditCompetitionDtIn editCompetitionDtIn) {
//		HttpStatus httpStatus = null;
//		Response response = new Response();
//		Map<String, Object> config = new HashMap<String, Object>();
//		Map<String, Object> result = new HashMap<String, Object>();
//		Map<String, Object> error = new HashMap<String, Object>();
//		try {
//			CompetitionEntity competitionEntity = new CompetitionEntity();
//			CompetitionEntity oldCompetitionEntity = new CompetitionEntity();
//			if (id != null) {
//				oldCompetitionEntity = CompetitionService.findOneById(id);
//				if (oldCompetitionEntity != null) {
//					competitionEntity = competitionConverter.toEntityUpdate(editCompetitionDtIn, oldCompetitionEntity);
//					CompetitionService.editCompetition(competitionEntity);
//					httpStatus = HttpStatus.OK;
//					error.put("messageCode", 0);
//					error.put("message", "Edit Competition Successfull");
//				} else {
//					httpStatus = HttpStatus.NOT_FOUND;
//					error.put("messageCode", 1);
//					error.put("message", "Not Find Competition to update ");
//				}
//			} else {
//				httpStatus = HttpStatus.NOT_FOUND;
//				error.put("messageCode", 1);
//				error.put("message", "competitionId is not enter");
//			}
//		} catch (Exception ex) {
//			httpStatus = HttpStatus.NOT_FOUND;
//			error.put("messageCode", 1);
//			error.put("message", "CompetitionId is fail");
//		}
//		response.setError(error);
//		response.setResult(result);
//		response.setConfig(config);
//		return new ResponseEntity<Response>(response, httpStatus);
//	}
//
//	/* delete one User */
//	@DeleteMapping("")
//	public ResponseEntity<Response> deleteUser(@RequestParam("id") Long id) {
//		Response response = new Response();
//		HttpStatus httpStatus = null;
//		Map<String, Object> config = new HashMap<String, Object>();
//		Map<String, Object> result = new HashMap<String, Object>();
//		Map<String, Object> error = new HashMap<String, Object>();
//		CompetitionEntity competitionEntity = new CompetitionEntity();
//		try {
//			if (id != null) {
//				competitionEntity = CompetitionService.findOneById(id);
//				if (competitionEntity != null) {
//					CompetitionService.deleteCompetition(competitionEntity);
//					httpStatus = HttpStatus.OK;
//					error.put("messageCode", 0);
//					error.put("message", "Delete Competition Successfull");
//				} else {
//					httpStatus = HttpStatus.NOT_FOUND;
//					error.put("messageCode", 1);
//					error.put("message", "Not Find Competition to delete ");
//				}
//			} else {
//				httpStatus = HttpStatus.NOT_FOUND;
//				error.put("messageCode", 1);
//				error.put("message", "CompetitionId is not enter");
//			}
//		} catch (Exception e) {
//			httpStatus = HttpStatus.NOT_FOUND;
//			error.put("messageCode", 1);
//			error.put("message", "CompetitionId is fail");
//		}
//		response.setError(error);
//		response.setResult(result);
//		response.setConfig(config);
//		return new ResponseEntity<Response>(response, httpStatus);
//	}
}
