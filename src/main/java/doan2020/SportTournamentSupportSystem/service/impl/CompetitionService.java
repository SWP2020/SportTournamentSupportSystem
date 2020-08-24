
package doan2020.SportTournamentSupportSystem.service.impl;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import doan2020.SportTournamentSupportSystem.entity.CompetitionEntity;
import doan2020.SportTournamentSupportSystem.repository.CompetitionRepository;
import doan2020.SportTournamentSupportSystem.repository.MatchRepository;
import doan2020.SportTournamentSupportSystem.service.ICompetitionService;

@Service
public class CompetitionService implements ICompetitionService {

	@Autowired
	private CompetitionRepository competitionRepository;
	
	@Autowired
	private MatchRepository matchRepository;

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
			updatedEntity.setCreatedBy(newEntity.getCreatedBy());
			updatedEntity.setCreatedDate(newEntity.getCreatedDate());
			updatedEntity.setModifiedBy(newEntity.getModifiedBy());
			updatedEntity.setModifiedDate(newEntity.getModifiedDate());
			updatedEntity.setStatus(newEntity.getStatus());
			updatedEntity.setUrl(newEntity.getUrl());
			updatedEntity.setGroupStageSetting(newEntity.getGroupStageSetting());
			updatedEntity.setFinalStageSetting(newEntity.getFinalStageSetting());
			updatedEntity = competitionRepository.save(updatedEntity);
		} catch (Exception e) {
			System.out.println("CompetitionService: update: has exception");
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
		System.out.println("CompetitionService: findOneById: start");
		CompetitionEntity foundEntity = null;
		try {
			foundEntity = competitionRepository.findOneById(id);
			System.out.println("CompetitionService: findOneById: no exception");
		} catch (Exception e) {
			System.out.println("CompetitionService: findOneById: has exception");
		}
		System.out.println("CompetitionService: findOneById: finish");
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
	public Collection<CompetitionEntity> findByTournamentIdAndSportId(Long tournamentId,
			Long sportId) {
		Collection<CompetitionEntity> foundEntitys = null;
		try {
			foundEntitys = (Collection<CompetitionEntity>) competitionRepository.findByTournamentIdAndSportId(tournamentId, sportId);
		} catch (Exception e) {
			return null;
		}
		return foundEntitys;
	}
	
	@Override
	public Collection<CompetitionEntity> findByTournamentId(Long tournamentId) {
		Collection<CompetitionEntity> foundEntitys = null;
		try {
			foundEntitys = competitionRepository.findByTournamentId(tournamentId);
		} catch (Exception e) {
			return null;
		}
		return foundEntitys;
	}

	@Override
	public CompetitionEntity updateStatus(CompetitionEntity entity, String status) {
		try {
			entity.setStatus(status);
			entity = competitionRepository.save(entity);
		} catch (Exception e) {
			return null;
		}
		return entity;
	}
	
	

}