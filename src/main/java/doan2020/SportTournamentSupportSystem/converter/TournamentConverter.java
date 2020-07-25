package doan2020.SportTournamentSupportSystem.converter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import doan2020.SportTournamentSupportSystem.dtIn.EditProfileDtIn;
import doan2020.SportTournamentSupportSystem.dtIn.RegisterDtIn;
import doan2020.SportTournamentSupportSystem.dtOut.TournamentDtOut;
import doan2020.SportTournamentSupportSystem.dtOut.UserDtOut;
import doan2020.SportTournamentSupportSystem.entity.TournamentEntity;
import doan2020.SportTournamentSupportSystem.entity.UserEntity;
import doan2020.SportTournamentSupportSystem.service.IUserService;
import doan2020.SportTournamentSupportSystem.service.impl.UserService;
import net.bytebuddy.implementation.bytecode.Throw;

@Component
public class TournamentConverter{
	
	@Autowired
	IUserService userService;
	
	public TournamentEntity toEntity(Map<String, Object> map) throws Exception{
		TournamentEntity entity = new TournamentEntity();
		System.out.println("In toEntity:");
		try {
			entity.setFullName((String) map.get("fullName"));
			entity.setShortName((String) map.get("shortName"));
			entity.setDescription((String) map.get("description"));
			
			Long id = Long.parseLong(String.valueOf(map.get("creatorId")));
			UserEntity creator = userService.findOneById(id);
					
			entity.setCreator(creator);
			
			entity.setOpeningLocation((String) map.get("openingLocation"));
			
			String open = String.valueOf(map.get("openingTime"));
			Date openingTime = new SimpleDateFormat("yyyy-mm-dd").parse(open);
			entity.setOpeningTime(openingTime);
			
			entity.setClosingLocation((String) map.get("closingLocation"));
			
			String close = String.valueOf(map.get("closingTime"));
			Date closingTime = new SimpleDateFormat("yyyy-mm-dd").parse(close);
			entity.setClosingTime(closingTime);
			
			entity.setDonor((String) map.get("donor"));
			entity.setStatus((String) map.get("status"));
			entity.setUrl((String) map.get("url"));
		}catch (Exception e) {
			System.out.println("Has Exception");
			throw e;
		}
		System.out.println("Out toEntity with no Exception");
		return entity;
	}

	public TournamentDtOut toDTO(TournamentEntity entity) throws Exception {
		
		TournamentDtOut dto = new TournamentDtOut();
		try {
			dto.setId(entity.getId());
			dto.setFullName(entity.getFullname());
			dto.setShortName(entity.getShortname());
			dto.setDescription(entity.getDescription());
			dto.setCreatorId(entity.getCreator().getId());
			dto.setOpeningLocation(entity.getOpeningLocation());
			dto.setOpeningTime(entity.getOpeningTime());
			dto.setClosingLocation(entity.getClosingLocation());
			dto.setClosingTime(entity.getClosingTime());
			dto.setDonor(entity.getDonor());
			dto.setStatus(entity.getStatus());
			dto.setUrl(entity.getUrl());
		} catch (Exception e) {
			throw e;
		}
		
		
		return dto;
	}

}
