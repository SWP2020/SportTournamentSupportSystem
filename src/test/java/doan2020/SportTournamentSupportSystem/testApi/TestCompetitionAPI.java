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

import doan2020.SportTournamentSupportSystem.api.CompetitionAPI;
import doan2020.SportTournamentSupportSystem.config.Const;
import doan2020.SportTournamentSupportSystem.converter.CompetitionConverter;
import doan2020.SportTournamentSupportSystem.converter.PermissionConverter;
import doan2020.SportTournamentSupportSystem.dto.CompetitionDTO;
import doan2020.SportTournamentSupportSystem.dto.PermissionDTO;
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

	//Bean definition
	@TestConfiguration
	public static class testCompetitionAPIConfiguration {
		
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
	
	PermissionDTO permissionDto3;
	CompetitionDTO competitionDto3;
	
	PermissionDTO permissionDto4;
	CompetitionDTO competitionDto4;
	
	CompetitionDTO competitionDto5;
	
	CompetitionDTO competitionDto6;
	
	CompetitionDTO competitionDto7;
	CompetitionDTO createCompetitionDto7;
	
	//Emulate before execute test
	@Before
	public void setUp() {
		//1
		//2
		Mockito.when(competitionService.findOneById((long)2)).thenReturn(null);
		//3
		UserEntity creator3 = new UserEntity();
		creator3.setId((long)3);
		
		TournamentEntity tournament3 = new TournamentEntity();
		tournament3.setCreator(creator3);
		
		CompetitionEntity competition3 = new CompetitionEntity();
		competition3.setTournament(tournament3);
		
		Mockito.when(competitionService.findOneById((long)3)).thenReturn(competition3);
		Mockito.when(jwtService.getUserNameFromJwtToken("jwt3")).thenReturn("user3");
		
		UserEntity user3 = new UserEntity();
		user3.setId((long)3);
		
		Mockito.when(userService.findByUsername("user3")).thenReturn(user3);
		
		PermissionEntity permissionEntity3 = new PermissionEntity();
		Mockito.when(permissionService.findOneByName(Const.OWNER)).thenReturn(permissionEntity3);
		permissionDto3 = new PermissionDTO();
		Mockito.when(permissionConverter.toDTO(permissionEntity3)).thenReturn(permissionDto3);
		competitionDto3 = new CompetitionDTO();
		Mockito.when(competitionConverter.toDTO(competition3)).thenReturn(competitionDto3);
		//4
		UserEntity creator4 = new UserEntity();
		creator4.setId((long)41);
		
		TournamentEntity tournament4 = new TournamentEntity();
		tournament4.setCreator(creator4);
		
		CompetitionEntity competition4 = new CompetitionEntity();
		competition4.setTournament(tournament4);
		
		Mockito.when(competitionService.findOneById((long)4)).thenReturn(competition4);
		Mockito.when(jwtService.getUserNameFromJwtToken("jwt4")).thenReturn("user4");
		
		UserEntity user4 = new UserEntity();
		user4.setId((long)4);
		
		Mockito.when(userService.findByUsername("user4")).thenReturn(user4);
		
		PermissionEntity permissionEntity4 = new PermissionEntity();
		Mockito.when(permissionService.findOneByName(Const.VIEWER)).thenReturn(permissionEntity4);
		permissionDto4 = new PermissionDTO();
		Mockito.when(permissionConverter.toDTO(permissionEntity4)).thenReturn(permissionDto4);
		competitionDto4 = new CompetitionDTO();
		Mockito.when(competitionConverter.toDTO(competition4)).thenReturn(competitionDto4);
		//5
		competitionDto5 = new CompetitionDTO();
		Mockito.when(competitionConverter.toEntity(competitionDto5)).thenReturn(null);
		//6
		TournamentEntity tournament6 = new TournamentEntity();
		tournament6.setStatus(Const.TOURNAMENT_STATUS_REGISTRATION_OPENING);
		CompetitionEntity competition6 = new CompetitionEntity();
		competition6.setTournament(tournament6);
		competitionDto6 = new CompetitionDTO();
		Mockito.when(competitionConverter.toEntity(competitionDto6)).thenReturn(competition6);
		//7
		TournamentEntity tournament7 = new TournamentEntity();
		tournament7.setStatus(Const.TOURNAMENT_STATUS_INITIALIZING);
		CompetitionEntity competition7 = new CompetitionEntity();
		competition7.setTournament(tournament7);
		Mockito.when(competitionConverter.toEntity(competitionDto7)).thenReturn(competition7);
		Mockito.when(competitionService.create(competition7)).thenReturn(null);//
		createCompetitionDto7 = new CompetitionDTO();
		Mockito.when(competitionConverter.toDTO(competition7)).thenReturn(createCompetitionDto7);
	}
	
	@Test
	public void testGetCompetitionCaseIdNull() {
		//Get actual result
		ResponseEntity<Response> response = competitionApi.GetCompetiton("jwt1", null);
		
		//Actual result
		int actualMessageCode = (int)response.getBody().getError().get("MessageCode");
		String actualMessage = (String)response.getBody().getError().get("Message");
		int actualConfigGlobal = (int)response.getBody().getConfig().get("Global");
		CompetitionEntity actualCompetition = (CompetitionEntity)response.getBody().getResult().get("Competition");

		//Compare expected and actual
		Assert.assertEquals(1, actualMessageCode);
		Assert.assertEquals("Requried id", actualMessage);
		Assert.assertEquals(0, actualConfigGlobal);
		Assert.assertEquals(null, actualCompetition);
	}
	
	@Test
	public void testGetCompetitionCaseCompetitionNotExist() {
		//Get actual result
		ResponseEntity<Response> response = competitionApi.GetCompetiton("jwt2", (long)2);
		
		//Actual result
		int actualMessageCode = (int)response.getBody().getError().get("MessageCode");
		String actualMessage = (String)response.getBody().getError().get("Message");
		int actualConfigGlobal = (int)response.getBody().getConfig().get("Global");
		CompetitionEntity actualCompetition = (CompetitionEntity)response.getBody().getResult().get("Competition");

		//Compare expected and actual
		Assert.assertEquals(1, actualMessageCode);
		Assert.assertEquals("Not found", actualMessage);
		Assert.assertEquals(0, actualConfigGlobal);
		Assert.assertEquals(null, actualCompetition);
	}
	
	@Test
	public void testGetCompetitionCaseOwner() {
		//Get actual result
		ResponseEntity<Response> response = competitionApi.GetCompetiton("jwt3", (long)3);
		
		//Actual result
		int actualMessageCode = (int)response.getBody().getError().get("MessageCode");
		String actualMessage = (String)response.getBody().getError().get("Message");
		PermissionDTO actualConfigGlobal = (PermissionDTO)response.getBody().getConfig().get("Global");
		CompetitionDTO actualCompetition = (CompetitionDTO)response.getBody().getResult().get("competition");
		
		//Compare expected and actual
		Assert.assertEquals(0, actualMessageCode);
		Assert.assertEquals("Found", actualMessage);
		Assert.assertEquals(permissionDto3, actualConfigGlobal);
		Assert.assertEquals(competitionDto3, actualCompetition);
	}
	
	@Test
	public void testGetCompetitionCaseViewer() {
		//Get actual result
		ResponseEntity<Response> response = competitionApi.GetCompetiton("jwt4", (long)4);
		
		//Actual result
		int actualMessageCode = (int)response.getBody().getError().get("MessageCode");
		String actualMessage = (String)response.getBody().getError().get("Message");
		PermissionDTO actualConfigGlobal = (PermissionDTO)response.getBody().getConfig().get("Global");
		CompetitionDTO actualCompetition = (CompetitionDTO)response.getBody().getResult().get("competition");
		
		//Compare expected and actual
		Assert.assertEquals(0, actualMessageCode);
		Assert.assertEquals("Found", actualMessage);
		Assert.assertEquals(permissionDto4, actualConfigGlobal);
		Assert.assertEquals(competitionDto4, actualCompetition);
	}
	
	@Test
	public void testCreateCompetitionCaseConvertFail() {
		//Get actual result
		ResponseEntity<Response> response = competitionApi.createCompetition(competitionDto5);
		
		//Actual result
		int actualMessageCode = (int)response.getBody().getError().get("MessageCode");
		String actualMessage = (String)response.getBody().getError().get("Message");
		int actualConfigGlobal = (int)response.getBody().getConfig().get("Global");
		CompetitionDTO actualCompetition = (CompetitionDTO)response.getBody().getResult().get("Competition");
		
		//Compare expected and actual
		Assert.assertEquals(1, actualMessageCode);
		Assert.assertEquals("create new Competition fail", actualMessage);
		Assert.assertEquals(0, actualConfigGlobal);
		Assert.assertEquals(null, actualCompetition);
	}
	
	@Test
	public void testCreateCompetitionCaseRegistrationOpening() {
		//Get actual result
		ResponseEntity<Response> response = competitionApi.createCompetition(competitionDto6);
		
		//Actual result
		int actualMessageCode = (int)response.getBody().getError().get("MessageCode");
		String actualMessage = (String)response.getBody().getError().get("Message");
		int actualConfigGlobal = (int)response.getBody().getConfig().get("Global");
		CompetitionDTO actualCompetition = (CompetitionDTO)response.getBody().getResult().get("Competition");
		
		//Compare expected and actual
		Assert.assertEquals(1, actualMessageCode);
		Assert.assertEquals(Const.TOURNAMENT_MESSAGE_REGISTRATION_OPENING, actualMessage);
		Assert.assertEquals(0, actualConfigGlobal);
		Assert.assertEquals(null, actualCompetition);
	}
	
	@Test
	public void testCreateCompetition() {
		//Get actual result
		ResponseEntity<Response> response = competitionApi.createCompetition(competitionDto7);
		
		//Actual result
		int actualMessageCode = (int)response.getBody().getError().get("MessageCode");
		String actualMessage = (String)response.getBody().getError().get("Message");
		int actualConfigGlobal = (int)response.getBody().getConfig().get("Global");
		CompetitionDTO actualCompetition = (CompetitionDTO)response.getBody().getResult().get("Competition");
		
		//Compare expected and actual
		Assert.assertEquals(0, actualMessageCode);
		Assert.assertEquals("create new Competition successfull", actualMessage);
		Assert.assertEquals(0, actualConfigGlobal);
		Assert.assertEquals(createCompetitionDto7, actualCompetition);
	}
}
