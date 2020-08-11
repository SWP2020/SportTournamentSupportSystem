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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import doan2020.SportTournamentSupportSystem.converter.PlayerConverter;
import doan2020.SportTournamentSupportSystem.converter.PlayerConverter;
import doan2020.SportTournamentSupportSystem.dtIn.PlayerDtIn;
import doan2020.SportTournamentSupportSystem.dtOut.PlayerDtOut;
import doan2020.SportTournamentSupportSystem.dto.PlayerDTO;
import doan2020.SportTournamentSupportSystem.entity.PlayerEntity;
import doan2020.SportTournamentSupportSystem.entity.PlayerEntity;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.IPlayerService;
import doan2020.SportTournamentSupportSystem.service.IPlayerService;

@RestController
@CrossOrigin
@RequestMapping("/players")
public class PlayerAPI {
	
	@Autowired
	private PlayerConverter converter;
	
	@Autowired
	private IPlayerService service;
	
	
	@GetMapping("")
	public ResponseEntity<Response> getPlayer(@RequestParam(value = "id", required = false) Long id) {
		System.out.println("PlayerAPI: getPlayer: no exception");
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		PlayerEntity playerEntity = new PlayerEntity();
		PlayerDTO playerDTO = new PlayerDTO();
		try {
			if (id == null) { // id null
				result.put("Player", null);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "Required param id");
			} else { // id not null
				
				playerEntity = service.findOneById(id);
				
				if (playerEntity == null) { // not found
					result.put("Player", null);
					config.put("Global", 0);
					error.put("MessageCode", 1);
					error.put("Message", "Not found");
				} else { // found
					
					playerDTO = converter.toDTO(playerEntity);
					
					result.put("Player", playerDTO);
					config.put("Global", 0);
					error.put("MessageCode", 0);
					error.put("Message", "Found");
				}
			}
			System.out.println("PlayerAPI: getPlayer: no exception");
		} catch (Exception e) {
			System.out.println("PlayerAPI: getPlayer: has exception");
			result.put("Player", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("PlayerAPI: getPlayer: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

	/*
	 * Tao moi mot Player
	 * 
	 */
	@PostMapping
	@CrossOrigin
	public ResponseEntity<Response> createPlayer(@RequestBody PlayerDTO newPlayer) {
		System.out.println("PlayerAPI: createPlayer: start");
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		PlayerEntity playerEntity = new PlayerEntity();
		
		try {
			playerEntity = converter.toEntity(newPlayer);
			
			playerEntity = service.create(playerEntity);
			
			PlayerDTO dto = converter.toDTO(playerEntity);

			result.put("Player", dto);
			config.put("Global", 0);
			error.put("MessageCode", 0);
			error.put("Message", "Player create successfuly");
			System.out.println("PlayerAPI: createPlayer: no exception");
		} catch (Exception e) {
			System.out.println("PlayerAPI: createPlayer: has exception");
			result.put("Player", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("PlayerAPI: createPlayer: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

	/*
	 * Edit mot Player
	 * 
	 */
	@PutMapping
	@CrossOrigin
	public ResponseEntity<Response> editPlayer(
			@RequestBody PlayerDTO player,
			@RequestParam Long id) {
		System.out.println("PlayerAPI: editPlayer: start");
		
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		PlayerEntity playerEntity = new PlayerEntity();
		
		try {
			playerEntity = converter.toEntity(player);
			
			playerEntity = service.update(id, playerEntity);
			
			PlayerDTO dto = converter.toDTO(playerEntity);

			result.put("Player", dto);
			config.put("Global", 0);
			error.put("MessageCode", 0);
			error.put("Message", "Player update successfuly");
			System.out.println("PlayerAPI: editPlayer: no exception");
		} catch (Exception e) {
			System.out.println("PlayerAPI: editPlayer: has exception");
			result.put("Player", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("PlayerAPI: editPlayer: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}
	
}
