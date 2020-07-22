package doan2020.SportTournamentSupportSystem.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import doan2020.SportTournamentSupportSystem.dtIn.EditProfileDtIn;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.IEditProfileService;

@RestController
@RequestMapping("/edit-profile")
public class EditProfileAPI {

	@Autowired
	private IEditProfileService editProfileService;

	/* ---------------- Edit Profile User ------------------------ */
	@PutMapping
	public ResponseEntity<Response> createUser(@RequestBody EditProfileDtIn editProfileDtIn) {
		HttpStatus httpStatus = null;
		Response response = new Response();
		try {
			response = editProfileService.editProfile(editProfileDtIn);
			httpStatus = HttpStatus.OK;

		} catch (Exception ex) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Response>(response, httpStatus);
	}
}
