
package doan2020.SportTournamentSupportSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import doan2020.SportTournamentSupportSystem.entity.SportEntity;

public interface SportRepository extends JpaRepository<SportEntity, Long>{
    SportEntity findOneById(Long id);
}