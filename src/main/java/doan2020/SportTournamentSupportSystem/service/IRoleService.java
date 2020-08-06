
package doan2020.SportTournamentSupportSystem.service;

import doan2020.SportTournamentSupportSystem.entity.RoleEntity;

public interface IRoleService {
	
	public RoleEntity findOneById(Long id);
	
//	public Collection<RoleEntity> findAll(Pageable pageable);
	
	public RoleEntity create(RoleEntity roleEntity);
	
	public RoleEntity update(Long id, RoleEntity newEntity);
	
//	public Collection<RoleEntity> findAll();
	
	public RoleEntity delete(Long id);
	
	public RoleEntity findOneByName(String name);
}