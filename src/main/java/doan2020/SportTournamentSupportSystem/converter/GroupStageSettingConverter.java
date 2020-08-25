package doan2020.SportTournamentSupportSystem.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import doan2020.SportTournamentSupportSystem.dto.GroupStageSettingDTO;
import doan2020.SportTournamentSupportSystem.entity.GroupStageSettingEntity;
import doan2020.SportTournamentSupportSystem.entity.CompetitionEntity;
import doan2020.SportTournamentSupportSystem.entity.FormatEntity;
import doan2020.SportTournamentSupportSystem.service.ICompetitionService;
import doan2020.SportTournamentSupportSystem.service.IFormatService;


@Component
public class GroupStageSettingConverter {

	@Autowired
	private IFormatService formatService;
	
	@Autowired
	private ICompetitionService competitionService;
	
	public GroupStageSettingEntity toEntity(GroupStageSettingDTO dto){
		System.out.println("GroupStageSettingConverter: toEntity: start");
		GroupStageSettingEntity entity = new GroupStageSettingEntity();
		try {
			entity.setHasHomeMatch(dto.isHasHomeMatch());
			if (dto.getFormatId() != null) {
				Long formatId = dto.getFormatId();
				if (dto.getCompetitionId() != null) {
					CompetitionEntity competition = competitionService.findOneById(dto.getCompetitionId());
					entity.setCompetition(competition);;
				}
				FormatEntity format = formatService.findOneById(formatId);
				entity.setFormat(format);
				entity.setMaxTeamPerTable(dto.getMaxTeamPerTable());
				entity.setAdvanceTeamPerTable(dto.getAdvanceTeamPerTable());
				entity.setStatus(dto.getStatus());
				entity.setUrl(dto.getUrl());
			}
			System.out.println("GroupStageSettingConverter: toEntity: no exception");
		}catch (Exception e) {
			System.out.println("GroupStageSettingConverter: toEntity: has exception");
			return null;
		}
		System.out.println("GroupStageSettingConverter: toEntity: finish");
		return entity;
	}

	public GroupStageSettingDTO toDTO(GroupStageSettingEntity entity){
		System.out.println("GroupStageSettingConverter: toDTO: finish");
		GroupStageSettingDTO dto = new GroupStageSettingDTO();
		try {
			dto.setId(entity.getId());
			if (entity.getFormat() != null)
				dto.setFormatId(entity.getFormat().getId());
			if (entity.getCompetition() != null)
				dto.setCompetitionId(entity.getCompetition().getId());
			dto.setHasHomeMatch(entity.isHasHomeMatch());
			dto.setMaxTeamPerTable(entity.getMaxTeamPerTable());
			dto.setAdvanceTeamPerTable(entity.getAdvanceTeamPerTable());
			dto.setStatus(entity.getStatus());
			dto.setUrl(entity.getUrl());
			System.out.println("GroupStageSettingConverter: toDTO: no exception");
		} catch (Exception e) {
			System.out.println("GroupStageSettingConverter: toDTO: has exception");
			return null;
		}
		
		System.out.println("GroupStageSettingConverter: toDTO: finish");
		return dto;
	}
}
