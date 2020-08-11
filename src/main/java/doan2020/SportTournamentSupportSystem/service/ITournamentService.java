
package doan2020.SportTournamentSupportSystem.service;

import java.util.Collection;
import org.springframework.data.domain.Pageable;
import doan2020.SportTournamentSupportSystem.entity.TournamentEntity;

public interface ITournamentService {
	
	public TournamentEntity findOneById(Long id);
	
	public Collection<TournamentEntity> findAll(Pageable pageable);
	
	public TournamentEntity create(TournamentEntity tournamentEntity);
	
	public TournamentEntity update(Long id, TournamentEntity newEntity);
	
//	public Collection<TournamentEntity> findAll();
	
	public TournamentEntity delete(Long id);
	
	public Collection<TournamentEntity> findByCreatorId(Pageable pageable, Long creatorId);
}