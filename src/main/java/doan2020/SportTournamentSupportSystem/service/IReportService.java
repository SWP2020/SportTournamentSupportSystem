package doan2020.SportTournamentSupportSystem.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import doan2020.SportTournamentSupportSystem.entity.ReportEntity;

public interface IReportService {
	public ReportEntity findOneById(Long id);

	public void addReport(ReportEntity reportEntity);

	public void editReport(ReportEntity reportEntity);

	public void deleteReport(ReportEntity reportEntity);

	public List<ReportEntity> findBySenderId(Pageable pageable, Long senderId);
	
	public List<ReportEntity> findByType(Pageable pageable, String type);

}
