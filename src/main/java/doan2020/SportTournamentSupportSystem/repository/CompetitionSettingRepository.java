
package doan2020.SportTournamentSupportSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import doan2020.SportTournamentSupportSystem.entity.CompetitionSettingEntity;

public interface CompetitionSettingRepository extends JpaRepository<CompetitionSettingEntity, Long>{
    CompetitionSettingEntity findOneById(Long id);
}