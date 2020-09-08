package doan2020.SportTournamentSupportSystem.testApi;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import doan2020.SportTournamentSupportSystem.api.RoleAPI;
import doan2020.SportTournamentSupportSystem.converter.RoleConverter;
import doan2020.SportTournamentSupportSystem.dto.RoleDTO;
import doan2020.SportTournamentSupportSystem.entity.RoleEntity;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.IRoleService;
import doan2020.SportTournamentSupportSystem.service.impl.RoleService;

@RunWith(SpringRunner.class)
public class TestRoleAPI {
	
	//Bean definition
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
	
	RoleDTO roleDto3;
	
	//Emulate before execute test
	@Before
	public void setUp() {
		//1
		//2
		Mockito.when(service.findOneById((long)2)).thenReturn(null);
		//3
		RoleEntity role3 = new RoleEntity();
		Mockito.when(service.findOneById((long)3)).thenReturn(role3);
		roleDto3 = new RoleDTO();
		Mockito.when(converter.toDTO(role3)).thenReturn(roleDto3);
	}
	
	@Test
	public void testGetRoleCaseIdNull() {
		//Get actual result
		ResponseEntity<Response> response = roleApi.getRole(null);
		
		//Actual result
		int actualMessageCode = (int)response.getBody().getError().get("MessageCode");
		String actualMessage = (String)response.getBody().getError().get("Message");
		int actualConfigGlobal = (int)response.getBody().getConfig().get("Global");
		RoleDTO actualRole = (RoleDTO)response.getBody().getResult().get("Role");
		
		//Compare expected and actual
		Assert.assertEquals(1, actualMessageCode);
		Assert.assertEquals("Required param id", actualMessage);
		Assert.assertEquals(0, actualConfigGlobal);
		Assert.assertEquals(null, actualRole);
	}
	
	@Test
	public void testGetRoleCaseRoleNotExist() {
		//Get actual result
		ResponseEntity<Response> response = roleApi.getRole((long)2);
		
		//Actual result
		int actualMessageCode = (int)response.getBody().getError().get("MessageCode");
		String actualMessage = (String)response.getBody().getError().get("Message");
		int actualConfigGlobal = (int)response.getBody().getConfig().get("Global");
		RoleDTO actualRole = (RoleDTO)response.getBody().getResult().get("Role");
		
		//Compare expected and actual
		Assert.assertEquals(1, actualMessageCode);
		Assert.assertEquals("Not found", actualMessage);
		Assert.assertEquals(0, actualConfigGlobal);
		Assert.assertEquals(null, actualRole);
	}
	
	@Test
	public void testGetRole() {
		//Get actual result
		ResponseEntity<Response> response = roleApi.getRole((long)3);
		
		//Actual result
		int actualMessageCode = (int)response.getBody().getError().get("MessageCode");
		String actualMessage = (String)response.getBody().getError().get("Message");
		int actualConfigGlobal = (int)response.getBody().getConfig().get("Global");
		RoleDTO actualRole = (RoleDTO)response.getBody().getResult().get("Role");
		
		//Compare expected and actual
		Assert.assertEquals(0, actualMessageCode);
		Assert.assertEquals("Found", actualMessage);
		Assert.assertEquals(0, actualConfigGlobal);
		Assert.assertEquals(roleDto3, actualRole);
	}
}
