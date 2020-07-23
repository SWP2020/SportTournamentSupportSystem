package doan2020.SportTournamentSupportSystem.service;

import org.springframework.data.domain.Pageable;

import doan2020.SportTournamentSupportSystem.dtIn.EditProfileDtIn;
import doan2020.SportTournamentSupportSystem.dtIn.RegisterDtIn;
import doan2020.SportTournamentSupportSystem.response.Response;

public interface IUserService {
	Response findAll(Pageable pageable);
	
	Response addNewUsers(RegisterDtIn dtIn);
	
	public Response editProfile(EditProfileDtIn editProfileDtIn);
	
	public Response getUserInfor(Long id);
}
