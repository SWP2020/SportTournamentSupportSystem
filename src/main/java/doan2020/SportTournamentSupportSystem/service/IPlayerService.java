
package doan2020.SportTournamentSupportSystem.service;

import java.util.Collection;
import org.springframework.data.domain.Pageable;
import doan2020.SportTournamentSupportSystem.entity.PlayerEntity;

public interface IPlayerService {
	
	public PlayerEntity findOneById(Long id);
	
//	public Collection<PlayerEntity> findAll(Pageable pageable);
	
	public PlayerEntity create(PlayerEntity playerEntity);
	
	public PlayerEntity update(Long id, PlayerEntity newEntity);
	
//	public Collection<PlayerEntity> findAll();
	
	public PlayerEntity delete(Long id);
	
	public Collection<PlayerEntity> findByTeamId(Long teamId);
}