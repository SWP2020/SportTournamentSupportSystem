
package doan2020.SportTournamentSupportSystem.repository;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import doan2020.SportTournamentSupportSystem.entity.CompetitionEntity;

public interface CompetitionRepository extends JpaRepository<CompetitionEntity, Long>{
	
    CompetitionEntity findOneById(Long id);
    
    Page<CompetitionEntity> findByTournamentId(Pageable pageable, Long tournamentId);
    
    Page<CompetitionEntity> findByTournamentIdAndSportId(Pageable pageable, Long tournamentId, Long sportId);
    
    Collection<CompetitionEntity> findByTournamentId(Long tournamentId);
}