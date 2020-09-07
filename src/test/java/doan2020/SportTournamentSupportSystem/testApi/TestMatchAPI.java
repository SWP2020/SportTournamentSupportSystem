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
import doan2020.SportTournamentSupportSystem.entity.MatchEntity;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.IMatchService;
import doan2020.SportTournamentSupportSystem.service.IScheduleService;
import doan2020.SportTournamentSupportSystem.service.impl.MatchService;
import doan2020.SportTournamentSupportSystem.service.impl.ScheduleService;

@RunWith(SpringRunner.class)
public class TestMatchAPI {

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
	
	@Before
	public void setUp() {
		MatchEntity match = new MatchEntity();
		Mockito.when(service.findOneById((long)1)).thenReturn(match);
		
	}
	
	@Test
	public void testGetMatchCaseIdNull() {

		//phần expected result
		String expectedMessage = "Required param id";
		int expectedConfigGlobal = 0;
		
		//phần execute test
		ResponseEntity<Response> response = matchApi.getMatch(null);
		
		//phan actual result
		String actualMessage = (String)response.getBody().getError().get("Message");
		int actualConfigGlobal = (int)response.getBody().getConfig().get("Global");
		MatchEntity actualMatch = (MatchEntity)response.getBody().getResult().get("Match");
		
		//phan so sanh ket qua
		Assert.assertEquals(expectedMessage, actualMessage);
		Assert.assertEquals(expectedConfigGlobal, actualConfigGlobal);
		Assert.assertEquals(null, actualMatch);
	}
	
}
