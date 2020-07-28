
package doan2020.SportTournamentSupportSystem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import doan2020.SportTournamentSupportSystem.entity.TeamEntity;

@Repository
public interface TeamRepository extends JpaRepository<TeamEntity, Long> {
	TeamEntity findByShortName(String name);

	TeamEntity findOneById(Long id);
	
	Page<TeamEntity> findByCreatorId(Long creatorId, Pageable pageable);
}