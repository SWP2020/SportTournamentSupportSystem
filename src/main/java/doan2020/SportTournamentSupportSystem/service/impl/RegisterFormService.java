
package doan2020.SportTournamentSupportSystem.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import doan2020.SportTournamentSupportSystem.entity.CompetitionEntity;
import doan2020.SportTournamentSupportSystem.entity.RegisterFormEntity;
import doan2020.SportTournamentSupportSystem.repository.RegisterFormRepository;
import doan2020.SportTournamentSupportSystem.service.IRegisterFormService;

@Service
public class RegisterFormService implements IRegisterFormService {

	@Autowired
	private RegisterFormRepository registerFormRepository;

	@Override
	public RegisterFormEntity create(RegisterFormEntity registerFormEntity) {
		RegisterFormEntity newEntity = null;
		try {
			newEntity = registerFormRepository.save(registerFormEntity);
		} catch (Exception e) {
			return null;
		}
		return newEntity;
	}

	@Override
	public RegisterFormEntity update(Long id, RegisterFormEntity newEntity) {
		RegisterFormEntity updatedEntity = null;
		try {
			updatedEntity = registerFormRepository.findOneById(id);

			updatedEntity.setCompetition(newEntity.getCompetition());
			updatedEntity.setTeam(newEntity.getTeam());
			updatedEntity.setCompetitionSetting(newEntity.getCompetitionSetting());
			updatedEntity.setDescription(newEntity.getDescription());
			updatedEntity.setCreatedBy(newEntity.getCreatedBy());
			updatedEntity.setCreatedDate(newEntity.getCreatedDate());
			updatedEntity.setModifiedBy(newEntity.getModifiedBy());
			updatedEntity.setModifiedDate(newEntity.getModifiedDate());
			updatedEntity.setStatus(newEntity.getStatus());
			updatedEntity.setUrl(newEntity.getUrl());
			updatedEntity = registerFormRepository.save(updatedEntity);
		} catch (Exception e) {
			return null;
		}
        
		return updatedEntity;
	}

	@Override
	public RegisterFormEntity delete(Long id) {
		RegisterFormEntity deletedEntity = null;
		try {
			deletedEntity = registerFormRepository.findOneById(id);
			deletedEntity.setStatus("deleted");
			deletedEntity = registerFormRepository.save(deletedEntity);
		} catch (Exception e) {
			return null;
		}
		return deletedEntity;
	}

	@Override
	public RegisterFormEntity findOneById(Long id) {
		RegisterFormEntity foundEntity = null;
		try {
			foundEntity = registerFormRepository.findOneById(id);
		} catch (Exception e) {
			return null;
		}
		return foundEntity;
	}

	@Override
	public Collection<RegisterFormEntity> findAll(Pageable pageable) {
		Collection<RegisterFormEntity> foundEntitys = null;
		try {
			foundEntitys = registerFormRepository.findAll(pageable).getContent();
		} catch (Exception e) {
			return null;
		}
		return foundEntitys;
	}

	@Override
	public Collection<RegisterFormEntity> findByCompetitionId(Pageable pageable, Long competitionId) {
		Collection<RegisterFormEntity> foundEntitys = null;
		try {
			foundEntitys = registerFormRepository.findByCompetitionId(pageable, competitionId).getContent();
		} catch (Exception e) {
			return null;
		}
		return foundEntitys;
	}

	@Override
	public Collection<RegisterFormEntity> findByTeamId(Long teamId) {
		Collection<RegisterFormEntity> foundEntitys = null;
		try {
			foundEntitys = registerFormRepository.findByTeamId(teamId);
		} catch (Exception e) {
			return null;
		}
		return foundEntitys;
	}

	@Override
	public Collection<RegisterFormEntity> findByCompetitionSettingId(Long competitionSettingId) {
		Collection<RegisterFormEntity> foundEntitys = null;
		try {
			foundEntitys = registerFormRepository.findByCompetitionSettingId(competitionSettingId);
		} catch (Exception e) {
			return null;
		}
		return foundEntitys;
	}

}