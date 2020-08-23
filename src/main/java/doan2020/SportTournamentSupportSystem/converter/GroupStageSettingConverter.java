package doan2020.SportTournamentSupportSystem.converter;

import doan2020.SportTournamentSupportSystem.dto.GroupStageSettingDTO;
import doan2020.SportTournamentSupportSystem.entity.GroupStageSettingEntity;

public class GroupStageSettingConverter {
	
	public GroupStageSettingEntity toEntity(GroupStageSettingDTO dto){
		System.out.println("FinalStageSettingConverter: toEntity: start");
		GroupStageSettingEntity entity = new GroupStageSettingEntity();
		try {
			entity.setAdvanceTeamPerTable(dto.getAdvanceTeamPerTable());
			entity.setMaxTeamPerTable(dto.getMaxTeamPerTable());
			System.out.println("FinalStageSettingConverter: toEntity: no exception");
		}catch (Exception e) {
			System.out.println("FinalStageSettingConverter: toEntity: has exception");
			return null;
		}
		System.out.println("FinalStageSettingConverter: toEntity: finish");
		return entity;
	}

	public GroupStageSettingDTO toDTO(GroupStageSettingEntity entity){
		System.out.println("FinalStageSettingConverter: toDTO: finish");
		GroupStageSettingDTO dto = new GroupStageSettingDTO();
		try {
			dto.setId(entity.getId());
			dto.setAdvanceTeamPerTable(entity.getAdvanceTeamPerTable());
			dto.setAdvanceTeamPerTable(entity.getMaxTeamPerTable());
			System.out.println("FinalStageSettingConverter: toDTO: no exception");
		} catch (Exception e) {
			System.out.println("FinalStageSettingConverter: toDTO: has exception");
			return null;
		}
		
		System.out.println("FinalStageSettingConverter: toDTO: finish");
		return dto;
	}
}
