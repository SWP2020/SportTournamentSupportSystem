
package doan2020.SportTournamentSupportSystem.service;

import java.util.Collection;
import org.springframework.data.domain.Pageable;
import doan2020.SportTournamentSupportSystem.entity.ReportEntity;

public interface IReportService {
	
	public ReportEntity findOneById(Long id);
	
//	public Collection<ReportEntity> findAll(Pageable pageable);
	
	public ReportEntity create(ReportEntity reportEntity);
	
	public ReportEntity update(Long id, ReportEntity newEntity);
	
//	public Collection<ReportEntity> findAll();
	
	public ReportEntity delete(Long id);
}