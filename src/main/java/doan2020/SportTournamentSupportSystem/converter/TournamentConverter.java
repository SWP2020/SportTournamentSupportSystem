package doan2020.SportTournamentSupportSystem.converter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import doan2020.SportTournamentSupportSystem.dtOut.TournamentDtOut;
import doan2020.SportTournamentSupportSystem.entity.TournamentEntity;
import doan2020.SportTournamentSupportSystem.entity.UserEntity;
import doan2020.SportTournamentSupportSystem.service.IUserService;
import doan2020.SportTournamentSupportSystem.validator.Validator;

@Component
public class TournamentConverter{
	
	@Autowired
	IUserService userService;
	
	@Autowired
	private Validator validator;
	
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
			System.out.println("CP1");
			dto.setId(entity.getId());
			dto.setFullName(entity.getFullName());
			dto.setShortName(entity.getShortName());
			dto.setDescription(entity.getDescription());
			System.out.println("CP2");
			dto.setCreatorId(entity.getCreator().getId());
			System.out.println("CP3");
			dto.setOpeningLocation(entity.getOpeningLocation());
			dto.setOpeningTime(validator.formatDate(entity.getOpeningTime()));
			dto.setClosingLocation(entity.getClosingLocation());
			dto.setClosingTime(validator.formatDate(entity.getClosingTime()));
			dto.setDonor(entity.getDonor());
			dto.setStatus(entity.getStatus());
			dto.setUrl(entity.getUrl());
			System.out.println("CP4");
		} catch (Exception e) {
			System.out.println("Has exception in TournamentConverter.toDTO");
			throw e;
		}
		
		
		return dto;
	}

}
