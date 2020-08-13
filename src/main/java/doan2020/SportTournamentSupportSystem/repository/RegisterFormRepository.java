
package doan2020.SportTournamentSupportSystem.repository;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import doan2020.SportTournamentSupportSystem.entity.RegisterFormEntity;

public interface RegisterFormRepository extends JpaRepository<RegisterFormEntity, Long>{
    RegisterFormEntity findOneById(Long id);
    
    Page<RegisterFormEntity> findByCompetitionId(Pageable pageable,Long competitionId);
    
    Collection<RegisterFormEntity> findByTeamId(Long teamId);
    
    Collection<RegisterFormEntity> findByCompetitionSettingId(Long competitionSettingId);
}