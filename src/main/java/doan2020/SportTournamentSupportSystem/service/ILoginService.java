package doan2020.SportTournamentSupportSystem.service;

import doan2020.SportTournamentSupportSystem.dtIn.LoginDtIn;
import doan2020.SportTournamentSupportSystem.response.Response;

public interface ILoginService {
	
	Response checkLogin(LoginDtIn loginDtIn);

}
