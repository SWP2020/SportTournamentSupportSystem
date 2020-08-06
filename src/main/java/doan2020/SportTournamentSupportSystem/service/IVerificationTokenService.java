package doan2020.SportTournamentSupportSystem.service;

import java.util.List;

import doan2020.SportTournamentSupportSystem.entity.UserEntity;
import doan2020.SportTournamentSupportSystem.entity.VerificationToken;

public interface IVerificationTokenService {
	public boolean createVerification(String email, String UserName);
	
	public VerificationToken verifyEmail(VerificationToken verificationToken);
	
	List<VerificationToken> findByUserEntityEmailAndUserEntity(String email,UserEntity userEntity);

	List<VerificationToken> findByToken(String token);
}
