
package doan2020.SportTournamentSupportSystem.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import doan2020.SportTournamentSupportSystem.config.Const;
import doan2020.SportTournamentSupportSystem.entity.TeamEntity;

public interface TeamRepository extends JpaRepository<TeamEntity, Long> {
	TeamEntity findOneById(Long id);

	Collection<TeamEntity> findByCreatorId(Long creatorId);

	Collection<TeamEntity> findByTournamentId(Long TournamentId);

	Collection<TeamEntity> findByTournamentIdAndStatus(Long TournamentId, String status);

	Long countByTournamentIdAndStatus(Long TournamentId, String status);

	Long countByTournamentId(Long TournamentId);

	@Query(value = "SELECT MAX(seed_no) FROM teams WHERE tournament_id = ?1 and status = \'" + Const.TEAM_STATUS_JOINED
			+ "\'", nativeQuery = true)
	Long getMaxSeedNoByTournamentId(Long TournamentId);
}