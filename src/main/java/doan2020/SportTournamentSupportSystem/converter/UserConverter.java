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
			dto.setId(entity.getId());
		}
		dto.setUsername(entity.getUsername());
		dto.setStatus(entity.getStatus());
		
		dto.setAddress(entity.getAddress());
		dto.setAvatar(entity.getAvatar());
		dto.setBackground(entity.getBackground());
		String dob = validator.formatDateToString(entity.getDob());
		dto.setDob(dob);
		dto.setEmail(entity.getEmail());
		dto.setFirstName(entity.getFirstName());
		dto.setGender(entity.getGender());
		dto.setLastName(entity.getLastName());
		dto.setPassword(entity.getPassword());
		dto.setPhoneNumber(entity.getPhoneNumber());
		dto.setStatus(entity.getStatus());
		dto.setUrl(entity.getUrl());
		dto.setUsername(entity.getUsername());
		dto.setAge(validator.ConvertDobToAge(dob));
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
		
		if (dto.getPassword() != null) {
			entity.setPassword(passwordEncoder.encode(dto.getPassword()));
		}
		
		return entity;
	}
}
