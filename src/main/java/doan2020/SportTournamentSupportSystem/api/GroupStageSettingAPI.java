package doan2020.SportTournamentSupportSystem.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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

import doan2020.SportTournamentSupportSystem.converter.GroupStageSettingConverter;
import doan2020.SportTournamentSupportSystem.dto.GroupStageSettingDTO;
import doan2020.SportTournamentSupportSystem.dto.GroupStageSettingDTO;
import doan2020.SportTournamentSupportSystem.entity.GroupStageSettingEntity;
import doan2020.SportTournamentSupportSystem.entity.GroupStageSettingEntity;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.IGroupStageSettingService;

@RestController
@CrossOrigin
@RequestMapping("/groupStageSetting")
public class GroupStageSettingAPI {
	
	@Autowired
	private GroupStageSettingConverter converter;
	
	@Autowired
	private IGroupStageSettingService service;
	
	
	@GetMapping("")
	public ResponseEntity<Response> getGroupStageSetting(@RequestParam(value = "id", required = false) Long id) {
		System.out.println("GroupStageSettingAPI: getGroupStageSetting: no exception");
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		GroupStageSettingEntity groupStageSettingEntity = new GroupStageSettingEntity();
		GroupStageSettingDTO groupStageSettingDTO = new GroupStageSettingDTO();
		try {
			if (id == null) { // id null
				result.put("GroupStageSetting", null);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "Required param id");
			} else { // id not null
				
				groupStageSettingEntity = service.findOneById(id);
				
				if (groupStageSettingEntity == null) { // not found
					result.put("GroupStageSetting", null);
					config.put("Global", 0);
					error.put("MessageCode", 1);
					error.put("Message", "Not found");
				} else { // found
					
					groupStageSettingDTO = converter.toDTO(groupStageSettingEntity);
					
					result.put("GroupStageSetting", groupStageSettingDTO);
					config.put("Global", 0);
					error.put("MessageCode", 0);
					error.put("Message", "Found");
				}
			}
			System.out.println("GroupStageSettingAPI: getGroupStageSetting: no exception");
		} catch (Exception e) {
			System.out.println("GroupStageSettingAPI: getGroupStageSetting: has exception");
			result.put("GroupStageSetting", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("GroupStageSettingAPI: getGroupStageSetting: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}
	
	@PostMapping
	public ResponseEntity<Response> createGroupStageSetting(@RequestBody GroupStageSettingDTO newGroupStageSetting) {
		System.out.println("GroupStageSettingAPI: createGroupStageSetting: start");
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		GroupStageSettingEntity groupStageSettingEntity = new GroupStageSettingEntity();
		
		try {
			groupStageSettingEntity = converter.toEntity(newGroupStageSetting);
			
			groupStageSettingEntity = service.create(groupStageSettingEntity);
			
			GroupStageSettingDTO dto = converter.toDTO(groupStageSettingEntity);

			result.put("GroupStageSetting", dto);
			config.put("Global", 0);
			error.put("MessageCode", 0);
			error.put("Message", "GroupStageSetting create successfuly");
			System.out.println("GroupStageSettingAPI: createGroupStageSetting: no exception");
		} catch (Exception e) {
			System.out.println("GroupStageSettingAPI: createGroupStageSetting: has exception");
			result.put("GroupStageSetting", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("GroupStageSettingAPI: createGroupStageSetting: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}


	@PutMapping
	public ResponseEntity<Response> editGroupStageSetting(
			@RequestBody GroupStageSettingDTO groupStageSetting,
			@RequestParam Long id) {
		System.out.println("GroupStageSettingAPI: editGroupStageSetting: start");
		
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		GroupStageSettingEntity groupStageSettingEntity = new GroupStageSettingEntity();
		
		try {
			groupStageSettingEntity = converter.toEntity(groupStageSetting);
			
			groupStageSettingEntity = service.update(id, groupStageSettingEntity);
			
			GroupStageSettingDTO dto = converter.toDTO(groupStageSettingEntity);

			result.put("GroupStageSetting", dto);
			config.put("Global", 0);
			error.put("MessageCode", 0);
			error.put("Message", "GroupStageSetting update successfuly");
			System.out.println("GroupStageSettingAPI: editGroupStageSetting: no exception");
		} catch (Exception e) {
			System.out.println("GroupStageSettingAPI: editGroupStageSetting: has exception");
			result.put("GroupStageSetting", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("GroupStageSettingAPI: editGroupStageSetting: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}
	
}
