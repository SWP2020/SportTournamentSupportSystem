
package doan2020.SportTournamentSupportSystem.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import doan2020.SportTournamentSupportSystem.entity.PostEntity;
import doan2020.SportTournamentSupportSystem.repository.PostRepository;
import doan2020.SportTournamentSupportSystem.service.IPostService;

@Service
public class PostService implements IPostService {

	@Autowired
	private PostRepository postRepository;

	@Override
	public PostEntity create(PostEntity postEntity) {
		PostEntity newEntity = null;
		try {
			newEntity = postRepository.save(postEntity);
		} catch (Exception e) {
			return null;
		}
		return newEntity;
	}

	@Override
	public PostEntity update(Long id, PostEntity newEntity) {
		PostEntity updatedEntity = null;
		try {
			updatedEntity = postRepository.findOneById(id);

			updatedEntity.setTitle(newEntity.getTitle());
			updatedEntity.setAuthor(newEntity.getAuthor());
			updatedEntity.setTournament(newEntity.getTournament());
			updatedEntity.setSystemPost(newEntity.getSystemPost());
			updatedEntity.setContent(newEntity.getContent());
			updatedEntity.setCreatedBy(newEntity.getCreatedBy());
			updatedEntity.setCreatedDate(newEntity.getCreatedDate());
			updatedEntity.setModifiedBy(newEntity.getModifiedBy());
			updatedEntity.setModifiedDate(newEntity.getModifiedDate());
			updatedEntity.setStatus(newEntity.getStatus());
			updatedEntity.setUrl(newEntity.getUrl());
			updatedEntity = postRepository.save(updatedEntity);
		} catch (Exception e) {
			return null;
		}
        
		return updatedEntity;
	}

	@Override
	public PostEntity delete(Long id) {
		PostEntity deletedEntity = null;
		try {
			deletedEntity = postRepository.findOneById(id);
			deletedEntity.setStatus("deleted");
			deletedEntity = postRepository.save(deletedEntity);
		} catch (Exception e) {
			return null;
		}
		return deletedEntity;
	}

	@Override
	public PostEntity findOneById(Long id) {
		PostEntity foundEntity = null;
		try {
			foundEntity = postRepository.findOneById(id);
		} catch (Exception e) {
			return null;
		}
		return foundEntity;
	}

	@Override
	public Collection<PostEntity> findAll(Pageable pageable) {
		Collection<PostEntity> foundEntitys = null;
		try {
			foundEntitys = postRepository.findAll(pageable).getContent();
		} catch (Exception e) {
			return null;
		}
		return foundEntitys;
	}

	@Override
	public Collection<PostEntity> findAll() {
		Collection<PostEntity> foundEntitys = null;
		try {
			foundEntitys = postRepository.findAll();
		} catch (Exception e) {
			return null;
		}
		return foundEntitys;
	}

	@Override
	public Collection<PostEntity> findByTournamentId(Pageable pageable, Long tournamentId) {
		Collection<PostEntity> foundEntitys = null;
		try {
			foundEntitys = postRepository.findByTournamentId(pageable, tournamentId).getContent();
		} catch (Exception e) {
			return null;
		}
		return foundEntitys;
	}

	@Override
	public Collection<PostEntity> findByUserId(Pageable pageable, Long UserId) {
		Collection<PostEntity> foundEntitys = null;
		try {
			foundEntitys = postRepository.findByAuthorId(pageable, UserId).getContent();
		} catch (Exception e) {
			return null;
		}
		return foundEntitys;
	}

}