package doan2020.SportTournamentSupportSystem.converter;

import org.springframework.stereotype.Component;

import doan2020.SportTournamentSupportSystem.dto.ApiDTO;
import doan2020.SportTournamentSupportSystem.entity.ApiEntity;

@Component
public class ApiConverter {
	
	public ApiEntity toEntity(ApiDTO dto) {
		System.out.println("ApiConverter: toEntity: start");
		ApiEntity entity = new ApiEntity();
		try {
			entity.setName(dto.getName());
			entity.setDescription(dto.getDescription());
			entity.setMethod(dto.getMethod());
			entity.setStatus(dto.getStatus());
			entity.setUrl(dto.getUrl());
			System.out.println("ApiConverter: toEntity: no exception");
		} catch (Exception e) {
			System.out.println("ApiConverter: toEntity: has exception");
			return null;
		}
		System.out.println("ApiConverter: toEntity: finish");
		return entity;
	}
	
	public ApiDTO toDTO(ApiEntity entity) {
		System.out.println("ApiConverter: toDTO: start");
		ApiDTO dto = new ApiDTO();
		try {
			dto.setName(entity.getName());
			dto.setDescription(entity.getDescription());
			dto.setMethod(entity.getMethod());
			dto.setStatus(entity.getStatus());
			dto.setUrl(entity.getUrl());
			System.out.println("ApiConverter: toDTO: no exception");
		} catch (Exception e) {
			System.out.println("ApiConverter: toDTO: has exception");
			return null;
		}
		System.out.println("ApiConverter: toDTO: finish");
		return dto;
	}

}
