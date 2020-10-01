
package doan2020.SportTournamentSupportSystem.repository;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import doan2020.SportTournamentSupportSystem.entity.TournamentEntity;

public interface TournamentRepository extends JpaRepository<TournamentEntity, Long>{
    TournamentEntity findOneById(Long id);
    Page<TournamentEntity> findByCreatorId(Pageable pageable, Long creatorId);

    @Query(value="SELECT t FROM TournamentEntity t WHERE t.fullName LIKE CONCAT('%',:str,'%') or t.shortName LIKE CONCAT('%',:str,'%') ORDER BY id DESC")
    Collection<TournamentEntity> findBySearchString(@Param("str") String searchString);
    
    @Query(value="SELECT t FROM TournamentEntity t WHERE (t.fullName LIKE CONCAT('%',:str,'%') or t.shortName LIKE CONCAT('%',:str,'%')) "+
            "and (t.sport like CASE WHEN :sportId = ''\r\n" + 
    		"THEN concat('%', '', '%')\r\n" + 
    		"ELSE concat(:sportId)\r\n" + 
    		"END)\r\n" + 
    		"and (t.status like CASE WHEN :status = ''\r\n" + 
    		"THEN concat('%', '', '%')\r\n" + 
    		"ELSE :status\r\n" + 
    		"END) ORDER BY id DESC")
    Collection<TournamentEntity> findBySearchStringAndSportAndStatus(@Param("str") String searchString,@Param("sportId") String sport,@Param("status") String status);
    
    Page<TournamentEntity> findBySportId(Pageable pageable, Long sportId);
    
    Collection<TournamentEntity> findBySportId(Long sportId);

}