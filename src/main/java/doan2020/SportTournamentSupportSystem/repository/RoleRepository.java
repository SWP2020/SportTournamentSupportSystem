package doan2020.SportTournamentSupportSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import doan2020.SportTournamentSupportSystem.entity.RoleTestEntity;

@Repository
public interface RoleRepository extends JpaRepository<RoleTestEntity, Long>{
	RoleTestEntity findOneByRolename(String roleName);

	RoleTestEntity findOneByRoleid(Long roleID);
}
