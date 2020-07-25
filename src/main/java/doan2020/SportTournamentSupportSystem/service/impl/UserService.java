package doan2020.SportTournamentSupportSystem.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import doan2020.SportTournamentSupportSystem.entity.UserEntity;
import doan2020.SportTournamentSupportSystem.repository.UserRepository;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public UserEntity findOneById(Long id) {
		UserEntity oldUserEntity = null;
		if (id != null) {
			System.out.println(id);
			oldUserEntity = userRepository.findOneById(id);
		}
		return oldUserEntity;
	}
	
//	@Override
//	@Transactional
//	public UserEntity findOneById(Long id) {
//		System.out.println("Find one");
//		List<UserEntity> x = userRepository.findAll();
//		for (UserEntity e: x) {
//			System.out.println(e.getUsername());
//		}
//		return null;
//	}

	@Override
	public List<UserEntity> findAll(Pageable pageable) {
		List<UserEntity> entities = userRepository.findAll(pageable).getContent();
		return entities;
	}

	@Override
	@Transactional
	public void addNewUsers(UserEntity userEntity) {
		Response results = new Response();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();

		
		userEntity = userRepository.save(userEntity);
		error.put("messageCode", "001");
		error.put("message", "Add users successfully");

		results.setError(error);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void editUser(UserEntity userEntity) {
		userRepository.save(userEntity);
	}


	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteUser(UserEntity userEntity) {
		userRepository.delete(userEntity);
	}

	@Override
	@Transactional
	public List<UserEntity> findAll() {
		List<UserEntity> userEntities = userRepository.findAll();
		return userEntities;
	}

}
