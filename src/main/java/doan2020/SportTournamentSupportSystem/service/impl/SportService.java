
package doan2020.SportTournamentSupportSystem.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import doan2020.SportTournamentSupportSystem.entity.SportEntity;
import doan2020.SportTournamentSupportSystem.repository.SportRepository;
import doan2020.SportTournamentSupportSystem.service.ISportService;

@Service
public class SportService implements ISportService {

	@Autowired
	private SportRepository sportRepository;

	
	@Override
	public SportEntity create(SportEntity sportEntity) {
		SportEntity newEntity = null;
		try {
			newEntity = sportRepository.save(sportEntity);
		} catch (Exception e) {
			return null;
		}
		return newEntity;
	}

	@Override
	public SportEntity update(Long id, SportEntity newEntity) {
		SportEntity updatedEntity = null;
		try {
			updatedEntity = sportRepository.findOneById(id);

			updatedEntity.setFullName(newEntity.getFullName());
			updatedEntity.setShortName(newEntity.getShortName());
			updatedEntity.setScoringUnit(newEntity.getScoringUnit());
			updatedEntity.setDescription(newEntity.getDescription());
			updatedEntity.setCreatedBy(newEntity.getCreatedBy());
			updatedEntity.setCreatedDate(newEntity.getCreatedDate());
			updatedEntity.setModifiedBy(newEntity.getModifiedBy());
			updatedEntity.setModifiedDate(newEntity.getModifiedDate());
			updatedEntity.setStatus(newEntity.getStatus());
			updatedEntity.setUrl(newEntity.getUrl());
			updatedEntity = sportRepository.save(updatedEntity);
		} catch (Exception e) {
			return null;
		}
        
		return updatedEntity;
	}

	@Override
	public SportEntity delete(Long id) {
		SportEntity deletedEntity = null;
		try {
			deletedEntity = sportRepository.findOneById(id);
			deletedEntity.setStatus("deleted");
			deletedEntity = sportRepository.save(deletedEntity);
		} catch (Exception e) {
			return null;
		}
		return deletedEntity;
	}

	@Override
	public SportEntity findOneById(Long id) {
		SportEntity foundEntity = null;
		try {
			foundEntity = sportRepository.findOneById(id);
		} catch (Exception e) {
			return null;
		}
		return foundEntity;
	}

}