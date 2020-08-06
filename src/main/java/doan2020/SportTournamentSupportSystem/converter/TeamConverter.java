package doan2020.SportTournamentSupportSystem.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import doan2020.SportTournamentSupportSystem.dto.TeamDTO;
import doan2020.SportTournamentSupportSystem.entity.CompetitionEntity;
import doan2020.SportTournamentSupportSystem.entity.TeamEntity;
import doan2020.SportTournamentSupportSystem.entity.UserEntity;
import doan2020.SportTournamentSupportSystem.service.ICompetitionService;
import doan2020.SportTournamentSupportSystem.service.IUserService;

@Component
public class TeamConverter{
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ICompetitionService competitionService;
	
	public TeamEntity toEntity(TeamDTO dto){
		System.out.println("TeamConverter: toEntity: start");
		TeamEntity entity = new TeamEntity();
		try {
			entity.setFullName(dto.getFullName());
			entity.setShortName(dto.getShortName());
			entity.setDescription(dto.getDescription());
			
			Long teamCompetitionId = dto.getCompetitionId();
			CompetitionEntity teamCompetition = competitionService.findOneById(teamCompetitionId);
			entity.setCompetition(teamCompetition);
			
			Long teamCreatorId = dto.getCreatorId();
			UserEntity teamCreator = userService.findOneById(teamCreatorId);
			entity.setCreator(teamCreator);
			
			entity.setStatus(dto.getStatus());
			entity.setUrl(dto.getUrl());
			System.out.println("TeamConverter: toEntity: no exception");
		}catch (Exception e) {
			System.out.println("TeamConverter: toEntity: has exception");
			return null;
		}
		System.out.println("TeamConverter: toEntity: finish");
		return entity;
	}

	public TeamDTO toDTO(TeamEntity entity){
		System.out.println("TeamConverter: toDTO: finish");
		TeamDTO dto = new TeamDTO();
		try {
			dto.setId(entity.getId());
			dto.setFullName(entity.getFullName());
			dto.setShortName(entity.getShortName());
			dto.setDescription(entity.getDescription());
			
			UserEntity teamCreator = entity.getCreator();
			Long teamCreatorId = teamCreator.getId();
			dto.setCreatorId(teamCreatorId);
			
			CompetitionEntity teamCompetition = entity.getCompetition();
			Long teamCompetitionId = teamCompetition.getId();
			dto.setCompetitionId(teamCompetitionId);
			
			dto.setStatus(entity.getStatus());
			dto.setUrl(entity.getUrl());
			System.out.println("TeamConverter: toDTO: no exception");
		} catch (Exception e) {
			System.out.println("TeamConverter: toDTO: has exception");
			return null;
		}
		
		System.out.println("TeamConverter: toDTO: finish");
		return dto;
	}

}
