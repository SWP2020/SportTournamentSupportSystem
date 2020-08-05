
package doan2020.SportTournamentSupportSystem.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import doan2020.SportTournamentSupportSystem.entity.RoleEntity;
import doan2020.SportTournamentSupportSystem.repository.RoleRepository;
import doan2020.SportTournamentSupportSystem.service.IRoleService;

@Service
public class RoleService implements IRoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public RoleEntity create(RoleEntity roleEntity) {
		RoleEntity newEntity = null;
		try {
			newEntity = roleRepository.save(roleEntity);
		} catch (Exception e) {
			return null;
		}
		return newEntity;
	}

	@Override
	public RoleEntity update(Long id, RoleEntity newEntity) {
		RoleEntity updatedEntity = null;
		try {
			updatedEntity = roleRepository.findOneById(id);

			updatedEntity.setName(newEntity.getName());
			updatedEntity.setDescription(newEntity.getDescription());
			updatedEntity.setCreatedBy(newEntity.getCreatedBy());
			updatedEntity.setCreatedDate(newEntity.getCreatedDate());
			updatedEntity.setModifiedBy(newEntity.getModifiedBy());
			updatedEntity.setModifiedDate(newEntity.getModifiedDate());
			updatedEntity.setStatus(newEntity.getStatus());
			updatedEntity.setUrl(newEntity.getUrl());
			updatedEntity = roleRepository.save(updatedEntity);
		} catch (Exception e) {
			return null;
		}
        
		return updatedEntity;
	}

	@Override
	public RoleEntity delete(Long id) {
		RoleEntity deletedEntity = null;
		try {
			deletedEntity = roleRepository.findOneById(id);
			deletedEntity.setStatus("deleted");
			deletedEntity = roleRepository.save(deletedEntity);
		} catch (Exception e) {
			return null;
		}
		return deletedEntity;
	}

	@Override
	public RoleEntity findOneById(Long id) {
		RoleEntity foundEntity = null;
		try {
			foundEntity = roleRepository.findOneById(id);
		} catch (Exception e) {
			return null;
		}
		return foundEntity;
	}

}