package doan2020.SportTournamentSupportSystem.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.sun.istack.NotNull;

@Entity
@Table(name = "group_stage_setting")
@EntityListeners(AuditingEntityListener.class)
public class GroupStageSettingEntity {

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String createdBy;

	private Date createdDate;

	private String modifiedBy;

	private Date modifiedDate;
	
	private int maxTeamPerTable;
	
	private int advanceTeamPerTable;
	
	private boolean hasHomeMatch;
	
	@ManyToOne
	@JoinColumn(name = "formatId")
	private FormatEntity format;
	
	private String status;

	private String url;

	public int getMaxTeamPerTable() {
		return maxTeamPerTable;
	}

	public void setMaxTeamPerTable(int maxTeamPerTable) {
		this.maxTeamPerTable = maxTeamPerTable;
	}

	public int getAdvanceTeamPerTable() {
		return advanceTeamPerTable;
	}

	public void setAdvanceTeamPerTable(int advanceTeamPerTable) {
		this.advanceTeamPerTable = advanceTeamPerTable;
	}

	public Long getId() {
		return id;
	}

	public boolean isHasHomeMatch() {
		return hasHomeMatch;
	}

	public void setHasHomeMatch(boolean hasHomeMatch) {
		this.hasHomeMatch = hasHomeMatch;
	}

	public FormatEntity getFormat() {
		return format;
	}

	public void setFormat(FormatEntity format) {
		this.format = format;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
