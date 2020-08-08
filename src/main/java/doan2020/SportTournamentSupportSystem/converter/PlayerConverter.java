package doan2020.SportTournamentSupportSystem.converter;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import doan2020.SportTournamentSupportSystem.dtIn.PlayerDtIn;
import doan2020.SportTournamentSupportSystem.dtOut.PlayerDtOut;
import doan2020.SportTournamentSupportSystem.dto.PlayerDTO;
import doan2020.SportTournamentSupportSystem.entity.CompetitionEntity;
import doan2020.SportTournamentSupportSystem.entity.PlayerEntity;
import doan2020.SportTournamentSupportSystem.entity.TeamEntity;
import doan2020.SportTournamentSupportSystem.entity.UserEntity;
import doan2020.SportTournamentSupportSystem.service.IPlayerService;
import doan2020.SportTournamentSupportSystem.service.ITeamService;
import doan2020.SportTournamentSupportSystem.validator.Validator;

@Component
public class PlayerConverter {
	
	@Autowired
	private Validator validator;
	
	@Autowired
	private ITeamService teamService;
	
	public PlayerEntity toEntity(PlayerDTO dto){
		System.out.println("PlayerConverter: toEntity: start");
		PlayerEntity entity = new PlayerEntity();
		try {
			entity.setFirstName(dto.getFirstName());
			entity.setLastName(dto.getLastName());
			entity.setGender(dto.getGender());
			
			Date dob = validator.formatStringToDate(dto.getDob());
			entity.setDob(dob);
			
			entity.setEmail(dto.getEmail());
			
			if (dto.getTeamId() != null) {
				Long teamId = dto.getTeamId();
				TeamEntity team = teamService.findOneById(teamId);
				entity.setTeam(team);
			}
			
			entity.setStatus(dto.getStatus());
			entity.setUrl(dto.getUrl());
			System.out.println("PlayerConverter: toEntity: no exception");
		}catch (Exception e) {
			System.out.println("PlayerConverter: toEntity: has exception");
			return null;
		}
		System.out.println("PlayerConverter: toEntity: finish");
		return entity;
	}

	public PlayerDTO toDTO(PlayerEntity entity){
		System.out.println("PlayerConverter: toDTO: finish");
		PlayerDTO dto = new PlayerDTO();
		try {
			dto.setId(entity.getId());
			dto.setFirstName(entity.getFirstName());
			dto.setLastName(entity.getLastName());
			dto.setGender(entity.getGender());
			
			String dob = validator.formatDateToString(entity.getDob());
			dto.setDob(dob);
			
			dto.setEmail(entity.getEmail());
			
			TeamEntity team = entity.getTeam();
			Long teamId = team.getId();
			dto.setTeamId(teamId);
			
			dto.setStatus(entity.getStatus());
			dto.setUrl(entity.getUrl());
			System.out.println("PlayerConverter: toDTO: no exception");
		} catch (Exception e) {
			System.out.println("PlayerConverter: toDTO: has exception");
			return null;
		}
		
		System.out.println("PlayerConverter: toDTO: finish");
		return dto;
	}
}
