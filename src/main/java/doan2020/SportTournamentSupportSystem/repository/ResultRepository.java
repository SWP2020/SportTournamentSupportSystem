
package doan2020.SportTournamentSupportSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import doan2020.SportTournamentSupportSystem.entity.ResultEntity;

public interface ResultRepository extends JpaRepository<ResultEntity, Long>{
    ResultEntity findOneById(Long id);
}