package doan2020.SportTournamentSupportSystem.service;

import doan2020.SportTournamentSupportSystem.dtIn.RegisterDtIn;
import doan2020.SportTournamentSupportSystem.response.Response;

public interface IRegisterService {
Response addNewUsers(RegisterDtIn dtIn);
}
