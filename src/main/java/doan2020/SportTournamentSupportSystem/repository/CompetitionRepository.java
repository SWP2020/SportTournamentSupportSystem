
package doan2020.SportTournamentSupportSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import doan2020.SportTournamentSupportSystem.entity.CompetitionEntity;

public interface CompetitionRepository extends JpaRepository<CompetitionEntity, Long>{
    CompetitionEntity findOneById(Long id);
}