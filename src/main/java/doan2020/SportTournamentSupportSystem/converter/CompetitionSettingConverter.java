package doan2020.SportTournamentSupportSystem.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import doan2020.SportTournamentSupportSystem.dto.CompetitionSettingDTO;
import doan2020.SportTournamentSupportSystem.entity.CompetitionEntity;
import doan2020.SportTournamentSupportSystem.entity.CompetitionSettingEntity;
import doan2020.SportTournamentSupportSystem.service.ICompetitionService;

@Component
public class CompetitionSettingConverter {
	
	@Autowired
	private ICompetitionService competitionService;
	
	public CompetitionSettingEntity toEntity(CompetitionSettingDTO dto) {
		System.out.println("CompetitionSettingConverter: toEntity: start");
		CompetitionSettingEntity entity = new CompetitionSettingEntity();
		try {
			if (dto.getCompetitionId() != null) {
				Long competitionId = dto.getCompetitionId();
				CompetitionEntity competition = competitionService.findOneById(competitionId);
				entity.setCompetition(competition);
			}
			
			entity.setMaxNumberOfTeam(dto.getMaxNumberOfTeam());
			entity.setMaxMemberPerTeam(dto.getMaxMemberPerTeam());
			entity.setNumberOfTable(dto.getNumberOfTable());
			entity.setNumberOfTeamPassPerTable(dto.getNumberOfTeamPassPerTable());
			entity.setHomeGame(dto.getHomeGame());
			entity.setStatus(dto.getStatus());
			entity.setUrl(dto.getUrl());
			System.out.println("CompetitionSettingConverter: toEntity: no exception");
		} catch (Exception e) {
			System.out.println("CompetitionSettingConverter: toEntity: has exception");
			return null;
		}
		System.out.println("CompetitionSettingConverter: toEntity: finish");
		return entity;
	}
	
	public CompetitionSettingDTO toDTO(CompetitionSettingEntity entity) {
		System.out.println("CompetitionSettingConverter: toDTO: start");
		CompetitionSettingDTO dto = new CompetitionSettingDTO();
		try {
			dto.setId(entity.getId());
			
			dto.setCompetitionId(entity.getCompetition().getId());
			dto.setMaxNumberOfTeam(entity.getMaxNumberOfTeam());
			dto.setMaxMemberPerTeam(entity.getMaxMemberPerTeam());
			dto.setNumberOfTable(entity.getNumberOfTable());
			dto.setNumberOfTeamPassPerTable(entity.getNumberOfTeamPassPerTable());
			dto.setHomeGame(entity.getHomeGame());
			dto.setStatus(entity.getStatus());
			dto.setUrl(entity.getUrl());
			System.out.println("CompetitionSettingConverter: toDTO: no exception");
		} catch (Exception e) {
			System.out.println("CompetitionSettingConverter: toDTO: has exception");
			return null;
		}
		System.out.println("CompetitionSettingConverter: toDTO: finish");
		return dto;
	}
}
