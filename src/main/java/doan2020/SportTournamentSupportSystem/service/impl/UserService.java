package doan2020.SportTournamentSupportSystem.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import doan2020.SportTournamentSupportSystem.converter.UserConverter;
import doan2020.SportTournamentSupportSystem.dtIn.LoginDtIn;
import doan2020.SportTournamentSupportSystem.dtIn.UserDtIn;
import doan2020.SportTournamentSupportSystem.dtOut.UserDtOut;
import doan2020.SportTournamentSupportSystem.entity.RoleTestEntity;
import doan2020.SportTournamentSupportSystem.entity.UserTestEntity;
import doan2020.SportTournamentSupportSystem.repository.RoleRepository;
import doan2020.SportTournamentSupportSystem.repository.UserRepository;
import doan2020.SportTournamentSupportSystem.service.IUserService;

@Service
public class UserService implements IUserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserConverter userConverter;
	
	@Autowired	
	private PasswordEncoder passwordEncoder;

	
	@Override
	public boolean checkLogin(LoginDtIn user) {
		List<UserTestEntity> listUser = userRepository.findAll(); 
		for (UserTestEntity userExist : listUser) {
			boolean checkPW = passwordEncoder.matches(user.getPassword(), userExist.getPassword());  ;
			System.out.println(user.getPassword());
			System.out.println(userExist.getPassword());
			System.out.println(checkPW);
			if (StringUtils.equals(user.getUsername(), userExist.getUserName())
					&& checkPW) {
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean add(UserDtIn user) {
		List<UserTestEntity> listUser = userRepository.findAll(); 
		for (UserTestEntity userExist : listUser) {
			if (StringUtils.equals(user.getUsername(), userExist.getUserName())) {
				return false;
			}
		}
		UserTestEntity userEntity = userConverter.toEntity(user);
		RoleTestEntity roleEntity = roleRepository.findOneByRolename("ROLE_ADMIN");
		if(roleEntity != null)
		userEntity.setRole(roleEntity);
		userEntity = userRepository.save(userEntity);
		return true;
	}

	@Override
	public List<UserDtOut> findAll(Pageable pageable) {
		List<UserDtOut> results = new ArrayList<>();
		List<UserTestEntity> entities = userRepository.findAll(pageable).getContent();
		for (UserTestEntity item: entities) {
			UserDtOut userDtOut = userConverter.toDTO(item);
			results.add(userDtOut);
		}
		return results;
	}

	
	
}
