package doan2020.SportTournamentSupportSystem.converter;

import org.springframework.stereotype.Component;

import doan2020.SportTournamentSupportSystem.dto.ScoringUnitDTO;
import doan2020.SportTournamentSupportSystem.entity.ScoringUnitEntity;

@Component
public class ScoringUnitConverter {
	
	public ScoringUnitEntity toEntity(ScoringUnitDTO dto) {
		ScoringUnitEntity entity = new ScoringUnitEntity();
		return entity;
	}
	
	public ScoringUnitDTO toDTO(ScoringUnitEntity entity) {
		ScoringUnitDTO dto = new ScoringUnitDTO();
		return dto;
	}

}
