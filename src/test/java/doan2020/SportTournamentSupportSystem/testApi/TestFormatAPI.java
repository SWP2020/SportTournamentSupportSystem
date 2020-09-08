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

import doan2020.SportTournamentSupportSystem.api.FormatAPI;
import doan2020.SportTournamentSupportSystem.converter.FormatConverter;
import doan2020.SportTournamentSupportSystem.entity.MatchEntity;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.IFormatService;
import doan2020.SportTournamentSupportSystem.service.impl.FormatService;

@RunWith(SpringRunner.class)
public class TestFormatAPI {

	@TestConfiguration
	public static class testFormatAPIConfiguration {
		@Bean
		FormatConverter converter() {
			return new FormatConverter();
		}
		
		@Bean
		IFormatService service() {
			return new FormatService();
		}
		
		@Bean
		FormatAPI formatApi() {
			return new FormatAPI();
		}
	}
	
	@MockBean
	FormatConverter converter;
	
	@MockBean
	IFormatService service;
	
	@Autowired
	FormatAPI formatApi;
	
	@Before
	public void setUp() {
		
	}
	
	@Test
	public void testGetFormatCaseIdNull() {
		//phần expected result
		String expectedMessage = "Required param id";
		int expectedConfigGlobal = 0;
		
		//phần execute test
		ResponseEntity<Response> response = formatApi.getFormat(null);
		
		//phan actual result
		String actualMessage = (String)response.getBody().getError().get("Message");
		int actualConfigGlobal = (int)response.getBody().getConfig().get("Global");
		MatchEntity actualMatch = (MatchEntity)response.getBody().getResult().get("Format");
		
		//phan so sanh ket qua
		Assert.assertEquals(expectedMessage, actualMessage);
		Assert.assertEquals(expectedConfigGlobal, actualConfigGlobal);
		Assert.assertEquals(null, actualMatch);
	}
	
	
}
