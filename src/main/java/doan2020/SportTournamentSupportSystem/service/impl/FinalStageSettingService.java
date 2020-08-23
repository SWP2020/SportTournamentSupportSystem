package doan2020.SportTournamentSupportSystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import doan2020.SportTournamentSupportSystem.entity.FinalStageSettingEntity;
import doan2020.SportTournamentSupportSystem.repository.FinalStageSettingRepository;
import doan2020.SportTournamentSupportSystem.service.IFinalStageSettingService;

@Service
public class FinalStageSettingService implements IFinalStageSettingService{

	@Autowired
	private FinalStageSettingRepository finalStageSettingRepository;
	
	@Override
	public FinalStageSettingEntity findOneById(Long id) {
		FinalStageSettingEntity foundEntity = null;
		try {
			foundEntity = finalStageSettingRepository.findOneById(id);
		} catch (Exception e) {
			return null;
		}
		return foundEntity;
	}

}
