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

import doan2020.SportTournamentSupportSystem.api.ResultAPI;
import doan2020.SportTournamentSupportSystem.converter.ResultConverter;
import doan2020.SportTournamentSupportSystem.entity.MatchEntity;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.IResultService;
import doan2020.SportTournamentSupportSystem.service.impl.ResultService;

@RunWith(SpringRunner.class)
public class TestResultAPI {
	
	@TestConfiguration
	public static class testReportAPIConfiguration {
		
		@Bean
		ResultConverter converter() {
			return new ResultConverter();
		}

		@Bean
		IResultService service() {
			return new ResultService();
		}
		
		@Bean
		ResultAPI resultApi() {
			return new ResultAPI();
		}
	}
	
	@MockBean
	private ResultConverter converter;

	@MockBean
	private IResultService service;
	
	@Autowired
	private ResultAPI resultApi;
	
	@Before
	public void setUp() {
		
	}
	
	@Test
	public void testGetResultCaseIdNull() {

		//phần expected result
		String expectedMessage = "Required param id";
		int expectedConfigGlobal = 0;
		
		//phần execute test
		ResponseEntity<Response> response = resultApi.getResult(null);
		
		//phan actual result
		String actualMessage = (String)response.getBody().getError().get("Message");
		int actualConfigGlobal = (int)response.getBody().getConfig().get("Global");
		MatchEntity actualMatch = (MatchEntity)response.getBody().getResult().get("Result");
		
		//phan so sanh ket qua
		Assert.assertEquals(expectedMessage, actualMessage);
		Assert.assertEquals(expectedConfigGlobal, actualConfigGlobal);
		Assert.assertEquals(null, actualMatch);
	}
}
