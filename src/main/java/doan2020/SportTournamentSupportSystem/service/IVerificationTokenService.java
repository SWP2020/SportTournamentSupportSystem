package doan2020.SportTournamentSupportSystem.service;

import doan2020.SportTournamentSupportSystem.dtIn.VerifyAuthenticationDtIn;
import doan2020.SportTournamentSupportSystem.response.Response;

public interface IVerificationTokenService {
	public void createVerification(String email, String UserName);
	
	public Response verifyEmail(VerifyAuthenticationDtIn token);
}
