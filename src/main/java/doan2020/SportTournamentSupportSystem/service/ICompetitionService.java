package doan2020.SportTournamentSupportSystem.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import doan2020.SportTournamentSupportSystem.entity.CompetitionEntity;
import doan2020.SportTournamentSupportSystem.entity.TournamentEntity;

public interface ICompetitionService {
	public CompetitionEntity findOneById(Long id);
	
	public TournamentEntity findOneByTournamentID(Long id);
	
	public void addCompetition(CompetitionEntity competitionEntity);
	
	public void editCompetition(CompetitionEntity competitionEntity);
	
	public List<CompetitionEntity> findAllCompetition();
	
	public void deleteCompetition(CompetitionEntity competitionEntity);
	
	public List<CompetitionEntity> findByTournamentId(Pageable pageable, Long tournamentId);
	
	public List<CompetitionEntity> findByTournamentId(Long tournamentId);
}
