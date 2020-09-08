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

import doan2020.SportTournamentSupportSystem.api.PermissionAPI;
import doan2020.SportTournamentSupportSystem.converter.PermissionConverter;
import doan2020.SportTournamentSupportSystem.entity.MatchEntity;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.IPermissionService;
import doan2020.SportTournamentSupportSystem.service.impl.PermissionService;

@RunWith(SpringRunner.class)
public class TestPermissionAPI {
	
	@TestConfiguration
	public static class testPermissionAPIConfiguration {
		@Bean
		PermissionConverter converter() {
			return new PermissionConverter();
		}
		
		@Bean
		IPermissionService service() {
			return new PermissionService();
		}
		
		@Bean
		PermissionAPI permissionApi() {
			return new PermissionAPI();
		}
	}
	
	@MockBean
	PermissionConverter converter;
	
	@MockBean
	IPermissionService service;
	
	@Autowired
	PermissionAPI permissionApi;
	
	@Before
	public void setUp() {
		
	}
	
	@Test
	public void testGetPermissionCaseIdNull() {

		//phần expected result
		String expectedMessage = "Required param id";
		int expectedConfigGlobal = 0;
		
		//phần execute test
		ResponseEntity<Response> response = permissionApi.getPermission(null);
		
		//phan actual result
		String actualMessage = (String)response.getBody().getError().get("Message");
		int actualConfigGlobal = (int)response.getBody().getConfig().get("Global");
		MatchEntity actualMatch = (MatchEntity)response.getBody().getResult().get("Permission");
		
		//phan so sanh ket qua
		Assert.assertEquals(expectedMessage, actualMessage);
		Assert.assertEquals(expectedConfigGlobal, actualConfigGlobal);
		Assert.assertEquals(null, actualMatch);
	}
}
