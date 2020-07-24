package doan2020.SportTournamentSupportSystem.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import doan2020.SportTournamentSupportSystem.dtIn.VerifyAuthenticationDtIn;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.impl.VerificationTokenService;

@RestController
@RequestMapping("/verify-authentication")
public class VerifyAuthenticationAPI {
	@Autowired
	VerificationTokenService verificationTokenService;
	
	@GetMapping
    public ResponseEntity<Response> verifyEmail(@RequestBody VerifyAuthenticationDtIn VerifyAuthenticationDtIn) {
		HttpStatus httpStatus = null;
		Response response = new Response();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
	    try {
	    	response = verificationTokenService.verifyEmail(VerifyAuthenticationDtIn);
	        httpStatus = HttpStatus.OK;

	    } catch (Exception ex) {
	      httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
	    }
	    return new ResponseEntity<Response>(response, httpStatus);
    }
}
