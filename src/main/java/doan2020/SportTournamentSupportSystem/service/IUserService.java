package doan2020.SportTournamentSupportSystem.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import doan2020.SportTournamentSupportSystem.entity.UserEntity;

public interface IUserService {
	
	public UserEntity findOneById(Long id);
	
	public List<UserEntity> findAll(Pageable pageable);
	
	public void addNewUsers(UserEntity userEntity);
	
	public void editUser(UserEntity userEntity);
	
	public List<UserEntity> findAll();
	
	public void deleteUser(UserEntity userEntity);
}
