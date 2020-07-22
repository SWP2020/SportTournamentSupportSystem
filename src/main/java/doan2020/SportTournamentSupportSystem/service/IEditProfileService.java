package doan2020.SportTournamentSupportSystem.service;

import doan2020.SportTournamentSupportSystem.dtIn.EditProfileDtIn;
import doan2020.SportTournamentSupportSystem.response.Response;

public interface IEditProfileService {
	
	public Response editProfile(EditProfileDtIn editProfileDtIn);

}
