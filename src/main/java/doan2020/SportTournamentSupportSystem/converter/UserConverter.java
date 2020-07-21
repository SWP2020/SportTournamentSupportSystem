package doan2020.SportTournamentSupportSystem.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import doan2020.SportTournamentSupportSystem.dtIn.RegisterDtIn;
import doan2020.SportTournamentSupportSystem.dtOut.UserDtOut;
import doan2020.SportTournamentSupportSystem.entity.UserEntity;

@Component
public class UserConverter {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public UserEntity toEntity(RegisterDtIn dto) {
		UserEntity entity = new UserEntity();
		if (dto.getPassword() != null) {
			entity.setPassword(passwordEncoder.encode(dto.getPassword()));
		}
		
		if (dto.getUsername() != null) {
			entity.setUsername(dto.getUsername());
		}
		entity.setEmail(dto.getEmail());
		return entity;
	}

	public UserDtOut toDTO(UserEntity entity) {
		UserDtOut dto = new UserDtOut();
		if (entity.getId() != null) {
			dto.setUserID(entity.getId());
		}
		dto.setUsername(entity.getUsername());
		dto.setActive(entity.getActive());
		dto.setCreatedate(entity.getCreateddate());
		dto.setDob(entity.getDob());
		dto.setEmail(entity.getEmail());
		dto.setFirstname(entity.getFirstname());
		dto.setGender(entity.getGender());
		dto.setImageprofile(entity.getAvatar());
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
