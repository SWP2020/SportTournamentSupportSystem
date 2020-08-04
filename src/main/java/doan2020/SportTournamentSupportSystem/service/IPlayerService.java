package doan2020.SportTournamentSupportSystem.service;

import java.util.List;

import doan2020.SportTournamentSupportSystem.entity.PlayerEntity;

public interface IPlayerService {
	public PlayerEntity findOneById(Long id);

	public void addOnePlayer(PlayerEntity playerEntity);
	
	public void addManyPlayer(List<PlayerEntity> playerEntitys);

	public void editPlayer(PlayerEntity playerEntity);

	public void deletePlayer(PlayerEntity playerEntity);	

	public List<PlayerEntity> findByTeamId(Long teamId);
}
