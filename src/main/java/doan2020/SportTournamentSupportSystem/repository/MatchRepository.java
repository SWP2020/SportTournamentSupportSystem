
package doan2020.SportTournamentSupportSystem.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import doan2020.SportTournamentSupportSystem.entity.MatchEntity;

public interface MatchRepository extends JpaRepository<MatchEntity, Long> {
	MatchEntity findOneById(Long id);

	Collection<MatchEntity> findByTournamentId(Long TournamentId);

	Integer countByTournamentId(Long tournamentId);
	
	Integer countByTournamentIdAndStatus(Long tournamentId, String status);
}