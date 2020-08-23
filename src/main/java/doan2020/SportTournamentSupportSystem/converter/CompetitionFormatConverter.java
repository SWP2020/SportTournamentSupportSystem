package doan2020.SportTournamentSupportSystem.converter;

import org.springframework.stereotype.Component;

import doan2020.SportTournamentSupportSystem.dto.CompetitionFormatDTO;
import doan2020.SportTournamentSupportSystem.entity.CompetitionFormatEntity;

@Component
public class CompetitionFormatConverter {
	
	public CompetitionFormatEntity toEntity(CompetitionFormatDTO dto){
		System.out.println("CompetitionFormatConverter: toEntity: start");
		CompetitionFormatEntity entity = new CompetitionFormatEntity();
		try {
			if (dto.getName() != null)
				entity.setName(dto.getName());

			entity.setDescription(dto.getDescription());
			entity.setStatus(dto.getStatus());
			entity.setUrl(dto.getUrl());
			entity.setHasHomeMatch(dto.isHasHomeMatch());
			System.out.println("CompetitionFormatConverter: toEntity: no exception");
		}catch (Exception e) {
			System.out.println("CompetitionFormatConverter: toEntity: has exception");
			return null;
		}
		System.out.println("CompetitionFormatConverter: toEntity: finish");
		return entity;
	}

	public CompetitionFormatDTO toDTO(CompetitionFormatEntity entity){
		System.out.println("CompetitionFormatConverter: toDTO: finish");
		CompetitionFormatDTO dto = new CompetitionFormatDTO();
		try {
			
			dto.setName(entity.getName());
			dto.setDescription(entity.getDescription());
			
			dto.setStatus(entity.getStatus());
			dto.setUrl(entity.getUrl());
			dto.setHasHomeMatch(entity.isHasHomeMatch());
			System.out.println("CompetitionFormatConverter: toDTO: no exception");
		} catch (Exception e) {
			System.out.println("CompetitionFormatConverter: toDTO: has exception");
			return null;
		}
		
		System.out.println("CompetitionFormatConverter: toDTO: finish");
		return dto;
	}

}
