
package doan2020.SportTournamentSupportSystem.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import doan2020.SportTournamentSupportSystem.entity.CommentEntity;
import doan2020.SportTournamentSupportSystem.repository.CommentRepository;
import doan2020.SportTournamentSupportSystem.service.ICommentService;

@Service
public class CommentService implements ICommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Override
	public CommentEntity create(CommentEntity commentEntity) {
		CommentEntity newEntity = null;
		try {
			newEntity = commentRepository.save(commentEntity);
		} catch (Exception e) {
			return null;
		}
		return newEntity;
	}

	@Override
	public CommentEntity update(Long id, CommentEntity newEntity) {
		CommentEntity updatedEntity = null;
		try {
			updatedEntity = commentRepository.findOneById(id);

			updatedEntity.setAuthor(newEntity.getAuthor());
			updatedEntity.setPost(newEntity.getPost());
			updatedEntity.setContent(newEntity.getContent());
			updatedEntity.setCreatedBy(newEntity.getCreatedBy());
			updatedEntity.setCreatedDate(newEntity.getCreatedDate());
			updatedEntity.setModifiedBy(newEntity.getModifiedBy());
			updatedEntity.setModifiedDate(newEntity.getModifiedDate());
			updatedEntity.setStatus(newEntity.getStatus());
			updatedEntity.setUrl(newEntity.getUrl());
			updatedEntity = commentRepository.save(updatedEntity);
		} catch (Exception e) {
			return null;
		}
        
		return updatedEntity;
	}

	@Override
	public CommentEntity delete(Long id) {
		CommentEntity deletedEntity = null;
		try {
			deletedEntity = commentRepository.findOneById(id);
			deletedEntity.setStatus("deleted");
			deletedEntity = commentRepository.save(deletedEntity);
		} catch (Exception e) {
			return null;
		}
		return deletedEntity;
	}

	@Override
	public CommentEntity findOneById(Long id) {
		CommentEntity foundEntity = null;
		try {
			foundEntity = commentRepository.findOneById(id);
		} catch (Exception e) {
			return null;
		}
		return foundEntity;
	}

}