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

import doan2020.SportTournamentSupportSystem.api.MatchAPI;
import doan2020.SportTournamentSupportSystem.converter.MatchConverter;
import doan2020.SportTournamentSupportSystem.dto.MatchDTO;
import doan2020.SportTournamentSupportSystem.entity.MatchEntity;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.IMatchService;
import doan2020.SportTournamentSupportSystem.service.IScheduleService;
import doan2020.SportTournamentSupportSystem.service.impl.MatchService;
import doan2020.SportTournamentSupportSystem.service.impl.ScheduleService;

@RunWith(SpringRunner.class)
public class TestMatchAPI {

	//Bean definition
	@TestConfiguration
	public static class testMatchAPIConfiguration {
		
		@Bean
		MatchConverter converter() {
			return new MatchConverter();
		}

		@Bean
		IMatchService service() {
			return new MatchService();
		}

		@Bean
		IScheduleService scheduleService() {
			return new ScheduleService();
		}
		
		@Bean
		MatchAPI matchApi() {
			return new MatchAPI();
		}
	}
	
	@MockBean
	private MatchConverter converter;

	@MockBean
	private IMatchService service;

	@MockBean
	private IScheduleService scheduleService;
	
	@Autowired
	private MatchAPI matchApi;
	
	MatchDTO matchDto3;
	
	//Emulate before execute test
	@Before
	public void setUp() {
		//1
		//2
		Mockito.when(service.findOneById((long)2)).thenReturn(null);
		//3
		MatchEntity match3 = new MatchEntity();
		Mockito.when(service.findOneById((long)3)).thenReturn(match3);
		matchDto3 = new MatchDTO();
		Mockito.when(converter.toDTO(match3)).thenReturn(matchDto3);
	}
	
	@Test
	public void testGetMatchCaseIdNull() {
		//Get actual result
		ResponseEntity<Response> response = matchApi.getMatch(null);
		
		//Actual result
		int actualMessageCode = (int)response.getBody().getError().get("MessageCode");
		String actualMessage = (String)response.getBody().getError().get("Message");
		int actualConfigGlobal = (int)response.getBody().getConfig().get("Global");
		MatchDTO actualMatch = (MatchDTO)response.getBody().getResult().get("Match");
		
		//Compare expected and actual
		Assert.assertEquals(1, actualMessageCode);
		Assert.assertEquals("Required param id", actualMessage);
		Assert.assertEquals(0, actualConfigGlobal);
		Assert.assertEquals(null, actualMatch);
	}
	
	@Test
	public void testGetMatchCaseMatchNotExist() {
		//Get actual result
		ResponseEntity<Response> response = matchApi.getMatch((long)2);
		
		//Actual result
		int actualMessageCode = (int)response.getBody().getError().get("MessageCode");
		String actualMessage = (String)response.getBody().getError().get("Message");
		int actualConfigGlobal = (int)response.getBody().getConfig().get("Global");
		MatchDTO actualMatch = (MatchDTO)response.getBody().getResult().get("Match");
		
		//Compare expected and actual
		Assert.assertEquals(1, actualMessageCode);
		Assert.assertEquals("Not found", actualMessage);
		Assert.assertEquals(0, actualConfigGlobal);
		Assert.assertEquals(null, actualMatch);
	}
	
	@Test
	public void testGetMatch() {
		//Get actual result
		ResponseEntity<Response> response = matchApi.getMatch((long)3);
		
		//Actual result
		int actualMessageCode = (int)response.getBody().getError().get("MessageCode");
		String actualMessage = (String)response.getBody().getError().get("Message");
		int actualConfigGlobal = (int)response.getBody().getConfig().get("Global");
		MatchDTO actualMatch = (MatchDTO)response.getBody().getResult().get("Match");
		
		//Compare expected and actual
		Assert.assertEquals(0, actualMessageCode);
		Assert.assertEquals("Found", actualMessage);
		Assert.assertEquals(0, actualConfigGlobal);
		Assert.assertEquals(matchDto3, actualMatch);
	}
}
