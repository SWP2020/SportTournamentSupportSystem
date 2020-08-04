package doan2020.SportTournamentSupportSystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import doan2020.SportTournamentSupportSystem.converter.PlayerConverter;
import doan2020.SportTournamentSupportSystem.entity.CompetitionEntity;
import doan2020.SportTournamentSupportSystem.entity.PlayerEntity;
import doan2020.SportTournamentSupportSystem.repository.PlayerRepository;
import doan2020.SportTournamentSupportSystem.service.IPlayerService;

@Service
public class PlayerService implements IPlayerService{
	
	@Autowired
	private PlayerRepository playerRepository;
	
	@Autowired
	private PlayerConverter playerConverter;
	
	@Override
	public PlayerEntity findOneById(Long id) {
		PlayerEntity playerEntity = null;
		try {
		playerEntity = playerRepository.findOneById(id);
		}catch (Exception e) {
			return null;
		}
		return playerEntity;
	}

	@Override
	public void addOnePlayer(PlayerEntity playerEntity) {
		
		playerRepository.save(playerEntity);
	}


	@Override
	public void addManyPlayer(List<PlayerEntity> playerEntitys) {
		// TODO Auto-generated method stub
		for(PlayerEntity playerEntity: playerEntitys) {
			playerRepository.save(playerEntity);
		}
		
	}
	
	@Override
	public void editPlayer(PlayerEntity playerEntity) {
		playerRepository.save(playerEntity);
	}

	@Override
	public void deletePlayer(PlayerEntity playerEntity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<PlayerEntity> findByTeamId(Long teamId) {
		List<PlayerEntity> playerEntities = playerRepository.findByTeamId(teamId);
		return playerEntities;
	}


	

}
