
package doan2020.SportTournamentSupportSystem.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import doan2020.SportTournamentSupportSystem.entity.CompetitionEntity;
import doan2020.SportTournamentSupportSystem.entity.TeamEntity;
import doan2020.SportTournamentSupportSystem.repository.TeamRepository;
import doan2020.SportTournamentSupportSystem.service.ITeamService;

@Service
public class TeamService implements ITeamService {

	@Autowired
	private TeamRepository teamRepository;

	@Override
	public TeamEntity create(TeamEntity teamEntity) {
		TeamEntity newEntity = null;
		try {
			Long competitionId = teamEntity.getCompetition().getId();
			Long maxSeedNo = teamRepository.getMaxSeedNoByCompetitionId(competitionId);
			if (maxSeedNo == null)
				maxSeedNo = 0l;
			teamEntity.setSeedNo(maxSeedNo + 1);
			newEntity = teamRepository.save(teamEntity);
		} catch (Exception e) {
			return null;
		}
		return newEntity;
	}

	@Override
	public TeamEntity update(Long id, TeamEntity newEntity) {
		TeamEntity updatedEntity = null;
		try {
			updatedEntity = teamRepository.findOneById(id);

			updatedEntity.setFullName(newEntity.getFullName());
			updatedEntity.setShortName(newEntity.getShortName());
			updatedEntity.setDescription(newEntity.getDescription());
			updatedEntity.setCreator(newEntity.getCreator());
			updatedEntity.setCompetition(newEntity.getCompetition());
			updatedEntity.setCreatedBy(newEntity.getCreatedBy());
			updatedEntity.setCreatedDate(newEntity.getCreatedDate());
			updatedEntity.setModifiedBy(newEntity.getModifiedBy());
			updatedEntity.setModifiedDate(newEntity.getModifiedDate());
			updatedEntity.setStatus(newEntity.getStatus());
			updatedEntity.setUrl(newEntity.getUrl());
			updatedEntity = teamRepository.save(updatedEntity);
		} catch (Exception e) {
			return null;
		}

		return updatedEntity;
	}

	@Override
	public TeamEntity delete(Long id) {
		TeamEntity deletedEntity = null;
		try {
			deletedEntity = teamRepository.findOneById(id);
			deletedEntity.setStatus("deleted");
			deletedEntity = teamRepository.save(deletedEntity);
		} catch (Exception e) {
			return null;
		}
		return deletedEntity;
	}

	@Override
	public TeamEntity findOneById(Long id) {
		TeamEntity foundEntity = null;
		try {
			foundEntity = teamRepository.getOne(id);
		} catch (Exception e) {
			return null;
		}
		return foundEntity;
	}

	@Override
	public Collection<TeamEntity> findAll(Pageable pageable) {
		Collection<TeamEntity> foundEntitys = null;
		try {
			foundEntitys = teamRepository.findAll(pageable).getContent();
		} catch (Exception e) {
			return null;
		}
		return foundEntitys;
	}

	@Override
	public Collection<TeamEntity> findAll() {
		Collection<TeamEntity> foundEntitys = null;
		try {
			foundEntitys = teamRepository.findAll();
		} catch (Exception e) {
			return null;
		}
		return foundEntitys;
	}

	@Override
	public Collection<TeamEntity> findByCreatorId(Long creatorId) {
		Collection<TeamEntity> foundEntitys = null;
		try {
			foundEntitys = teamRepository.findByCreatorId(creatorId);
		} catch (Exception e) {
			return null;
		}
		return foundEntitys;
	}

	@Override
	public Collection<TeamEntity> findByCompetitionId(Long competitionId) {
		Collection<TeamEntity> foundEntities = null;
		try {
			foundEntities = teamRepository.findByCompetitionId(competitionId);
		} catch (Exception e) {
			return null;
		}
		return foundEntities;
	}

	@Override
	public Collection<TeamEntity> swap(Long team1Id, Long team2Id) {
		Collection<TeamEntity> teams = null;
		try {
			TeamEntity team1 = teamRepository.findOneById(team1Id);
			TeamEntity team2 = teamRepository.findOneById(team2Id);
			CompetitionEntity thisComp = team1.getCompetition();
			Long compId = thisComp.getId();
			
			Long tmp = team1.getSeedNo();
			team1.setSeedNo(team2.getSeedNo());
			team2.setSeedNo(tmp);
			teamRepository.save(team1);
			teamRepository.save(team2);
			
			teams = teamRepository.findByCompetitionId(compId);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return teams;
	}

}