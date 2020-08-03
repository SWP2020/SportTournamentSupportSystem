
package doan2020.SportTournamentSupportSystem.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import doan2020.SportTournamentSupportSystem.entity.CompetitionEntity;

@Repository
public interface CompetitionRepository extends JpaRepository<CompetitionEntity, Long>{
	CompetitionEntity findOneById(Long id);
	
	Page<CompetitionEntity> findByTournamentId(Pageable pageable, Long tournamentId);
	
	List<CompetitionEntity> findByTournamentId(Long tournamentId);
}