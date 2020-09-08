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

import doan2020.SportTournamentSupportSystem.api.FormatAPI;
import doan2020.SportTournamentSupportSystem.converter.FormatConverter;
import doan2020.SportTournamentSupportSystem.dto.FormatDTO;
import doan2020.SportTournamentSupportSystem.entity.FormatEntity;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.IFormatService;
import doan2020.SportTournamentSupportSystem.service.impl.FormatService;

@RunWith(SpringRunner.class)
public class TestFormatAPI {

	//Bean definition
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
	
	FormatDTO formatDto3;
	
	//Emulate before execute test
	@Before
	public void setUp() {
		//1
		//2
		Mockito.when(service.findOneById((long)2)).thenReturn(null);
		//3
		FormatEntity formatEntity3 = new FormatEntity();
		Mockito.when(service.findOneById((long)3)).thenReturn(formatEntity3);
		formatDto3 = new FormatDTO();
		Mockito.when(converter.toDTO(formatEntity3)).thenReturn(formatDto3);
	}
	
	@Test
	public void testGetFormatCaseIdNull() {
		//Get actual result
		ResponseEntity<Response> response = formatApi.getFormat(null);
		
		//Actual result
		int actualMessageCode = (int)response.getBody().getError().get("MessageCode");
		String actualMessage = (String)response.getBody().getError().get("Message");
		int actualConfigGlobal = (int)response.getBody().getConfig().get("Global");
		FormatDTO actualFormat = (FormatDTO)response.getBody().getResult().get("Format");
		
		//Compare expected and actual
		Assert.assertEquals(1, actualMessageCode);
		Assert.assertEquals("Required param id", actualMessage);
		Assert.assertEquals(0, actualConfigGlobal);
		Assert.assertEquals(null, actualFormat);
	}
	
	@Test
	public void testGetFormatCaseFormatNotExist() {
		//Get actual result
		ResponseEntity<Response> response = formatApi.getFormat((long)2);
		
		//Actual result
		int actualMessageCode = (int)response.getBody().getError().get("MessageCode");
		String actualMessage = (String)response.getBody().getError().get("Message");
		int actualConfigGlobal = (int)response.getBody().getConfig().get("Global");
		FormatDTO actualFormat = (FormatDTO)response.getBody().getResult().get("Format");
		
		//Compare expected and actual
		Assert.assertEquals(1, actualMessageCode);
		Assert.assertEquals("Not found", actualMessage);
		Assert.assertEquals(0, actualConfigGlobal);
		Assert.assertEquals(null, actualFormat);
	}
	
	@Test
	public void testGetFormat() {
		//Get actual result
		ResponseEntity<Response> response = formatApi.getFormat((long)3);
		
		//Actual result
		int actualMessageCode = (int)response.getBody().getError().get("MessageCode");
		String actualMessage = (String)response.getBody().getError().get("Message");
		int actualConfigGlobal = (int)response.getBody().getConfig().get("Global");
		FormatDTO actualFormat = (FormatDTO)response.getBody().getResult().get("Format");
		
		//Compare expected and actual
		Assert.assertEquals(0, actualMessageCode);
		Assert.assertEquals("Found", actualMessage);
		Assert.assertEquals(0, actualConfigGlobal);
		Assert.assertEquals(formatDto3, actualFormat);
	}
}
