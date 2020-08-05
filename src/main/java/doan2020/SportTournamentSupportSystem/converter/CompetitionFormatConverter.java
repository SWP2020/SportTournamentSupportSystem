package doan2020.SportTournamentSupportSystem.converter;

import org.springframework.stereotype.Component;

import doan2020.SportTournamentSupportSystem.dto.CompetitionFormatDTO;
import doan2020.SportTournamentSupportSystem.entity.CompetitionFormatEntity;

@Component
public class CompetitionFormatConverter {
	
	public CompetitionFormatEntity toEntity(CompetitionFormatDTO competitionFormatDTO) {
		CompetitionFormatEntity entity = new CompetitionFormatEntity();
		return entity;
	}
	
	public CompetitionFormatDTO toDTO(CompetitionFormatEntity competitionFormatEntity) {
		CompetitionFormatDTO dto = new CompetitionFormatDTO();
		return dto;
	}

}
