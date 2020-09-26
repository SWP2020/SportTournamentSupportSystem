package doan2020.SportTournamentSupportSystem.apiTest;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import doan2020.SportTournamentSupportSystem.api.UserAPI;
import doan2020.SportTournamentSupportSystem.config.Const;
import doan2020.SportTournamentSupportSystem.converter.PermissionConverter;
import doan2020.SportTournamentSupportSystem.converter.UserConverter;
import doan2020.SportTournamentSupportSystem.dto.PermissionDTO;
import doan2020.SportTournamentSupportSystem.dto.UserDTO;
import doan2020.SportTournamentSupportSystem.entity.PermissionEntity;
import doan2020.SportTournamentSupportSystem.entity.RoleEntity;
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
	 * Case Username exist
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
	
	/*
	 * Test method getById()
	 * Case id is null
	*/
	@Test
	public void getUserByIdCaseIdNull() {
		//Get actual result
		ResponseEntity<Response> response = userApi.getById("tempJwt", null);
		
		//Actual result
		int actualMessageCode = (int)response.getBody().getError().get("MessageCode");
		String actualMessage = (String)response.getBody().getError().get("Message");
		int actualConfigGlobal = (int)response.getBody().getConfig().get("Global");
		UserDTO actualUser = (UserDTO)response.getBody().getResult().get("User");
		
		//Compare expected and actual
		Assert.assertEquals(1, actualMessageCode);
		Assert.assertEquals("Required param id", actualMessage);
		Assert.assertEquals(0, actualConfigGlobal);
		Assert.assertEquals(null, actualUser);
	}
	
	/*
	 * Test method getById()
	 * Case user not exist
	*/
	@Test
	public void getUserByIdCaseUserNotExist() {
		//Emulate return of userService.findByUsername()
		Mockito.when(userService.findOneById((long)1)).thenReturn(null);
		
		//Get actual result
		ResponseEntity<Response> response = userApi.getById("tempJwt", (long)1);
		
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
	 * Test method getById()
	 * Case user exist and is owner
	*/
	@Test
	public void getUserByIdCaseUserExistAndIsOwner() {
		//Emulate return of userService.findByUsername()
		UserEntity user = new UserEntity();
		Mockito.when(userService.findOneById((long)1)).thenReturn(user);
		UserDTO userDto = new UserDTO();
		Mockito.when(userConverter.toDTO(user)).thenReturn(userDto);
		
		Mockito.when(jwtService.getUserNameFromJwtToken("tempJwt")).thenReturn("username");
		UserEntity user2 = new UserEntity();
		user2.setId((long)1);
		Mockito.when(userService.findByUsername("username")).thenReturn(user2);
		
		PermissionEntity permissionEntity = new PermissionEntity();
		Mockito.when(permissionService.findOneByName(Const.OWNER)).thenReturn(permissionEntity);
		PermissionDTO permissionDto = new PermissionDTO();
		Mockito.when(permissionConverter.toDTO(permissionEntity)).thenReturn(permissionDto);
		
		//Get actual result
		ResponseEntity<Response> response = userApi.getById("tempJwt", (long)1);
		
		//Actual result
		int actualMessageCode = (int)response.getBody().getError().get("MessageCode");
		String actualMessage = (String)response.getBody().getError().get("Message");
		PermissionDTO actualConfigGlobal = (PermissionDTO)response.getBody().getConfig().get("Global");
		UserDTO actualUser = (UserDTO)response.getBody().getResult().get("User");
		
		//Compare expected and actual
		Assert.assertEquals(0, actualMessageCode);
		Assert.assertEquals("Found", actualMessage);
		Assert.assertEquals(permissionDto, actualConfigGlobal);
		Assert.assertEquals(userDto, actualUser);
	}
	
	/*
	 * Test method getById()
	 * Case user exist and is viewer
	*/
	@Test
	public void getUserByIdCaseUserExistAndIsViewer() {
		//Emulate return of userService.findByUsername()
		UserEntity user = new UserEntity();
		Mockito.when(userService.findOneById((long)1)).thenReturn(user);
		UserDTO userDto = new UserDTO();
		Mockito.when(userConverter.toDTO(user)).thenReturn(userDto);
		
		Mockito.when(jwtService.getUserNameFromJwtToken("tempJwt")).thenReturn("username");
		UserEntity user2 = new UserEntity();
		user2.setId((long)2);
		Mockito.when(userService.findByUsername("username")).thenReturn(user2);
		
		PermissionEntity permissionEntity = new PermissionEntity();
		Mockito.when(permissionService.findOneByName(Const.VIEWER)).thenReturn(permissionEntity);
		PermissionDTO permissionDto = new PermissionDTO();
		Mockito.when(permissionConverter.toDTO(permissionEntity)).thenReturn(permissionDto);
		
		//Get actual result
		ResponseEntity<Response> response = userApi.getById("tempJwt", (long)1);
		
		//Actual result
		int actualMessageCode = (int)response.getBody().getError().get("MessageCode");
		String actualMessage = (String)response.getBody().getError().get("Message");
		PermissionDTO actualConfigGlobal = (PermissionDTO)response.getBody().getConfig().get("Global");
		UserDTO actualUser = (UserDTO)response.getBody().getResult().get("User");
		
		//Compare expected and actual
		Assert.assertEquals(0, actualMessageCode);
		Assert.assertEquals("Found", actualMessage);
		Assert.assertEquals(permissionDto, actualConfigGlobal);
		Assert.assertEquals(userDto, actualUser);
	}
	
	/*
	 * Test method getByEmail()
	 * Case Email is null
	*/
	@Test
	public void getUserByEmailCaseEmailNull() {
		//Get actual result
		ResponseEntity<Response> response = userApi.getByEmail(null);
		
		//Actual result
		int actualMessageCode = (int)response.getBody().getError().get("MessageCode");
		String actualMessage = (String)response.getBody().getError().get("Message");
		int actualConfigGlobal = (int)response.getBody().getConfig().get("Global");
		UserDTO actualUser = (UserDTO)response.getBody().getResult().get("User");
		
		//Compare expected and actual
		Assert.assertEquals(1, actualMessageCode);
		Assert.assertEquals("Required param email", actualMessage);
		Assert.assertEquals(0, actualConfigGlobal);
		Assert.assertEquals(null, actualUser);
	}
	
	/*
	 * Test method getByEmail()
	 * Case Email not exist
	*/
	@Test
	public void getUserByEmailCaseEmailNotExist() {
		//Emulate return of userService.findByEmail()
		Mockito.when(userService.findByEmail("email")).thenReturn(null);
		
		//Get actual result
		ResponseEntity<Response> response = userApi.getByUserName("email");
		
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
	 * Test method getByEmail()
	 * Case Email exist
	*/
	@Test
	public void getUserByEmail() {
		//Emulate return of userService.findByEmail()
		UserEntity user = new UserEntity();
		Mockito.when(userService.findByUsername("email")).thenReturn(user);
		UserDTO userDto = new UserDTO();
		Mockito.when(userConverter.toDTO(user)).thenReturn(userDto);
		
		//Get actual result
		ResponseEntity<Response> response = userApi.getByUserName("email");
		
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
	
	/*
	 * Test method createUser()
	 * Case user existed (existed Username or existed Email)
	*/
	@Test
	public void createUserCaseUserExisted() {
		//Emulate return of userService.findByUsername()
		UserEntity user = new UserEntity();
		Mockito.when(userService.findByUsername("email")).thenReturn(user);
		
		UserDTO userDto = new UserDTO();
		UserEntity newUser = new UserEntity();
		newUser.setUsername("newUsername");
		newUser.setEmail("newEmail");
		Mockito.when(userConverter.toEntity(userDto)).thenReturn(newUser);
		
		UserEntity userCheckUsername = new UserEntity();
		Mockito.when(userService.findByUsername(newUser.getUsername())).thenReturn(userCheckUsername);
		UserEntity userCheckEmail = new UserEntity();
		Mockito.when(userService.findByEmail(newUser.getEmail())).thenReturn(userCheckEmail);
		
		//Get actual result
		ResponseEntity<Response> response = userApi.createUser(userDto);
		
		//Actual result
		int actualMessageCode = (int)response.getBody().getError().get("MessageCode");
		String actualMessage = (String)response.getBody().getError().get("Message");
		
		//Compare expected and actual
		Assert.assertEquals(1, actualMessageCode);
		Assert.assertEquals("User is Exists", actualMessage);
	}
	
	/*
	 * Test method createUser()
	 * Case role user
	*/
	@Test
	public void createUserCaseRoleUser() {
		//Emulate return of userService.findByUsername()
		UserEntity user = new UserEntity();
		Mockito.when(userService.findByUsername("email")).thenReturn(user);
		
		UserDTO userDto = new UserDTO();
		UserEntity newUser = new UserEntity();
		newUser.setUsername("newUsername");
		newUser.setEmail("newEmail");
		Mockito.when(userConverter.toEntity(userDto)).thenReturn(newUser);
		
		Mockito.when(userService.findByUsername(newUser.getUsername())).thenReturn(null);
		Mockito.when(userService.findByEmail(newUser.getEmail())).thenReturn(null);
		
		RoleEntity roleEntity = new RoleEntity();
		Mockito.when(roleService.findOneByName(Const.ROLE_USER)).thenReturn(roleEntity);
		
		UserEntity newUser2 = new UserEntity();
		newUser2.setRole(roleEntity);
		Mockito.when(userService.create(newUser)).thenReturn(newUser2);

		UserDTO userDto2 = new UserDTO();
		Mockito.when(userConverter.toDTO(newUser2)).thenReturn(userDto2);

		Mockito.when(verificationTokenService.createVerification(newUser2.getEmail(), newUser2.getUsername())).thenReturn(true);
		
		//Get actual result
		ResponseEntity<Response> response = userApi.createUser(userDto);
		
		//Actual result
		int actualMessageCode = (int)response.getBody().getError().get("MessageCode");
		String actualMessage = (String)response.getBody().getError().get("Message");
		
		//Compare expected and actual
		Assert.assertEquals(0, actualMessageCode);
		Assert.assertEquals("Create user successful", actualMessage);
	}
	
	/*
	 * Test method createUser()
	 * Case role Admin
	*/
	@Test
	public void createUserCaseRoleAdmin() {
		//Emulate return of userService.findByUsername()
		UserEntity user = new UserEntity();
		Mockito.when(userService.findByUsername("email")).thenReturn(user);
		
		UserDTO userDto = new UserDTO();
		UserEntity newUser = new UserEntity();
		newUser.setUsername("newUsername");
		newUser.setEmail("newEmail");
		Mockito.when(userConverter.toEntity(userDto)).thenReturn(newUser);
		
		Mockito.when(userService.findByUsername(newUser.getUsername())).thenReturn(null);
		Mockito.when(userService.findByEmail(newUser.getEmail())).thenReturn(null);
		
		Mockito.when(roleService.findOneByName(Const.ROLE_USER)).thenReturn(null);
		
		RoleEntity roleEntity = new RoleEntity();
		Mockito.when(roleService.findOneById((long)1)).thenReturn(roleEntity);
		
		UserEntity newUser2 = new UserEntity();
		newUser2.setRole(roleEntity);
		Mockito.when(userService.create(newUser)).thenReturn(newUser2);

		UserDTO userDto2 = new UserDTO();
		Mockito.when(userConverter.toDTO(newUser2)).thenReturn(userDto2);

		Mockito.when(verificationTokenService.createVerification(newUser2.getEmail(), newUser2.getUsername())).thenReturn(true);
		
		//Get actual result
		ResponseEntity<Response> response = userApi.createUser(userDto);
		
		//Actual result
		int actualMessageCode = (int)response.getBody().getError().get("MessageCode");
		String actualMessage = (String)response.getBody().getError().get("Message");
		
		//Compare expected and actual
		Assert.assertEquals(0, actualMessageCode);
		Assert.assertEquals("Create user successful", actualMessage);
	}
	
	/*
	 * Test method editUser()
	 * Case id is null
	*/
	@Test
	public void editUserProfileCaseIdNull() {
		//Get actual result
		UserDTO userDto = new UserDTO();
		ResponseEntity<Response> response = userApi.editUser(null, userDto);
		
		//Actual result
		int actualMessageCode = (int)response.getBody().getError().get("MessageCode");
		String actualMessage = (String)response.getBody().getError().get("Message");
		
		//Compare expected and actual
		Assert.assertEquals(1, actualMessageCode);
		Assert.assertEquals("required user id", actualMessage);
	}
	
	/*
	 * Test method editUser()
	 * Case id user exist
	*/
	@Test
	public void editUserProfileCaseIdUserExist() {
		//Emulate return of userService.update()
		UserEntity userEntity = new UserEntity();
		UserDTO userDto = new UserDTO();
		Mockito.when(userConverter.toEntity(userDto)).thenReturn(userEntity);
		Mockito.when(userService.update((long)1, userEntity)).thenReturn(userEntity);
		
		//Get actual result
		ResponseEntity<Response> response = userApi.editUser((long)1, userDto);
		
		//Actual result
		int actualMessageCode = (int)response.getBody().getError().get("MessageCode");
		String actualMessage = (String)response.getBody().getError().get("Message");
		
		//Compare expected and actual
		Assert.assertEquals(0, actualMessageCode);
		Assert.assertEquals("Edit Profile User Successfull", actualMessage);
	}
	
	/*
	 * Test method editUser()
	 * Case id is null
	*/
	@Test
	public void deleteUserCaseIdNull() {
		//Get actual result
		ResponseEntity<Response> response = userApi.deleteUser(null);
		
		//Actual result
		int actualMessageCode = (int)response.getBody().getError().get("MessageCode");
		String actualMessage = (String)response.getBody().getError().get("Message");
		
		//Compare expected and actual
		Assert.assertEquals(1, actualMessageCode);
		Assert.assertEquals("required user id", actualMessage);
	}
	
	/*
	 * Test method deleteUser()
	 * Case id user exist
	*/
	@Test
	public void deleteUserCaseIdUserExist() {
		//Emulate return of userService.delete()
		UserEntity userEntity = new UserEntity();
		Mockito.when(userService.delete((long)1)).thenReturn(userEntity);
		
		//Get actual result
		ResponseEntity<Response> response = userApi.deleteUser((long)1);
		
		//Actual result
		int actualMessageCode = (int)response.getBody().getError().get("MessageCode");
		String actualMessage = (String)response.getBody().getError().get("Message");
		
		//Compare expected and actual
		Assert.assertEquals(0, actualMessageCode);
		Assert.assertEquals("Delete User Successfull", actualMessage);
	}
	
	/*
	 * Test method sendMail()
	 * Case Username not exist
	*/
	@Test
	public void sendMailCaseUserNotExist() {
		//Emulate return of userService.findByUsername()
		Mockito.when(userService.findByUsername("username")).thenReturn(null);

		//Get actual result
		ResponseEntity<Response> response = userApi.sendMail("username");
		
		//Actual result
		int actualMessageCode = (int)response.getBody().getError().get("MessageCode");
		String actualMessage = (String)response.getBody().getError().get("Message");
		
		//Compare expected and actual
		Assert.assertEquals(1, actualMessageCode);
		Assert.assertEquals("User is not exist", actualMessage);
	}
	
	/*
	 * Test method sendMail()
	 * Case Username and Email correct
	*/
	@Test
	public void sendMailCaseUsernameAndEmailCorrect() {
		//Emulate return of userService.findByUsername()
		UserEntity userEntity = new UserEntity();
		userEntity.setEmail("email");
		Mockito.when(userService.findByUsername("username")).thenReturn(userEntity);
		
		UserDTO userDto = new UserDTO();
		Mockito.when(userConverter.toDTO(userEntity)).thenReturn(userDto);
		
		Mockito.when(verificationTokenService.createVerification("email", "username")).thenReturn(true);
		
		//Get actual result
		ResponseEntity<Response> response = userApi.sendMail("username");
		
		//Actual result
		int actualMessageCode = (int)response.getBody().getError().get("MessageCode");
		String actualMessage = (String)response.getBody().getError().get("Message");
		
		//Compare expected and actual
		Assert.assertEquals(0, actualMessageCode);
		Assert.assertEquals("Sending mail successfully", actualMessage);
	}
	
	/*
	 * Test method sendMail()
	 * Case Username and Email incorrect
	*/
	@Test
	public void sendMailCaseUsernameAndEmailIncorrect() {
		//Emulate return of userService.findByUsername()
		UserEntity userEntity = new UserEntity();
		userEntity.setEmail("email");
		Mockito.when(userService.findByUsername("username")).thenReturn(userEntity);
		
		UserDTO userDto = new UserDTO();
		Mockito.when(userConverter.toDTO(userEntity)).thenReturn(userDto);
		
		Mockito.when(verificationTokenService.createVerification("email", "username")).thenReturn(false);
		
		//Get actual result
		ResponseEntity<Response> response = userApi.sendMail("username");
		
		//Actual result
		int actualMessageCode = (int)response.getBody().getError().get("MessageCode");
		String actualMessage = (String)response.getBody().getError().get("Message");
		
		//Compare expected and actual
		Assert.assertEquals(1, actualMessageCode);
		Assert.assertEquals("Sending mail fail", actualMessage);
	}
	
	/*
	 * Test method forgotPassword()
	 * Case Email not exist
	*/
	@Test
	public void forgotPasswordCaseEmailNotExist() {
		//Emulate return of userService.findByEmail()
		Mockito.when(userService.findByEmail("email")).thenReturn(null);

		//Get actual result
		ResponseEntity<Response> response = userApi.forgotPassword("email");
		
		//Actual result
		int actualMessageCode = (int)response.getBody().getError().get("MessageCode");
		String actualMessage = (String)response.getBody().getError().get("Message");
		
		//Compare expected and actual
		Assert.assertEquals(1, actualMessageCode);
		Assert.assertEquals("User is not exist", actualMessage);
	}
	//More 2 cases of forgot password
	
	/*
	 * Test method uploadBackground()
	 * Case id is null
	*/
	@Test
	public void uploadBackgroundCaseIdNull() {
		byte[] data = new byte[10];
		MockMultipartFile file = new MockMultipartFile("file", data);
		
		//Get actual result
		ResponseEntity<Response> response = userApi.uploadBackground(file, null);
		
		//Actual result
		int actualMessageCode = (int)response.getBody().getError().get("MessageCode");
		String actualMessage = (String)response.getBody().getError().get("Message");
		
		//Compare expected and actual
		Assert.assertEquals(1, actualMessageCode);
		Assert.assertEquals("Required param id", actualMessage);
	}
	//More 2 cases of upload background
	
	
	
}
