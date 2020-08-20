
package doan2020.SportTournamentSupportSystem.service;

import java.util.Collection;
import org.springframework.data.domain.Pageable;
import doan2020.SportTournamentSupportSystem.entity.ScoringUnitEntity;

public interface IScoringUnitService {
	
	public ScoringUnitEntity findOneById(Long id);
	
//	public Collection<ScoringUnitEntity> findAll(Pageable pageable);
	
	public ScoringUnitEntity create(ScoringUnitEntity scoringUnitEntity);
	
	public ScoringUnitEntity update(Long id, ScoringUnitEntity newEntity);
	
	public Collection<ScoringUnitEntity> findAll();
	
	public ScoringUnitEntity delete(Long id);
}