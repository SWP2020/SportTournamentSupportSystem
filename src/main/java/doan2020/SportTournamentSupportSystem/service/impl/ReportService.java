
package doan2020.SportTournamentSupportSystem.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import doan2020.SportTournamentSupportSystem.entity.ReportEntity;
import doan2020.SportTournamentSupportSystem.repository.ReportRepository;
import doan2020.SportTournamentSupportSystem.service.IReportService;

@Service
public class ReportService implements IReportService {

	@Autowired
	private ReportRepository reportRepository;

	@Override
	public ReportEntity create(ReportEntity reportEntity) {
		ReportEntity newEntity = null;
		try {
			newEntity = reportRepository.save(reportEntity);
		} catch (Exception e) {
			return null;
		}
		return newEntity;
	}

	@Override
	public ReportEntity update(Long id, ReportEntity newEntity) {
		ReportEntity updatedEntity = null;
		try {
			updatedEntity = reportRepository.findOneById(id);

			updatedEntity.setSender(newEntity.getSender());
			updatedEntity.setSubject(newEntity.getSubject());
			updatedEntity.setContent(newEntity.getContent());
			updatedEntity.setTournament(newEntity.getTournament());
			updatedEntity.setCreatedBy(newEntity.getCreatedBy());
			updatedEntity.setCreatedDate(newEntity.getCreatedDate());
			updatedEntity.setModifiedBy(newEntity.getModifiedBy());
			updatedEntity.setModifiedDate(newEntity.getModifiedDate());
			updatedEntity.setStatus(newEntity.getStatus());
			updatedEntity.setUrl(newEntity.getUrl());
			updatedEntity = reportRepository.save(updatedEntity);
		} catch (Exception e) {
			return null;
		}
        
		return updatedEntity;
	}

	@Override
	public ReportEntity delete(Long id) {
		ReportEntity deletedEntity = null;
		try {
			deletedEntity = reportRepository.findOneById(id);
			deletedEntity.setStatus("deleted");
			deletedEntity = reportRepository.save(deletedEntity);
		} catch (Exception e) {
			return null;
		}
		return deletedEntity;
	}

	@Override
	public ReportEntity findOneById(Long id) {
		ReportEntity foundEntity = null;
		try {
			foundEntity = reportRepository.findOneById(id);
		} catch (Exception e) {
			return null;
		}
		return foundEntity;
	}

	@Override
	public Collection<ReportEntity> findAll(Pageable pageable) {
		Collection<ReportEntity> foundEntitys = null;
		try {
			foundEntitys = reportRepository.findAll(pageable).getContent();
		} catch (Exception e) {
			return null;
		}
		return foundEntitys;
	}

	@Override
	public Collection<ReportEntity> findBySenderId(Pageable pageable, Long senderId) {
		Collection<ReportEntity> foundEntitys = null;
		try {
			foundEntitys = reportRepository.findBySenderId(pageable, senderId).getContent();
		} catch (Exception e) {
			return null;
		}
		return foundEntitys;
	}

	@Override
	public Collection<ReportEntity> findByTournamentId(Pageable pageable, Long tournamentId) {
		Collection<ReportEntity> foundEntitys = null;
		try {
			foundEntitys = reportRepository.findByTournamentId(pageable, tournamentId).getContent();
		} catch (Exception e) {
			return null;
		}
		return foundEntitys;
	}

	@Override
	public int countReports() {
		int count = 0;
		try {
			count = (int) reportRepository.count();
		} catch (Exception e) {
			return 0;
		}
		return count;
	}

}