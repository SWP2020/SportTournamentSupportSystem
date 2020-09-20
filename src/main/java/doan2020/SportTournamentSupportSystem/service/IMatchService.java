
package doan2020.SportTournamentSupportSystem.service;

import java.util.Collection;

import doan2020.SportTournamentSupportSystem.entity.MatchEntity;

public interface IMatchService {
	
	public MatchEntity findOneById(Long id);
	
	public Collection<MatchEntity> findAll();
	
	public MatchEntity create(MatchEntity matchEntity);
	
	public MatchEntity update(Long id, MatchEntity newEntity);
	
	Collection<MatchEntity> findByTournamentId(Long TournamentId);
	
	public MatchEntity delete(Long id);
	
	public Collection<MatchEntity> schedule(Long TournamentId, Long TournamentFormatId);
}