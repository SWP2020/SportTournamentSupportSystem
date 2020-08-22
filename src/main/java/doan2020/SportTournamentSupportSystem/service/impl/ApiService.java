
package doan2020.SportTournamentSupportSystem.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import doan2020.SportTournamentSupportSystem.entity.ApiEntity;
import doan2020.SportTournamentSupportSystem.repository.ApiRepository;
import doan2020.SportTournamentSupportSystem.service.IApiService;

@Service
public class ApiService implements IApiService {

	@Autowired
	private ApiRepository apiRepository;

	@Override
	public ApiEntity create(ApiEntity apiEntity) {
		ApiEntity newEntity = null;
		try {
			newEntity = apiRepository.save(apiEntity);
		} catch (Exception e) {
			return null;
		}
		return newEntity;
	}

	@Override
	public ApiEntity update(Long id, ApiEntity newEntity) {
		ApiEntity updatedEntity = null;
		try {
			updatedEntity = apiRepository.findOneById(id);

			updatedEntity.setName(newEntity.getName());
			updatedEntity.setDescription(newEntity.getDescription());
			updatedEntity.setMethod(newEntity.getMethod());
			updatedEntity.setCreatedBy(newEntity.getCreatedBy());
			updatedEntity.setCreatedDate(newEntity.getCreatedDate());
			updatedEntity.setModifiedBy(newEntity.getModifiedBy());
			updatedEntity.setModifiedDate(newEntity.getModifiedDate());
			updatedEntity.setStatus(newEntity.getStatus());
			updatedEntity.setUrl(newEntity.getUrl());
			updatedEntity = apiRepository.save(updatedEntity);
		} catch (Exception e) {
			return null;
		}
        
		return updatedEntity;
	}

	@Override
	public ApiEntity delete(Long id) {
		ApiEntity deletedEntity = null;
		try {
			deletedEntity = apiRepository.findOneById(id);
			deletedEntity.setStatus("deleted");
			deletedEntity = apiRepository.save(deletedEntity);
		} catch (Exception e) {
			return null;
		}
		return deletedEntity;
	}

	@Override
	public ApiEntity findOneById(Long id) {
		ApiEntity foundEntity = null;
		try {
			foundEntity = apiRepository.findOneById(id);
		} catch (Exception e) {
			return null;
		}
		return foundEntity;
	}

}