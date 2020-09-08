
package doan2020.SportTournamentSupportSystem.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import doan2020.SportTournamentSupportSystem.entity.MatchEntity;

public interface MatchRepository extends JpaRepository<MatchEntity, Long> {
	MatchEntity findOneById(Long id);

	Collection<MatchEntity> findByCompetitionId(Long competitionId);

	@Query(value = "select count(m.id) from matches m left join competitions c on m.competition_id=c.id "
			+ "where c.tournament_id=?1", nativeQuery = true)
	Integer countByTournamentId(Long tournamentId);
	
	@Query(value = "select count(m.id) from matches m left join competitions c on m.competition_id=c.id "
			+ "where c.tournament_id=?1 and m.status=?2", nativeQuery = true)
	Integer countByTournamentIdAndStatus(Long tournamentId, String status);
}