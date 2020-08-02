
package doan2020.SportTournamentSupportSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import doan2020.SportTournamentSupportSystem.entity.ScoringUniEntity;

@Repository
public interface ScoringunitRepository extends JpaRepository<ScoringUniEntity, Long>{
	
}