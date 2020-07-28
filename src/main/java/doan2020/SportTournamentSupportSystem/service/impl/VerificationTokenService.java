package doan2020.SportTournamentSupportSystem.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import doan2020.SportTournamentSupportSystem.dtIn.VerifyAuthenticationDtIn;
import doan2020.SportTournamentSupportSystem.entity.UserEntity;
import doan2020.SportTournamentSupportSystem.entity.VerificationToken;
import doan2020.SportTournamentSupportSystem.repository.UserRepository;
import doan2020.SportTournamentSupportSystem.repository.VerificationTokenRepository;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.IVerificationTokenService;

@Service
public class VerificationTokenService implements IVerificationTokenService{
    
	@Autowired
	private UserRepository userRepository;
	@Autowired
    private VerificationTokenRepository verificationTokenRepository;
	@Autowired
    private SendingMailService sendingMailService;
	@Autowired
    public VerificationTokenService(UserRepository userRepository, VerificationTokenRepository verificationTokenRepository, SendingMailService sendingMailService){
        this.userRepository = userRepository;
        this.verificationTokenRepository = verificationTokenRepository;
        this.sendingMailService = sendingMailService;
    }

    public boolean createVerification(String email, String UserName){
        List<UserEntity> users = userRepository.findByEmailAndUsername(email, UserName);
        UserEntity user;
        if (users.isEmpty()) {
            return false;
        } else {
            user = users.get(0);
        }

        List<VerificationToken> verificationTokens = verificationTokenRepository.findByUserEntityEmailAndUserEntity(email, user);
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

    public Response verifyEmail(VerifyAuthenticationDtIn verifyAuthenticationDtIn){
    	Response results = new Response();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> error = new HashMap<String, Object>();
		String token = verifyAuthenticationDtIn.getCode();
        List<VerificationToken> verificationTokens = verificationTokenRepository.findByToken(token);
        if (verificationTokens.isEmpty()) {
        	error.put("messageCode", "002");
			error.put("message", "Invalid token.");
			results.setError(error);
			
	        return results;
        }

        VerificationToken verificationToken = verificationTokens.get(0);
        if (verificationToken.getExpiredDateTime().isBefore(LocalDateTime.now())) {
        	error.put("messageCode", "002");
			error.put("message", "Expired token.");
			results.setError(error);
			
	        return results;
        }

        verificationToken.setConfirmedDateTime(LocalDateTime.now());
        verificationToken.setStatus(VerificationToken.STATUS_VERIFIED);
        verificationToken.getUser().setActive(true);
        verificationTokenRepository.save(verificationToken);
        error.put("messageCode", "001");
		error.put("message", "You have successfully verified your email address.");
		results.setError(error);
		
        return results;
    }

}
