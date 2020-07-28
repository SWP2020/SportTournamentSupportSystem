package doan2020.SportTournamentSupportSystem.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import doan2020.SportTournamentSupportSystem.dtIn.EditProfileDtIn;
import doan2020.SportTournamentSupportSystem.dtIn.RegisterDtIn;
import doan2020.SportTournamentSupportSystem.dtOut.UserDtOut;
import doan2020.SportTournamentSupportSystem.entity.UserEntity;
import doan2020.SportTournamentSupportSystem.validator.Validator;

@Component
public class UserConverter {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private Validator validator;
	
	

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
		dto.setCreatedate(validator.formatDate(entity.getCreateddate()));
		dto.setDob(validator.formatDate(entity.getDob()));
		dto.setEmail(entity.getEmail());
		dto.setFirstname(entity.getFirstname());
		dto.setGender(entity.getGender());
		dto.setImageprofile(entity.getAvatar());
		dto.setLastname(dto.getLastname());
		dto.setPassword(entity.getPassword());
		dto.setAge(validator.ConvertDobToAge(validator.formatDate(entity.getDob())));
		return dto;
	}

	public UserEntity toEntity(EditProfileDtIn dto, UserEntity entity) {
		if (dto.getAvartar() != null) {
			entity.setAvatar(dto.getAvartar());
		}
			entity.setGender(dto.isGender());
		if (dto.getAddress() != null) {
			entity.setAddress(dto.getAddress());
		}
		if (dto.getBackground() != null) {
			entity.setBackground(dto.getBackground());
		}
		if (dto.getDob() != null) {
			entity.setDob(dto.getDob());
		}
		
		if (dto.getEmail() != null) {
			entity.setEmail(dto.getEmail());
		}
		
		return entity;
	}
}
