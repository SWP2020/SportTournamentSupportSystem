package doan2020.SportTournamentSupportSystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import doan2020.SportTournamentSupportSystem.entity.UserEntity;
import doan2020.SportTournamentSupportSystem.entity.VerificationToken;
import doan2020.SportTournamentSupportSystem.repository.UserRepository;
import doan2020.SportTournamentSupportSystem.repository.VerificationTokenRepository;
import doan2020.SportTournamentSupportSystem.service.IVerificationTokenService;

@Service
public class VerificationTokenService implements IVerificationTokenService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private VerificationTokenRepository verificationTokenRepository;
	@Autowired
	private SendingMailService sendingMailService;

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

		List<VerificationToken> verificationTokens = verificationTokenRepository
				.findByUserEntityEmailAndUserEntity(email, user);
		VerificationToken verificationToken;
		if (verificationTokens.isEmpty()) {
			verificationToken = new VerificationToken();
			verificationToken.setUser(user);
			verificationTokenRepository.save(verificationToken);
		} else {
			verificationToken = verificationTokens.get(0);
		}

		sendingMailService.sendVerificationMail(email, verificationToken.getToken());
		return true;
	}

	public VerificationToken verifyEmail(VerificationToken verificationToken) {
		VerificationToken token = new VerificationToken();
		try {
			token = verificationTokenRepository.save(verificationToken);
		} catch (Exception e) {
			return null;
		}
		return token;
	}

	public List<VerificationToken> findByUserEntityEmailAndUserEntity(String email, UserEntity userEntity) {
		List<VerificationToken> verificationTokens = null;
		try {
			verificationTokens = verificationTokenRepository.findByUserEntityEmailAndUserEntity(email, userEntity);
		} catch (Exception e) {
			return null;
		}
		return verificationTokens;
	}

	public List<VerificationToken> findByToken(String token) {
		List<VerificationToken> verificationTokens = null;
		try {
			verificationTokens = verificationTokenRepository.findByToken(token);
		} catch (Exception e) {
			return null;
		}
		return verificationTokens;
	}

}
