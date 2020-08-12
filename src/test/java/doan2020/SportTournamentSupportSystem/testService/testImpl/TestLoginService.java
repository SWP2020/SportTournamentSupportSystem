package doan2020.SportTournamentSupportSystem.testService.testImpl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import doan2020.SportTournamentSupportSystem.dtIn.LoginDtIn;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.impl.LoginService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestLoginService {
	  @Autowired
	  private LoginService loginService;
	
	@Test
	public void testCheckLogin() {
		//phần data test (thay đổi theo các test case tương ứng)
		String username = "test";
		String password = "123456";
		LoginDtIn user = new LoginDtIn(username, password);
		
		//phần expected result
		String expectedMessage = "login Successfull";
		
		//phần execute test
		Response response = new Response();
		//response = loginService.checkLogin(user);
		System.out.println(response.getError());
		String actualMessage = (String)response.getError().get("message");
		System.out.println(actualMessage);
		Assert.assertEquals(expectedMessage, actualMessage);
	}
	
}
