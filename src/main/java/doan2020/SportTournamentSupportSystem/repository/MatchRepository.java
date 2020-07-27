
package doan2020.SportTournamentSupportSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import doan2020.SportTournamentSupportSystem.entity.MatchEntity;
import doan2020.SportTournamentSupportSystem.entity.TeamEntity;

@Repository
public interface MatchRepository extends JpaRepository<MatchEntity, Long>{

	MatchEntity findOneById(Long id);
}