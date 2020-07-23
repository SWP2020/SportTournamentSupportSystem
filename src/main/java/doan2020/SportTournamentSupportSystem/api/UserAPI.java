package doan2020.SportTournamentSupportSystem.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import doan2020.SportTournamentSupportSystem.dtIn.EditProfileDtIn;
import doan2020.SportTournamentSupportSystem.dtIn.RegisterDtIn;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.IUserService;
import doan2020.SportTournamentSupportSystem.service.impl.VerificationTokenService;


@RestController
@RequestMapping("/users")
public class UserAPI {
	@Autowired
	private IUserService userService;
	
	@Autowired
	VerificationTokenService verificationTokenService;

	/* ---------------- GET ALL USER ------------------------ */
//	@GetMapping
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Response> getAllUser(@RequestParam("page") int page,
			@RequestParam("limit") int limit) {
		Sort sortable = Sort.by("userID").ascending();
		Pageable pageable = PageRequest.of(page - 1, limit, sortable);
		return new ResponseEntity<Response>(userService.findAll(pageable), HttpStatus.OK);
	}
	
	/* get One User */
	@GetMapping("/{id}")
	public ResponseEntity<Response> getUserInfor(@PathVariable("id") Long id) {
		Response response = new Response();
		HttpStatus httpStatus = null;
		
		try {
			response = userService.getUserInfor(id);
			httpStatus = HttpStatus.OK;
		}catch (Exception e) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Response>(response, httpStatus);
	}
	
	
	/* ---------------- register NEW USER ------------------------ */
	@PostMapping
	public ResponseEntity<Response> createUser(@RequestBody RegisterDtIn user) {
		HttpStatus httpStatus = null;
		Response response = new Response();
		try {
			response = userService.addNewUsers(user);
			
			if (response.getError().containsValue("001")) {	
				verificationTokenService.createVerification(user.getEmail(), user.getUsername());
				httpStatus = HttpStatus.OK;
			} else {
				httpStatus = HttpStatus.BAD_REQUEST;
			}
		} catch (Exception ex) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Response>(response, httpStatus);
	}
	
	/* ---------------- Edit Profile User ------------------------ */
	@PutMapping
	public ResponseEntity<Response> editUser(@RequestBody EditProfileDtIn editProfileDtIn) {
		HttpStatus httpStatus = null;
		Response response = new Response();
		try {
			response = userService.editProfile(editProfileDtIn);
			httpStatus = HttpStatus.OK;

		} catch (Exception ex) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Response>(response, httpStatus);
	}


}
