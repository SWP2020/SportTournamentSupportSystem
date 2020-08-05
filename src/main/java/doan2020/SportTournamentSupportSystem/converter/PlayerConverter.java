package doan2020.SportTournamentSupportSystem.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import doan2020.SportTournamentSupportSystem.dtIn.PlayerDtIn;
import doan2020.SportTournamentSupportSystem.dtOut.PlayerDtOut;
import doan2020.SportTournamentSupportSystem.entity.PlayerEntity;
import doan2020.SportTournamentSupportSystem.entity.TeamEntity;
import doan2020.SportTournamentSupportSystem.service.ITeamService;
import doan2020.SportTournamentSupportSystem.validator.Validator;

@Component
public class PlayerConverter {
//	@Autowired
//	private Validator validator;
//
//	@Autowired
//	private ITeamService teamService;
//	
//	public PlayerEntity toEntity(PlayerDtIn playerDtIn) {
//		PlayerEntity playerEntity = new PlayerEntity();
//		if(playerDtIn.getDob() !=null) {
//			playerEntity.setDob(playerDtIn.getDob());
//		}
//		if(playerDtIn.getEmail() !=null) {
//			playerEntity.setEmail(playerDtIn.getEmail());
//		}
//		if(playerDtIn.getFirstName() !=null) {
//			playerEntity.setFirstName(playerDtIn.getFirstName());
//		}
//			playerEntity.setGender(playerDtIn.isGender());
//			
//		if(playerDtIn.getLastName() !=null) {
//			playerEntity.setLastName(playerDtIn.getLastName());
//		}
//		if(playerDtIn.getTeamId() !=null) {
//			TeamEntity teamEntity = new TeamEntity();
//			teamEntity = teamService.findById(playerDtIn.getTeamId());
//			playerEntity.setTeam(teamEntity);
//		}
//		
//		return playerEntity;
//	}
//
//	public PlayerDtOut toDTO(PlayerEntity entity) {
//		PlayerDtOut playerDtOut = new PlayerDtOut();
//		try {
//		playerDtOut.setDob(validator.formatDateToString(entity.getDob()));
//		playerDtOut.setEmail(entity.getEmail());
//		playerDtOut.setFirstName(entity.getFirstName());
//		playerDtOut.setGender(entity.getGender());
//		playerDtOut.setLastName(entity.getLastName());
//		playerDtOut.setPlayerId(entity.getId());
//		playerDtOut.setStatus(entity.getStatus());
//		playerDtOut.setTeamId(entity.getTeam().getId());
//		playerDtOut.setUrl(entity.getUrl());
//		}catch (Exception e) {
//			return null;
//		}
//		
//		return playerDtOut;
//
//	}
//	
//	
//	public PlayerEntity toEntity(PlayerDtIn playerDtIn, PlayerEntity playerEntity) {
//		if(playerDtIn.getDob() !=null) {
//			playerEntity.setDob(playerDtIn.getDob());
//		}
//		if(playerDtIn.getEmail() !=null) {
//			playerEntity.setEmail(playerDtIn.getEmail());
//		}
//		if(playerDtIn.getFirstName() !=null) {
//			playerEntity.setFirstName(playerDtIn.getFirstName());
//		}
//			playerEntity.setGender(playerDtIn.isGender());
//			
//		if(playerDtIn.getLastName() !=null) {
//			playerEntity.setLastName(playerDtIn.getLastName());
//		}
//		
//		return playerEntity;
//	}
}
