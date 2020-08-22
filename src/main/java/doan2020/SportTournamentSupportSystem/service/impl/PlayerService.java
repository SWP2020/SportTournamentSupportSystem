
package doan2020.SportTournamentSupportSystem.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import doan2020.SportTournamentSupportSystem.entity.PlayerEntity;
import doan2020.SportTournamentSupportSystem.repository.PlayerRepository;
import doan2020.SportTournamentSupportSystem.service.IPlayerService;

@Service
public class PlayerService implements IPlayerService {

	@Autowired
	private PlayerRepository playerRepository;

	@Override
	public PlayerEntity create(PlayerEntity playerEntity) {
		PlayerEntity newEntity = null;
		try {
			newEntity = playerRepository.save(playerEntity);
		} catch (Exception e) {
			return null;
		}
		return newEntity;
	}

	@Override
	public PlayerEntity update(Long id, PlayerEntity newEntity) {
		PlayerEntity updatedEntity = null;
		try {
			updatedEntity = playerRepository.findOneById(id);

			updatedEntity.setFirstName(newEntity.getFirstName());
			updatedEntity.setLastName(newEntity.getLastName());
			updatedEntity.setGender(newEntity.getGender());
			updatedEntity.setDob(newEntity.getDob());
			updatedEntity.setEmail(newEntity.getEmail());
			updatedEntity.setTeam(newEntity.getTeam());
			updatedEntity.setCreatedBy(newEntity.getCreatedBy());
			updatedEntity.setCreatedDate(newEntity.getCreatedDate());
			updatedEntity.setModifiedBy(newEntity.getModifiedBy());
			updatedEntity.setModifiedDate(newEntity.getModifiedDate());
			updatedEntity.setStatus(newEntity.getStatus());
			updatedEntity.setUrl(newEntity.getUrl());
			updatedEntity = playerRepository.save(updatedEntity);
		} catch (Exception e) {
			return null;
		}
        
		return updatedEntity;
	}

	@Override
	public PlayerEntity delete(Long id) {
		PlayerEntity deletedEntity = null;
		try {
			deletedEntity = playerRepository.findOneById(id);
			deletedEntity.setStatus("deleted");
			deletedEntity = playerRepository.save(deletedEntity);
		} catch (Exception e) {
			return null;
		}
		return deletedEntity;
	}

	@Override
	public PlayerEntity findOneById(Long id) {
		PlayerEntity foundEntity = null;
		try {
			foundEntity = playerRepository.findOneById(id);
		} catch (Exception e) {
			return null;
		}
		return foundEntity;
	}
	
	
	@Override
	public Collection<PlayerEntity> findByTeamId(Long teamId) {
		Collection<PlayerEntity> players = null;
		try {
			players = playerRepository.findByTeamId(teamId);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return players;
	}
}