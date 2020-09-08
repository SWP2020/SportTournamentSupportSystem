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

import doan2020.SportTournamentSupportSystem.api.ResultAPI;
import doan2020.SportTournamentSupportSystem.converter.ResultConverter;
import doan2020.SportTournamentSupportSystem.dto.ResultDTO;
import doan2020.SportTournamentSupportSystem.entity.ResultEntity;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.IResultService;
import doan2020.SportTournamentSupportSystem.service.impl.ResultService;

@RunWith(SpringRunner.class)
public class TestResultAPI {
	
	//Bean definition
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
	
	ResultDTO resultDto3;
	
	//Emulate before execute test
	@Before
	public void setUp() {
		//1
		//2
		Mockito.when(service.findOneById((long)2)).thenReturn(null);
		//3
		ResultEntity result3 = new ResultEntity();
		Mockito.when(service.findOneById((long)3)).thenReturn(result3);
		resultDto3 = new ResultDTO();
		Mockito.when(converter.toDTO(result3)).thenReturn(resultDto3);
	}
	
	@Test
	public void testGetResultCaseIdNull() {
		//Get actual result
		ResponseEntity<Response> response = resultApi.getResult(null);
		
		//Actual result
		int actualMessageCode = (int)response.getBody().getError().get("MessageCode");
		String actualMessage = (String)response.getBody().getError().get("Message");
		int actualConfigGlobal = (int)response.getBody().getConfig().get("Global");
		ResultDTO actualResult = (ResultDTO)response.getBody().getResult().get("Result");
		
		//Compare expected and actual
		Assert.assertEquals(1, actualMessageCode);
		Assert.assertEquals("Required param id", actualMessage);
		Assert.assertEquals(0, actualConfigGlobal);
		Assert.assertEquals(null, actualResult);
	}
	
	@Test
	public void testGetResultCaseResultNotExist() {
		//Get actual result
		ResponseEntity<Response> response = resultApi.getResult((long)2);
		
		//Actual result
		int actualMessageCode = (int)response.getBody().getError().get("MessageCode");
		String actualMessage = (String)response.getBody().getError().get("Message");
		int actualConfigGlobal = (int)response.getBody().getConfig().get("Global");
		ResultDTO actualResult = (ResultDTO)response.getBody().getResult().get("Result");
		
		//Compare expected and actual
		Assert.assertEquals(1, actualMessageCode);
		Assert.assertEquals("Not found", actualMessage);
		Assert.assertEquals(0, actualConfigGlobal);
		Assert.assertEquals(null, actualResult);
	}
	
	@Test
	public void testGetResult() {
		//Get actual result
		ResponseEntity<Response> response = resultApi.getResult((long)3);
		
		//Actual result
		int actualMessageCode = (int)response.getBody().getError().get("MessageCode");
		String actualMessage = (String)response.getBody().getError().get("Message");
		int actualConfigGlobal = (int)response.getBody().getConfig().get("Global");
		ResultDTO actualResult = (ResultDTO)response.getBody().getResult().get("Result");
		
		//Compare expected and actual
		Assert.assertEquals(0, actualMessageCode);
		Assert.assertEquals("Found", actualMessage);
		Assert.assertEquals(0, actualConfigGlobal);
		Assert.assertEquals(resultDto3, actualResult);
	}
}
