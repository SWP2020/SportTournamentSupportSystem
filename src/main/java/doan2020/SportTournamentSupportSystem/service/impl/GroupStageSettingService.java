package doan2020.SportTournamentSupportSystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import doan2020.SportTournamentSupportSystem.entity.GroupStageSettingEntity;
import doan2020.SportTournamentSupportSystem.repository.GroupStageSettingRepository;
import doan2020.SportTournamentSupportSystem.service.IGroupStageSettingService;

@Service
public class GroupStageSettingService implements IGroupStageSettingService {
	@Autowired
	private GroupStageSettingRepository groupStageSettingRepository;
	
	@Override
	public GroupStageSettingEntity findOneById(Long id) {
		GroupStageSettingEntity foundEntity = null;
		try {
			foundEntity = groupStageSettingRepository.findOneById(id);
		} catch (Exception e) {
			return null;
		}
		return foundEntity;
	}
}
