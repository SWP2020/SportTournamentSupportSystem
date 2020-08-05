package doan2020.SportTournamentSupportSystem.converter;

import org.springframework.stereotype.Component;

import doan2020.SportTournamentSupportSystem.dto.SportDTO;
import doan2020.SportTournamentSupportSystem.entity.SportEntity;

@Component
public class SportConverter {
	
	public SportEntity toEntity(SportDTO dto) {
		SportEntity entity = new SportEntity();
		return entity;
	}
	
	public SportDTO toDTO(SportEntity entity) {
		SportDTO dto = new SportDTO();
		return dto;
	}
}
