package doan2020.SportTournamentSupportSystem.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import doan2020.SportTournamentSupportSystem.dtIn.LoginDtIn;
import doan2020.SportTournamentSupportSystem.dtIn.UserDtIn;
import doan2020.SportTournamentSupportSystem.dtOut.UserDtOut;

public interface IUserService {
	boolean checkLogin(LoginDtIn user);
	boolean add(UserDtIn user);
	List<UserDtOut> findAll(Pageable pageable);
}
