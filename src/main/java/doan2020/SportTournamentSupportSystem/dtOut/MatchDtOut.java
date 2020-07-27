package doan2020.SportTournamentSupportSystem.dtOut;

import java.util.Date;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import doan2020.SportTournamentSupportSystem.entity.CompetitionEntity;
import doan2020.SportTournamentSupportSystem.entity.UserEntity;

public class MatchDtOut {

private Long id;
	
	private String name;
	
	private int numOfSet;
	
	private Date expectedDate;
	
	private String expectedPlace;
	
	private Date realDate;
	
	private String realPlace;
	
	private String status;
	
	private Long competitionId;

	public MatchDtOut() {
		
	}

	public MatchDtOut(Long id, String name, int numOfSet, Date expectedDate, String expectedPlace, Date realDate,
			String realPlace, String status, Long competitionId) {
		super();
		this.id = id;
		this.name = name;
		this.numOfSet = numOfSet;
		this.expectedDate = expectedDate;
		this.expectedPlace = expectedPlace;
		this.realDate = realDate;
		this.realPlace = realPlace;
		this.status = status;
		this.competitionId = competitionId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumOfSet() {
		return numOfSet;
	}

	public void setNumOfSet(int numOfSet) {
		this.numOfSet = numOfSet;
	}

	public Date getExpectedDate() {
		return expectedDate;
	}

	public void setExpectedDate(Date expectedDate) {
		this.expectedDate = expectedDate;
	}

	public String getExpectedPlace() {
		return expectedPlace;
	}

	public void setExpectedPlace(String expectedPlace) {
		this.expectedPlace = expectedPlace;
	}

	public Date getRealDate() {
		return realDate;
	}

	public void setRealDate(Date realDate) {
		this.realDate = realDate;
	}

	public String getRealPlace() {
		return realPlace;
	}

	public void setRealPlace(String realPlace) {
		this.realPlace = realPlace;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getCompetitionId() {
		return competitionId;
	}

	public void setCompetitionId(Long competitionId) {
		this.competitionId = competitionId;
	}

	
	
}