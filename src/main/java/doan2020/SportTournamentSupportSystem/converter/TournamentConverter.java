package doan2020.SportTournamentSupportSystem.converter;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import doan2020.SportTournamentSupportSystem.dto.TournamentDTO;
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
	
	public TournamentEntity toEntity(TournamentDTO dto){
		System.out.println("TournamentConverter: toEntity: start");
		TournamentEntity entity = new TournamentEntity();
		System.out.println("In toEntity:");
		try {
			if (dto.getFullName() != null)
				entity.setFullName(dto.getFullName());
			if (dto.getShortName() != null)
				entity.setShortName(dto.getShortName());
			entity.setDescription(dto.getDescription());
			
			if (dto.getCreatorId() != null) {
				Long tournamentCreatorId = dto.getCreatorId();
				UserEntity tournamentCreator = userService.findOneById(tournamentCreatorId);
				entity.setCreator(tournamentCreator);
			}
			entity.setOpeningLocation(dto.getOpeningLocation());
			
			Date openingTime = validator.formatStringToDate(dto.getOpeningTime());
			entity.setOpeningTime(openingTime);
			
			entity.setClosingLocation(dto.getClosingLocation());
			
			Date closingTime = validator.formatStringToDate(dto.getClosingTime());
			entity.setOpeningTime(closingTime);
			
			entity.setDonor(dto.getDonor());
			entity.setStatus(dto.getStatus());
			entity.setUrl(dto.getUrl());
			entity.setAvatar(dto.getAvatar());
			entity.setBackground(dto.getBackground());
			System.out.println("TournamentConverter: toEntity: no exception");
		}catch (Exception e) {
			System.out.println("TournamentConverter: toEntity: has exception");
			return null;
		}
		System.out.println("TournamentConverter: toEntity: finish");
		return entity;
	}

	public TournamentDTO toDTO(TournamentEntity entity){
		System.out.println("TournamentConverter: toDTO: finish");
		TournamentDTO dto = new TournamentDTO();
		try {
			dto.setId(entity.getId());
			dto.setFullName(entity.getFullName());
			dto.setShortName(entity.getShortName());
			dto.setDescription(entity.getDescription());
			
			
			UserEntity tournamentCreator = entity.getCreator();
			Long tournamentCreatorId = tournamentCreator.getId();
			dto.setCreatorId(tournamentCreatorId);
			
			System.out.println("TournamentConverter: toDTO: CP1");
			
			dto.setOpeningLocation(entity.getOpeningLocation());
			
			String openingTime = validator.formatDateToString(entity.getOpeningTime());
			dto.setOpeningTime(openingTime);
			
			dto.setClosingLocation(entity.getClosingLocation());
			
			String closingTime = validator.formatDateToString(entity.getClosingTime());
			dto.setClosingTime(closingTime);
			
			dto.setDonor(entity.getDonor());
			dto.setStatus(entity.getStatus());
			dto.setUrl(entity.getUrl());
			String createdDate = validator.formatDateToString(entity.getCreatedDate());
			dto.setCreatedDate(createdDate);
			dto.setAvatar(entity.getAvatar());
			dto.setBackground(entity.getBackground());
			System.out.println("TournamentConverter: toDTO: no exception");
		} catch (Exception e) {
			System.out.println("TournamentConverter: toDTO: has exception");
			return null;
		}
		
		System.out.println("TournamentConverter: toDTO: finish");
		return dto;
	}

}
