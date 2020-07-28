package doan2020.SportTournamentSupportSystem.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import doan2020.SportTournamentSupportSystem.dtIn.LoginDtIn;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.impl.LoginService;

@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginAPI {

	@Autowired
	private LoginService loginService;

	@PostMapping
	public ResponseEntity<Response> login(@RequestBody LoginDtIn user) {
		HttpStatus httpStatus = HttpStatus.OK;
		Response response = new Response();
		try {
			System.out.println(user.getUsername());
			response = loginService.checkLogin(user);
			if (response.getResult().containsKey("Authentication")) {

				httpStatus = HttpStatus.OK;
			}
		} catch (Exception ex) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Response>(response, httpStatus);
	}

}
