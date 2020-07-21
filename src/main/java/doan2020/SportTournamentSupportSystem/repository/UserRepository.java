
package doan2020.SportTournamentSupportSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import doan2020.SportTournamentSupportSystem.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
	List<UserEntity> findByEmailAndUsername(String email, String Username);
	
	UserEntity findByUsername(String username);
}