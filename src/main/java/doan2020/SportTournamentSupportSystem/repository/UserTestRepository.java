package doan2020.SportTournamentSupportSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import doan2020.SportTournamentSupportSystem.entity.UserTestEntity;

@Repository
public interface UserTestRepository extends JpaRepository<UserTestEntity, Long>{
	UserTestEntity findByUsername(String username);
	
}
