
package doan2020.SportTournamentSupportSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import doan2020.SportTournamentSupportSystem.entity.TeamEntity;

public interface TeamRepository extends JpaRepository<TeamEntity, Long>{
    TeamEntity findOneById(Long id);
}