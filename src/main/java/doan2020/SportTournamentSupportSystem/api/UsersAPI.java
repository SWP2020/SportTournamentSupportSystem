package doan2020.SportTournamentSupportSystem.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import doan2020.SportTournamentSupportSystem.converter.UserConverter;
import doan2020.SportTournamentSupportSystem.dto.UserDTO;
import doan2020.SportTournamentSupportSystem.entity.UserEntity;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.IRoleService;
import doan2020.SportTournamentSupportSystem.service.IUserService;
import doan2020.SportTournamentSupportSystem.service.impl.VerificationTokenService;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UsersAPI {
	@Autowired
	private IUserService userService;

	@Autowired
	private IRoleService roleService;

	@Autowired
	private VerificationTokenService verificationTokenService;

	@Autowired
	private UserConverter userConverter;

	/* ---------------- GET ALL USER ------------------------ */
	@GetMapping("")
	public ResponseEntity<Response> getAllUser(@RequestParam(value = "page", required = false) Integer page) {
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		List<UserDTO> listUsers = new ArrayList<>();
		HttpStatus httpStatus = HttpStatus.OK;
		try {
			if (page != null) {
				Sort sortable = Sort.by("userID").ascending();
				int limit = 10;
				Pageable pageable = PageRequest.of(page - 1, limit, sortable);
				List<UserEntity> ListUserEntity = (List<UserEntity>) userService.findAll(pageable);

				for (UserEntity user : ListUserEntity) {
					UserDTO userDTO = userConverter.toDTO(user);
					listUsers.add(userDTO);
					result.put("listUsers", listUsers);
					error.put("messageCode", 0);
					error.put("message", "get List Users successfully");
				}
			} else {
				error.put("messageCode", 1);
				error.put("message", "list Users is not exist");
			}
		} catch (Exception e) {
			result.put("listUsers", null);
			error.put("messageCode", 1);
			error.put("message", "get List Users fail");
		}
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		return new ResponseEntity<Response>(response, httpStatus);

	}
}
