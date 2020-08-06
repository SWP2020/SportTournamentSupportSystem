
package doan2020.SportTournamentSupportSystem.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import doan2020.SportTournamentSupportSystem.entity.UserEntity;
import doan2020.SportTournamentSupportSystem.repository.UserRepository;
import doan2020.SportTournamentSupportSystem.service.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserEntity create(UserEntity userEntity) {
		UserEntity newEntity = null;
		try {
			newEntity = userRepository.save(userEntity);
		} catch (Exception e) {
			return null;
		}
		return newEntity;
	}

	@Override
	public UserEntity update(Long id, UserEntity newEntity) {
		UserEntity updatedEntity = null;
		try {
			updatedEntity = userRepository.findOneById(id);

			updatedEntity.setUsername(newEntity.getUsername());
			updatedEntity.setPassword(newEntity.getPassword());
			updatedEntity.setFirstName(newEntity.getFirstName());
			updatedEntity.setLastName(newEntity.getLastName());
			updatedEntity.setAddress(newEntity.getAddress());
			updatedEntity.setPhoneNumber(newEntity.getPhoneNumber());
			updatedEntity.setGender(newEntity.getGender());
			updatedEntity.setDob(newEntity.getDob());
			updatedEntity.setEmail(newEntity.getEmail());
			updatedEntity.setAvatar(newEntity.getAvatar());
			updatedEntity.setBackground(newEntity.getBackground());
			updatedEntity.setRole(newEntity.getRole());
			updatedEntity.setCreatedBy(newEntity.getCreatedBy());
			updatedEntity.setCreatedDate(newEntity.getCreatedDate());
			updatedEntity.setModifiedBy(newEntity.getModifiedBy());
			updatedEntity.setModifiedDate(newEntity.getModifiedDate());
			updatedEntity.setStatus(newEntity.getStatus());
			updatedEntity.setUrl(newEntity.getUrl());
			updatedEntity = userRepository.save(updatedEntity);
		} catch (Exception e) {
			return null;
		}
        
		return updatedEntity;
	}

	@Override
	public UserEntity delete(Long id) {
		UserEntity deletedEntity = null;
		try {
			deletedEntity = userRepository.findOneById(id);
			deletedEntity.setStatus("deleted");
			deletedEntity = userRepository.save(deletedEntity);
		} catch (Exception e) {
			return null;
		}
		return deletedEntity;
	}

	@Override
	public UserEntity findOneById(Long id) {
		UserEntity foundEntity = null;
		try {
			foundEntity = userRepository.findOneById(id);
		} catch (Exception e) {
			return null;
		}
		return foundEntity;
	}

	@Override
	public UserEntity findByUsername(String username) {
		UserEntity foundEntity = null;
		try {
			foundEntity = userRepository.findByUsername(username);
		} catch (Exception e) {
			return null;
		}
		return foundEntity;
	}

	@Override
	public Collection<UserEntity> findAll(Pageable pageable) {
		Collection<UserEntity> userEntities = null;
		try {
			userEntities = userRepository.findAll(pageable).getContent();
		} catch (Exception e) {
			return null;
		}
		return userEntities;
	}

	@Override
	public Collection<UserEntity> findAll() {
		Collection<UserEntity> userEntities = null;
		try {
			userEntities = userRepository.findAll();
		} catch (Exception e) {
			return null;
		}
		return userEntities;
	}

	@Override
	public UserEntity findByEmail(String email) {
		UserEntity foundEntity = null;
		try {
			foundEntity = userRepository.findByEmail(email);
		} catch (Exception e) {
			return null;
		}
		return foundEntity;
	}

}