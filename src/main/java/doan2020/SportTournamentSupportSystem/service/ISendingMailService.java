package doan2020.SportTournamentSupportSystem.service;

public interface ISendingMailService {
	public boolean sendVerificationMail(String toEmail, String verificationCode);
	
	public boolean sendMail(String toEmail, String subject, String body);
}
