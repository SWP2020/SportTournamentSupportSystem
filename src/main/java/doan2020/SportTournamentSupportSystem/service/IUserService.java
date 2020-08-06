
package doan2020.SportTournamentSupportSystem.service;

import java.util.Collection;

import org.springframework.data.domain.Pageable;

import doan2020.SportTournamentSupportSystem.entity.UserEntity;

public interface IUserService {
	
	public UserEntity findOneById(Long id);
	
	public Collection<UserEntity> findAll(Pageable pageable);
	
	public UserEntity create(UserEntity userEntity);
	
	public UserEntity update(Long id, UserEntity newEntity);
	
	public Collection<UserEntity> findAll();
	
	public UserEntity delete(Long id);
	
	public UserEntity findByUsername(String username);
}