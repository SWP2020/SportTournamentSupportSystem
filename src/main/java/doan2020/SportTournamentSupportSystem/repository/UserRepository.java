
package doan2020.SportTournamentSupportSystem.repository;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import doan2020.SportTournamentSupportSystem.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	UserEntity findOneById(Long id);

	UserEntity findByEmailAndUsername(String email, String Username);

	UserEntity findByUsername(String username);
	
	UserEntity findByEmail(String email);
	
	Page<UserEntity> findByRoleId(Pageable pageable, Long roleId);
	
	@Query(value="SELECT u FROM UserEntity u"
			+ " WHERE u.firstName LIKE CONCAT('%',:str,'%')"
			+ " or u.lastName LIKE CONCAT('%',:str,'%')"
			+ " or u.username LIKE CONCAT('%',:str,'%')"
			+ " or u.email LIKE CONCAT('%',:str,'%')")
    Collection<UserEntity> findBySearchString(@Param("str") String searchString);
	
	int countByRoleId(Long roleId);
}