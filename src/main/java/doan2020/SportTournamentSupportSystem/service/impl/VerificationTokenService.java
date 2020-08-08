
package doan2020.SportTournamentSupportSystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import doan2020.SportTournamentSupportSystem.entity.UserEntity;
import doan2020.SportTournamentSupportSystem.entity.VerificationTokenEntity;
import doan2020.SportTournamentSupportSystem.repository.UserRepository;
import doan2020.SportTournamentSupportSystem.repository.VerificationTokenRepository;
import doan2020.SportTournamentSupportSystem.service.IVerificationTokenService;

@Service
public class VerificationTokenService implements IVerificationTokenService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SendingMailService sendingMailService;

	@Autowired
	private VerificationTokenRepository verificationTokenRepository;

	@Override
	public VerificationTokenEntity create(VerificationTokenEntity verificationTokenEntity) {
		VerificationTokenEntity newEntity = null;
		try {
			newEntity = verificationTokenRepository.save(verificationTokenEntity);
		} catch (Exception e) {
			return null;
		}
		return newEntity;
	}

	@Override
	public VerificationTokenEntity update(Long id, VerificationTokenEntity newEntity) {
		VerificationTokenEntity updatedEntity = null;
		try {
			updatedEntity = verificationTokenRepository.findOneById(id);

			updatedEntity.setToken(newEntity.getToken());
			updatedEntity.setExpiredDateTime(newEntity.getExpiredDateTime());
			updatedEntity.setIssuedDateTime(newEntity.getIssuedDateTime());
			updatedEntity.setConfirmedDateTime(newEntity.getConfirmedDateTime());
			updatedEntity.setUser(newEntity.getUser());
			updatedEntity.setCreatedBy(newEntity.getCreatedBy());
			updatedEntity.setCreatedDate(newEntity.getCreatedDate());
			updatedEntity.setModifiedBy(newEntity.getModifiedBy());
			updatedEntity.setModifiedDate(newEntity.getModifiedDate());
			updatedEntity.setStatus(newEntity.getStatus());
			updatedEntity.setUrl(newEntity.getUrl());
			updatedEntity = verificationTokenRepository.save(updatedEntity);
		} catch (Exception e) {
			return null;
		}
        
		return updatedEntity;
	}

	@Override
	public VerificationTokenEntity delete(Long id) {
		VerificationTokenEntity deletedEntity = null;
		try {
			deletedEntity = verificationTokenRepository.findOneById(id);
			deletedEntity.setStatus("deleted");
			deletedEntity = verificationTokenRepository.save(deletedEntity);
		} catch (Exception e) {
			return null;
		}
		return deletedEntity;
	}

	@Override
	public VerificationTokenEntity findOneById(Long id) {
		VerificationTokenEntity foundEntity = null;
		try {
			foundEntity = verificationTokenRepository.findOneById(id);
		} catch (Exception e) {
			return null;
		}
		return foundEntity;
	}

	

	@Autowired
	public VerificationTokenService(UserRepository userRepository,
			VerificationTokenRepository verificationTokenRepository, SendingMailService sendingMailService) {
		this.userRepository = userRepository;
		this.verificationTokenRepository = verificationTokenRepository;
		this.sendingMailService = sendingMailService;
	}

	public boolean createVerification(String email, String UserName) {
		List<UserEntity> users = userRepository.findByEmailAndUsername(email, UserName);
		UserEntity user;
		if (users.isEmpty()) {
			return false;
		} else {
			user = users.get(0);
		}

		List<VerificationTokenEntity> verificationTokens = verificationTokenRepository
				.findByUserEntityEmailAndUserEntity(email, user);
		VerificationTokenEntity verificationToken;
		if (verificationTokens.isEmpty()) {
			verificationToken = new VerificationTokenEntity();
			verificationToken.setUser(user);
			verificationTokenRepository.save(verificationToken);
		} else {
			verificationToken = verificationTokens.get(0);
		}

		sendingMailService.sendVerificationMail(email, verificationToken.getToken());
		return true;
	}

	public VerificationTokenEntity verifyEmail(VerificationTokenEntity verificationToken) {
		VerificationTokenEntity token = new VerificationTokenEntity();
		try {
			token = verificationTokenRepository.save(verificationToken);
		} catch (Exception e) {
			return null;
		}
		return token;
	}

	public List<VerificationTokenEntity> findByUserEntityEmailAndUserEntity(String email, UserEntity userEntity) {
		List<VerificationTokenEntity> verificationTokens = null;
		try {
			verificationTokens = verificationTokenRepository.findByUserEntityEmailAndUserEntity(email, userEntity);
		} catch (Exception e) {
			return null;
		}
		return verificationTokens;
	}

	public List<VerificationTokenEntity> findByToken(String token) {
		List<VerificationTokenEntity> verificationTokens = null;
		try {
			verificationTokens = verificationTokenRepository.findByToken(token);
		} catch (Exception e) {
			return null;
		}
		return verificationTokens;
	}
	
}