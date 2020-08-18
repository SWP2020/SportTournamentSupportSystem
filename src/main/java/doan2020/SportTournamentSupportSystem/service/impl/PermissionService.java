
package doan2020.SportTournamentSupportSystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import doan2020.SportTournamentSupportSystem.entity.PermissionEntity;
import doan2020.SportTournamentSupportSystem.repository.PermissionRepository;
import doan2020.SportTournamentSupportSystem.service.IPermissionService;

@Service
public class PermissionService implements IPermissionService {

	@Autowired
	private PermissionRepository permissionRepository;

	@Override
	public PermissionEntity create(PermissionEntity permissionEntity) {
		PermissionEntity newEntity = null;
		try {
			newEntity = permissionRepository.save(permissionEntity);
		} catch (Exception e) {
			return null;
		}
		return newEntity;
	}

	@Override
	public PermissionEntity update(Long id, PermissionEntity newEntity) {
		PermissionEntity updatedEntity = null;
		try {
			updatedEntity = permissionRepository.findOneById(id);

			updatedEntity.setName(newEntity.getName());
			updatedEntity.setDescription(newEntity.getDescription());
			updatedEntity.setCanEdit(newEntity.getCanEdit());
			updatedEntity.setCanDelete(newEntity.getCanDelete());
			updatedEntity.setCreatedBy(newEntity.getCreatedBy());
			updatedEntity.setCreatedDate(newEntity.getCreatedDate());
			updatedEntity.setModifiedBy(newEntity.getModifiedBy());
			updatedEntity.setModifiedDate(newEntity.getModifiedDate());
			updatedEntity.setStatus(newEntity.getStatus());
			updatedEntity.setUrl(newEntity.getUrl());
			updatedEntity = permissionRepository.save(updatedEntity);
		} catch (Exception e) {
			return null;
		}
        
		return updatedEntity;
	}

	@Override
	public PermissionEntity delete(Long id) {
		PermissionEntity deletedEntity = null;
		try {
			deletedEntity = permissionRepository.findOneById(id);
			deletedEntity.setStatus("deleted");
			deletedEntity = permissionRepository.save(deletedEntity);
		} catch (Exception e) {
			return null;
		}
		return deletedEntity;
	}

	@Override
	public PermissionEntity findOneById(Long id) {
		PermissionEntity foundEntity = null;
		try {
			foundEntity = permissionRepository.findOneById(id);
		} catch (Exception e) {
			return null;
		}
		return foundEntity;
	}

	@Override
	public PermissionEntity findOneByName(String name) {
		PermissionEntity foundEntity = null;
		try {
			foundEntity = permissionRepository.findOneByName(name);
		} catch (Exception e) {
			return null;
		}
		return foundEntity;
	}

}