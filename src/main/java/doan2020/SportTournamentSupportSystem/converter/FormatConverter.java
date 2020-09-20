package doan2020.SportTournamentSupportSystem.converter;

import org.springframework.stereotype.Component;

import doan2020.SportTournamentSupportSystem.dto.FormatDTO;
import doan2020.SportTournamentSupportSystem.entity.FormatEntity;

@Component
public class FormatConverter {
	
	public FormatEntity toEntity(FormatDTO dto){
		System.out.println("TournamentFormatConverter: toEntity: start");
		FormatEntity entity = new FormatEntity();
		try {
			if (dto.getName() != null)
				entity.setName(dto.getName());

			entity.setDescription(dto.getDescription());
			
			
			System.out.println("TournamentFormatConverter: toEntity: no exception");
		}catch (Exception e) {
			System.out.println("TournamentFormatConverter: toEntity: has exception");
			return null;
		}
		System.out.println("TournamentFormatConverter: toEntity: finish");
		return entity;
	}

	public FormatDTO toDTO(FormatEntity entity){
		System.out.println("TournamentFormatConverter: toDTO: finish");
		FormatDTO dto = new FormatDTO();
		try {
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setDescription(entity.getDescription());
			
			dto.setStatus(entity.getStatus());
			dto.setUrl(entity.getUrl());
			System.out.println("TournamentFormatConverter: toDTO: no exception");
		} catch (Exception e) {
			System.out.println("TournamentFormatConverter: toDTO: has exception");
			return null;
		}
		
		System.out.println("TournamentFormatConverter: toDTO: finish");
		return dto;
	}

}
