package doan2020.SportTournamentSupportSystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import doan2020.SportTournamentSupportSystem.entity.RoleEntity;
import doan2020.SportTournamentSupportSystem.repository.RoleRepository;
import doan2020.SportTournamentSupportSystem.service.IRoleService;

@Service
public class RoleService implements IRoleService{
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	@Transactional
	public RoleEntity findOneByName(String name) {
		RoleEntity roleEntity = roleRepository.findOneByName(name);
		return roleEntity;
	}

}
