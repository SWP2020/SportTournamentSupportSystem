
package doan2020.SportTournamentSupportSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import doan2020.SportTournamentSupportSystem.entity.PlayerEntity;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity, Long>{
	PlayerEntity findOneById(Long id);
	
	List<PlayerEntity> findByTeamId(Long teamId);
}