
package doan2020.SportTournamentSupportSystem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import doan2020.SportTournamentSupportSystem.entity.ReportEntity;

public interface ReportRepository extends JpaRepository<ReportEntity, Long>{
    ReportEntity findOneById(Long id);
    
    Page<ReportEntity> findBySenderId(Pageable pageable,Long senderId);
    
    Page<ReportEntity> findByTournamentId(Pageable pageable,Long tournamentId);
    
}