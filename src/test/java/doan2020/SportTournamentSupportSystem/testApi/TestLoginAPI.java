package doan2020.SportTournamentSupportSystem.testApi;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import doan2020.SportTournamentSupportSystem.api.LoginAPI;
import doan2020.SportTournamentSupportSystem.converter.UserConverter;
import doan2020.SportTournamentSupportSystem.dtIn.LoginDtIn;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.IVerificationTokenService;
import doan2020.SportTournamentSupportSystem.service.impl.JwtService;
import doan2020.SportTournamentSupportSystem.service.impl.LoginService;
import doan2020.SportTournamentSupportSystem.service.impl.UserService;

@RunWith(SpringRunner.class)
public class TestLoginAPI {
	
	@InjectMocks
	LoginAPI loginApi;
	
	@Mock
	LoginService loginService;
	
	@Mock
	BCryptPasswordEncoder passwordEncoder;

	@Mock
	JwtService jwtService;

	@Mock
	UserService userService;

	@Mock
	UserConverter converter;

	@Mock
	IVerificationTokenService verificationTokenService;
	
	@Test
	public void testLogin() {
		//phần data test (thay đổi theo các test case tương ứng)
		String username = "Cong";
		String password = "123456";
		LoginDtIn user = new LoginDtIn(username, password);
		
		//phần expected result
		HttpStatus expectedHttpStatus = HttpStatus.OK;
		
		//phần execute test
		ResponseEntity<Response> response = loginApi.login(user);
		
		HttpStatus actualHttpStatus = response.getStatusCode();
		System.out.println(actualHttpStatus);
		Assert.assertEquals(expectedHttpStatus, actualHttpStatus);
	}
	
}
