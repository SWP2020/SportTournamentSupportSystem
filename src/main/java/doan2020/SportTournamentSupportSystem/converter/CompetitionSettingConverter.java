package doan2020.SportTournamentSupportSystem.converter;

import org.springframework.stereotype.Component;

import doan2020.SportTournamentSupportSystem.dto.CompetitionSettingDTO;
import doan2020.SportTournamentSupportSystem.entity.CompetitionSettingEntity;

@Component
public class CompetitionSettingConverter {

	public CompetitionSettingEntity toEntity(CompetitionSettingDTO competitionSettingDTO) {
		CompetitionSettingEntity entity = new CompetitionSettingEntity();
		return entity;
	}

	public CompetitionSettingDTO toDTO(CompetitionSettingEntity competitionFormatEntity) {
		CompetitionSettingDTO dto = new CompetitionSettingDTO();
		return dto;
	}
}
