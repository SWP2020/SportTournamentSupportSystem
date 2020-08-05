
package doan2020.SportTournamentSupportSystem.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import doan2020.SportTournamentSupportSystem.entity.MatchEntity;
import doan2020.SportTournamentSupportSystem.repository.MatchRepository;
import doan2020.SportTournamentSupportSystem.service.IMatchService;

@Service
public class MatchService implements IMatchService {

	@Autowired
	private MatchRepository matchRepository;

	@Override
	public MatchEntity create(MatchEntity matchEntity) {
		MatchEntity newEntity = null;
		try {
			newEntity = matchRepository.save(matchEntity);
		} catch (Exception e) {
			return null;
		}
		return newEntity;
	}

	@Override
	public MatchEntity update(Long id, MatchEntity newEntity) {
		MatchEntity updatedEntity = null;
		try {
			updatedEntity = matchRepository.findOneById(id);

			updatedEntity.setName(newEntity.getName());
			updatedEntity.setNumOfSet(newEntity.getNumOfSet());
			updatedEntity.setExpectedDate(newEntity.getExpectedDate());
			updatedEntity.setExpectedPlace(newEntity.getExpectedPlace());
			updatedEntity.setRealDate(newEntity.getRealDate());
			updatedEntity.setRealPlace(newEntity.getRealPlace());
			updatedEntity.setCompetition(newEntity.getCompetition());
			updatedEntity.setNextMatch(newEntity.getNextMatch());
			updatedEntity.setRoundNo(newEntity.getRoundNo());
			updatedEntity.setCreatedBy(newEntity.getCreatedBy());
			updatedEntity.setCreatedDate(newEntity.getCreatedDate());
			updatedEntity.setModifiedBy(newEntity.getModifiedBy());
			updatedEntity.setModifiedDate(newEntity.getModifiedDate());
			updatedEntity.setStatus(newEntity.getStatus());
			updatedEntity.setUrl(newEntity.getUrl());
			updatedEntity = matchRepository.save(updatedEntity);
		} catch (Exception e) {
			return null;
		}
        
		return updatedEntity;
	}

	@Override
	public MatchEntity delete(Long id) {
		MatchEntity deletedEntity = null;
		try {
			deletedEntity = matchRepository.findOneById(id);
			deletedEntity.setStatus("deleted");
			deletedEntity = matchRepository.save(deletedEntity);
		} catch (Exception e) {
			return null;
		}
		return deletedEntity;
	}

	@Override
	public MatchEntity findOneById(Long id) {
		MatchEntity foundEntity = null;
		try {
			foundEntity = matchRepository.findOneById(id);
		} catch (Exception e) {
			return null;
		}
		return foundEntity;
	}

}