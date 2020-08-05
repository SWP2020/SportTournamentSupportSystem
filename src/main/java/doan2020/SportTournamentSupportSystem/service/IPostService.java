
package doan2020.SportTournamentSupportSystem.service;

import java.util.Collection;
import org.springframework.data.domain.Pageable;
import doan2020.SportTournamentSupportSystem.entity.PostEntity;

public interface IPostService {
	
	public PostEntity findOneById(Long id);
	
//	public Collection<PostEntity> findAll(Pageable pageable);
	
	public PostEntity create(PostEntity postEntity);
	
	public PostEntity update(Long id, PostEntity newEntity);
	
//	public Collection<PostEntity> findAll();
	
	public PostEntity delete(Long id);
}