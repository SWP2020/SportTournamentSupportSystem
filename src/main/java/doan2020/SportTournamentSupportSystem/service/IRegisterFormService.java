
package doan2020.SportTournamentSupportSystem.service;

import java.util.Collection;

import org.springframework.data.domain.Pageable;

import doan2020.SportTournamentSupportSystem.entity.CompetitionEntity;
import doan2020.SportTournamentSupportSystem.entity.RegisterFormEntity;

public interface IRegisterFormService {

	public RegisterFormEntity findOneById(Long id);

	public Collection<RegisterFormEntity> findAll(Pageable pageable);

	public RegisterFormEntity create(RegisterFormEntity registerFormEntity);

	public RegisterFormEntity update(Long id, RegisterFormEntity newEntity);

//	public Collection<RegisterFormEntity> findAll();

	public RegisterFormEntity delete(Long id);

	Collection<RegisterFormEntity> findByCompetitionId(Pageable pageable, Long competitionId);

	Collection<RegisterFormEntity> findByTeamId(Long teamId);

	Collection<RegisterFormEntity> findByCompetitionSettingId(Long competitionSettingId);
}