package doan2020.SportTournamentSupportSystem.service;

import java.util.Collection;

import org.springframework.data.domain.Pageable;

import doan2020.SportTournamentSupportSystem.entity.TournamentEntity;

public interface ITournamentService {
	public Collection<TournamentEntity> findAll(Pageable pageable);

	public TournamentEntity findByName(String name);
	public TournamentEntity findOneById(Long id);
	
	public void addOne(TournamentEntity tournament);
	public void addMany(Collection<TournamentEntity> tournaments);
	
	public TournamentEntity update(Long id, TournamentEntity tournament);
	
//	public void delete(TournamentEntity tournament);
	
//	public Collection<TournamentEntity> findAllByCreator(Pageable pageable, Long id);
}
