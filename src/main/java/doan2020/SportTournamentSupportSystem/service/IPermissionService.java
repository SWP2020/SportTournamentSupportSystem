
package doan2020.SportTournamentSupportSystem.service;

import java.util.Collection;
import org.springframework.data.domain.Pageable;
import doan2020.SportTournamentSupportSystem.entity.PermissionEntity;

public interface IPermissionService {
	
	public PermissionEntity findOneById(Long id);
	
//	public Collection<PermissionEntity> findAll(Pageable pageable);
	
	public PermissionEntity create(PermissionEntity permissionEntity);
	
	public PermissionEntity update(Long id, PermissionEntity newEntity);
	
//	public Collection<PermissionEntity> findAll();
	
	public PermissionEntity delete(Long id);
}