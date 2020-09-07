package doan2020.SportTournamentSupportSystem.testApi;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import doan2020.SportTournamentSupportSystem.api.RoleAPI;
import doan2020.SportTournamentSupportSystem.converter.RoleConverter;
import doan2020.SportTournamentSupportSystem.entity.MatchEntity;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.IRoleService;
import doan2020.SportTournamentSupportSystem.service.impl.RoleService;

@RunWith(SpringRunner.class)
public class TestRoleAPI {
	
	@TestConfiguration
	public static class testReportAPIConfiguration {
		
		@Bean
		RoleConverter converter() {
			return new RoleConverter();
		}

		@Bean
		IRoleService service() {
			return new RoleService();
		}
		
		@Bean
		RoleAPI roleApi() {
			return new RoleAPI();
		}
	}
	
	@MockBean
	private RoleConverter converter;

	@MockBean
	private IRoleService service;
	
	@Autowired
	private RoleAPI roleApi;
	
	@Before
	public void setUp() {
		
	}
	
	@Test
	public void testGetRoleCaseIdNull() {

		//phần expected result
		String expectedMessage = "Required param id";
		int expectedConfigGlobal = 0;
		
		//phần execute test
		ResponseEntity<Response> response = roleApi.getRole(null);
		
		//phan actual result
		String actualMessage = (String)response.getBody().getError().get("Message");
		int actualConfigGlobal = (int)response.getBody().getConfig().get("Global");
		MatchEntity actualMatch = (MatchEntity)response.getBody().getResult().get("Role");
		
		//phan so sanh ket qua
		Assert.assertEquals(expectedMessage, actualMessage);
		Assert.assertEquals(expectedConfigGlobal, actualConfigGlobal);
		Assert.assertEquals(null, actualMatch);
	}
}
