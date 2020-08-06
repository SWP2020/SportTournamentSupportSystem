package doan2020.SportTournamentSupportSystem.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import doan2020.SportTournamentSupportSystem.converter.UserConverter;
import doan2020.SportTournamentSupportSystem.dtIn.RegisterDtIn;
import doan2020.SportTournamentSupportSystem.dto.UserDTO;
import doan2020.SportTournamentSupportSystem.entity.RoleEntity;
import doan2020.SportTournamentSupportSystem.entity.UserEntity;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.IRoleService;
import doan2020.SportTournamentSupportSystem.service.IUserService;
import doan2020.SportTournamentSupportSystem.service.impl.VerificationTokenService;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserAPI {
	@Autowired
	private IUserService userService;

	@Autowired
	private IRoleService roleService;

	@Autowired
	private VerificationTokenService verificationTokenService;

	@Autowired
	private UserConverter userConverter;

	/* get One User */

	@GetMapping("")
	public ResponseEntity<Response> getUserInfor(@RequestParam(value = "id") Long id) {
		Response response = new Response();
		HttpStatus httpStatus = HttpStatus.OK;
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		UserEntity user = new UserEntity();
		try {
			user = userService.findOneById(id);
			if (user == null) {
				result.put("user", null);
				config.put("global", 0);
				error.put("messageCode", 1);
				error.put("message", "user is not exist");
				response.setConfig(config);
				response.setResult(result);
				response.setError(error);
				return new ResponseEntity<Response>(response, httpStatus);
			}
			UserDTO userDTO = userConverter.toDTO(user);
			
			int countOfTournament = user.getTournaments().size();
			int countOfTeam = user.getTeams().size();
			
			result.put("User", userDTO);
			result.put("countOfTournament", countOfTournament);
			result.put("countOfTeam", countOfTeam);
			error.put("messageCode", 0);
			error.put("message", "get User successfully");
		} catch (Exception e) {
			result.put("listUsers", null);
			error.put("messageCode", 1);
			error.put("message", "get User fail");
		}
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);

		return new ResponseEntity<Response>(response, httpStatus);
	}

	/* ---------------- register NEW USER ------------------------ */
	@PostMapping
	public ResponseEntity<Response> createUser(@RequestBody UserDTO userDTO) {
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		try {
			UserEntity user = userConverter.toEntity(userDTO);
			List<UserEntity> listUser = (List<UserEntity>) userService.findAll();
			for (UserEntity userExist : listUser) {
				if (StringUtils.equals(user.getUsername(), userExist.getUsername())) {
					error.put("messageCode", 1);
					error.put("message", "User is Exists");
					response.setError(error);
					response.setResult(result);
					response.setConfig(config);
					return new ResponseEntity<Response>(response, httpStatus);
				}

				if (StringUtils.equals(user.getEmail(), userExist.getEmail())) {
					error.put("messageCode", 1);
					error.put("message", "Email is Exists");
					response.setError(error);
					response.setResult(result);
					response.setConfig(config);
					return new ResponseEntity<Response>(response, httpStatus);
				}
			}
			RoleEntity roleEntity = roleService.findOneByName("ROLE_USER");
			if (roleEntity != null)
				user.setRole(roleEntity);
			user.setStatus("not active");

			userService.create(user);
			
			userDTO = userConverter.toDTO(user);

			verificationTokenService.createVerification(user.getEmail(), user.getUsername());

			result.put("User", userDTO);

			error.put("messageCode", 0);
			error.put("message", "Register successfully");
			httpStatus = HttpStatus.OK;

		} catch (Exception e) {
			result.put("User", null);
			error.put("messageCode", 1);
			error.put("message", "register  User fail");
		}
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		return new ResponseEntity<Response>(response, httpStatus);
	}

	/* ---------------- Edit Profile User ------------------------ */
	@PutMapping("")
	public ResponseEntity<Response> editUser(@RequestParam(value = "id") Long id,
			@RequestBody UserDTO dto) {
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		try {
			UserEntity userEntity = new UserEntity();
			if (id != null) {
					userEntity = userConverter.toEntity(dto);
					userService.update(id, userEntity);
					error.put("messageCode", 0);
					error.put("message", "Edit Profile User Successfull");
				} else {
					error.put("messageCode", 1);
					error.put("message", "required user id");
				}
		} catch (Exception ex) {
			result.put("User", null);
			error.put("messageCode", 1);
			error.put("message", "edit  User fail");
		}
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		return new ResponseEntity<Response>(response, httpStatus);
	}

	/* delete one User */
	@DeleteMapping("")
	public ResponseEntity<Response> deleteUser(@RequestParam(value = "id") Long id) {
		Response response = new Response();
		HttpStatus httpStatus = null;
		httpStatus = HttpStatus.OK;
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		try {
			if (id != null) {
					userService.delete(id);
					error.put("messageCode", 0);
					error.put("message", "Delete User Successfull");
				} else {
					httpStatus = HttpStatus.NOT_FOUND;
					error.put("messageCode", 1);
					error.put("message", "required user id");
				}	
		} catch (Exception e) {
			result.put("User", null);
			error.put("messageCode", 1);
			error.put("message", "delete  User fail");
		}
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		return new ResponseEntity<Response>(response, httpStatus);
	}

	@PostMapping("/sendMail")
	public ResponseEntity<Response> sendMail(@RequestBody RegisterDtIn registerDtIn) {
		Response response = new Response();
		HttpStatus httpStatus = null;
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		httpStatus = HttpStatus.OK;
		try {
			if (verificationTokenService.createVerification(registerDtIn.getEmail(), registerDtIn.getUsername())) {
				error.put("messageCode", 0);
				error.put("message", "Sending mail successfully");

			} else {
				error.put("messageCode", 1);
				error.put("message", "Sending mail fail");
				response.setError(error);
				response.setResult(result);
				response.setConfig(config);
				return new ResponseEntity<Response>(response, httpStatus);
			}

			

		} catch (Exception e) {
			result.put("User", null);
			error.put("messageCode", 1);
			error.put("message", "send  Mail fail");
		}
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		return new ResponseEntity<Response>(response, httpStatus);

	}
	
	@PostMapping("/forgotPassword")
	public ResponseEntity<Response> forgotPassword(@RequestBody RegisterDtIn registerDtIn){
		Response response = new Response();
		HttpStatus httpStatus = null;
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		try {
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		
		return new ResponseEntity<Response>(response, httpStatus);
	}

}
