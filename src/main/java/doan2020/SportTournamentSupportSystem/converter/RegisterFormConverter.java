package doan2020.SportTournamentSupportSystem.converter;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import doan2020.SportTournamentSupportSystem.dto.RegisterFormDTO;
import doan2020.SportTournamentSupportSystem.dto.RegisterFormDTO;
import doan2020.SportTournamentSupportSystem.entity.CompetitionEntity;
import doan2020.SportTournamentSupportSystem.entity.RegisterFormEntity;
import doan2020.SportTournamentSupportSystem.entity.RegisterFormEntity;
import doan2020.SportTournamentSupportSystem.entity.TeamEntity;
import doan2020.SportTournamentSupportSystem.service.ICompetitionService;
import doan2020.SportTournamentSupportSystem.service.ICompetitionSettingService;
import doan2020.SportTournamentSupportSystem.service.ITeamService;

@Component
public class RegisterFormConverter {
	
	@Autowired
	private ITeamService teamService;
	
	@Autowired
	private ICompetitionService competitionService;
	
	@Autowired
	private ICompetitionSettingService competitionSettingService;
	
	public RegisterFormEntity toEntity(RegisterFormDTO dto){
		System.out.println("RegisterFormConverter: toEntity: start");
		RegisterFormEntity entity = new RegisterFormEntity();
		try {
			
			if (dto.getCompetitionId() != null) {
				Long competitionId = dto.getCompetitionId();
				CompetitionEntity competition = competitionService.findOneById(competitionId);
				entity.setCompetition(competition);
			}
			
			if (dto.getCompetitionSettingId() != null) {
				Long competitionSettingId = dto.getCompetitionSettingId();
				CompetitionEntity competitionSetting = competitionService.findOneById(competitionSettingId);
				entity.setCompetition(competitionSetting);
			}
			
			if (dto.getTeamId() != null) {
				Long playerTeamId = dto.getTeamId();
				TeamEntity playerTeam = teamService.findOneById(playerTeamId);
				entity.setTeam(playerTeam);
			}
			
			entity.setDescription(dto.getDescription());
			entity.setStatus(dto.getStatus());
			entity.setUrl(dto.getUrl());
			System.out.println("RegisterFormConverter: toEntity: no exception");
		}catch (Exception e) {
			System.out.println("RegisterFormConverter: toEntity: has exception");
			return null;
		}
		System.out.println("RegisterFormConverter: toEntity: finish");
		return entity;
	}

	public RegisterFormDTO toDTO(RegisterFormEntity entity){
		System.out.println("RegisterFormConverter: toDTO: finish");
		RegisterFormDTO dto = new RegisterFormDTO();
		try {
			dto.setCompetitionId(entity.getCompetition().getId());
			dto.setCompetitionSettingId(entity.getCompetitionSetting().getId());
			
			TeamEntity playerTeam = entity.getTeam();
			Long playerTeamId = playerTeam.getId();
			dto.setTeamId(playerTeamId);
			
			dto.setDescription(entity.getDescription());
			
			dto.setStatus(entity.getStatus());
			dto.setUrl(entity.getUrl());
			System.out.println("RegisterFormConverter: toDTO: no exception");
		} catch (Exception e) {
			System.out.println("RegisterFormConverter: toDTO: has exception");
			return null;
		}
		
		System.out.println("RegisterFormConverter: toDTO: finish");
		return dto;
	}
}
