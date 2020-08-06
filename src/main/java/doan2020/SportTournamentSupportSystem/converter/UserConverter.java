package doan2020.SportTournamentSupportSystem.converter;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import doan2020.SportTournamentSupportSystem.dtIn.EditProfileDtIn;
import doan2020.SportTournamentSupportSystem.dtOut.UserDtOut;
import doan2020.SportTournamentSupportSystem.dto.UserDTO;
import doan2020.SportTournamentSupportSystem.entity.RoleEntity;
import doan2020.SportTournamentSupportSystem.entity.UserEntity;
import doan2020.SportTournamentSupportSystem.service.IRoleService;
import doan2020.SportTournamentSupportSystem.validator.Validator;

@Component
public class UserConverter {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private Validator validator;
	
	@Autowired
	private IRoleService roleService;
	
	

	public UserEntity toEntity(UserDTO dto) {
		System.out.println("UserConverter: toEntity: start");
		UserEntity entity = new UserEntity();
		try {
			
			String userPassword = passwordEncoder.encode(dto.getUsername());
			entity.setUsername(userPassword);
			
			entity.setPassword(dto.getPassword());
			entity.setFirstName(dto.getFirstName());
			entity.setLastName(dto.getLastName());
			entity.setAddress(dto.getAddress());
			entity.setPhoneNumber(dto.getPhoneNumber());
			entity.setGender(dto.getGender());
			
			Date userDob = validator.formatStringToDate(dto.getDob());
			entity.setDob(userDob);
			
			entity.setEmail(dto.getEmail());
			entity.setAvatar(dto.getAvatar());
			entity.setBackground(dto.getBackground());
			
			Long userRoleId = dto.getRoleId();
			RoleEntity userRole = roleService.findOneById(userRoleId);
			entity.setRole(userRole);
			
			entity.setStatus(dto.getStatus());
			entity.setUrl(dto.getUrl());
			System.out.println("UserConverter: toEntity: no exception");
		} catch (Exception e) {
			System.out.println("UserConverter: toEntity: has exception");
			return null;
		}
		System.out.println("UserConverter: toEntity: finish");
		return entity;
	}

	public UserDtOut toDTO(UserEntity entity) {
		System.out.println("UserConverter: toDTO: start");
		UserDtOut dto = new UserDtOut();
		try {
			
			System.out.println("UserConverter: toEntity: no exception");
		} catch (Exception e) {
			System.out.println("UserConverter: toEntity: has exception");
			return null;
		}
		System.out.println("UserConverter: toDTO: finish");
		return dto;
	}
}