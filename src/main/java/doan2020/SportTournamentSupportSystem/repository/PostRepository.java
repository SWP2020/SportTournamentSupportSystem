
package doan2020.SportTournamentSupportSystem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import doan2020.SportTournamentSupportSystem.entity.PostEntity;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long>{
	PostEntity findOneById(Long id);
	
	Page<PostEntity> findByTournamentId(Long tournamentId, Pageable pageable);
}