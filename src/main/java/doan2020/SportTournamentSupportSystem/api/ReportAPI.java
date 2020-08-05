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

import doan2020.SportTournamentSupportSystem.converter.ReportConverter;
import doan2020.SportTournamentSupportSystem.dtIn.ReportDtIn;
import doan2020.SportTournamentSupportSystem.dtOut.ReportDtOut;
import doan2020.SportTournamentSupportSystem.entity.ReportEntity;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.IReportService;

@RestController
@CrossOrigin
@RequestMapping("/reports")
public class ReportAPI {

//	@Autowired
//	private IReportService service;
//
//	@Autowired
//	private ReportConverter converter;
//
//	/*
//	 * Tim kiem reports theo paging by Type: for admin or Management(tournament)
//	 */
//
//	@GetMapping("/getAllByType")
//	public ResponseEntity<Response> getReportPagingByType(@RequestParam(value = "page") Integer page,
//			@RequestParam(value = "type") String type) {
//		System.out.println("get reports");
//		HttpStatus httpStatus = HttpStatus.OK;
//		Response response = new Response();
//		Map<String, Object> config = new HashMap<String, Object>();
//		Map<String, Object> result = new HashMap<String, Object>();
//		Map<String, Object> error = new HashMap<String, Object>();
//		List<ReportDtOut> reportDtOuts = new ArrayList<ReportDtOut>();
//		List<ReportEntity> list = new ArrayList<ReportEntity>();
////		System.out.println("2");
//
//		if (page == null || type == null) {
//			result.put("reports", null);
//			config.put("global", 0);
//			error.put("messageCode", 1);
//			error.put("message", "Required reports page or type!");
//			httpStatus = HttpStatus.OK;
//			response.setConfig(config);
//			response.setResult(result);
//			response.setError(error);
//			return new ResponseEntity<Response>(response, httpStatus);
//		}
//
//		Sort sortable = Sort.by("id").ascending();
//		int limit = 10;
//		Pageable pageable = PageRequest.of(page - 1, limit, sortable);
//
//		list = (List<ReportEntity>) service.findByType(pageable, type);
//
//		if (list.isEmpty()) {
//			result.put("Report", null);
//			config.put("global", 0);
//			error.put("messageCode", 1);
//			error.put("message", "Report is not exist");
//			response.setConfig(config);
//			response.setResult(result);
//			response.setError(error);
//			return new ResponseEntity<Response>(response, httpStatus);
//		}
//
//		try {
//			for (ReportEntity reportEntity : list) {
//
//				ReportDtOut resDTO = converter.toDTO(reportEntity);
//				reportDtOuts.add(resDTO);
//			}
//			int total = reportDtOuts.size();
//			int totalPage = total / limit;
//			if (total % limit != 0) {
//				totalPage++;
//			}
//			result.put("total page", totalPage);
//			result.put("list Report", reportDtOuts);
//			config.put("global", 0);
//			error.put("messageCode", 0);
//			error.put("message", "Found");
//
//			System.out.println("true");
//
//		} catch (Exception e) {
//			result.put("Report", null);
//			config.put("global", 0);
//			error.put("messageCode", 1);
//			error.put("message", "Report is not exist");
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
//	 * Tim kiem reports theo paging by UserId
//	 */
//
//	@GetMapping("/getAllBySenderId")
//	public ResponseEntity<Response> getReportsPagingByUserId(@RequestParam(value = "page") Integer page,
//			@RequestParam(value = "id") Long id) {
//		System.out.println("get reports");
//		HttpStatus httpStatus = HttpStatus.OK;
//		Response response = new Response();
//		Map<String, Object> config = new HashMap<String, Object>();
//		Map<String, Object> result = new HashMap<String, Object>();
//		Map<String, Object> error = new HashMap<String, Object>();
//		List<ReportDtOut> reportDtOuts = new ArrayList<ReportDtOut>();
//		List<ReportEntity> list = new ArrayList<ReportEntity>();
////		System.out.println("2");
//
//		if (page == null || id == null) {
//			result.put("reports", null);
//			config.put("global", 0);
//			error.put("messageCode", 1);
//			error.put("message", "Required reports page or id !");
//			httpStatus = HttpStatus.OK;
//			response.setConfig(config);
//			response.setResult(result);
//			response.setError(error);
//			return new ResponseEntity<Response>(response, httpStatus);
//		}
//
//		Sort sortable = Sort.by("id").ascending();
//		int limit = 10;
//		Pageable pageable = PageRequest.of(page - 1, limit, sortable);
//
//		list = (List<ReportEntity>) service.findBySenderId(pageable, id);
//
//		if (list.isEmpty()) {
//			result.put("Report", null);
//			config.put("global", 0);
//			error.put("messageCode", 1);
//			error.put("message", "Report is not exist");
//			response.setConfig(config);
//			response.setResult(result);
//			response.setError(error);
//			return new ResponseEntity<Response>(response, httpStatus);
//		}
//
//		try {
//			for (ReportEntity reportEntity : list) {
//
//				ReportDtOut resDTO = converter.toDTO(reportEntity);
//				reportDtOuts.add(resDTO);
//			}
//			int total = reportDtOuts.size();
//			int totalPage = total / limit;
//			if (total % limit != 0) {
//				totalPage++;
//			}
//			result.put("total page", totalPage);
//			result.put("list Report", reportDtOuts);
//			config.put("global", 0);
//			error.put("messageCode", 0);
//			error.put("message", "Found");
//
//			System.out.println("true");
//
//		} catch (Exception e) {
//			result.put("Report", null);
//			config.put("global", 0);
//			error.put("messageCode", 1);
//			error.put("message", "Report is not exist");
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
//	 * Tim kiem getOneReport theo id
//	 */
//	@GetMapping("/getOne")
//	public ResponseEntity<Response> getOneReport(@RequestParam(value = "id") Long id) {
//		System.out.println("getOneReport");
//		HttpStatus httpStatus = HttpStatus.OK;
//		Response response = new Response();
//		Map<String, Object> config = new HashMap<String, Object>();
//		Map<String, Object> result = new HashMap<String, Object>();
//		Map<String, Object> error = new HashMap<String, Object>();
//		if (id == null) {
//			result.put("Report", null);
//			config.put("global", 0);
//			error.put("messageCode", 1);
//			error.put("message", "Required Report id");
//			httpStatus = HttpStatus.OK;
//			response.setConfig(config);
//			response.setResult(result);
//			response.setError(error);
//			return new ResponseEntity<Response>(response, httpStatus);
//		}
//
//		ReportEntity res;
//
//		res = service.findOneById(id);
//
//		if (res == null) {
//			result.put("Report", null);
//			config.put("global", 0);
//			error.put("messageCode", 1);
//			error.put("message", "Report is not exist");
//			response.setConfig(config);
//			response.setResult(result);
//			response.setError(error);
//			return new ResponseEntity<Response>(response, httpStatus);
//		}
//
//		try {
//
//			ReportDtOut resDTO = converter.toDTO(res);
//			System.out.println("Convert OK");
//
//			result.put("Report", resDTO);
//			config.put("global", 0);
//			error.put("messageCode", 0);
//			error.put("message", "Found");
//
//		} catch (Exception e) {
//			result.put("tournament", null);
//			config.put("global", 0);
//			error.put("messageCode", 1);
//			error.put("message", "Report is not exist");
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
//	 * Tao moi mot Report
//	 * 
//	 */
//	@PostMapping
//	@CrossOrigin
//	public ResponseEntity<Response> createReport(@RequestBody ReportDtIn reportDtIn) {
//		System.out.println("createTournament");
//		HttpStatus httpStatus = HttpStatus.OK;
//		Response response = new Response();
//		Map<String, Object> config = new HashMap<String, Object>();
//		Map<String, Object> result = new HashMap<String, Object>();
//		Map<String, Object> error = new HashMap<String, Object>();
//		try {
//			ReportEntity reportEntity = converter.toEntity(reportDtIn);
//			System.out.println("convert OK");
//			service.addReport(reportEntity);
//			System.out.println("add OK");
//			ReportDtOut dto = converter.toDTO(reportEntity);
//			System.out.println("convert OK");
//			result.put("Report", dto);
//			config.put("global", 0);
//			error.put("messageCode", 0);
//			error.put("message", "Report create successfuly");
//		} catch (Exception e) {
//			result.put("Report", null);
//			config.put("global", 0);
//			error.put("messageCode", 1);
//			error.put("message", "Report create fail");
//		}
//
//		response.setConfig(config);
//		response.setResult(result);
//		response.setError(error);
//		return new ResponseEntity<Response>(response, httpStatus);
//	}
//
//	/*
//	 * Edit mot Report
//	 * 
//	 */
//	@PutMapping
//	@CrossOrigin
//	public ResponseEntity<Response> editReport(@RequestBody ReportDtIn reportDtIn, @RequestParam Long id) {
//		System.out.println("editReport");
//		HttpStatus httpStatus = HttpStatus.OK;
//		Response response = new Response();
//		Map<String, Object> config = new HashMap<String, Object>();
//		Map<String, Object> result = new HashMap<String, Object>();
//		Map<String, Object> error = new HashMap<String, Object>();
//		try {
//			ReportEntity oldReportEntity = service.findOneById(id);
//			if (oldReportEntity == null) {
//				result.put("Report", null);
//				config.put("global", 0);
//				error.put("messageCode", 0);
//				error.put("message", "Report is not Exist");
//				response.setConfig(config);
//				response.setResult(result);
//				response.setError(error);
//				return new ResponseEntity<Response>(response, httpStatus);
//			}
//		   
//			ReportEntity reportEntity = converter.toEntity(reportDtIn, oldReportEntity);
//			System.out.println("convert OK");
//			service.editReport(reportEntity);
//			System.out.println("add OK");
//			ReportDtOut dto = converter.toDTO(reportEntity);
//			System.out.println("convert OK");
//			result.put("Report", dto);
//			config.put("global", 0);
//			error.put("messageCode", 0);
//			error.put("message", "Report edit successfuly");
//		} catch (Exception e) {
//			result.put("Report", null);
//			config.put("global", 0);
//			error.put("messageCode", 1);
//			error.put("message", "Report edit fail");
//		}
//
//		response.setConfig(config);
//		response.setResult(result);
//		response.setError(error);
//		return new ResponseEntity<Response>(response, httpStatus);
//	}

}
