
package doan2020.SportTournamentSupportSystem.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import doan2020.SportTournamentSupportSystem.entity.CompetitionSettingEntity;

public interface CompetitionSettingRepository extends JpaRepository<CompetitionSettingEntity, Long>{
    CompetitionSettingEntity findOneById(Long id);
    
    Collection<CompetitionSettingEntity> findByCompetitionId(Long CompetitionId);
}