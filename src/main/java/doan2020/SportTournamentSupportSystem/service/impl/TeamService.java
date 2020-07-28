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

import doan2020.SportTournamentSupportSystem.entity.TeamEntity;
import doan2020.SportTournamentSupportSystem.repository.TeamRepository;
import doan2020.SportTournamentSupportSystem.service.ITeamService;


@Service
public class TeamService implements ITeamService {

	@Autowired
	private TeamRepository teamRepository;

	@Override
	public void addOne(TeamEntity team) {
		
		teamRepository.save(team);

	}

	@Override
	public void addMany(Collection<TeamEntity> teams) {
		for (TeamEntity team : teams)
			teamRepository.save(team);

	}

	@Override
	public TeamEntity findById(Long id) {
		TeamEntity res = null;
		try {
			res = teamRepository.findOneById(id);
		} catch (Exception e) {
			return null;
		}
		
		return res;
	}

	@Override
	public TeamEntity findByName(String name) {
		TeamEntity res = null;
		try {
			res = teamRepository.findByShortName(name);
		} catch (Exception e) {
			return null;
		}
		
		return res;
	}

	@Override
	public Collection<TeamEntity> findAll(Pageable pageable) {
		
		return teamRepository.findAll(pageable).getContent();
	}

	@Override
	public TeamEntity update(Long id, TeamEntity newData) {
		
		TeamEntity old = teamRepository.findOneById(id);
		old.setFullName(newData.getFullName());
		old.setShortName(newData.getShortName());
		old.setDescription(newData.getDescription());
		old.setCreator(newData.getCreator());
		
		old.setStatus(newData.getStatus());
		old = teamRepository.save(old);
		return old;
	}
}
