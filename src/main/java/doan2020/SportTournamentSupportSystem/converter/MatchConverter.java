package doan2020.SportTournamentSupportSystem.converter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import doan2020.SportTournamentSupportSystem.dtIn.EditProfileDtIn;
import doan2020.SportTournamentSupportSystem.dtIn.RegisterDtIn;
import doan2020.SportTournamentSupportSystem.dtOut.MatchDtOut;
import doan2020.SportTournamentSupportSystem.dtOut.TournamentDtOut;
import doan2020.SportTournamentSupportSystem.dtOut.UserDtOut;
import doan2020.SportTournamentSupportSystem.entity.MatchEntity;
import doan2020.SportTournamentSupportSystem.entity.CompetitionEntity;
import doan2020.SportTournamentSupportSystem.entity.UserEntity;
import doan2020.SportTournamentSupportSystem.service.ICompetitionService;
import doan2020.SportTournamentSupportSystem.service.IUserService;
import doan2020.SportTournamentSupportSystem.service.impl.UserService;
import net.bytebuddy.implementation.bytecode.Throw;

@Component
public class MatchConverter{
	
	@Autowired
	ICompetitionService competitionService;
	
	public MatchEntity toEntity(Map<String, Object> map) throws Exception{
		MatchEntity entity = new MatchEntity();
		System.out.println("In toEntity:");
		try {
			entity.setName((String) map.get("name"));
			entity.setNumOfSet((int) map.get("numOfSet"));
			
			String expected = String.valueOf(map.get("expectedDate"));
			Date expectedDate = new SimpleDateFormat("yyyy-mm-dd").parse(expected);
			entity.setExpectedDate((Date) map.get("expectedDate"));
			
			entity.setExpectedPlace((String) map.get("expectedPlace"));
			
			String real = String.valueOf(map.get("realDate"));
			Date realDate = new SimpleDateFormat("yyyy-mm-dd").parse(real);
			entity.setRealDate((Date) map.get("realDate"));
			
			entity.setRealPlace((String) map.get("realPlace"));
			entity.setStatus((String) map.get("status"));
			
			Long id = Long.parseLong(String.valueOf(map.get("competitionId")));
			CompetitionEntity competition = competitionService.findOneById(id);
			entity.setCompetition(competition);
		}catch (Exception e) {
			System.out.println("Has Exception");
			throw e;
		}
		System.out.println("Out toEntity with no Exception");
		return entity;
	}

	public MatchDtOut toDTO(MatchEntity entity) throws Exception {
		
		MatchDtOut dto = new MatchDtOut();
		try {
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setNumOfSet(entity.getNumOfSet());
			dto.setExpectedDate(entity.getExpectedDate());
			dto.setExpectedPlace(entity.getExpectedPlace());
			dto.setRealDate(entity.getRealDate());
			dto.setRealPlace(entity.getRealPlace());
			dto.setStatus(entity.getStatus());
			dto.setCompetitionId(entity.getCompetition().getId());
		} catch (Exception e) {
			throw e;
		}
		
		return dto;
	}

}
