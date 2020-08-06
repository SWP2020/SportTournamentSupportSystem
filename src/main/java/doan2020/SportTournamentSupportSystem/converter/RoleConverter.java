package doan2020.SportTournamentSupportSystem.converter;

import org.springframework.stereotype.Component;

import doan2020.SportTournamentSupportSystem.dto.RoleDTO;
import doan2020.SportTournamentSupportSystem.entity.RoleEntity;

@Component
public class RoleConverter {
	
	public RoleEntity toEntity(RoleDTO dto){
		System.out.println("RoleConverter: toEntity: start");
		RoleEntity entity = new RoleEntity();
		try {
			entity.setName(dto.getName());
			entity.setDescription(dto.getDescription());
			
			entity.setStatus(dto.getStatus());
			entity.setUrl(dto.getUrl());
			System.out.println("RoleConverter: toEntity: no exception");
		}catch (Exception e) {
			System.out.println("RoleConverter: toEntity: has exception");
			return null;
		}
		System.out.println("RoleConverter: toEntity: finish");
		return entity;
	}

	public RoleDTO toDTO(RoleEntity entity){
		System.out.println("RoleConverter: toDTO: finish");
		RoleDTO dto = new RoleDTO();
		try {
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setDescription(entity.getDescription());
			
			dto.setStatus(entity.getStatus());
			dto.setUrl(entity.getUrl());
			System.out.println("RoleConverter: toDTO: no exception");
		} catch (Exception e) {
			System.out.println("RoleConverter: toDTO: has exception");
			return null;
		}
		
		System.out.println("RoleConverter: toDTO: finish");
		return dto;
	}

}
