
package doan2020.SportTournamentSupportSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import doan2020.SportTournamentSupportSystem.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Long>{
    CommentEntity findOneById(Long id);
}