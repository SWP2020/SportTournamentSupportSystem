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
import doan2020.SportTournamentSupportSystem.entity.GroupStageSettingEntity;
import doan2020.SportTournamentSupportSystem.entity.MatchEntity;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.IGroupStageSettingService;
import doan2020.SportTournamentSupportSystem.service.impl.GroupStageSettingService;

@RunWith(SpringRunner.class)
public class TestGroupStageSettingAPI {

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
	
	@Before
	public void setUp() {
		GroupStageSettingEntity groupStageSettingEntity = new GroupStageSettingEntity();
		Mockito.when(service.findByCompetitionId((long)1)).thenReturn(groupStageSettingEntity);
	}
	
	@Test
	public void testGetGroupStageSettingCaseIdNull() {

		//phần expected result
		String expectedMessage = "Required param competitionId";
		int expectedConfigGlobal = 0;
		
		//phần execute test
		ResponseEntity<Response> response = groupStageSettingApi.getGroupStageSetting(null);
		
		//phan actual result
		String actualMessage = (String)response.getBody().getError().get("Message");
		int actualConfigGlobal = (int)response.getBody().getConfig().get("Global");
		MatchEntity actualGroupStageSetting = (MatchEntity)response.getBody().getResult().get("GroupStageSetting");
		
		//phan so sanh ket qua
		Assert.assertEquals(expectedMessage, actualMessage);
		Assert.assertEquals(expectedConfigGlobal, actualConfigGlobal);
		Assert.assertEquals(null, actualGroupStageSetting);
	}
}
