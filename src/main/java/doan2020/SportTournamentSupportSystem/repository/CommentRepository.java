
package doan2020.SportTournamentSupportSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import doan2020.SportTournamentSupportSystem.entity.CommentEntity;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

	CommentEntity findOneById(Long id);

	List<CommentEntity> findByPostId(Long postId);
	
	List<CommentEntity> findByAuthorId(Long authorId);
	
}