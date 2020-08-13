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

import doan2020.SportTournamentSupportSystem.converter.RegisterFormConverter;
import doan2020.SportTournamentSupportSystem.dto.RegisterFormDTO;
import doan2020.SportTournamentSupportSystem.entity.RegisterFormEntity;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.IRegisterFormService;

@RestController
@CrossOrigin
@RequestMapping("/registerForm")
public class RegisterFormAPI {
	
	@Autowired
	private RegisterFormConverter converter;
	
	@Autowired
	private IRegisterFormService service;
	
	
	@GetMapping("")
	public ResponseEntity<Response> getRegisterForm(@RequestParam(value = "id", required = false) Long id) {
		System.out.println("RegisterFormAPI: getRegisterForm: no exception");
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		RegisterFormEntity registerFormEntity = new RegisterFormEntity();
		RegisterFormDTO registerFormDTO = new RegisterFormDTO();
		try {
			if (id == null) { // id null
				result.put("RegisterForm", null);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "Required param id");
			} else { // id not null
				
				registerFormEntity = service.findOneById(id);
				
				if (registerFormEntity == null) { // not found
					result.put("RegisterForm", null);
					config.put("Global", 0);
					error.put("MessageCode", 1);
					error.put("Message", "Not found");
				} else { // found
					
					registerFormDTO = converter.toDTO(registerFormEntity);
					
					result.put("RegisterForm", registerFormDTO);
					config.put("Global", 0);
					error.put("MessageCode", 0);
					error.put("Message", "Found");
				}
			}
			System.out.println("RegisterFormAPI: getRegisterForm: no exception");
		} catch (Exception e) {
			System.out.println("RegisterFormAPI: getRegisterForm: has exception");
			result.put("RegisterForm", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("RegisterFormAPI: getRegisterForm: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

	/*
	 * Tao moi mot RegisterForm
	 * 
	 */
	@PostMapping
	@CrossOrigin
	public ResponseEntity<Response> createRegisterForm(@RequestBody RegisterFormDTO newRegisterForm) {
		System.out.println("RegisterFormAPI: createRegisterForm: start");
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		RegisterFormEntity registerFormEntity = new RegisterFormEntity();
		
		try {
			registerFormEntity = converter.toEntity(newRegisterForm);
			
			registerFormEntity = service.create(registerFormEntity);
			
			RegisterFormDTO dto = converter.toDTO(registerFormEntity);

			result.put("RegisterForm", dto);
			config.put("Global", 0);
			error.put("MessageCode", 0);
			error.put("Message", "RegisterForm create successfuly");
			System.out.println("RegisterFormAPI: createRegisterForm: no exception");
		} catch (Exception e) {
			System.out.println("RegisterFormAPI: createRegisterForm: has exception");
			result.put("RegisterForm", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("RegisterFormAPI: createRegisterForm: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

	/*
	 * Edit mot RegisterForm
	 * 
	 */
	@PutMapping
	@CrossOrigin
	public ResponseEntity<Response> editRegisterForm(
			@RequestBody RegisterFormDTO registerForm,
			@RequestParam Long id) {
		System.out.println("RegisterFormAPI: editRegisterForm: start");
		
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		RegisterFormEntity registerFormEntity = new RegisterFormEntity();
		
		try {
			registerFormEntity = converter.toEntity(registerForm);
			
			registerFormEntity = service.update(id, registerFormEntity);
			
			RegisterFormDTO dto = converter.toDTO(registerFormEntity);

			result.put("RegisterForm", dto);
			config.put("Global", 0);
			error.put("MessageCode", 0);
			error.put("Message", "RegisterForm update successfuly");
			System.out.println("RegisterFormAPI: editRegisterForm: no exception");
		} catch (Exception e) {
			System.out.println("RegisterFormAPI: editRegisterForm: has exception");
			result.put("RegisterForm", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		response.setConfig(config);
		response.setResult(result);
		response.setError(error);
		System.out.println("RegisterFormAPI: editRegisterForm: finish");
		return new ResponseEntity<Response>(response, httpStatus);
	}

}
