package doan2020.SportTournamentSupportSystem.dtOut;

import java.util.Date;

public class CompetitionDtOut {

	private Long competitionId;

	private String name;

	private String status;

	private String createdBy;

	private Date createdDate;

	private String modifiedBy;

	private Date modifiedDate;

	public Long getCompetitionId() {
		return competitionId;
	}

	public void setId(Long competitionId) {
		this.competitionId = competitionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedby() {
		return createdBy;
	}

	public void setCreatedby(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreateddate() {
		return createdDate;
	}

	public void setCreateddate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedby() {
		return modifiedBy;
	}

	public void setModifiedby(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifieddate() {
		return modifiedDate;
	}

	public void setModifieddate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
}
