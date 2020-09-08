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

import doan2020.SportTournamentSupportSystem.api.ReportAPI;
import doan2020.SportTournamentSupportSystem.converter.ReportConverter;
import doan2020.SportTournamentSupportSystem.dto.ReportDTO;
import doan2020.SportTournamentSupportSystem.entity.ReportEntity;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.IReportService;
import doan2020.SportTournamentSupportSystem.service.impl.ReportService;

@RunWith(SpringRunner.class)
public class TestReportAPI {

	//Bean definition
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
	
	ReportDTO reportDto3;
	
	//Emulate before execute test
	@Before
	public void setUp() {
		//1
		//2
		Mockito.when(service.findOneById((long)2)).thenReturn(null);
		//3
		ReportEntity report3 = new ReportEntity();
		Mockito.when(service.findOneById((long)3)).thenReturn(report3);
		reportDto3 = new ReportDTO();
		Mockito.when(converter.toDTO(report3)).thenReturn(reportDto3);
	}
	
	@Test
	public void testGetReportCaseIdNull() {
		//Get actual result
		ResponseEntity<Response> response = reportApi.getReport(null);
		
		//Actual result
		int actualMessageCode = (int)response.getBody().getError().get("MessageCode");
		String actualMessage = (String)response.getBody().getError().get("Message");
		int actualConfigGlobal = (int)response.getBody().getConfig().get("Global");
		ReportDTO actualreport = (ReportDTO)response.getBody().getResult().get("Report");
		
		//Compare expected and actual
		Assert.assertEquals(1, actualMessageCode);
		Assert.assertEquals("Required param id", actualMessage);
		Assert.assertEquals(0, actualConfigGlobal);
		Assert.assertEquals(null, actualreport);
	}
	
	@Test
	public void testGetReportCaseReportNotExist() {
		//Get actual result
		ResponseEntity<Response> response = reportApi.getReport((long)2);
		
		//Actual result
		int actualMessageCode = (int)response.getBody().getError().get("MessageCode");
		String actualMessage = (String)response.getBody().getError().get("Message");
		int actualConfigGlobal = (int)response.getBody().getConfig().get("Global");
		ReportDTO actualreport = (ReportDTO)response.getBody().getResult().get("Report");
		
		//Compare expected and actual
		Assert.assertEquals(1, actualMessageCode);
		Assert.assertEquals("Not found", actualMessage);
		Assert.assertEquals(0, actualConfigGlobal);
		Assert.assertEquals(null, actualreport);
	}
	
	@Test
	public void testGetReport() {
		//Get actual result
		ResponseEntity<Response> response = reportApi.getReport((long)3);
		
		//Actual result
		int actualMessageCode = (int)response.getBody().getError().get("MessageCode");
		String actualMessage = (String)response.getBody().getError().get("Message");
		int actualConfigGlobal = (int)response.getBody().getConfig().get("Global");
		ReportDTO actualreport = (ReportDTO)response.getBody().getResult().get("Report");
		
		//Compare expected and actual
		Assert.assertEquals(0, actualMessageCode);
		Assert.assertEquals("Found", actualMessage);
		Assert.assertEquals(0, actualConfigGlobal);
		Assert.assertEquals(reportDto3, actualreport);
	}
}
