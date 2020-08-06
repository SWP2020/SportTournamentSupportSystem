package doan2020.SportTournamentSupportSystem.converter;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

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
			entity.setUsername(dto.getUsername());
			
			String userPassword = passwordEncoder.encode(dto.getUsername());
			entity.setPassword(userPassword);
			
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

	public UserDTO toDTO(UserEntity entity) {
		System.out.println("UserConverter: toDTO: start");
		UserDTO dto = new UserDTO();
		try {
			dto.setId(entity.getId());
			dto.setUsername(entity.getUsername());
//			dto.setPassword(entity.getPassword());
			dto.setFirstName(entity.getFirstName());
			dto.setLastName(entity.getLastName());
			dto.setAddress(entity.getAddress());
			dto.setPhoneNumber(entity.getPhoneNumber());
			dto.setGender(entity.getGender());
			
			String userDob = validator.formatDateToString(entity.getDob());
			dto.setDob(userDob);
			
			dto.setEmail(entity.getEmail());
			dto.setAvatar(entity.getAvatar());
			dto.setBackground(entity.getBackground());
			
			RoleEntity userRole = entity.getRole();
			Long userRoleId = userRole.getId();
			dto.setRoleId(userRoleId);
			
			dto.setStatus(entity.getStatus());
			dto.setUrl(entity.getUrl());
			System.out.println("UserConverter: toEntity: no exception");
		} catch (Exception e) {
			System.out.println("UserConverter: toEntity: has exception");
			return null;
		}
		System.out.println("UserConverter: toDTO: finish");
		return dto;
	}
}