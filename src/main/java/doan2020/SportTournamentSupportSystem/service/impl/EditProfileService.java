package doan2020.SportTournamentSupportSystem.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import doan2020.SportTournamentSupportSystem.converter.UserConverter;
import doan2020.SportTournamentSupportSystem.dtIn.EditProfileDtIn;
import doan2020.SportTournamentSupportSystem.entity.UserEntity;
import doan2020.SportTournamentSupportSystem.repository.UserRepository;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.IEditProfileService;

@Service
public class EditProfileService implements IEditProfileService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserConverter userConverter;

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

}
