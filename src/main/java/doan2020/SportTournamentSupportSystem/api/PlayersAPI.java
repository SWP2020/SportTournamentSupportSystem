package doan2020.SportTournamentSupportSystem.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import doan2020.SportTournamentSupportSystem.converter.PlayerConverter;
import doan2020.SportTournamentSupportSystem.dto.PlayerDTO;
import doan2020.SportTournamentSupportSystem.entity.PlayerEntity;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.IPlayerService;

@RestController
@CrossOrigin
@RequestMapping("/players")
public class PlayersAPI {
	
	@Autowired
	private PlayerConverter converter;
	
	@Autowired
	private IPlayerService service;
	
	
	@GetMapping("/getByTeamId")
	public ResponseEntity<Response> getByTeamId(@RequestParam(value = "teamId", required = false) Long teamId) {
		System.out.println("PlayersAPI: getByTeamId: start");
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		List<PlayerEntity> entities = new ArrayList<PlayerEntity>();
		List<PlayerDTO> dtos = new ArrayList<PlayerDTO>();
		try {
			if (teamId == null) { // id null
				result.put("Players", dtos);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "Required param teamId");
			} else { // id not null
				
				entities = (List<PlayerEntity>) service.findByTeamId(teamId);
				
				if (entities == null) { // not found
					result.put("Players", dtos);
					config.put("Global", 0);
					error.put("MessageCode", 1);
					error.put("Message", "Not found");
				} else { // found
					for (PlayerEntity entity: entities) {
						PlayerDTO dto = converter.toDTO(entity);
						dtos.add(dto);
					} 
					
					result.put("Players", dtos);
					config.put("Global", 0);
					error.put("MessageCode", 0);
					error.put("Message", "Found");
				}
			}
			System.out.println("PlayersAPI: getByTeamId: no exception");
		} catch (Exception e) {
			System.out.println("PlayersAPI: getByTeamId: has exception");
			result.put("Players", dtos);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("PlayersAPI: getByTeamId: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

	
	
}
