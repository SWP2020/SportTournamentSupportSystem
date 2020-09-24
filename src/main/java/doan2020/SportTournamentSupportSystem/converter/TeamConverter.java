package doan2020.SportTournamentSupportSystem.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import doan2020.SportTournamentSupportSystem.dto.TeamDTO;
import doan2020.SportTournamentSupportSystem.entity.TournamentEntity;
import doan2020.SportTournamentSupportSystem.entity.TeamEntity;
import doan2020.SportTournamentSupportSystem.entity.UserEntity;
import doan2020.SportTournamentSupportSystem.service.ITournamentService;
import doan2020.SportTournamentSupportSystem.service.IUserService;

@Component
public class TeamConverter{
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ITournamentService TournamentService;
	
	public TeamEntity toEntity(TeamDTO dto){
		System.out.println("TeamConverter: toEntity: start");
		TeamEntity entity = new TeamEntity();
		try {
			
			if (dto.getFullName() != null)
				entity.setFullName(dto.getFullName());
			if (dto.getShortName() != null)
				entity.setShortName(dto.getShortName());
			entity.setDescription(dto.getDescription());
			
			if (dto.getTournamentId() != null) {
				Long TournamentId = dto.getTournamentId();
				TournamentEntity Tournament = TournamentService.findOneById(TournamentId);
				entity.setTournament(Tournament);
			}
			
			if (dto.getCreatorId() != null) {
				Long creatorId = dto.getCreatorId();
				UserEntity creator = userService.findOneById(creatorId);
				entity.setCreator(creator);
			}
			if (dto.getSeedNo() != null)
				entity.setSeedNo(dto.getSeedNo());
			
			
			
			System.out.println("TeamConverter: toEntity: no exception");
		}catch (Exception e) {
			System.out.println("TeamConverter: toEntity: has exception");
			return null;
		}
		System.out.println("TeamConverter: toEntity: finish");
		return entity;
	}

	public TeamDTO toDTO(TeamEntity entity){
		System.out.println("TeamConverter: toDTO: start");
		TeamDTO dto = new TeamDTO();
		try {
			dto.setId(entity.getId());
			dto.setFullName(entity.getFullName());
			dto.setShortName(entity.getShortName());
			dto.setDescription(entity.getDescription());
			
			System.out.println("TeamConverter: toDTO: name OK");
			
			UserEntity creator = entity.getCreator();
			Long creatorId = creator.getId();
			dto.setCreatorId(creatorId);
			
			System.out.println("TeamConverter: toDTO: creator OK");
			
			TournamentEntity Tournament = entity.getTournament();
			Long TournamentId = Tournament.getId();
			dto.setTournamentId(TournamentId);
			
			System.out.println("TeamConverter: toDTO: Tournament OK");
			
			dto.setSeedNo(entity.getSeedNo());
			
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
