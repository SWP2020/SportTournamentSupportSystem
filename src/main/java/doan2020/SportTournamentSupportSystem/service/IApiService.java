
package doan2020.SportTournamentSupportSystem.service;

import java.util.Collection;
import org.springframework.data.domain.Pageable;
import doan2020.SportTournamentSupportSystem.entity.ApiEntity;

public interface IApiService {
	
	public ApiEntity findOneById(Long id);
	
//	public Collection<ApiEntity> findAll(Pageable pageable);
	
	public ApiEntity create(ApiEntity apiEntity);
	
	public ApiEntity update(Long id, ApiEntity newEntity);
	
//	public Collection<ApiEntity> findAll();
	
	public ApiEntity delete(Long id);
}