package doan2020.SportTournamentSupportSystem.converter;

import org.springframework.stereotype.Component;

import doan2020.SportTournamentSupportSystem.dto.ScoringUniDTO;
import doan2020.SportTournamentSupportSystem.entity.ScoringUniEntity;

@Component
public class ScoringUniConverter {
	
	public ScoringUniEntity toEntity(ScoringUniDTO dto) {
		ScoringUniEntity entity = new ScoringUniEntity();
		return entity;
	}
	
	public ScoringUniDTO toDTO(ScoringUniEntity entity) {
		ScoringUniDTO dto = new ScoringUniDTO();
		return dto;
	}

}
