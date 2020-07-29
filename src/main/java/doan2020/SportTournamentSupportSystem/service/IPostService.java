package doan2020.SportTournamentSupportSystem.service;

import java.util.Collection;

import org.springframework.data.domain.Pageable;

import doan2020.SportTournamentSupportSystem.entity.PostEntity;

public interface IPostService {
	public Collection<PostEntity> findAll(Pageable pageable);

	public PostEntity findById(Long id);
	
	public void addOne(PostEntity entity);
	public void addMany(Collection<PostEntity> list);
	
	public PostEntity update(Long id, PostEntity entity);
	
	public Collection<PostEntity> findByTournamentId(Long tournamentId, Pageable pageable);
}
