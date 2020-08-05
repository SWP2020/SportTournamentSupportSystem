
package doan2020.SportTournamentSupportSystem.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import doan2020.SportTournamentSupportSystem.entity.CompetitionFormatEntity;
import doan2020.SportTournamentSupportSystem.repository.CompetitionFormatRepository;
import doan2020.SportTournamentSupportSystem.service.ICompetitionFormatService;

@Service
public class CompetitionFormatService implements ICompetitionFormatService {

	@Autowired
	private CompetitionFormatRepository competitionFormatRepository;

	@Override
	public CompetitionFormatEntity create(CompetitionFormatEntity competitionFormatEntity) {
		CompetitionFormatEntity newEntity = null;
		try {
			newEntity = competitionFormatRepository.save(competitionFormatEntity);
		} catch (Exception e) {
			return null;
		}
		return newEntity;
	}

	@Override
	public CompetitionFormatEntity update(Long id, CompetitionFormatEntity newEntity) {
		CompetitionFormatEntity updatedEntity = null;
		try {
			updatedEntity = competitionFormatRepository.findOneById(id);

			updatedEntity.setName(newEntity.getName());
			updatedEntity.setDescription(newEntity.getDescription());
			updatedEntity.setCreatedBy(newEntity.getCreatedBy());
			updatedEntity.setCreatedDate(newEntity.getCreatedDate());
			updatedEntity.setModifiedBy(newEntity.getModifiedBy());
			updatedEntity.setModifiedDate(newEntity.getModifiedDate());
			updatedEntity.setStatus(newEntity.getStatus());
			updatedEntity.setUrl(newEntity.getUrl());
			updatedEntity = competitionFormatRepository.save(updatedEntity);
		} catch (Exception e) {
			return null;
		}
        
		return updatedEntity;
	}

	@Override
	public CompetitionFormatEntity delete(Long id) {
		CompetitionFormatEntity deletedEntity = null;
		try {
			deletedEntity = competitionFormatRepository.findOneById(id);
			deletedEntity.setStatus("deleted");
			deletedEntity = competitionFormatRepository.save(deletedEntity);
		} catch (Exception e) {
			return null;
		}
		return deletedEntity;
	}

	@Override
	public CompetitionFormatEntity findOneById(Long id) {
		CompetitionFormatEntity foundEntity = null;
		try {
			foundEntity = competitionFormatRepository.findOneById(id);
		} catch (Exception e) {
			return null;
		}
		return foundEntity;
	}

}