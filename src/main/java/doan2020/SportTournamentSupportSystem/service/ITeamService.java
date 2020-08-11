
package doan2020.SportTournamentSupportSystem.service;

import java.util.Collection;

import org.springframework.data.domain.Pageable;

import doan2020.SportTournamentSupportSystem.entity.TeamEntity;

public interface ITeamService {

	public TeamEntity findOneById(Long id);

	public Collection<TeamEntity> findAll(Pageable pageable);

	public TeamEntity create(TeamEntity teamEntity);

	public TeamEntity update(Long id, TeamEntity newEntity);

	public Collection<TeamEntity> findAll();

	public TeamEntity delete(Long id);

	public Collection<TeamEntity> findByCreatorId(Pageable pageable, Long creatorId);
	
	public Collection<TeamEntity> findByCompetitionId(Pageable pageable, Long creatorId);
}