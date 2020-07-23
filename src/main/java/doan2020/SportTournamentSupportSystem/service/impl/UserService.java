package doan2020.SportTournamentSupportSystem.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import doan2020.SportTournamentSupportSystem.converter.UserConverter;
import doan2020.SportTournamentSupportSystem.dtIn.EditProfileDtIn;
import doan2020.SportTournamentSupportSystem.dtIn.RegisterDtIn;
import doan2020.SportTournamentSupportSystem.dtOut.UserDtOut;
import doan2020.SportTournamentSupportSystem.dtOut.ViewUserInforOverviewDtOut;
import doan2020.SportTournamentSupportSystem.entity.RoleEntity;
import doan2020.SportTournamentSupportSystem.entity.UserEntity;
import doan2020.SportTournamentSupportSystem.repository.RoleRepository;
import doan2020.SportTournamentSupportSystem.repository.UserRepository;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.IUserService;

@Service
public class UserService implements IUserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserConverter userConverter;
	

	@Override
	public Response findAll(Pageable pageable) {
		Response results = new Response();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		List<UserDtOut> listUsers = new ArrayList<>();
		try {
		List<UserEntity> entities = userRepository.findAll(pageable).getContent();
		for (UserEntity item: entities) {
			UserDtOut userDtOut = userConverter.toDTO(item);
			listUsers.add(userDtOut);
			result.put("listUsers", listUsers);
		    error.put("messageCode", "001");
		    error.put("message", "get List Users successfully");
		}
		}catch (Exception e) {
		    error.put("messageCode", "002");
		    error.put("message", "ko get List Users");
		}
		
	    
	    results.setResult(result);
	    results.setError(error);
		
		
		return results;
	}


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


	@Override
	@Transactional
	public Response editProfile(EditProfileDtIn editProfileDtIn) {
		Response response = new Response();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		
		try {
			UserEntity userEntity = new UserEntity();
			if(editProfileDtIn.getUserID() != null) {
			UserEntity oldUserEntity = userRepository.findOneById(editProfileDtIn.getUserID());
			if(oldUserEntity != null) {
				userEntity = userConverter.toEntity(editProfileDtIn, oldUserEntity);
				userRepository.save(userEntity);				
				error.put("messageCode", "001");
				error.put("message", "Edit Profile User Successfull");
			}else {
				error.put("messageCode", "002");
				error.put("message", "Edit Profile User fail");
			}
			}else {
			error.put("messageCode", "002");
			error.put("message", "Edit Profile User fail");
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		response.setError(error);
		return response;
	}


	@Override
	@Transactional
	public Response getUserInfor(Long userID) {
		Response response = new Response();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		
		if(userID != null) {
			UserEntity userInfor = userRepository.findOneById(userID);
			
			if(userInfor != null) {
			
//			int numberOfTournament = userInfor.getTournaments().size();
//			int numberOfTeam = userInfor.getTeams().size();
		
			ViewUserInforOverviewDtOut inforDtOut = new ViewUserInforOverviewDtOut();
			inforDtOut.setUserID(userInfor.getId());
			inforDtOut.setUsername(userInfor.getUsername());
			inforDtOut.setAvartar(userInfor.getAvatar());
			inforDtOut.setCreatedate(userInfor.getCreateddate());
			inforDtOut.setDob(userInfor.getDob());
			inforDtOut.setEmail(userInfor.getEmail());
			inforDtOut.setFirstname(userInfor.getFirstname());
			inforDtOut.setLastname(userInfor.getLastname());
			inforDtOut.setGender(userInfor.getGender());
			
//			inforDtOut.setNumberOfTeam(numberOfTeam);
//			inforDtOut.setNumberOfTournament(numberOfTournament);
			
			result.put("ViewUserInforOverview", inforDtOut);
			error.put("messageCode", "001");
			error.put("message", "ViewUserInforOverview successfull");
			}
			error.put("messageCode", "002");
			error.put("message", "ViewUserInforOverview fail");
		}else {
			error.put("messageCode", "002");
			error.put("message", "ViewUserInforOverview fail");
		}
		response.setError(error);
		response.setResult(result);
		return response;
	}

	
	
	
	
}
