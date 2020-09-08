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

import doan2020.SportTournamentSupportSystem.api.PermissionAPI;
import doan2020.SportTournamentSupportSystem.converter.PermissionConverter;
import doan2020.SportTournamentSupportSystem.dto.PermissionDTO;
import doan2020.SportTournamentSupportSystem.entity.PermissionEntity;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.IPermissionService;
import doan2020.SportTournamentSupportSystem.service.impl.PermissionService;

@RunWith(SpringRunner.class)
public class TestPermissionAPI {
	
	//Bean definition
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
	
	PermissionDTO permissionDto3;
	
	//Emulate before execute test
	@Before
	public void setUp() {
		//1
		//2
		Mockito.when(service.findOneById((long)2)).thenReturn(null);
		//3
		PermissionEntity permission3 = new PermissionEntity();
		Mockito.when(service.findOneById((long)3)).thenReturn(permission3);
		permissionDto3 = new PermissionDTO();
		Mockito.when(converter.toDTO(permission3)).thenReturn(permissionDto3);
		
	}
	
	@Test
	public void testGetPermissionCaseIdNull() {
		//Get actual result
		ResponseEntity<Response> response = permissionApi.getPermission(null);
		
		//Actual result
		int actualMessageCode = (int)response.getBody().getError().get("MessageCode");
		String actualMessage = (String)response.getBody().getError().get("Message");
		int actualConfigGlobal = (int)response.getBody().getConfig().get("Global");
		PermissionDTO actualPermission = (PermissionDTO)response.getBody().getResult().get("Permission");
		
		//Compare expected and actual
		Assert.assertEquals(1, actualMessageCode);
		Assert.assertEquals("Required param id", actualMessage);
		Assert.assertEquals(0, actualConfigGlobal);
		Assert.assertEquals(null, actualPermission);
	}
	
	@Test
	public void testGetPermissionCasePermissionNotExist() {
		//Get actual result
		ResponseEntity<Response> response = permissionApi.getPermission((long)2);
		
		//Actual result
		int actualMessageCode = (int)response.getBody().getError().get("MessageCode");
		String actualMessage = (String)response.getBody().getError().get("Message");
		int actualConfigGlobal = (int)response.getBody().getConfig().get("Global");
		PermissionDTO actualPermission = (PermissionDTO)response.getBody().getResult().get("Permission");
		
		//Compare expected and actual
		Assert.assertEquals(1, actualMessageCode);
		Assert.assertEquals("Not found", actualMessage);
		Assert.assertEquals(0, actualConfigGlobal);
		Assert.assertEquals(null, actualPermission);
	}
	
	@Test
	public void testGetPermission() {
		//Get actual result
		ResponseEntity<Response> response = permissionApi.getPermission((long)3);
		
		//Actual result
		int actualMessageCode = (int)response.getBody().getError().get("MessageCode");
		String actualMessage = (String)response.getBody().getError().get("Message");
		int actualConfigGlobal = (int)response.getBody().getConfig().get("Global");
		PermissionDTO actualPermission = (PermissionDTO)response.getBody().getResult().get("Permission");
		
		//Compare expected and actual
		Assert.assertEquals(0, actualMessageCode);
		Assert.assertEquals("Found", actualMessage);
		Assert.assertEquals(0, actualConfigGlobal);
		Assert.assertEquals(permissionDto3, actualPermission);
	}
}
