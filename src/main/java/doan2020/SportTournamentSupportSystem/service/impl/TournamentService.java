
package doan2020.SportTournamentSupportSystem.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import doan2020.SportTournamentSupportSystem.entity.TournamentEntity;
import doan2020.SportTournamentSupportSystem.entity.UserEntity;
import doan2020.SportTournamentSupportSystem.repository.TournamentRepository;
import doan2020.SportTournamentSupportSystem.repository.UserRepository;
import doan2020.SportTournamentSupportSystem.service.ITournamentService;

@Service
public class TournamentService implements ITournamentService {

	@Autowired
	private TournamentRepository tournamentRepository;
	
	@Autowired
	private UserRepository UserRepository;

	@Override
	public TournamentEntity create(TournamentEntity tournamentEntity) {
		TournamentEntity newEntity = null;
		try {
			newEntity = tournamentRepository.save(tournamentEntity);
		} catch (Exception e) {
			return null;
		}
		return newEntity;
	}

	@Override
	public TournamentEntity update(Long id, TournamentEntity newEntity) {
		TournamentEntity updatedEntity = null;
		try {
			updatedEntity = tournamentRepository.findOneById(id);

			updatedEntity.setFullName(newEntity.getFullName());
			updatedEntity.setShortName(newEntity.getShortName());
			updatedEntity.setDescription(newEntity.getDescription());
//			updatedEntity.setCreator(newEntity.getCreator());
			updatedEntity.setOpeningLocation(newEntity.getOpeningLocation());
			updatedEntity.setOpeningTime(newEntity.getOpeningTime());
			updatedEntity.setClosingLocation(newEntity.getClosingLocation());
			updatedEntity.setClosingTime(newEntity.getClosingTime());
			updatedEntity.setDonor(newEntity.getDonor());
			updatedEntity.setCreatedBy(newEntity.getCreatedBy());
			updatedEntity.setCreatedDate(newEntity.getCreatedDate());
			updatedEntity.setModifiedBy(newEntity.getModifiedBy());
			updatedEntity.setModifiedDate(newEntity.getModifiedDate());
			updatedEntity.setStatus(newEntity.getStatus());
			updatedEntity.setUrl(newEntity.getUrl());
			updatedEntity = tournamentRepository.save(updatedEntity);
		} catch (Exception e) {
			return null;
		}
        
		return updatedEntity;
	}

	@Override
	public TournamentEntity delete(Long id) {
		TournamentEntity deletedEntity = null;
		try {
			deletedEntity = tournamentRepository.findOneById(id);
			deletedEntity.setStatus("deleted");
			deletedEntity = tournamentRepository.save(deletedEntity);
		} catch (Exception e) {
			return null;
		}
		return deletedEntity;
	}

	@Override
	public TournamentEntity findOneById(Long id) {
		TournamentEntity foundEntity = null;
		try {
			foundEntity = tournamentRepository.findOneById(id);
		} catch (Exception e) {
			return null;
		}
		return foundEntity;
	}
	
	@Override
	public Collection<TournamentEntity> findByCreatorId(Pageable pageable, Long creatorId) {
		Collection<TournamentEntity> findTournaments = null;
		try {
			findTournaments = tournamentRepository.findByCreatorId(pageable, creatorId).getContent();
		} catch (Exception e) {
			return null;
		}
		return findTournaments;
	}

}