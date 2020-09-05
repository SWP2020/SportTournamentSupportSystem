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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import doan2020.SportTournamentSupportSystem.api.CompetitionAPI;
import doan2020.SportTournamentSupportSystem.config.Const;
import doan2020.SportTournamentSupportSystem.converter.CompetitionConverter;
import doan2020.SportTournamentSupportSystem.converter.PermissionConverter;
import doan2020.SportTournamentSupportSystem.dto.CompetitionDTO;
import doan2020.SportTournamentSupportSystem.dto.PermissionDTO;
import doan2020.SportTournamentSupportSystem.dto.UserDTO;
import doan2020.SportTournamentSupportSystem.entity.CompetitionEntity;
import doan2020.SportTournamentSupportSystem.entity.PermissionEntity;
import doan2020.SportTournamentSupportSystem.entity.TournamentEntity;
import doan2020.SportTournamentSupportSystem.entity.UserEntity;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.ICompetitionService;
import doan2020.SportTournamentSupportSystem.service.IFinalStageSettingService;
import doan2020.SportTournamentSupportSystem.service.IGroupStageSettingService;
import doan2020.SportTournamentSupportSystem.service.IPermissionService;
import doan2020.SportTournamentSupportSystem.service.IUserService;
import doan2020.SportTournamentSupportSystem.service.impl.CompetitionService;
import doan2020.SportTournamentSupportSystem.service.impl.FinalStageSettingService;
import doan2020.SportTournamentSupportSystem.service.impl.GroupStageSettingService;
import doan2020.SportTournamentSupportSystem.service.impl.JwtService;
import doan2020.SportTournamentSupportSystem.service.impl.PermissionService;
import doan2020.SportTournamentSupportSystem.service.impl.UserService;

@RunWith(SpringRunner.class)
public class TestCompetitionAPI {

	@TestConfiguration
	public static class testloginAPIConfiguration {
		
		@Bean
		CompetitionAPI competitionApi() {
			return new CompetitionAPI();
		}
		
		@Bean
		ICompetitionService competitionService() {
			return new CompetitionService();
		}

		@Bean
		CompetitionConverter competitionConverter() {
			return new CompetitionConverter();
		}

		@Bean
		IUserService userService() {
			return new UserService();
		}

		@Bean
		IPermissionService permissionService() {
			return new PermissionService();
		}

		@Bean
		PermissionConverter permissionConverter() {
			return new PermissionConverter();
		}

		@Bean
		IFinalStageSettingService fssService() {
			return new FinalStageSettingService();
		}

		@Bean
		IGroupStageSettingService gssService() {
			return new GroupStageSettingService();
		}

		@Bean
		JwtService jwtService() {
			return new JwtService();
		}
	}
	
	@MockBean
	private ICompetitionService competitionService;

	@MockBean
	private CompetitionConverter competitionConverter;

	@MockBean
	private IUserService userService;

	@MockBean
	private IPermissionService permissionService;

	@MockBean
	private PermissionConverter permissionConverter;

	@MockBean
	private IFinalStageSettingService fssService;

	@MockBean
	private IGroupStageSettingService gssService;

	@MockBean
	private JwtService jwtService;
	
	@Autowired
	private CompetitionAPI competitionApi;
	
	PermissionDTO permissionDTO;
	CompetitionDTO competitionDTO;
	
	@Before
	public void setUp() {
		CompetitionEntity foundCompetition2 = new CompetitionEntity();
		CompetitionEntity foundCompetition3 = new CompetitionEntity();
		CompetitionEntity foundCompetition4 = new CompetitionEntity();
		CompetitionEntity foundCompetition5 = new CompetitionEntity();
		
		Mockito.when(competitionService.findOneById((long)1)).thenReturn(null);
		
		
		UserEntity creator2 = new UserEntity();
		creator2.setId((long)2);
		
		TournamentEntity tournament2 = new TournamentEntity();
		tournament2.setCreator(creator2);
		foundCompetition2.setTournament(tournament2);
		
		Mockito.when(competitionService.findOneById((long)2)).thenReturn(foundCompetition2);
		Mockito.when(jwtService.getUserNameFromJwtToken("anyJwt")).thenReturn("curentUserName2");
		
		UserEntity user2 = new UserEntity();
		user2.setId((long)2);
		
		Mockito.when(userService.findByUsername("curentUserName2")).thenReturn(user2);
		
		PermissionEntity permissionEntity = new PermissionEntity();
		Mockito.when(permissionService.findOneByName(Const.OWNER)).thenReturn(permissionEntity);
		permissionDTO = new PermissionDTO();
		Mockito.when(permissionConverter.toDTO(permissionEntity)).thenReturn(permissionDTO);
		competitionDTO = new CompetitionDTO();
		Mockito.when(competitionConverter.toDTO(foundCompetition2)).thenReturn(competitionDTO);
		
		Mockito.when(competitionService.findOneById((long)3)).thenReturn(foundCompetition3);
		Mockito.when(competitionService.findOneById((long)4)).thenReturn(foundCompetition4);
		Mockito.when(competitionService.findOneById((long)5)).thenReturn(foundCompetition5);
	}
	
	@Test
	public void testGetCompetitionCaseIdNotExist() {
		//phần expected result
		String expectedMessage = "Requried id";
		int expectedConfigGlobal = 0;
		
		//phần execute test
		ResponseEntity<Response> response = competitionApi.GetCompetiton("anyJwt", null);
		
		//phan actual result
		String actualMessage = (String)response.getBody().getError().get("Message");
		int actualConfigGlobal = (int)response.getBody().getConfig().get("Global");
		CompetitionEntity actualCompetition = (CompetitionEntity)response.getBody().getResult().get("Competition");
		//phan so sanh ket qua
		Assert.assertEquals(expectedMessage, actualMessage);
		Assert.assertEquals(expectedConfigGlobal, actualConfigGlobal);
		Assert.assertEquals(null, actualCompetition);
	}
	
	@Test
	public void testGetCompetitionCaseCompetitionNotExist() {
		//phần expected result
		String expectedMessage = "Not found";
		int expectedConfigGlobal = 0;
		
		//phần execute test
		ResponseEntity<Response> response = competitionApi.GetCompetiton("anyJwt", (long)1);
		
		//phan actual result
		String actualMessage = (String)response.getBody().getError().get("Message");
		int actualConfigGlobal = (int)response.getBody().getConfig().get("Global");
		CompetitionEntity actualCompetition = (CompetitionEntity)response.getBody().getResult().get("Competition");
		//phan so sanh ket qua
		Assert.assertEquals(expectedMessage, actualMessage);
		Assert.assertEquals(expectedConfigGlobal, actualConfigGlobal);
		Assert.assertEquals(null, actualCompetition);
	}
	
	@Test
	public void testGetCompetition() {
		//phần expected result
		String expectedMessage = "Found";
		
		//phần execute test
		ResponseEntity<Response> response = competitionApi.GetCompetiton("anyJwt", (long)2);
		
		//phan actual result
		String actualMessage = (String)response.getBody().getError().get("Message");
		PermissionDTO actualConfigGlobal = (PermissionDTO)response.getBody().getConfig().get("Global");
		CompetitionDTO actualCompetition = (CompetitionDTO)response.getBody().getResult().get("competition");
		
		//phan so sanh ket qua
		Assert.assertEquals(expectedMessage, actualMessage);
		Assert.assertEquals(permissionDTO, actualConfigGlobal);
		Assert.assertEquals(competitionDTO, actualCompetition);
	}
}
