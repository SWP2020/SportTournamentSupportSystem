package doan2020.SportTournamentSupportSystem.api;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.DateTimeAtCompleted;

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
import doan2020.SportTournamentSupportSystem.entity.VerificationTokenEntity;
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
		System.out.println("LoginAPI: login: start");
		HttpStatus httpStatus = null;
		httpStatus = HttpStatus.OK;
		Response response = new Response();
		Map<String, Object> config = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		try {
			UserEntity findUser = userService.findByUsername(user.getUsername());
			if (findUser == null) { // User is not Exist
				System.out.println("LoginAPI: login: User is not Exist");
				result.put("User", null);
				result.put("Authentication", null);
				config.put("Global", 0);
				error.put("MessageCode", 1);
				error.put("Message", "User is not Exist");
			} else { // User exist
				if (!findUser.getStatus().equals("active")) { // User is not active
					System.out.println("LoginAPI: login: User is not active");
					result.put("User", null);
					result.put("Authentication", null);
					config.put("Global", 0);
					error.put("MessageCode", 1);
					error.put("Message", "User is not active");
				} else { // User is active
					boolean checkPW = passwordEncoder.matches(user.getPassword(), findUser.getPassword());
					if (!checkPW) {// password wrong
						System.out.println("LoginAPI: login: Password wrong");
						result.put("User", null);
						result.put("Authentication", null);
						config.put("Global", 0);
						error.put("MessageCode", 1);
						error.put("Message", "Password wrong");
					} else {// password right
						UserDTO userDTO = converter.toDTO(findUser);
						String token = jwtService.generateTokenLogin(user.getUsername());

						result.put("User", userDTO);
						result.put("Authentication", token);
						config.put("Global", 0);
						error.put("MessageCode", 0);
						error.put("Message", "Login successfull");
					
						System.out.println("LoginAPI: login: " + findUser.getUsername() + " login success");
					}
				}
			}
			System.out.println("LoginAPI: login: no exception");
		} catch (Exception e) {
			System.out.println("LoginAPI: login: has exception");
			result.put("User", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "Server error");
		}

		
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("LoginAPI: login: finish");
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
			List<VerificationTokenEntity> verificationTokens = verificationTokenService.findByToken(token);
			if (verificationTokens.isEmpty()) {
				error.put("MessageCode", 2);
				error.put("Message", "Invalid token.");
				response.setError(error);
				response.setError(error);
				response.setResult(result);
				response.setConfig(config);
				return new ResponseEntity<Response>(response, httpStatus);
			}
			
			Date in = new Date();
			LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
			Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
			
			VerificationTokenEntity verificationToken = verificationTokens.get(0);
			if (verificationToken.getExpiredDateTime().isBefore(out)) {
				error.put("MessageCode", 2);
				error.put("Message", "Expired token.");
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
			config.put("Global", 0);
			error.put("MessageCode", 0);
			error.put("Message", "");
			System.out.println("LoginAPI - cp2");
		} catch (Exception e) {
			System.out.println("LoginAPI - exception");
			result.put("VerificationToken", null);
			config.put("Global", 0);
			error.put("MessageCode", 1);
			error.put("Message", "");
		}

		System.out.println("LoginAPI - cp3");
		response.setError(error);
		response.setResult(result);
		response.setConfig(config);
		System.out.println("LoginAPI - cp pass");
		return new ResponseEntity<Response>(response, httpStatus);
	}

}
