package doan2020.SportTournamentSupportSystem.service;

import doan2020.SportTournamentSupportSystem.entity.RoleEntity;

public interface IRoleService {
	public RoleEntity findOneByName(String name);
}
