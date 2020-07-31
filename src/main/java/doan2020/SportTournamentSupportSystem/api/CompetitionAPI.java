package doan2020.SportTournamentSupportSystem.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import doan2020.SportTournamentSupportSystem.converter.CompetitionConverter;
import doan2020.SportTournamentSupportSystem.dtIn.CreateCompetitionDtIn;
import doan2020.SportTournamentSupportSystem.dtIn.EditCompetitionDtIn;
import doan2020.SportTournamentSupportSystem.dtOut.CompetitionDtOut;
import doan2020.SportTournamentSupportSystem.entity.CompetitionEntity;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.ICompetitionService;

@RestController
@RequestMapping("/competitions")
public class CompetitionAPI {

	@Autowired
	private ICompetitionService CompetitionService;

	@Autowired
	private CompetitionConverter competitionConverter;

	/* ----------------GetCompetiton ------------------------ */
	@GetMapping("/{id}")
	public ResponseEntity<Response> GetCompetiton(@PathVariable("id") Long id) {
		HttpStatus httpStatus = null;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		CompetitionEntity competitionEntity = new CompetitionEntity();
		CompetitionDtOut competitionDtOut = new CompetitionDtOut();
		try {
			competitionEntity = CompetitionService.findOneById(id);

			competitionDtOut = competitionConverter.toDTO(competitionEntity);

			result.put("competition", competitionDtOut);

			error.put("messageCode", true);
			error.put("message", "get Competition Successfully");

			httpStatus = HttpStatus.OK;

		} catch (Exception e) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		return new ResponseEntity<Response>(response, httpStatus);
	}

	/* ---------------- find all Competition ------------------------ */
	@GetMapping
	public ResponseEntity<Response> GetAllCompetiton() {
		HttpStatus httpStatus = null;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		List<CompetitionEntity> competitionEntitys = new ArrayList<CompetitionEntity>();
		List<CompetitionDtOut> competitionDtOuts = new ArrayList<CompetitionDtOut>();
		try {
			competitionEntitys = CompetitionService.findAllCompetition();

			for (CompetitionEntity competitionEntity : competitionEntitys) {
				CompetitionDtOut competitionDtOut = competitionConverter.toDTO(competitionEntity);
				competitionDtOuts.add(competitionDtOut);
			}

			result.put("listCompetition", competitionDtOuts);

			error.put("messageCode", true);
			error.put("message", "get all Competition Successfully");

			httpStatus = HttpStatus.OK;

		} catch (Exception e) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		return new ResponseEntity<Response>(response, httpStatus);
	}

	/* ---------------- add new Competition ------------------------ */
	@PostMapping
	public ResponseEntity<Response> createCompetition(@RequestBody CreateCompetitionDtIn competitionDtIn) {
		HttpStatus httpStatus = null;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		CompetitionEntity competitionEntity = new CompetitionEntity();

		try {
			competitionEntity = competitionConverter.toEntity(competitionDtIn);

			CompetitionService.addCompetition(competitionEntity);

			error.put("messageCode", true);
			error.put("message", "Add Competition Successfully");

			httpStatus = HttpStatus.OK;

		} catch (Exception e) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		return new ResponseEntity<Response>(response, httpStatus);
	}

	/* ---------------- Edit Competition ------------------------ */
	@PutMapping("/{id}")
	public ResponseEntity<Response> editCompetition(@PathVariable("id") Long id,
			@RequestBody EditCompetitionDtIn editCompetitionDtIn) {
		HttpStatus httpStatus = null;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		try {
			CompetitionEntity competitionEntity = new CompetitionEntity();
			CompetitionEntity oldCompetitionEntity = new CompetitionEntity();
			if (id != null) {
				oldCompetitionEntity = CompetitionService.findOneById(id);
				if (oldCompetitionEntity != null) {
					competitionEntity = competitionConverter.toEntityUpdate(editCompetitionDtIn, oldCompetitionEntity);
					CompetitionService.editCompetition(competitionEntity);
					httpStatus = HttpStatus.OK;
					error.put("messageCode", true);
					error.put("message", "Edit Competition Successfull");
				} else {
					httpStatus = HttpStatus.NOT_FOUND;
					error.put("messageCode", false);
					error.put("message", "Not Find Competition to update ");
				}
			} else {
				httpStatus = HttpStatus.NOT_FOUND;
				error.put("messageCode", false);
				error.put("message", "competitionId is not enter");
			}
		} catch (Exception ex) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		return new ResponseEntity<Response>(response, httpStatus);
	}

	/* delete one User */
	@DeleteMapping("/{id}")
	public ResponseEntity<Response> deleteUser(@PathVariable("id") Long id) {
		Response response = new Response();
		HttpStatus httpStatus = null;
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		CompetitionEntity competitionEntity = new CompetitionEntity();
		try {
			if (id != null) {
				competitionEntity = CompetitionService.findOneById(id);
				if (competitionEntity != null) {
					CompetitionService.deleteCompetition(competitionEntity);
					httpStatus = HttpStatus.OK;
					error.put("messageCode", true);
					error.put("message", "Delete Competition Successfull");
				} else {
					httpStatus = HttpStatus.NOT_FOUND;
					error.put("messageCode", false);
					error.put("message", "Not Find Competition to delete ");
				}
			} else {
				httpStatus = HttpStatus.NOT_FOUND;
				error.put("messageCode", false);
				error.put("message", "CompetitionId is not enter");
			}
		} catch (Exception e) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		return new ResponseEntity<Response>(response, httpStatus);
	}
}
