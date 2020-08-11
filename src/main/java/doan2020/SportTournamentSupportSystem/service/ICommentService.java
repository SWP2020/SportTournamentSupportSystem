
package doan2020.SportTournamentSupportSystem.service;

import java.util.Collection;

import org.springframework.data.domain.Pageable;

import doan2020.SportTournamentSupportSystem.entity.CommentEntity;

public interface ICommentService {
	
	public CommentEntity findOneById(Long id);
	
	public Collection<CommentEntity> findAll(Pageable pageable);
	
	public CommentEntity create(CommentEntity commentEntity);
	
	public CommentEntity update(Long id, CommentEntity newEntity);
	
	public Collection<CommentEntity> findAll();
	
	public CommentEntity delete(Long id);
	
	public Collection<CommentEntity> findByPostId(Long postId);
    
	public Collection<CommentEntity> findByUserId(Long userId);
} 