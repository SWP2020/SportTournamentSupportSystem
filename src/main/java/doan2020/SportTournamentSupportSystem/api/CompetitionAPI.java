package doan2020.SportTournamentSupportSystem.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import doan2020.SportTournamentSupportSystem.dtIn.EditProfileDtIn;
import doan2020.SportTournamentSupportSystem.dtIn.RegisterDtIn;
import doan2020.SportTournamentSupportSystem.entity.CompetitionEntity;
import doan2020.SportTournamentSupportSystem.entity.UserEntity;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.ICompetitionService;

@RestController
@RequestMapping("/competitions")
public class CompetitionAPI {
	
	@Autowired
	private ICompetitionService CompetitionService;
	
	/* ---------------- add new Competition ------------------------ */
	@PostMapping
	public ResponseEntity<Response> createUser(@RequestBody RegisterDtIn registerDtIn) {
		HttpStatus httpStatus = null;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		try {
			httpStatus = HttpStatus.OK;

		} catch (Exception e) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Response>(response, httpStatus);
	}

	/* ---------------- Edit Competition ------------------------ */
	@PutMapping("/{id}")
	public ResponseEntity<Response> editCompetition(@PathVariable("id") Long id,
			@RequestBody EditProfileDtIn editProfileDtIn) {
		HttpStatus httpStatus = null;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		try {
			CompetitionEntity competitionEntity = new CompetitionEntity();
			CompetitionEntity oldCompetitionEntity = new CompetitionEntity();
			if (id != null) {
				oldCompetitionEntity = CompetitionService.findOneByID(id);
				if (oldCompetitionEntity != null) {
//					userEntity = userConverter.toEntity(editProfileDtIn, oldCompetitionEntity);
//					userService.editUser(competitionEntity);
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
				competitionEntity = CompetitionService.findOneByID(id);
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
		return new ResponseEntity<Response>(response, httpStatus);
	}
}
