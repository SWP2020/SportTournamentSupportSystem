package doan2020.SportTournamentSupportSystem.converter;

import org.springframework.stereotype.Component;

import doan2020.SportTournamentSupportSystem.dto.ApiDTO;
import doan2020.SportTournamentSupportSystem.entity.ApiEntity;

@Component
public class ApiConverter {
	
	public ApiEntity toEntity(ApiDTO apiDTO) {
		ApiEntity entity = new ApiEntity();
		return entity;
	}
	
	public ApiDTO toDTO(ApiEntity apiEntity) {
		ApiDTO dto = new ApiDTO();
		return dto;
	}

}
