
package doan2020.SportTournamentSupportSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import doan2020.SportTournamentSupportSystem.entity.ScoringUnitEntity;

public interface ScoringUniRepository extends JpaRepository<ScoringUnitEntity, Long>{
    ScoringUnitEntity findOneById(Long id);
}