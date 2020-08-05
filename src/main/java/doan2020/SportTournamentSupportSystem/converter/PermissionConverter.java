package doan2020.SportTournamentSupportSystem.converter;

import org.springframework.stereotype.Component;

import doan2020.SportTournamentSupportSystem.dto.PermissionDTO;
import doan2020.SportTournamentSupportSystem.entity.PermissionEntity;

@Component
public class PermissionConverter {
	
	public PermissionEntity toEntity(PermissionDTO permissionDTO) {
		PermissionEntity entity = new PermissionEntity();
		return entity;
	}
	
	public PermissionDTO toDTO(PermissionEntity permissionEntity) {
		PermissionDTO dto = new PermissionDTO();
		return dto;
	}
}
