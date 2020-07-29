
package doan2020.SportTournamentSupportSystem.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import doan2020.SportTournamentSupportSystem.entity.MatchEntity;

@Repository
public interface MatchRepository extends JpaRepository<MatchEntity, Long>{

	MatchEntity findOneById(Long id);
	
	List<MatchEntity> findByCompetitionId(Long competitionId);

	Page<MatchEntity> findByCompetitionId(Long competitionId, Pageable pageable);
}