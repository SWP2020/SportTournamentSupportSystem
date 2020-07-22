package doan2020.SportTournamentSupportSystem.service;

import doan2020.SportTournamentSupportSystem.dtIn.ViewUserInforDtIn;
import doan2020.SportTournamentSupportSystem.response.Response;

public interface IViewUserInforService {
    public Response getUserInforOverView(ViewUserInforDtIn viewUserInforDtIn);
}
