package doan2020.SportTournamentSupportSystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import doan2020.SportTournamentSupportSystem.entity.CommentEntity;
import doan2020.SportTournamentSupportSystem.repository.CommentRepository;
import doan2020.SportTournamentSupportSystem.service.ICommentService;

@Service
public class CommentService implements ICommentService{
	
	@Autowired
	private CommentRepository commentRepository;

	@Override
	public CommentEntity findOneById(Long id) {
		CommentEntity commentEntity = commentRepository.findOneById(id);
		return commentEntity;
	}

	@Override
	public List<CommentEntity> findByPostId(Long postId) {
		List<CommentEntity> list = commentRepository.findByPostId(postId);
		return list;
	}

	@Override
	public List<CommentEntity> findByAuthorId(Long authorId) {
		List<CommentEntity> list = commentRepository.findByAuthorId(authorId);
		return list;
	}

	@Override
	public void addComment(CommentEntity commentEntity) {
		commentRepository.save(commentEntity);
		
	}

	@Override
	public void editComment(CommentEntity commentEntity) {
		commentRepository.save(commentEntity);
		
	}

	@Override
	public void deleteComment(CommentEntity commentEntity) {
		commentRepository.delete(commentEntity);
	}

}
