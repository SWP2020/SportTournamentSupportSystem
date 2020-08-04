package doan2020.SportTournamentSupportSystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import doan2020.SportTournamentSupportSystem.entity.ReportEntity;
import doan2020.SportTournamentSupportSystem.repository.ReportRepository;
import doan2020.SportTournamentSupportSystem.service.IReportService;

@Service
public class ReportService implements IReportService{
	
	@Autowired
	private ReportRepository reportRepository;

	@Override
	public ReportEntity findOneById(Long id) {
		ReportEntity entity = new ReportEntity();
		try {
			entity = reportRepository.findOneById(id);
			System.out.println("ok");
		}catch (Exception e) {
			System.out.println("fail");
			return null;
		}
		return entity;
	}

	@Override
	public void addReport(ReportEntity reportEntity) {
		// TODO Auto-generated method stub
		reportRepository.save(reportEntity);
		
	}

	@Override
	public void editReport(ReportEntity reportEntity) {
		// TODO Auto-generated method stub
		reportRepository.save(reportEntity);
	}

	@Override
	public void deleteReport(ReportEntity ReportEntity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ReportEntity> findBySenderId(Pageable pageable, Long senderId) {
		List<ReportEntity> reportEntities = reportRepository.findBySenderId(pageable, senderId).getContent();
		return reportEntities;
	}

	@Override
	public List<ReportEntity> findByType(Pageable pageable, String type) {
		List<ReportEntity> reportEntities = reportRepository.findByType(pageable, type).getContent();
		return reportEntities;
	}

}
