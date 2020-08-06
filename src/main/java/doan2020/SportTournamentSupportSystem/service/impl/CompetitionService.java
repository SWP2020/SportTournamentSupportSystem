
package doan2020.SportTournamentSupportSystem.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import doan2020.SportTournamentSupportSystem.entity.CompetitionEntity;
import doan2020.SportTournamentSupportSystem.repository.CompetitionRepository;
import doan2020.SportTournamentSupportSystem.service.ICompetitionService;

@Service
public class CompetitionService implements ICompetitionService {

	@Autowired
	private CompetitionRepository competitionRepository;

	@Override
	public CompetitionEntity create(CompetitionEntity competitionEntity) {
		CompetitionEntity newEntity = null;
		try {
			newEntity = competitionRepository.save(competitionEntity);
		} catch (Exception e) {
			return null;
		}
		return newEntity;
	}

	@Override
	public CompetitionEntity update(Long id, CompetitionEntity newEntity) {
		CompetitionEntity updatedEntity = null;
		try {
			updatedEntity = competitionRepository.findOneById(id);

			updatedEntity.setName(newEntity.getName());
			updatedEntity.setDescription(newEntity.getDescription());
			updatedEntity.setTournament(newEntity.getTournament());
			updatedEntity.setSport(newEntity.getSport());
			updatedEntity.setMainFormat(newEntity.getMainFormat());
			updatedEntity.setGroupStage(newEntity.getGroupStage());
			updatedEntity.setGroupStageFormat(newEntity.getGroupStageFormat());
			updatedEntity.setCreatedBy(newEntity.getCreatedBy());
			updatedEntity.setCreatedDate(newEntity.getCreatedDate());
			updatedEntity.setModifiedBy(newEntity.getModifiedBy());
			updatedEntity.setModifiedDate(newEntity.getModifiedDate());
			updatedEntity.setStatus(newEntity.getStatus());
			updatedEntity.setUrl(newEntity.getUrl());
			updatedEntity = competitionRepository.save(updatedEntity);
		} catch (Exception e) {
			return null;
		}
        
		return updatedEntity;
	}

	@Override
	public CompetitionEntity delete(Long id) {
		CompetitionEntity deletedEntity = null;
		try {
			deletedEntity = competitionRepository.findOneById(id);
			deletedEntity.setStatus("deleted");
			deletedEntity = competitionRepository.save(deletedEntity);
		} catch (Exception e) {
			return null;
		}
		return deletedEntity;
	}

	@Override
	public CompetitionEntity findOneById(Long id) {
		CompetitionEntity foundEntity = null;
		try {
			System.out.println(foundEntity.toString());
			foundEntity = competitionRepository.findOneById(id);
			System.out.println(foundEntity.toString());
			System.out.println("true");
		} catch (Exception e) {
			System.out.println("false");
			return null;
		}
		return foundEntity;
	}

	@Override
	public Collection<CompetitionEntity> findAll(Pageable pageable) {
		Collection<CompetitionEntity> foundEntitys = null;
		try {
			foundEntitys = (Collection<CompetitionEntity>) competitionRepository.findAll(pageable).getContent();
		} catch (Exception e) {
			return null;
		}
		return foundEntitys;
	}

	@Override
	public Collection<CompetitionEntity> findByTournamentId(Pageable pageable, Long tournamentId) {
		Collection<CompetitionEntity> foundEntitys = null;
		try {
			foundEntitys = (Collection<CompetitionEntity>) competitionRepository.findByTournamentId(pageable, tournamentId).getContent();
		} catch (Exception e) {
			return null;
		}
		return foundEntitys;
	}

	@Override
	public Collection<CompetitionEntity> findByTournamentIdAndSportId(Pageable pageable, Long tournamentId,
			Long sportId) {
		Collection<CompetitionEntity> foundEntitys = null;
		try {
			foundEntitys = (Collection<CompetitionEntity>) competitionRepository.findByTournamentIdAndSportId(pageable, tournamentId, sportId).getContent();
		} catch (Exception e) {
			return null;
		}
		return foundEntitys;
	}

}