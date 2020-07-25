package doan2020.SportTournamentSupportSystem.service;

import java.util.Collection;

import org.springframework.data.domain.Pageable;

import doan2020.SportTournamentSupportSystem.dtIn.EditProfileDtIn;
import doan2020.SportTournamentSupportSystem.dtIn.RegisterDtIn;
import doan2020.SportTournamentSupportSystem.entity.TeamEntity;
import doan2020.SportTournamentSupportSystem.entity.TournamentEntity;
import doan2020.SportTournamentSupportSystem.response.Response;

public interface ITeamService {
	public Collection<TeamEntity> findAll(Pageable pageable);

	public TeamEntity findByName(String name);
	public TeamEntity findById(Long id);
	
	public void addOne(TeamEntity tournament);
	public void addMany(Collection<TeamEntity> tournaments);
	
	public TeamEntity update(Long id, TeamEntity tournament);
}
