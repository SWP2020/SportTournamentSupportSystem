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

import doan2020.SportTournamentSupportSystem.converter.CompetitionSettingConverter;
import doan2020.SportTournamentSupportSystem.dto.CompetitionSettingDTO;
import doan2020.SportTournamentSupportSystem.entity.CompetitionSettingEntity;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.ICompetitionSettingService;

@RestController
@CrossOrigin
@RequestMapping("/competitionSettings")
public class CompetitionSettingsAPI {

	@Autowired
	private CompetitionSettingConverter converter;

	@Autowired
	private ICompetitionSettingService service;

	@GetMapping("")
	public ResponseEntity<Response> getAllCompetitionSetting(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit) {
		System.out.println("CompetitionSettingsAPI: getAllCompetitionSetting: start");

		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		HttpStatus httpStatus = HttpStatus.OK;

		Collection<CompetitionSettingEntity> findPage = new ArrayList<>();
		List<CompetitionSettingDTO> findPageDTO = new ArrayList<>();

		try {
			if (limit == null)
				limit = 10;
			if (limit == 0)
				limit = 10;
			if (page == null)
				page = 1;

			Pageable pageable = PageRequest.of(page - 1, limit);
			findPage = service.findAll(pageable);

			for (CompetitionSettingEntity entity : findPage) {
				CompetitionSettingDTO dto = converter.toDTO(entity);
				findPageDTO.add(dto);
			}

			result.put("CompetitionSettings", findPageDTO);
			error.put("MessageCode", 0);
			error.put("Message", "Get page successfully");

			System.out.println("CompetitionSettingsAPI: getAllCompetitionSetting: no exception");
		} catch (Exception e) {
			System.out.println("CompetitionSettingsAPI: getAllCompetitionSetting: has exception");
			result.put("Users", findPageDTO);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("CompetitionSettingsAPI: getAllCompetitionSetting: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

	@GetMapping("/getByCompetitionId")
	public ResponseEntity<Response> getByCompetitionId(@RequestParam("competitionId") Long competitionId) {
		System.out.println("CompetitionSettingsAPI: getByCompetitionId: start");

		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		HttpStatus httpStatus = HttpStatus.OK;
		Collection<CompetitionSettingEntity> findPage = new ArrayList<>();
		List<CompetitionSettingDTO> findPageDTO = new ArrayList<>();
		try {

			if (competitionId == null) {// competitionId null
				result.put("CompetitionSettings", null);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "Required param competitionId");
			} else {// competitionId not null

				findPage = service.findByCompetitionId(competitionId);

				for (CompetitionSettingEntity entity : findPage) {
					CompetitionSettingDTO dto = converter.toDTO(entity);
					findPageDTO.add(dto);
				}

				result.put("CompetitionSettings", findPageDTO);
				error.put("MessageCode", 0);
				error.put("Message", "Get page successfully");
			}
			System.out.println("CompetitionSettingsAPI: getByCompetitionId: no exception");
		} catch (Exception e) {
			System.out.println("CompetitionSettingsAPI: getByCompetitionId: has exception");
			result.put("Users", findPageDTO);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("CompetitionSettingsAPI: getByCompetitionId: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}
}
