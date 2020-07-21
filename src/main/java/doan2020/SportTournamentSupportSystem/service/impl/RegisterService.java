package doan2020.SportTournamentSupportSystem.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import doan2020.SportTournamentSupportSystem.converter.UserConverter;
import doan2020.SportTournamentSupportSystem.dtIn.RegisterDtIn;
import doan2020.SportTournamentSupportSystem.entity.RoleEntity;
import doan2020.SportTournamentSupportSystem.entity.UserEntity;
import doan2020.SportTournamentSupportSystem.repository.RoleRepository;
import doan2020.SportTournamentSupportSystem.repository.UserRepository;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.IRegisterService;

@Service
public class RegisterService implements IRegisterService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserConverter userConverter;
	
	@Override
	@Transactional
	public Response addNewUsers(RegisterDtIn user) {
		Response results = new Response();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		
		List<UserEntity> listUser = userRepository.findAll(); 
		for (UserEntity userExist : listUser) {
			if (StringUtils.equals(user.getUsername(), userExist.getUsername())) {
				error.put("messageCode", "003");
			    error.put("message", "User is Exists");
			    results.setError(error);
			    return results;
			}
		}
		UserEntity userEntity = userConverter.toEntity(user);
		RoleEntity roleEntity = roleRepository.findOneByName("ROLE_USER");
		if(roleEntity != null)
		userEntity.setRole(roleEntity);
		userEntity.setActive(false);
		userEntity = userRepository.save(userEntity);
		error.put("messageCode", "001");
	    error.put("message", "Add users successfully");
	    
	    results.setError(error);
		return results;
	}

}
