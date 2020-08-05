
package doan2020.SportTournamentSupportSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import doan2020.SportTournamentSupportSystem.entity.CompetitionFormatEntity;

public interface CompetitionFormatRepository extends JpaRepository<CompetitionFormatEntity, Long>{
    CompetitionFormatEntity findOneById(Long id);
}