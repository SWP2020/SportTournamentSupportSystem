
package doan2020.SportTournamentSupportSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import doan2020.SportTournamentSupportSystem.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	UserEntity findOneById(Long id);

	UserEntity findByEmailAndUsername(String email, String Username);

	UserEntity findByUsername(String username);
	
	UserEntity findByEmail(String email);
}