
package doan2020.SportTournamentSupportSystem.service;

import java.util.Collection;
import org.springframework.data.domain.Pageable;
import doan2020.SportTournamentSupportSystem.entity.CompetitionFormatEntity;

public interface ICompetitionFormatService {
	
	public CompetitionFormatEntity findOneById(Long id);
	
//	public Collection<CompetitionFormatEntity> findAll(Pageable pageable);
	
	public CompetitionFormatEntity create(CompetitionFormatEntity competitionFormatEntity);
	
	public CompetitionFormatEntity update(Long id, CompetitionFormatEntity newEntity);
	
//	public Collection<CompetitionFormatEntity> findAll();
	
	public CompetitionFormatEntity delete(Long id);
}