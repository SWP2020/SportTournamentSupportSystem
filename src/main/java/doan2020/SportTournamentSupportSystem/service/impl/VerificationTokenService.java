
package doan2020.SportTournamentSupportSystem.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import doan2020.SportTournamentSupportSystem.entity.VerificationTokenEntity;
import doan2020.SportTournamentSupportSystem.repository.VerificationTokenRepository;
import doan2020.SportTournamentSupportSystem.service.IVerificationTokenService;

@Service
public class VerificationTokenService implements IVerificationTokenService {

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

}