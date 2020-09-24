
package doan2020.SportTournamentSupportSystem.service;

import java.util.Collection;

import doan2020.SportTournamentSupportSystem.entity.FormatEntity;

public interface IFormatService {
	
	public FormatEntity findOneById(Long id);
	
//	public Collection<TournamentFormatEntity> findAll(Pageable pageable);
	
	public FormatEntity create(FormatEntity formatEntity);
	
	public Collection<FormatEntity> findAll();
	
	public FormatEntity findByName(String name);
}