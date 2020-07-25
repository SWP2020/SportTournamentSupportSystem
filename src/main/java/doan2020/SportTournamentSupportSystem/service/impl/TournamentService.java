package doan2020.SportTournamentSupportSystem.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import doan2020.SportTournamentSupportSystem.converter.UserConverter;
import doan2020.SportTournamentSupportSystem.dtIn.EditProfileDtIn;
import doan2020.SportTournamentSupportSystem.dtIn.RegisterDtIn;
import doan2020.SportTournamentSupportSystem.dtOut.UserDtOut;
import doan2020.SportTournamentSupportSystem.dtOut.ViewUserInforOverviewDtOut;
import doan2020.SportTournamentSupportSystem.entity.RoleEntity;
import doan2020.SportTournamentSupportSystem.entity.TournamentEntity;
import doan2020.SportTournamentSupportSystem.entity.UserEntity;
import doan2020.SportTournamentSupportSystem.repository.RoleRepository;
import doan2020.SportTournamentSupportSystem.repository.TournamentRepository;
import doan2020.SportTournamentSupportSystem.repository.UserRepository;
import doan2020.SportTournamentSupportSystem.response.Response;
import doan2020.SportTournamentSupportSystem.service.ITournamentService;
import doan2020.SportTournamentSupportSystem.service.IUserService;

@Service
public class TournamentService implements ITournamentService {

	@Autowired
	private TournamentRepository tournamentRepository;

	@Override
	public void addOne(TournamentEntity tournament) {
		
		tournamentRepository.save(tournament);

	}

	@Override
	public void addMany(Collection<TournamentEntity> tournaments) {
		for (TournamentEntity tournament : tournaments)
			tournamentRepository.save(tournament);

	}

	@Override
	public TournamentEntity findById(Long id) {
		TournamentEntity res = null;
		try {
			res = tournamentRepository.findOneById(id);
		} catch (Exception e) {
			return null;
		}
		
		return res;
	}

	@Override
	public TournamentEntity findByName(String name) {
		TournamentEntity res = null;
		try {
			res = tournamentRepository.findByShortName(name);
		} catch (Exception e) {
			return null;
		}
		
		return res;
	}

	@Override
	public Collection<TournamentEntity> findAll(Pageable pageable) {
		
		return tournamentRepository.findAll();
	}

	@Override
	public void update(TournamentEntity tournament) {
		
		tournamentRepository.save(tournament);

	}
}
