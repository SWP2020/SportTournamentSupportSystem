package doan2020.SportTournamentSupportSystem.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import doan2020.SportTournamentSupportSystem.entity.CompetitionEntity;
import doan2020.SportTournamentSupportSystem.entity.TournamentEntity;
import doan2020.SportTournamentSupportSystem.repository.CompetitionRepository;
import doan2020.SportTournamentSupportSystem.repository.TournamentRepository;
import doan2020.SportTournamentSupportSystem.service.ICompetitionService;

@Service
public class CompetitionService implements ICompetitionService {

	@Autowired
	private CompetitionRepository competitionRepository;

	@Autowired
	private TournamentRepository tournamentRepository;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void addCompetition(CompetitionEntity competitionEntity) {
		competitionRepository.save(competitionEntity);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void editCompetition(CompetitionEntity competitionEntity) {
		competitionRepository.save(competitionEntity);

	}

	@Override
	public List<CompetitionEntity> findAllCompetition() {
		List<CompetitionEntity> competitionEntities = competitionRepository.findAll();
		return competitionEntities;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteCompetition(CompetitionEntity competitionEntity) {
		// TODO Auto-generated method stub
		competitionRepository.delete(competitionEntity);

	}

	@Override
	public CompetitionEntity findOneById(Long id) {
		CompetitionEntity competitionEntity = null;
		if (id != null) {
			competitionEntity = competitionRepository.findOneById(id);
		}
		return competitionEntity;
	}

	@Override
	public TournamentEntity findOneByTournamentID(Long id) {
		TournamentEntity entity = new TournamentEntity();
		entity = tournamentRepository.getOne(id);
		return entity;
	}

	@Override
	public List<CompetitionEntity> findByTournamentId(Pageable pageable, Long tournamentId) {
		List<CompetitionEntity> competitionEntities = new ArrayList<CompetitionEntity>();

		competitionEntities = competitionRepository.findByTournamentId(pageable, tournamentId).getContent();
		return competitionEntities;
	}

	@Override
	public List<CompetitionEntity> findByTournamentId(Long tournamentId) {
		List<CompetitionEntity> competitionEntities = new ArrayList<CompetitionEntity>();

		competitionEntities = competitionRepository.findByTournamentId(tournamentId);
		return competitionEntities;
	}

}
