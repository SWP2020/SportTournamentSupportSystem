
package doan2020.SportTournamentSupportSystem.service;

import java.util.Collection;
import org.springframework.data.domain.Pageable;
import doan2020.SportTournamentSupportSystem.entity.CompetitionSettingEntity;

public interface ICompetitionSettingService {
	
	public CompetitionSettingEntity findOneById(Long id);
	
	public Collection<CompetitionSettingEntity> findAll(Pageable pageable);
	
	public CompetitionSettingEntity create(CompetitionSettingEntity competitionSettingEntity);
	
	public CompetitionSettingEntity update(Long id, CompetitionSettingEntity newEntity);
	
//	public Collection<CompetitionSettingEntity> findAll();
	
	public CompetitionSettingEntity delete(Long id);
	
	public Collection<CompetitionSettingEntity> findByCompetitionId(Long CompetitionId);
}