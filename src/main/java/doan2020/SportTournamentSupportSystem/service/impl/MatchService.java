package doan2020.SportTournamentSupportSystem.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import doan2020.SportTournamentSupportSystem.entity.MatchEntity;
import doan2020.SportTournamentSupportSystem.repository.MatchRepository;
import doan2020.SportTournamentSupportSystem.service.IMatchService;


@Service
public class MatchService implements IMatchService {

	@Autowired
	private MatchRepository matchRepository;

	@Override
	public void addOne(MatchEntity match) {
		
		matchRepository.save(match);

	}

	@Override
	public void addMany(Collection<MatchEntity> matches) {
		for (MatchEntity match : matches)
			matchRepository.save(match);

	}

	@Override
	public MatchEntity findById(Long id) {
		MatchEntity res = null;
		try {
			res = matchRepository.findOneById(id);
		} catch (Exception e) {
			return null;
		}
		
		return res;
	}

	@Override
	public Collection<MatchEntity> findAll(Pageable pageable) {
		
		return matchRepository.findAll();
	}

	@Override
	public MatchEntity update(Long id, MatchEntity newData) {
		
		MatchEntity old = matchRepository.findOneById(id);
		
		old.setName(newData.getName());
		old.setNumOfSet(newData.getNumOfSet());
		old.setExpectedDate(newData.getExpectedDate());
		old.setExpectedPlace(newData.getExpectedPlace());
		old.setRealDate(newData.getRealDate());
		old.setRealPlace(newData.getRealPlace());
		old.setStatus(newData.getStatus());
		old.setCompetition(newData.getCompetition());
		
		old.setStatus(newData.getStatus());
		old = matchRepository.save(old);
		return old;
	}
}
