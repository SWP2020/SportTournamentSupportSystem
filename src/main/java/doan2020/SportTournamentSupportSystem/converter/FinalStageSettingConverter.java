package doan2020.SportTournamentSupportSystem.converter;

import doan2020.SportTournamentSupportSystem.dto.FinalStageSettingDTO;
import doan2020.SportTournamentSupportSystem.entity.FinalStageSettingEntity;

public class FinalStageSettingConverter {
	
	public FinalStageSettingEntity toEntity(FinalStageSettingDTO dto){
		System.out.println("FinalStageSettingConverter: toEntity: start");
		FinalStageSettingEntity entity = new FinalStageSettingEntity();
		try {
			System.out.println("FinalStageSettingConverter: toEntity: no exception");
		}catch (Exception e) {
			System.out.println("FinalStageSettingConverter: toEntity: has exception");
			return null;
		}
		System.out.println("FinalStageSettingConverter: toEntity: finish");
		return entity;
	}

	public FinalStageSettingDTO toDTO(FinalStageSettingEntity entity){
		System.out.println("FinalStageSettingConverter: toDTO: finish");
		FinalStageSettingDTO dto = new FinalStageSettingDTO();
		try {
			dto.setId(entity.getId());
			
			System.out.println("FinalStageSettingConverter: toDTO: no exception");
		} catch (Exception e) {
			System.out.println("FinalStageSettingConverter: toDTO: has exception");
			return null;
		}
		
		System.out.println("FinalStageSettingConverter: toDTO: finish");
		return dto;
	}
}
