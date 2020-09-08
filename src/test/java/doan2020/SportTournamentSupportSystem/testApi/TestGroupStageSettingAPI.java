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

import doan2020.SportTournamentSupportSystem.api.GroupStageSettingAPI;
import doan2020.SportTournamentSupportSystem.converter.GroupStageSettingConverter;
import doan2020.SportTournamentSupportSystem.dto.GroupStageSettingDTO;
import doan2020.SportTournamentSupportSystem.entity.GroupStageSettingEntity;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.IGroupStageSettingService;
import doan2020.SportTournamentSupportSystem.service.impl.GroupStageSettingService;

@RunWith(SpringRunner.class)
public class TestGroupStageSettingAPI {

	//Bean definition
	@TestConfiguration
	public static class testGroupStageSettingAPIConfiguration {
		
		@Bean
		GroupStageSettingConverter converter() {
			return new GroupStageSettingConverter();
		}

		@Bean
		IGroupStageSettingService service() {
			return new GroupStageSettingService();
		}
		
		@Bean
		GroupStageSettingAPI groupStageSettingApi() {
			return new GroupStageSettingAPI();
		}
	}
	
	@MockBean
	private GroupStageSettingConverter converter;

	@MockBean
	private IGroupStageSettingService service;
	
	@Autowired
	GroupStageSettingAPI groupStageSettingApi;
	
	GroupStageSettingDTO groupStageSettingDto3;
	
	//Emulate before execute test
	@Before
	public void setUp() {
		//1
		//2
		Mockito.when(service.findByCompetitionId((long)2)).thenReturn(null);
		//3
		GroupStageSettingEntity groupStageSettingEntity3 = new GroupStageSettingEntity();
		Mockito.when(service.findByCompetitionId((long)3)).thenReturn(groupStageSettingEntity3);
		groupStageSettingDto3 = new GroupStageSettingDTO();
		Mockito.when(converter.toDTO(groupStageSettingEntity3)).thenReturn(groupStageSettingDto3);
	}
	
	@Test
	public void testGetGroupStageSettingCaseIdNull() {
		//Get actual result
		ResponseEntity<Response> response = groupStageSettingApi.getGroupStageSetting(null);
		
		//Actual result
		int actualMessageCode = (int)response.getBody().getError().get("MessageCode");
		String actualMessage = (String)response.getBody().getError().get("Message");
		int actualConfigGlobal = (int)response.getBody().getConfig().get("Global");
		GroupStageSettingDTO actualgroupStageSetting = (GroupStageSettingDTO)response.getBody().getResult().get("GroupStageSetting");
		
		//Compare expected and actual
		Assert.assertEquals(1, actualMessageCode);
		Assert.assertEquals("Required param competitionId", actualMessage);
		Assert.assertEquals(0, actualConfigGlobal);
		Assert.assertEquals(null, actualgroupStageSetting);
	}
	
	@Test
	public void testGetGroupStageSettingCaseGroupStageSettingNotExist() {
		//Get actual result
		ResponseEntity<Response> response = groupStageSettingApi.getGroupStageSetting((long)2);
		
		//Actual result
		int actualMessageCode = (int)response.getBody().getError().get("MessageCode");
		String actualMessage = (String)response.getBody().getError().get("Message");
		int actualConfigGlobal = (int)response.getBody().getConfig().get("Global");
		GroupStageSettingDTO actualgroupStageSetting = (GroupStageSettingDTO)response.getBody().getResult().get("GroupStageSetting");
		
		//Compare expected and actual
		Assert.assertEquals(1, actualMessageCode);
		Assert.assertEquals("Not found", actualMessage);
		Assert.assertEquals(0, actualConfigGlobal);
		Assert.assertEquals(null, actualgroupStageSetting);
	}
	
	@Test
	public void testGetGroupStageSetting() {
		//Get actual result
		ResponseEntity<Response> response = groupStageSettingApi.getGroupStageSetting((long)3);
		
		//Actual result
		int actualMessageCode = (int)response.getBody().getError().get("MessageCode");
		String actualMessage = (String)response.getBody().getError().get("Message");
		int actualConfigGlobal = (int)response.getBody().getConfig().get("Global");
		GroupStageSettingDTO actualgroupStageSetting = (GroupStageSettingDTO)response.getBody().getResult().get("GroupStageSetting");
		
		//Compare expected and actual
		Assert.assertEquals(0, actualMessageCode);
		Assert.assertEquals("Found", actualMessage);
		Assert.assertEquals(0, actualConfigGlobal);
		Assert.assertEquals(groupStageSettingDto3, actualgroupStageSetting);
	}
}
