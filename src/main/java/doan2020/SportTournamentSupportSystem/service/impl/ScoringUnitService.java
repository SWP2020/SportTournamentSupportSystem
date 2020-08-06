
package doan2020.SportTournamentSupportSystem.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import doan2020.SportTournamentSupportSystem.entity.ScoringUnitEntity;
import doan2020.SportTournamentSupportSystem.repository.ScoringUniRepository;
import doan2020.SportTournamentSupportSystem.service.IScoringUnitService;

@Service
public class ScoringUnitService implements IScoringUnitService {

	@Autowired
	private ScoringUniRepository scoringUniRepository;

	@Override
	public ScoringUnitEntity create(ScoringUnitEntity scoringUniEntity) {
		ScoringUnitEntity newEntity = null;
		try {
			newEntity = scoringUniRepository.save(scoringUniEntity);
		} catch (Exception e) {
			return null;
		}
		return newEntity;
	}

	@Override
	public ScoringUnitEntity update(Long id, ScoringUnitEntity newEntity) {
		ScoringUnitEntity updatedEntity = null;
		try {
			updatedEntity = scoringUniRepository.findOneById(id);

			updatedEntity.setFullName(newEntity.getFullName());
			updatedEntity.setShortName(newEntity.getShortName());
			updatedEntity.setDescription(newEntity.getDescription());
			updatedEntity.setCreatedBy(newEntity.getCreatedBy());
			updatedEntity.setCreatedDate(newEntity.getCreatedDate());
			updatedEntity.setModifiedBy(newEntity.getModifiedBy());
			updatedEntity.setModifiedDate(newEntity.getModifiedDate());
			updatedEntity.setStatus(newEntity.getStatus());
			updatedEntity.setUrl(newEntity.getUrl());
			updatedEntity = scoringUniRepository.save(updatedEntity);
		} catch (Exception e) {
			return null;
		}
        
		return updatedEntity;
	}

	@Override
	public ScoringUnitEntity delete(Long id) {
		ScoringUnitEntity deletedEntity = null;
		try {
			deletedEntity = scoringUniRepository.findOneById(id);
			deletedEntity.setStatus("deleted");
			deletedEntity = scoringUniRepository.save(deletedEntity);
		} catch (Exception e) {
			return null;
		}
		return deletedEntity;
	}

	@Override
	public ScoringUnitEntity findOneById(Long id) {
		ScoringUnitEntity foundEntity = null;
		try {
			foundEntity = scoringUniRepository.findOneById(id);
		} catch (Exception e) {
			return null;
		}
		return foundEntity;
	}

}