
package doan2020.SportTournamentSupportSystem.service;

import java.util.Collection;
import org.springframework.data.domain.Pageable;
import doan2020.SportTournamentSupportSystem.entity.VerificationTokenEntity;

public interface IVerificationTokenService {
	
	public VerificationTokenEntity findOneById(Long id);
	
//	public Collection<VerificationTokenEntity> findAll(Pageable pageable);
	
	public VerificationTokenEntity create(VerificationTokenEntity verificationTokenEntity);
	
	public VerificationTokenEntity update(Long id, VerificationTokenEntity newEntity);
	
//	public Collection<VerificationTokenEntity> findAll();
	
	public VerificationTokenEntity delete(Long id);
}