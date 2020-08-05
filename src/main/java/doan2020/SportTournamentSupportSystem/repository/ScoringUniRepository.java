
package doan2020.SportTournamentSupportSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import doan2020.SportTournamentSupportSystem.entity.ScoringUniEntity;

public interface ScoringUniRepository extends JpaRepository<ScoringUniEntity, Long>{
    ScoringUniEntity findOneById(Long id);
}