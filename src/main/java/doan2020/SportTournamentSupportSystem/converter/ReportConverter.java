package doan2020.SportTournamentSupportSystem.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import doan2020.SportTournamentSupportSystem.dtIn.ReportDtIn;
import doan2020.SportTournamentSupportSystem.dtOut.ReportDtOut;
import doan2020.SportTournamentSupportSystem.entity.ReportEntity;
import doan2020.SportTournamentSupportSystem.entity.UserEntity;
import doan2020.SportTournamentSupportSystem.service.IUserService;
import doan2020.SportTournamentSupportSystem.validator.Validator;

@Component
public class ReportConverter {
	
	@Autowired
	private Validator validator;
	
	@Autowired
	private IUserService userService;
	
	public ReportEntity toEntity(ReportDtIn reportDtIn) {
		ReportEntity reportEntity = new ReportEntity();
		if(reportDtIn.getContent()!=null) {
			reportEntity.setContent(reportDtIn.getContent());
		}
		if(reportDtIn.getLink()!=null) {
			reportEntity.setLink(reportDtIn.getLink());
		}
		if(reportDtIn.getSenderId()!=null) {
			UserEntity userEntity = userService.findOneById(reportDtIn.getSenderId());
			reportEntity.setSender(userEntity);
		}
		if(reportDtIn.getStatus()!=null) {
			reportEntity.setStatus(reportDtIn.getStatus());
		}
		if(reportDtIn.getSubject()!=null) {
			reportEntity.setSubject(reportDtIn.getSubject());
		}
		if(reportDtIn.getType()!=null) {
			reportEntity.setType(reportDtIn.getType());
		}
		if(reportDtIn.getUrl()!=null) {
			reportEntity.setUrl(reportDtIn.getUrl());
		}
		return reportEntity;
	}
	
	public ReportDtOut toDTO(ReportEntity reportEntity) {
		ReportDtOut reportDtOut = new ReportDtOut();
		try {
		reportDtOut.setId(reportEntity.getId());
		reportDtOut.setContent(reportEntity.getContent());
		reportDtOut.setLink(reportEntity.getLink());
		reportDtOut.setSenderId(reportEntity.getSender().getId());
		reportDtOut.setStatus(reportEntity.getStatus());
		reportDtOut.setSubject(reportEntity.getSubject());
		reportDtOut.setType(reportEntity.getType());
		reportDtOut.setUrl(reportEntity.getUrl());
		System.out.println(true);
		}catch (Exception e) {
			System.out.println(false);
			return null;
		}
		return reportDtOut;
	}
	
	public ReportEntity toEntity(ReportDtIn reportDtIn, ReportEntity reportEntity) {
		if(reportDtIn.getContent()!=null) {
			reportEntity.setContent(reportDtIn.getContent());
		}
		if(reportDtIn.getLink()!=null) {
			reportEntity.setLink(reportDtIn.getLink());
		}
		if(reportDtIn.getSenderId()!=null) {
			UserEntity userEntity = userService.findOneById(reportDtIn.getSenderId());
			reportEntity.setSender(userEntity);
		}
		if(reportDtIn.getStatus()!=null) {
			reportEntity.setStatus(reportDtIn.getStatus());
		}
		if(reportDtIn.getSubject()!=null) {
			reportEntity.setSubject(reportDtIn.getSubject());
		}
		if(reportDtIn.getType()!=null) {
			reportEntity.setType(reportDtIn.getType());
		}
		if(reportDtIn.getUrl()!=null) {
			reportEntity.setUrl(reportDtIn.getUrl());
		}
		return reportEntity;
	}
	
	

}
