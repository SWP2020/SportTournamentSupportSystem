
package doan2020.SportTournamentSupportSystem.service;

import java.util.Collection;

import doan2020.SportTournamentSupportSystem.entity.FormatEntity;

public interface IFormatService {
	
	public FormatEntity findOneById(Long id);
	
//	public Collection<CompetitionFormatEntity> findAll(Pageable pageable);
	
	public FormatEntity create(FormatEntity formatEntity);
	
	public FormatEntity update(Long id, FormatEntity newEntity);
	
	public Collection<FormatEntity> findAll();
	
	public FormatEntity delete(Long id);
	
	public FormatEntity findByName(String name);
}