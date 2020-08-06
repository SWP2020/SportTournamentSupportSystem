package doan2020.SportTournamentSupportSystem.converter;

import org.springframework.stereotype.Component;

import doan2020.SportTournamentSupportSystem.dto.ScoringUnitDTO;
import doan2020.SportTournamentSupportSystem.entity.ScoringUnitEntity;

@Component
public class ScoringUnitConverter {
	
	public ScoringUnitEntity toEntity(ScoringUnitDTO dto){
		System.out.println("ScoringUnitConverter: toEntity: start");
		ScoringUnitEntity entity = new ScoringUnitEntity();
		try {
			if (dto.getFullName() != null)
				entity.setFullName(dto.getFullName());
			if (dto.getShortName() != null)
				entity.setShortName(dto.getShortName());
			entity.setDescription(dto.getDescription());
			
			entity.setStatus(dto.getStatus());
			entity.setUrl(dto.getUrl());
			System.out.println("ScoringUnitConverter: toEntity: no exception");
		}catch (Exception e) {
			System.out.println("ScoringUnitConverter: toEntity: has exception");
			return null;
		}
		System.out.println("ScoringUnitConverter: toEntity: finish");
		return entity;
	}

	public ScoringUnitDTO toDTO(ScoringUnitEntity entity){
		System.out.println("ScoringUnitConverter: toDTO: finish");
		ScoringUnitDTO dto = new ScoringUnitDTO();
		try {
			dto.setId(entity.getId());
			dto.setFullName(entity.getFullName());
			dto.setShortName(entity.getShortName());
			
			dto.setDescription(entity.getDescription());
			dto.setStatus(entity.getStatus());
			dto.setUrl(entity.getUrl());
			System.out.println("ScoringUnitConverter: toDTO: no exception");
		} catch (Exception e) {
			System.out.println("ScoringUnitConverter: toDTO: has exception");
			return null;
		}
		
		System.out.println("ScoringUnitConverter: toDTO: finish");
		return dto;
	}
}
