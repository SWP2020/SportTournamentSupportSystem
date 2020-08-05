package doan2020.SportTournamentSupportSystem.converter;

import org.springframework.stereotype.Component;

import doan2020.SportTournamentSupportSystem.dto.RoleDTO;
import doan2020.SportTournamentSupportSystem.entity.RoleEntity;

@Component
public class RoleConverter {
	
	public RoleEntity toEntity(RoleDTO dto) {
		RoleEntity entity = new RoleEntity();
		return entity;
	}
	
	public RoleDTO toDTO(RoleEntity entity) {
		RoleDTO dto = new RoleDTO();
		return dto;
	}

}
