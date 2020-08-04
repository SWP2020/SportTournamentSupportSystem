package doan2020.SportTournamentSupportSystem.service;

import java.util.List;

import doan2020.SportTournamentSupportSystem.entity.CommentEntity;

public interface ICommentService {
	CommentEntity findOneById(Long id);

	List<CommentEntity> findByPostId(Long postId);
	
	List<CommentEntity> findByAuthorId(Long authorId);
	
	void addComment(CommentEntity commentEntity);
	
	void editComment(CommentEntity commentEntity);
	
	void deleteComment(CommentEntity commentEntity);
}
