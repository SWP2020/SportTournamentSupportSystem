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

import doan2020.SportTournamentSupportSystem.api.ReportAPI;
import doan2020.SportTournamentSupportSystem.converter.ReportConverter;
import doan2020.SportTournamentSupportSystem.entity.MatchEntity;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.IReportService;
import doan2020.SportTournamentSupportSystem.service.impl.ReportService;

@RunWith(SpringRunner.class)
public class TestReportAPI {

	@TestConfiguration
	public static class testReportAPIConfiguration {
		
		@Bean
		ReportConverter converter() {
			return new ReportConverter();
		}

		@Bean
		IReportService service() {
			return new ReportService();
		}
		
		@Bean
		ReportAPI reportApi() {
			return new ReportAPI();
		}
	}
	
	@MockBean
	private ReportConverter converter;

	@MockBean
	private IReportService service;
	
	@Autowired
	private ReportAPI reportApi;
	
	@Before
	public void setUp() {
		
	}
	
	@Test
	public void testGetReportCaseIdNull() {

		//phần expected result
		String expectedMessage = "Required param id";
		int expectedConfigGlobal = 0;
		
		//phần execute test
		ResponseEntity<Response> response = reportApi.getReport(null);
		
		//phan actual result
		String actualMessage = (String)response.getBody().getError().get("Message");
		int actualConfigGlobal = (int)response.getBody().getConfig().get("Global");
		MatchEntity actualMatch = (MatchEntity)response.getBody().getResult().get("Report");
		
		//phan so sanh ket qua
		Assert.assertEquals(expectedMessage, actualMessage);
		Assert.assertEquals(expectedConfigGlobal, actualConfigGlobal);
		Assert.assertEquals(null, actualMatch);
	}
}
