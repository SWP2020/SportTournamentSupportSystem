
package doan2020.SportTournamentSupportSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import doan2020.SportTournamentSupportSystem.entity.RegisterFormEntity;

public interface RegisterFormRepository extends JpaRepository<RegisterFormEntity, Long>{
    RegisterFormEntity findOneById(Long id);
}