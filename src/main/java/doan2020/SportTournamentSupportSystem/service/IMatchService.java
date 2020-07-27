package doan2020.SportTournamentSupportSystem.service;

import java.util.Collection;

import org.springframework.data.domain.Pageable;

import doan2020.SportTournamentSupportSystem.entity.MatchEntity;

public interface IMatchService {
	public Collection<MatchEntity> findAll(Pageable pageable);

	public MatchEntity findById(Long id);
	
	public void addOne(MatchEntity match);
	public void addMany(Collection<MatchEntity> matches);
	
	public MatchEntity update(Long id, MatchEntity match);
}
