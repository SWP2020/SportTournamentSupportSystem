
package doan2020.SportTournamentSupportSystem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import doan2020.SportTournamentSupportSystem.entity.TournamentEntity;

@Repository
public interface TournamentRepository extends JpaRepository<TournamentEntity, Long> {
	
	TournamentEntity findByShortName(String name);
	
	TournamentEntity findOneById(Long id);
	
	Page<TournamentEntity> findByCreatorId(Long creatorId, Pageable pageable);
}