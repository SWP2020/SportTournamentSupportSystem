
package doan2020.SportTournamentSupportSystem.service;

import java.util.Collection;

import org.springframework.data.domain.Pageable;

import doan2020.SportTournamentSupportSystem.entity.TeamEntity;
import doan2020.SportTournamentSupportSystem.model.Entity.Player;

public interface ITeamService {

	public TeamEntity findOneById(Long id);

	public Collection<TeamEntity> findAll(Pageable pageable);

	public TeamEntity create(TeamEntity teamEntity);

	public TeamEntity update(Long id, TeamEntity newEntity);

	public Collection<TeamEntity> findAll();

	public TeamEntity delete(Long id);

	public Collection<TeamEntity> findByCreatorId(Long creatorId);
	
	public Collection<TeamEntity> findByTournamentId(Long creatorId);
	
	public Collection<TeamEntity> swap(Long team1Id, Long team2Id);
	
	public String saveTeamPlayersToFile(TeamEntity team, Collection<Player> players);

	public Collection<Player> getTeamPlayerFromFile(Long TournamentId, Long teamId);
	
	public Collection<TeamEntity> findByTournamentIdAndStatus(Long TournamentId, String status);
		
	public Long getMaxSeedNoByTournamentId(Long TournamentId);
	
	public Long countByTournamentIdAndStatus(Long TournamentId, String status);
	
}