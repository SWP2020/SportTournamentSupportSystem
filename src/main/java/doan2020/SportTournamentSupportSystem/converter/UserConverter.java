package doan2020.SportTournamentSupportSystem.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import doan2020.SportTournamentSupportSystem.dtIn.UserDtIn;
import doan2020.SportTournamentSupportSystem.dtOut.UserDtOut;
import doan2020.SportTournamentSupportSystem.entity.UserTestEntity;

@Component
public class UserConverter {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public UserTestEntity toEntity(UserDtIn dto) {
		UserTestEntity entity = new UserTestEntity();
		if (dto.getPassword() != null) {
			entity.setPassword(passwordEncoder.encode(dto.getPassword()));
		}
		
		if (dto.getUsername() != null) {
			entity.setUserName(dto.getUsername());
		}
		entity.setEmail(dto.getEmail());
		return entity;
	}

	public UserDtOut toDTO(UserTestEntity entity) {
		UserDtOut dto = new UserDtOut();
		if (entity.getUserID() != null) {
			dto.setUserID(entity.getUserID());
		}
		dto.setUsername(entity.getUserName());
		dto.setActive(entity.getActive());
		dto.setCreatedate(entity.getCreateDate());
		dto.setDob(entity.getDOB());
		dto.setEmail(entity.getEmail());
		dto.setFirstname(entity.getFirstName());
		dto.setGender(entity.isGender());
		dto.setImageprofile(entity.getImageProfile());
		dto.setLastname(dto.getLastname());
		dto.setPassword(entity.getPassword());
		return dto;
	}
//
//	public UserEntity toEntity(UserDTO dto, UserEntity entity) {
//		if (dto.getUserName() != null) {
//			entity.setUserName(dto.getUserName());
//		}
//		entity.setFirstName(dto.getFirstName());
//		entity.setLastName(dto.getLastName());
//		entity.setEmail(dto.getEmail());
//		entity.setDOB(dto.getDOB());
//		entity.setGender(dto.getGender());
//		if (dto.getPassword() != null) {
//			entity.setPassword(dto.getPassword());
//		}
//		entity.setCreateDate(dto.getCreateDate());
//		if ((dto.isActive() == true && entity.getActive() == false)) {
//			entity.setActive(true);
//		}
//		entity.setImageProfile(dto.getImageProfile());
//		return entity;
//	}
}
