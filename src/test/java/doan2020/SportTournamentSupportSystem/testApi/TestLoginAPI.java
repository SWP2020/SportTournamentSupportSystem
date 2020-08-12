package doan2020.SportTournamentSupportSystem.testApi;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import doan2020.SportTournamentSupportSystem.api.LoginAPI;
import doan2020.SportTournamentSupportSystem.dtIn.LoginDtIn;
import doan2020.SportTournamentSupportSystem.service.impl.LoginService;

@RunWith(SpringRunner.class)
@WebMvcTest(LoginAPI.class)
public class TestLoginAPI {
	
	@Autowired
	private MockMvc mockMvc;
	
    @MockBean
    private LoginService loginService;
	/*
	@Autowired
	  private LoginAPI loginApi;
	
	@Test
	public void testLogin() {
		//phần data test (thay đổi theo các test case tương ứng)
		String username = "test";
		String password = "123456";
		LoginDtIn user = new LoginDtIn(username, password);
		
		//phần expected result
		HttpStatus expectedHttpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		
		//phần execute test
		ResponseEntity<Response> response = loginApi.login(user);
		
		HttpStatus actualHttpStatus = response.getStatusCode();
		System.out.println(actualHttpStatus);
		Assert.assertEquals(expectedHttpStatus, actualHttpStatus);
	}
	*/

	@Test
	public void testLogin() throws Exception {
		//phần data test (thay đổi theo các test case tương ứng)
		String username = "test";
		String password = "123456";
		LoginDtIn user = new LoginDtIn(username, password);
		
		//phần execute test
		mockMvc.perform(post("/login").content(asJsonString(user)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        final ObjectMapper mapper = new ObjectMapper();
	        final String jsonContent = mapper.writeValueAsString(obj);
	        return jsonContent;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
