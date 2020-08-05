
package doan2020.SportTournamentSupportSystem.service;

import java.util.Collection;
import org.springframework.data.domain.Pageable;
import doan2020.SportTournamentSupportSystem.entity.CompetitionEntity;

public interface ICompetitionService {
	
	public CompetitionEntity findOneById(Long id);
	
//	public Collection<CompetitionEntity> findAll(Pageable pageable);
	
	public CompetitionEntity create(CompetitionEntity competitionEntity);
	
	public CompetitionEntity update(Long id, CompetitionEntity newEntity);
	
//	public Collection<CompetitionEntity> findAll();
	
	public CompetitionEntity delete(Long id);
}