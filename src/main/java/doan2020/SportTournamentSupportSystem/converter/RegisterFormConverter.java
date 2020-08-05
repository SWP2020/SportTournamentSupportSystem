package doan2020.SportTournamentSupportSystem.converter;

import org.springframework.stereotype.Component;

import doan2020.SportTournamentSupportSystem.dto.RegisterFormDTO;
import doan2020.SportTournamentSupportSystem.entity.RegisterFormEntity;

@Component
public class RegisterFormConverter {
	
	public RegisterFormEntity toEntity(RegisterFormDTO dto) {
		RegisterFormEntity entity = new RegisterFormEntity();
		return entity;
	}
	
	public RegisterFormDTO toDTO(RegisterFormEntity entity) {
		RegisterFormDTO dto = new RegisterFormDTO();
		return dto;
	}
}
