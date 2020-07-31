package doan2020.SportTournamentSupportSystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import doan2020.SportTournamentSupportSystem.entity.SportEntity;
import doan2020.SportTournamentSupportSystem.repository.SportRepository;
import doan2020.SportTournamentSupportSystem.service.ISportService;

@Service
public class SportService implements ISportService {
	
	@Autowired
    private SportRepository sportRepository;
	
	@Override
	public SportEntity getSportbyId(Long id) {
		SportEntity sportEntity = sportRepository.getOne(id);
		return sportEntity;
	}

}
