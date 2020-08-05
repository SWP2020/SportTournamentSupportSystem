
package doan2020.SportTournamentSupportSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import doan2020.SportTournamentSupportSystem.entity.TournamentEntity;

public interface TournamentRepository extends JpaRepository<TournamentEntity, Long>{
    TournamentEntity findOneById(Long id);
}