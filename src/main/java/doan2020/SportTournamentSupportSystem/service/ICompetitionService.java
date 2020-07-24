package doan2020.SportTournamentSupportSystem.service;

import java.util.List;

import doan2020.SportTournamentSupportSystem.entity.CompetitionEntity;

public interface ICompetitionService {
	public CompetitionEntity findOneByID(Long id);
	
	public void addCompetition(CompetitionEntity competitionEntity);
	
	public void editCompetition(CompetitionEntity competitionEntity);
	
	public List<CompetitionEntity> findAllCompetition();
	
	public void deleteCompetition(CompetitionEntity competitionEntity);
}
