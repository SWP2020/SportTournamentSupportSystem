package doan2020.SportTournamentSupportSystem.converter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import doan2020.SportTournamentSupportSystem.dtIn.EditProfileDtIn;
import doan2020.SportTournamentSupportSystem.dtIn.RegisterDtIn;
import doan2020.SportTournamentSupportSystem.dtOut.TeamDtOut;
import doan2020.SportTournamentSupportSystem.dtOut.TournamentDtOut;
import doan2020.SportTournamentSupportSystem.dtOut.UserDtOut;
import doan2020.SportTournamentSupportSystem.entity.TeamEntity;
import doan2020.SportTournamentSupportSystem.entity.TournamentEntity;
import doan2020.SportTournamentSupportSystem.entity.UserEntity;
import doan2020.SportTournamentSupportSystem.service.IUserService;
import doan2020.SportTournamentSupportSystem.service.impl.UserService;
import net.bytebuddy.implementation.bytecode.Throw;

@Component
public class TeamConverter{
	
	@Autowired
	IUserService userService;
	
	public TeamEntity toEntity(Map<String, Object> map) throws Exception{
		TeamEntity entity = new TeamEntity();
		System.out.println("In toEntity:");
		try {
			entity.setFullName((String) map.get("fullName"));
			entity.setShortName((String) map.get("shortName"));
			entity.setDescription((String) map.get("description"));
			
			Long id = Long.parseLong(String.valueOf(map.get("creatorId")));
			UserEntity creator = userService.findOneById(id);
					
			entity.setCreator(creator);
			
			entity.setStatus((String) map.get("status"));

		}catch (Exception e) {
			System.out.println("Has Exception");
			throw e;
		}
		System.out.println("Out toEntity with no Exception");
		return entity;
	}

	public TeamDtOut toDTO(TeamEntity entity) throws Exception {
		
		TeamDtOut dto = new TeamDtOut();
		try {
			dto.setId(entity.getId());
			dto.setFullName(entity.getFullName());
			dto.setShortName(entity.getShortName());
			dto.setDescription(entity.getDescription());
			dto.setCreatorId(entity.getCreator().getId());
			dto.setStatus(entity.getStatus());
		} catch (Exception e) {
			throw e;
		}
		
		return dto;
	}

}
