package doan2020.SportTournamentSupportSystem.service;

import java.util.Collection;

import org.springframework.data.domain.Pageable;

import doan2020.SportTournamentSupportSystem.dtIn.EditProfileDtIn;
import doan2020.SportTournamentSupportSystem.dtIn.RegisterDtIn;
import doan2020.SportTournamentSupportSystem.entity.TournamentEntity;
import doan2020.SportTournamentSupportSystem.response.Response;

public interface ITournamentService {
	public Collection<TournamentEntity> findAll(Pageable pageable);

	public TournamentEntity findByName(String name);
	public TournamentEntity findById(Long id);
	
	public void addOne(TournamentEntity tournament);
	public void addMany(Collection<TournamentEntity> tournaments);
	
	public void update(TournamentEntity tournament);
}
