
package doan2020.SportTournamentSupportSystem.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import doan2020.SportTournamentSupportSystem.entity.ResultEntity;
import doan2020.SportTournamentSupportSystem.repository.ResultRepository;
import doan2020.SportTournamentSupportSystem.service.IResultService;

@Service
public class ResultService implements IResultService {

	@Autowired
	private ResultRepository resultRepository;

	@Override
	public ResultEntity create(ResultEntity resultEntity) {
		ResultEntity newEntity = null;
		try {
			newEntity = resultRepository.save(resultEntity);
		} catch (Exception e) {
			return null;
		}
		return newEntity;
	}

	@Override
	public ResultEntity update(Long id, ResultEntity newEntity) {
		ResultEntity updatedEntity = null;
		try {
			updatedEntity = resultRepository.findOneById(id);

			updatedEntity.setMatch(newEntity.getMatch());
			updatedEntity.setTeam(newEntity.getTeam());
			updatedEntity.setSetNo(newEntity.getSetNo());
			updatedEntity.setScore(newEntity.getScore());
			updatedEntity.setRank(newEntity.getRank());
			updatedEntity.setCreatedBy(newEntity.getCreatedBy());
			updatedEntity.setCreatedDate(newEntity.getCreatedDate());
			updatedEntity.setModifiedBy(newEntity.getModifiedBy());
			updatedEntity.setModifiedDate(newEntity.getModifiedDate());
			updatedEntity.setStatus(newEntity.getStatus());
			updatedEntity.setUrl(newEntity.getUrl());
			updatedEntity = resultRepository.save(updatedEntity);
		} catch (Exception e) {
			return null;
		}
        
		return updatedEntity;
	}

	@Override
	public ResultEntity delete(Long id) {
		ResultEntity deletedEntity = null;
		try {
			deletedEntity = resultRepository.findOneById(id);
			deletedEntity.setStatus("deleted");
			deletedEntity = resultRepository.save(deletedEntity);
		} catch (Exception e) {
			return null;
		}
		return deletedEntity;
	}

	@Override
	public ResultEntity findOneById(Long id) {
		ResultEntity foundEntity = null;
		try {
			foundEntity = resultRepository.findOneById(id);
		} catch (Exception e) {
			return null;
		}
		return foundEntity;
	}

}