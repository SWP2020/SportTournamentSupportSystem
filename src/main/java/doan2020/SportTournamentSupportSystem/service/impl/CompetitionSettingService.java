
package doan2020.SportTournamentSupportSystem.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import doan2020.SportTournamentSupportSystem.entity.CompetitionSettingEntity;
import doan2020.SportTournamentSupportSystem.repository.CompetitionSettingRepository;
import doan2020.SportTournamentSupportSystem.service.ICompetitionSettingService;

@Service
public class CompetitionSettingService implements ICompetitionSettingService {

	@Autowired
	private CompetitionSettingRepository competitionSettingRepository;

	@Override
	public CompetitionSettingEntity create(CompetitionSettingEntity competitionSettingEntity) {
		CompetitionSettingEntity newEntity = null;
		try {
			newEntity = competitionSettingRepository.save(competitionSettingEntity);
		} catch (Exception e) {
			return null;
		}
		return newEntity;
	}

	@Override
	public CompetitionSettingEntity update(Long id, CompetitionSettingEntity newEntity) {
		CompetitionSettingEntity updatedEntity = null;
		try {
			updatedEntity = competitionSettingRepository.findOneById(id);

			updatedEntity.setCompetition(newEntity.getCompetition());
			updatedEntity.setMaxNumberOfTeam(newEntity.getMaxNumberOfTeam());
			updatedEntity.setMaxMemberPerTeam(newEntity.getMaxMemberPerTeam());
			updatedEntity.setNumberOfTable(newEntity.getNumberOfTable());
			updatedEntity.setNumberOfTeamPassPerTable(newEntity.getNumberOfTeamPassPerTable());
			updatedEntity.setHomeGame(newEntity.getHomeGame());
			updatedEntity.setCreatedBy(newEntity.getCreatedBy());
			updatedEntity.setCreatedDate(newEntity.getCreatedDate());
			updatedEntity.setModifiedBy(newEntity.getModifiedBy());
			updatedEntity.setModifiedDate(newEntity.getModifiedDate());
			updatedEntity.setStatus(newEntity.getStatus());
			updatedEntity.setUrl(newEntity.getUrl());
			updatedEntity = competitionSettingRepository.save(updatedEntity);
		} catch (Exception e) {
			return null;
		}
        
		return updatedEntity;
	}

	@Override
	public CompetitionSettingEntity delete(Long id) {
		CompetitionSettingEntity deletedEntity = null;
		try {
			deletedEntity = competitionSettingRepository.findOneById(id);
			deletedEntity.setStatus("deleted");
			deletedEntity = competitionSettingRepository.save(deletedEntity);
		} catch (Exception e) {
			return null;
		}
		return deletedEntity;
	}

	@Override
	public CompetitionSettingEntity findOneById(Long id) {
		CompetitionSettingEntity foundEntity = null;
		try {
			foundEntity = competitionSettingRepository.findOneById(id);
		} catch (Exception e) {
			return null;
		}
		return foundEntity;
	}

	@Override
	public Collection<CompetitionSettingEntity> findAll(Pageable pageable) {
		Collection<CompetitionSettingEntity> foundEntitys = null;
		try {
			foundEntitys = competitionSettingRepository.findAll(pageable).getContent();
		} catch (Exception e) {
			return null;
		}
		return foundEntitys;
	}

	@Override
	public Collection<CompetitionSettingEntity> findByCompetitionId(Long CompetitionId) {
		Collection<CompetitionSettingEntity> foundEntitys = null;
		try {
			foundEntitys = competitionSettingRepository.findByCompetitionId(CompetitionId);
		} catch (Exception e) {
			return null;
		}
		return foundEntitys;
	}

}