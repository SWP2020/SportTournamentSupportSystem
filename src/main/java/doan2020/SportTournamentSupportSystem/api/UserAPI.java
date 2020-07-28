package doan2020.SportTournamentSupportSystem.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import doan2020.SportTournamentSupportSystem.converter.UserConverter;
import doan2020.SportTournamentSupportSystem.dtIn.EditProfileDtIn;
import doan2020.SportTournamentSupportSystem.dtIn.RegisterDtIn;
import doan2020.SportTournamentSupportSystem.dtOut.UserDtOut;
import doan2020.SportTournamentSupportSystem.entity.RoleEntity;
import doan2020.SportTournamentSupportSystem.entity.UserEntity;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.IRoleService;
import doan2020.SportTournamentSupportSystem.service.IUserService;
import doan2020.SportTournamentSupportSystem.service.impl.VerificationTokenService;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserAPI {
	@Autowired
	private IUserService userService;

	@Autowired
	private IRoleService roleService;

	@Autowired
	private VerificationTokenService verificationTokenService;

	@Autowired
	private UserConverter userConverter;

	/* ---------------- GET ALL USER ------------------------ */
//	@GetMapping
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public ResponseEntity<Response> getAllUser(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit) {
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		List<UserDtOut> listUsers = new ArrayList<>();
		HttpStatus httpStatus = HttpStatus.OK;
		try {
			if (page != null && limit != null) {
				Sort sortable = Sort.by("userID").ascending();
				Pageable pageable = PageRequest.of(page - 1, limit, sortable);
				List<UserEntity> ListUserEntity = userService.findAll(pageable);

				for (UserEntity user : ListUserEntity) {
					UserDtOut userDtOut = userConverter.toDTO(user);
					listUsers.add(userDtOut);
					result.put("listUsers", listUsers);
					error.put("messageCode", true);
					error.put("message", "get List Users successfully");

					httpStatus = HttpStatus.OK;

				}
			} else {
				httpStatus = HttpStatus.NO_CONTENT;
				error.put("messageCode", 1);
				error.put("message", "can't get List Users");
			}
		} catch (Exception e) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		return new ResponseEntity<Response>(response, httpStatus);

	}

	/* get One User */

	@GetMapping("/getOne")
	public ResponseEntity<Response> getUserInfor(@RequestParam(value = "id") Long id) {
		Response response = new Response();
		HttpStatus httpStatus = HttpStatus.OK;
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		UserEntity user = new UserEntity();
		try {
			user = userService.findOneById(id);
			UserDtOut userDtOut = userConverter.toDTO(user);
			
			int countOfTournament = user.getTournaments().size();
			int countOfTeam = user.getTeams().size();
			
			result.put("User", userDtOut);
			result.put("countOfTournament", countOfTournament);
			result.put("countOfTeam", countOfTeam);
			error.put("messageCode", 0);
			error.put("message", "get User successfully");
			httpStatus = HttpStatus.OK;
		} catch (Exception e) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);

		return new ResponseEntity<Response>(response, httpStatus);
	}

	/* ---------------- register NEW USER ------------------------ */
	@PostMapping
	public ResponseEntity<Response> createUser(@RequestBody RegisterDtIn registerDtIn) {
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		try {
			UserEntity user = userConverter.toEntity(registerDtIn);
			List<UserEntity> listUser = userService.findAll();
			for (UserEntity userExist : listUser) {
				if (StringUtils.equals(user.getUsername(), userExist.getUsername())) {
					error.put("messageCode", 1);
					error.put("message", "User is Exists");
				}

				if (StringUtils.equals(user.getEmail(), userExist.getEmail())) {
					error.put("messageCode", 1);
					error.put("message", "Email is Exists");
				}
			}
			RoleEntity roleEntity = roleService.findOneByName("ROLE_USER");
			if (roleEntity != null)
				user.setRole(roleEntity);
			user.setActive(false);

			userService.addNewUsers(user);

			verificationTokenService.createVerification(user.getEmail(), user.getUsername());

			result.put("User", user);

			error.put("messageCode", 0);
			error.put("message", "Register successfully");
			httpStatus = HttpStatus.OK;

		} catch (Exception e) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		return new ResponseEntity<Response>(response, httpStatus);
	}

	/* ---------------- Edit Profile User ------------------------ */
	@PutMapping("")
	public ResponseEntity<Response> editUser(@RequestParam(value = "id") Long id,
			@RequestBody EditProfileDtIn editProfileDtIn) {
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		try {
			UserEntity userEntity = new UserEntity();
			UserEntity oldUserEntity = new UserEntity();
			if (id != null) {
				oldUserEntity = userService.findOneById(id);
				if (oldUserEntity != null) {
					userEntity = userConverter.toEntity(editProfileDtIn, oldUserEntity);
					userService.editUser(userEntity);
					httpStatus = HttpStatus.OK;
					error.put("messageCode", 0);
					error.put("message", "Edit Profile User Successfull");
				} else {
					httpStatus = HttpStatus.NOT_FOUND;
					error.put("messageCode", 1);
					error.put("message", "Not Find User to update ");
				}
			} else {
				httpStatus = HttpStatus.NOT_FOUND;
				error.put("messageCode", 1);
				error.put("message", "userId is not enter");
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
	@DeleteMapping("")
	public ResponseEntity<Response> deleteUser(@RequestParam(value = "id") Long id) {
		Response response = new Response();
		HttpStatus httpStatus = null;
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		UserEntity oldUserEntity = new UserEntity();
		try {
			if (id != null) {
				oldUserEntity = userService.findOneById(id);
				if (oldUserEntity != null) {
					userService.deleteUser(oldUserEntity);
					httpStatus = HttpStatus.OK;
					error.put("messageCode", 0);
					error.put("message", "Delete User Successfull");
				} else {
					httpStatus = HttpStatus.NOT_FOUND;
					error.put("messageCode", 1);
					error.put("message", "Not Find User to delete ");
				}
			} else {
				httpStatus = HttpStatus.NOT_FOUND;
				error.put("messageCode", 1);
				error.put("message", "userId is not enter");
			}
		} catch (Exception e) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
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

		try {
			if (verificationTokenService.createVerification(registerDtIn.getEmail(), registerDtIn.getUsername())) {
				;
				error.put("messageCode", 0);
				error.put("message", "Sending mail successfully");

			} else {
				error.put("messageCode", 1);
				error.put("message", "Sending mail fail");
			}

			httpStatus = HttpStatus.OK;

		} catch (Exception e) {
			error.put("messageCode", 1);
			error.put("message", "Sending mail fail");
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
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
