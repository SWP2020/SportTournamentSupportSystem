
package doan2020.SportTournamentSupportSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import doan2020.SportTournamentSupportSystem.entity.PlayerEntity;

public interface PlayerRepository extends JpaRepository<PlayerEntity, Long>{
    PlayerEntity findOneById(Long id);
}