
package doan2020.SportTournamentSupportSystem.repository;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import doan2020.SportTournamentSupportSystem.entity.TeamEntity;

public interface TeamRepository extends JpaRepository<TeamEntity, Long>{
    TeamEntity findOneById(Long id);
    
    Collection<TeamEntity> findByCreatorId(Long creatorId);
    
    Collection<TeamEntity> findByCompetitionId(Long competitionId);
    
    Long countByCompetitionId(Long competitionId);
    
    @Query(value="SELECT MAX(seed_no) FROM teams WHERE competition_id = ?1", nativeQuery = true)
    Long getMaxSeedNoByCompetitionId(Long competitionId);
}