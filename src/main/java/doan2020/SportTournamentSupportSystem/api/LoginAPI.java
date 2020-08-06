package doan2020.SportTournamentSupportSystem.api;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import doan2020.SportTournamentSupportSystem.converter.UserConverter;
import doan2020.SportTournamentSupportSystem.dtIn.LoginDtIn;
import doan2020.SportTournamentSupportSystem.dtIn.VerifyAuthenticationDtIn;
import doan2020.SportTournamentSupportSystem.dto.UserDTO;
import doan2020.SportTournamentSupportSystem.entity.UserEntity;
import doan2020.SportTournamentSupportSystem.entity.VerificationToken;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.IVerificationTokenService;
import doan2020.SportTournamentSupportSystem.service.impl.JwtService;
import doan2020.SportTournamentSupportSystem.service.impl.LoginService;
import doan2020.SportTournamentSupportSystem.service.impl.UserService;

@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginAPI {

	@Autowired
	private LoginService loginService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserService userService;

	@Autowired
	private UserConverter converter;

	@Autowired
	private IVerificationTokenService verificationTokenService;

	@PostMapping
	public ResponseEntity<Response> login(@RequestBody LoginDtIn user) {
		System.out.println("LoginAPI - login");
		HttpStatus httpStatus = null;
		httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		try {
			System.out.println(user.getUsername());
			UserEntity findUser = userService.findByUsername(user.getUsername());
			System.out.println(findUser);
			boolean checkPW = passwordEncoder.matches(user.getPassword(), findUser.getPassword());
			System.out.println("LoginAPI- check pass OK");
			if (StringUtils.equals(user.getUsername(), findUser.getUsername()) && checkPW) {
				System.out.println("LoginAPI- cp1");
				if (findUser.getStatus().equals("active")) {
					System.out.println("LoginAPI- cp2");
					String token = jwtService.generateTokenLogin(user.getUsername());
					UserDTO userDtO = converter.toDTO(findUser);

					System.out.println("convert OK");

					result.put("User", userDtO);
					result.put("Authentication", token);
					config.put("global", 0);
					error.put("messageCode", 0);
					error.put("message", "login Successfull");

					System.out.println("LoginAPI- cp3");
				} else {
					System.out.println("LoginAPI- cp4");
					config.put("global", 0);
					error.put("messageCode", 1);
					error.put("message", "User is not active");
					System.out.println("LoginAPI- cp5");
					response.setError(error);
					response.setResult(result);
					response.setConfig(config);
					return new ResponseEntity<Response>(response, httpStatus);
				}
			} else {
				System.out.println("cp6");
				config.put("global", 0);
				error.put("messageCode", 1);
				error.put("message", "UserName or PassWrong is Wrong");
				System.out.println("cp7");
				response.setError(error);
				response.setResult(result);
				response.setConfig(config);
				return new ResponseEntity<Response>(response, httpStatus);
			}
		} catch (Exception e) {
			System.out.println("LoginAPI - exception");
			result.put("User", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "Login Fail");
		}

		System.out.println("LoginAPI - cp8");
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("LoginAPI - cp pass");
		return new ResponseEntity<Response>(response, httpStatus);
	}

	@GetMapping("/verify-authentication")
	public ResponseEntity<Response> verifyEmail(@RequestBody VerifyAuthenticationDtIn verifyAuthenticationDtIn) {
		System.out.println("LoginAPI - verifyEmail");
		HttpStatus httpStatus = null;
		httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();

		try {
			String token = verifyAuthenticationDtIn.getCode();
	        List<VerificationToken> verificationTokens = verificationTokenService.findByToken(token);
	        if (verificationTokens.isEmpty()) {
	        	error.put("messageCode", "002");
				error.put("message", "Invalid token.");
				response.setError(error);
				response.setError(error);
				response.setResult(result);
				response.setConfig(config);
				return new ResponseEntity<Response>(response, httpStatus);
	        }

	        VerificationToken verificationToken = verificationTokens.get(0);
	        if (verificationToken.getExpiredDateTime().isBefore(LocalDateTime.now())) {
	        	error.put("messageCode", "002");
				error.put("message", "Expired token.");
				response.setError(error);
				response.setError(error);
				response.setResult(result);
				response.setConfig(config);
				return new ResponseEntity<Response>(response, httpStatus);
	        }

	        verificationToken.setConfirmedDateTime(LocalDateTime.now());
	        verificationToken.setStatus(VerificationToken.STATUS_VERIFIED);
	        verificationToken.getUser().setStatus("active");
	        
	        verificationToken = verificationTokenService.verifyEmail(verificationToken);
	        
			System.out.println("LoginAPI - cp1");
			result.put("verificationToken", verificationToken);
			config.put("global", 0);
			error.put("messageCode", 0);
			error.put("message", "");
			System.out.println("LoginAPI - cp2");
		} catch (Exception e) {
			System.out.println("LoginAPI - exception");
			result.put("verificationToken", null);
			config.put("global", 0);
			error.put("messageCode", 1);
			error.put("message", "");
		}

		System.out.println("LoginAPI - cp3");
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("LoginAPI - cp pass");
		return new ResponseEntity<Response>(response, httpStatus);
	}

}
