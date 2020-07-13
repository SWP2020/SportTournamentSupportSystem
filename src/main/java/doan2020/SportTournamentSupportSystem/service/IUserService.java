package doan2020.SportTournamentSupportSystem.service;

import org.springframework.data.domain.Pageable;

import doan2020.SportTournamentSupportSystem.response.Response;

public interface IUserService {
	Response findAll(Pageable pageable);
}
