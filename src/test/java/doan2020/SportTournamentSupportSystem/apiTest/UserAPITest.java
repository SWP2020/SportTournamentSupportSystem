package doan2020.SportTournamentSupportSystem.apiTest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import doan2020.SportTournamentSupportSystem.api.UserAPI;
import doan2020.SportTournamentSupportSystem.converter.PermissionConverter;
import doan2020.SportTournamentSupportSystem.converter.UserConverter;
import doan2020.SportTournamentSupportSystem.dto.UserDTO;
import doan2020.SportTournamentSupportSystem.entity.UserEntity;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.IPermissionService;
import doan2020.SportTournamentSupportSystem.service.IRoleService;
import doan2020.SportTournamentSupportSystem.service.IUserService;
import doan2020.SportTournamentSupportSystem.service.impl.AzureBlobAdapterService;
import doan2020.SportTournamentSupportSystem.service.impl.FileStorageService;
import doan2020.SportTournamentSupportSystem.service.impl.JwtService;
import doan2020.SportTournamentSupportSystem.service.impl.PermissionService;
import doan2020.SportTournamentSupportSystem.service.impl.RoleService;
import doan2020.SportTournamentSupportSystem.service.impl.SendingMailService;
import doan2020.SportTournamentSupportSystem.service.impl.UserService;
import doan2020.SportTournamentSupportSystem.service.impl.VerificationTokenService;
import freemarker.template.Configuration;

@RunWith(SpringRunner.class)
public class UserAPITest {

	@TestConfiguration
	public static class userAPITestConfiguration{
		@Bean
		IUserService userService() {
			return new UserService();
		}

		@Bean
		IRoleService roleService() {
			return new RoleService();
		}

		@Bean
		IPermissionService permissionService() {
			return new PermissionService();
		}

		@Bean
		VerificationTokenService verificationTokenService() {
			return new VerificationTokenService();
		}

		@Bean
		UserConverter userConverter() {
			return new UserConverter();
		}

		@Bean
		PermissionConverter permissionConverter() {
			return new PermissionConverter();
		}

		@Bean
		FileStorageService fileStorageService() {
			return new FileStorageService();
		}

		@Bean
		JwtService jwtService() {
			return new JwtService();
		}

		@Bean
		AzureBlobAdapterService azureBlobAdapterService() {
			return new AzureBlobAdapterService();
		}

		private Configuration templates;
		
		@Bean
		SendingMailService sendingMailService() {
			return new SendingMailService(templates);
		}
		
		@Bean
		UserAPI userApi() {
			return new UserAPI();
		}
	}
	
	@MockBean
	private IUserService userService;

	@MockBean
	private IRoleService roleService;

	@MockBean
	private IPermissionService permissionService;

	@MockBean
	private VerificationTokenService verificationTokenService;

	@MockBean
	private UserConverter userConverter;

	@MockBean
	private PermissionConverter permissionConverter;

	@MockBean
	private FileStorageService fileStorageService;

	@MockBean
	private JwtService jwtService;

	@MockBean
	private AzureBlobAdapterService azureBlobAdapterService;
	
	@MockBean
	private PasswordEncoder passwordEncoder;
	
	@MockBean
	private SendingMailService sendingMailService;
	
	@Autowired
	UserAPI userApi;
	
	/*
	 * Test method getByUserName()
	 * Case Username is null
	*/
	@Test
	public void getUserByUsernameCaseUsernameNull() {
		//Get actual result
		ResponseEntity<Response> response = userApi.getByUserName(null);
		
		//Actual result
		int actualMessageCode = (int)response.getBody().getError().get("MessageCode");
		String actualMessage = (String)response.getBody().getError().get("Message");
		int actualConfigGlobal = (int)response.getBody().getConfig().get("Global");
		UserDTO actualUser = (UserDTO)response.getBody().getResult().get("User");
		
		//Compare expected and actual
		Assert.assertEquals(1, actualMessageCode);
		Assert.assertEquals("Required param username", actualMessage);
		Assert.assertEquals(0, actualConfigGlobal);
		Assert.assertEquals(null, actualUser);
	}
	
	/*
	 * Test method getByUserName()
	 * Case Username not exist
	*/
	@Test
	public void getUserByUsernameCaseUsernameNotExist() {
		//Emulate return of userService.findByUsername()
		Mockito.when(userService.findByUsername("username")).thenReturn(null);
		
		//Get actual result
		ResponseEntity<Response> response = userApi.getByUserName("username");
		
		//Actual result
		int actualMessageCode = (int)response.getBody().getError().get("MessageCode");
		String actualMessage = (String)response.getBody().getError().get("Message");
		int actualConfigGlobal = (int)response.getBody().getConfig().get("Global");
		UserDTO actualUser = (UserDTO)response.getBody().getResult().get("User");
		
		//Compare expected and actual
		Assert.assertEquals(1, actualMessageCode);
		Assert.assertEquals("Not found", actualMessage);
		Assert.assertEquals(0, actualConfigGlobal);
		Assert.assertEquals(null, actualUser);
	}
	
	/*
	 * Test method getByUserName()
	 * Case Username not exist
	*/
	@Test
	public void getUserByUsername() {
		//Emulate return of userService.findByUsername()
		UserEntity user = new UserEntity();
		Mockito.when(userService.findByUsername("username")).thenReturn(user);
		UserDTO userDto = new UserDTO();
		Mockito.when(userConverter.toDTO(user)).thenReturn(userDto);
		
		//Get actual result
		ResponseEntity<Response> response = userApi.getByUserName("username");
		
		//Actual result
		int actualMessageCode = (int)response.getBody().getError().get("MessageCode");
		String actualMessage = (String)response.getBody().getError().get("Message");
		int actualConfigGlobal = (int)response.getBody().getConfig().get("Global");
		UserDTO actualUser = (UserDTO)response.getBody().getResult().get("User");
		
		//Compare expected and actual
		Assert.assertEquals(0, actualMessageCode);
		Assert.assertEquals("Found", actualMessage);
		Assert.assertEquals(0, actualConfigGlobal);
		Assert.assertEquals(userDto, actualUser);
	}
	
}
