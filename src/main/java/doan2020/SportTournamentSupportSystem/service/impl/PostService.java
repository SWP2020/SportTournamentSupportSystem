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
	public void addOne(PostEntity entity) {
		
		postRepository.save(entity);

	}

	@Override
	public void addMany(Collection<PostEntity> list) {
		for (PostEntity entity : list)
			postRepository.save(entity);

	}

	@Override
	public PostEntity findById(Long id) {
		PostEntity res = null;
		try {
			res = postRepository.findOneById(id);
		} catch (Exception e) {
			return null;
		}
		
		return res;
	}

	@Override
	public Collection<PostEntity> findAll(Pageable pageable) {
		
		return postRepository.findAll(pageable).getContent();
	}

	@Override
	public PostEntity update(Long id, PostEntity entity) {
		
		PostEntity old = postRepository.findOneById(id);

		old.setTitle(entity.getTitle());
		old.setContent(entity.getContent());
		old.setTournament(entity.getTournament());
		old.setAuthor(entity.getAuthor());
		old = postRepository.save(old);
		return old;
	}

	@Override
	public Collection<PostEntity> findByTournamentId(Long tournamentId, Pageable pageable) {
		// TODO Auto-generated method stub
		return (Collection<PostEntity>) postRepository.findByTournamentId(tournamentId, pageable).getContent();
	}
}
