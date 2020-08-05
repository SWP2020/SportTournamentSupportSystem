
package doan2020.SportTournamentSupportSystem.service;

import java.util.Collection;
import org.springframework.data.domain.Pageable;
import doan2020.SportTournamentSupportSystem.entity.ScoringUniEntity;

public interface IScoringUniService {
	
	public ScoringUniEntity findOneById(Long id);
	
//	public Collection<ScoringUniEntity> findAll(Pageable pageable);
	
	public ScoringUniEntity create(ScoringUniEntity scoringUniEntity);
	
	public ScoringUniEntity update(Long id, ScoringUniEntity newEntity);
	
//	public Collection<ScoringUniEntity> findAll();
	
	public ScoringUniEntity delete(Long id);
}