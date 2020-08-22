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

import doan2020.SportTournamentSupportSystem.converter.RegisterFormConverter;
import doan2020.SportTournamentSupportSystem.dto.RegisterFormDTO;
import doan2020.SportTournamentSupportSystem.entity.RegisterFormEntity;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.IRegisterFormService;

@RestController
@CrossOrigin
@RequestMapping("/registerForms")
public class RegistersFormAPI {

	@Autowired
	private RegisterFormConverter converter;

	@Autowired
	private IRegisterFormService service;

	@GetMapping("")
	public ResponseEntity<Response> getAllRegisterForm(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit) {
		System.out.println("RegistersFormAPI: getAllRegisterForm: start");

		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		HttpStatus httpStatus = HttpStatus.OK;

		Collection<RegisterFormEntity> findPage = new ArrayList<>();
		List<RegisterFormDTO> findPageDTO = new ArrayList<>();

		try {
			if (limit == null)
				limit = 10;
			if (limit == 0)
				limit = 10;
			if (page == null)
				page = 1;

			Pageable pageable = PageRequest.of(page - 1, limit);
			findPage = service.findAll(pageable);

			for (RegisterFormEntity entity : findPage) {
				RegisterFormDTO dto = converter.toDTO(entity);
				findPageDTO.add(dto);
			}

			result.put("RegisterForms", findPageDTO);
			error.put("MessageCode", 0);
			error.put("Message", "Get page successfully");

			System.out.println("RegistersFormAPI: getAllRegisterForm: no exception");
		} catch (Exception e) {
			System.out.println("RegistersFormAPI: getAllRegisterForm: has exception");
			result.put("Users", findPageDTO);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("RegistersFormAPI: getAllRegisterForm: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

	@GetMapping("/getByCompetitionId")
	public ResponseEntity<Response> getByCompetitionId(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam("competitionId") Long competitionId) {
		System.out.println("RegistersFormAPI: getByCompetitionId: start");

		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		HttpStatus httpStatus = HttpStatus.OK;

		Collection<RegisterFormEntity> findPage = new ArrayList<>();
		List<RegisterFormDTO> findPageDTO = new ArrayList<>();

		try {
			if (limit == null)
				limit = 10;
			if (limit == 0)
				limit = 10;
			if (page == null)
				page = 1;

			if (competitionId == null) {// matchId null
				result.put("RegisterForms", null);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "Required param competitionId");
			} else {// matchId not null
				Pageable pageable = PageRequest.of(page - 1, limit);
				findPage = service.findByCompetitionId(pageable, competitionId);

				for (RegisterFormEntity entity : findPage) {
					RegisterFormDTO dto = converter.toDTO(entity);
					findPageDTO.add(dto);
				}

				result.put("RegisterForms", findPageDTO);
				error.put("MessageCode", 0);
				error.put("Message", "Get page successfully");
			}
			System.out.println("RegistersFormAPI: getByCompetitionId: no exception");
		} catch (Exception e) {
			System.out.println("RegistersFormAPI: getByCompetitionId: has exception");
			result.put("Users", findPageDTO);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("RegistersFormAPI: getByCompetitionId: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

	@GetMapping("/getByTeamId")
	public ResponseEntity<Response> getByTeamId(@RequestParam(value = "teamId") Long teamId) {
		System.out.println("RegistersFormAPI: getByTeamId: no exception");
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		Collection<RegisterFormEntity> registerFormEnties = new ArrayList<RegisterFormEntity>();
		List<RegisterFormDTO> registerDTOs = new ArrayList<RegisterFormDTO>();
		try {

			if (teamId == null) {// teamId null
				result.put("RegisterForms", registerDTOs);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "Required param teamId");
			} else {// teamId not null

				registerFormEnties = service.findByTeamId(teamId);
				if (registerFormEnties.isEmpty()) { // not found
					result.put("RegisterForms", null);
					config.put("Global", 0);
					error.put("MessageCode", 1);
					error.put("Message", "Not found");
				} else { // found

					for (RegisterFormEntity entity : registerFormEnties) {
						RegisterFormDTO dto = converter.toDTO(entity);
						registerDTOs.add(dto);
					}

					result.put("RegisterForms", registerDTOs);
					config.put("Global", 0);
					error.put("MessageCode", 0);
					error.put("Message", "Found");
				}
			}
			System.out.println("RegistersFormAPI: getByTeamId: no exception");
		} catch (Exception e) {
			System.out.println("RegistersFormAPI: getByTeamId: has exception");
			result.put("RegisterForm", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("RegistersFormAPI: getByTeamId: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

	@GetMapping("/getByCompetitionIdAndStatus")
	public ResponseEntity<Response> getByCompetitionSettingIdAndStatus(
			@RequestParam(value = "competitionId") Long competitionId,
			@RequestParam(value = "status") String status) {
		System.out.println("RegistersFormAPI: getByCompetitionSettingIdAndStatus: no exception");
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		Collection<RegisterFormEntity> registerFormEnties = new ArrayList<RegisterFormEntity>();
		List<RegisterFormDTO> registerDTOs = new ArrayList<RegisterFormDTO>();
		try {

			if (competitionId == null || status == null) {// competitionSettingId or status null
				result.put("RegisterForms", registerDTOs);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "Required param competitionSettingId or status");
			} else {// competitionSettingId and status not null

				registerFormEnties = service.findByCompetitionIdAndStatus(competitionId, status);

				if (registerFormEnties.isEmpty()) { // not found
					result.put("RegisterForms", null);
					config.put("Global", 0);
					error.put("MessageCode", 1);
					error.put("Message", "Not found");
				} else { // found

					for (RegisterFormEntity entity : registerFormEnties) {
						RegisterFormDTO dto = converter.toDTO(entity);
						registerDTOs.add(dto);
					}

					result.put("RegisterForms", registerDTOs);
					config.put("Global", 0);
					error.put("MessageCode", 0);
					error.put("Message", "Found");
				}
			}
			System.out.println("RegistersFormAPI: getByCompetitionSettingIdAndStatus: no exception");
		} catch (Exception e) {
			System.out.println("RegistersFormAPI: getByCompetitionSettingIdAndStatus: has exception");
			result.put("RegisterForm", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("RegistersFormAPI: getByCompetitionSettingIdAndStatus: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

	@GetMapping("/getByCompetitionSettingId")
	public ResponseEntity<Response> getByCompetitionSettingId(
			@RequestParam(value = "competitionSettingId") Long competitionSettingId) {
		System.out.println("RegistersFormAPI: getByCompetitionSettingId: no exception");
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		Collection<RegisterFormEntity> registerFormEnties = new ArrayList<RegisterFormEntity>();
		List<RegisterFormDTO> registerDTOs = new ArrayList<RegisterFormDTO>();
		try {

			if (competitionSettingId == null) {// competitionSettingId null
				result.put("RegisterForms", registerDTOs);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "Required param competitionSettingId");
			} else {// competitionSettingId not null

				registerFormEnties = service.findByCompetitionSettingId(competitionSettingId);

				if (registerFormEnties.isEmpty()) { // not found
					result.put("RegisterForms", null);
					config.put("Global", 0);
					error.put("MessageCode", 1);
					error.put("Message", "Not found");
				} else { // found

					for (RegisterFormEntity entity : registerFormEnties) {
						RegisterFormDTO dto = converter.toDTO(entity);
						registerDTOs.add(dto);
					}

					result.put("RegisterForms", registerDTOs);
					config.put("Global", 0);
					error.put("MessageCode", 0);
					error.put("Message", "Found");
				}
			}
			System.out.println("RegistersFormAPI: getByCompetitionSettingId: no exception");
		} catch (Exception e) {
			System.out.println("RegistersFormAPI: getByCompetitionSettingId: has exception");
			result.put("RegisterForm", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("RegistersFormAPI: getByCompetitionSettingId: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

}
