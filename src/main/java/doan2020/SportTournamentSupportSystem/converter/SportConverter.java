package doan2020.SportTournamentSupportSystem.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import doan2020.SportTournamentSupportSystem.dto.SportDTO;
import doan2020.SportTournamentSupportSystem.entity.ScoringUnitEntity;
import doan2020.SportTournamentSupportSystem.entity.SportEntity;
import doan2020.SportTournamentSupportSystem.service.impl.ScoringUnitService;

@Component
public class SportConverter {
	
	@Autowired
	private ScoringUnitService scoringUnitService;
	
	public SportEntity toEntity(SportDTO dto){
		System.out.println("SportConverter: toEntity: start");
		SportEntity entity = new SportEntity();
		try {
			if (dto.getFullName() != null)
				entity.setFullName(dto.getFullName());
			if (dto.getShortName() != null)
				entity.setShortName(dto.getShortName());
			
			if (dto.getScoringUnitId() != null) {
				Long sportScoringUnitId = dto.getScoringUnitId();
				ScoringUnitEntity sportScoringUnit = scoringUnitService.findOneById(sportScoringUnitId);
				entity.setScoringUnit(sportScoringUnit);
			}
			entity.setDescription(dto.getDescription());
			
			entity.setStatus(dto.getStatus());
			entity.setUrl(dto.getUrl());
			System.out.println("SportConverter: toEntity: no exception");
		}catch (Exception e) {
			System.out.println("SportConverter: toEntity: has exception");
			return null;
		}
		System.out.println("SportConverter: toEntity: finish");
		return entity;
	}

	public SportDTO toDTO(SportEntity entity){
		System.out.println("SportConverter: toDTO: finish");
		SportDTO dto = new SportDTO();
		try {
			dto.setId(entity.getId());
			dto.setFullName(entity.getFullName());
			dto.setShortName(entity.getShortName());
			
			ScoringUnitEntity sportScoringUnit = entity.getScoringUnit();
			Long sportScoringUnitId = sportScoringUnit.getId();
			dto.setScoringUnitId(sportScoringUnitId);
			
			dto.setDescription(entity.getDescription());
			dto.setStatus(entity.getStatus());
			dto.setUrl(entity.getUrl());
			System.out.println("SportConverter: toDTO: no exception");
		} catch (Exception e) {
			System.out.println("SportConverter: toDTO: has exception");
			return null;
		}
		
		System.out.println("SportConverter: toDTO: finish");
		return dto;
	}
}
