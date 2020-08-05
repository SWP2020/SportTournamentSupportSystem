
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
			updatedEntity.setType(newEntity.getType());
			updatedEntity.setSubject(newEntity.getSubject());
			updatedEntity.setContent(newEntity.getContent());
			updatedEntity.setLink(newEntity.getLink());
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

}