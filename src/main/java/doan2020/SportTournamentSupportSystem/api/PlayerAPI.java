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
import doan2020.SportTournamentSupportSystem.dtIn.PlayerDtIn;
import doan2020.SportTournamentSupportSystem.dtOut.PlayerDtOut;
import doan2020.SportTournamentSupportSystem.entity.PlayerEntity;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.IPlayerService;

@RestController
@CrossOrigin
@RequestMapping("/players")
public class PlayerAPI {
	
	@Autowired
	private IPlayerService service;

	@Autowired
	private PlayerConverter converter;
     
	@GetMapping("/getOne")
	public ResponseEntity<Response> getOnePlayer(@RequestParam("id") Long id){
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();

		if (id == null) {
			result.put("player", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "Required player id !");
			httpStatus = HttpStatus.OK;
			response.setConfig(config);
			response.setResult(result);
			response.setError(error);
			return new ResponseEntity<Response>(response, httpStatus);
		}

		PlayerEntity res;

			res = service.findOneById(id);

		if (res == null) {
			result.put("Player", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "Player is not exist");
			response.setConfig(config);
			response.setResult(result);
			response.setError(error);
			return new ResponseEntity<Response>(response, httpStatus);
		}

		try {

			PlayerDtOut resDTO = converter.toDTO(res);
			System.out.println("Convert OK");

			result.put("Player", resDTO);
			config.put("global", 0);
			error.put("messageCode", 0);
			error.put("message", "Found");

		} catch (Exception e) {
			result.put("Player", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "Player is not exist");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);

		return new ResponseEntity<Response>(response, httpStatus);
		
	}
	
	@GetMapping("/getAllbyTeamId")
	public ResponseEntity<Response> getAllbyTeamId(@RequestParam("id") Long id){
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		List<PlayerDtOut> playerDtOuts = new ArrayList<PlayerDtOut>();
		if (id == null) {
			result.put("player", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "Required player id !");
			httpStatus = HttpStatus.OK;
			response.setConfig(config);
			response.setResult(result);
			response.setError(error);
			return new ResponseEntity<Response>(response, httpStatus);
		}

		List<PlayerEntity> res = new ArrayList<PlayerEntity>();

			res = service.findByTeamId(id);

		if (res.isEmpty()) {
			result.put("Player", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "Player is not exist");
			response.setConfig(config);
			response.setResult(result);
			response.setError(error);
			return new ResponseEntity<Response>(response, httpStatus);
		}

		try {
            for(PlayerEntity playerEntity: res) {
			PlayerDtOut resDTO = converter.toDTO(playerEntity);
			
			playerDtOuts.add(resDTO);
			
            }
            System.out.println("Convert OK");

			result.put("Players", playerDtOuts);
			config.put("global", 0);
			error.put("messageCode", 0);
			error.put("message", "Found");

		} catch (Exception e) {
			result.put("Player", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "Player is not exist");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);

		return new ResponseEntity<Response>(response, httpStatus);
		
	}
	
	/*
	 * Tao moi mot Player
	 * 
	 */
	@PostMapping
	@CrossOrigin
	public ResponseEntity<Response> createPlayer(@RequestBody PlayerDtIn playerDtIn) {
		System.out.println("createPlayer");
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		try {
			PlayerEntity playerEntity = converter.toEntity(playerDtIn);
			System.out.println("convert OK");
			service.addOnePlayer(playerEntity);
			System.out.println("add OK");
			PlayerDtOut dto = converter.toDTO(playerEntity);
			System.out.println("convert OK");
			result.put("player", dto);
			config.put("global", 0);
			error.put("messageCode", 0);
			error.put("message", "player create successfuly");
		} catch (Exception e) {
			result.put("player", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "player create fail");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		return new ResponseEntity<Response>(response, httpStatus);
	}
	
	/*
	 * edit mot Player
	 * 
	 */
	@PutMapping
	@CrossOrigin
	public ResponseEntity<Response> editPlayer(@RequestBody PlayerDtIn playerDtIn, @RequestParam("id") Long id) {
		System.out.println("editTournament");
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		try {
			PlayerEntity oldUserEntity = service.findOneById(id);
			if (oldUserEntity == null) {
				result.put("player", null);
				config.put("global", 0);
				error.put("messageCode", 0);
				error.put("message", "player is not Exist");
				response.setConfig(config);
				response.setResult(result);
				response.setError(error);
				return new ResponseEntity<Response>(response, httpStatus);
			}
		   
			PlayerEntity playerEntity = converter.toEntity(playerDtIn, oldUserEntity);
			System.out.println("convert OK");
			service.editPlayer(playerEntity);
			System.out.println("add OK");
			PlayerDtOut dto = converter.toDTO(playerEntity);
			System.out.println("convert OK");
			result.put("player", dto);
			config.put("global", 0);
			error.put("messageCode", 0);
			error.put("message", "player edit successfuly");
		} catch (Exception e) {
			result.put("player", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "player edit fail");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		return new ResponseEntity<Response>(response, httpStatus);
	}
	
}
