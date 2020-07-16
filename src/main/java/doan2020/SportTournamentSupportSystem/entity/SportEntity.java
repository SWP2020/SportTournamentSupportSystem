
package doan2020.SportTournamentSupportSystem.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.sun.istack.NotNull;



@Entity
@Table(name = "sports")
@EntityListeners(AuditingEntityListener.class)
public class SportEntity{

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String fullName;
	
	private String shortName;
	
	private String description;
	
	private String createdBy;
	
	private Date createdDate;
	
	private String modifiedBy;
	
	private Date modifiedDate;
	

	@ManyToOne
	@JoinColumn(name = "scoringunitId")
	private ScoringunitEntity scoringunit;
	
	@OneToMany(mappedBy = "sport", cascade = CascadeType.ALL)
	private Collection<CompetitionEntity> competitions;


	public Long getId() {
		return id;
	}
	
	public String getFullname() {
		return fullName;
	}
	
	public void setFullname(String fullName) {
		this.fullName = fullName;
	}
	
	public String getShortname() {
		return shortName;
	}
	
	public void setShortname(String shortName) {
		this.shortName = shortName;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
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
	
	public ScoringunitEntity getScoringunit() {
		return scoringunit;
	}
	
	public void setScoringunit(ScoringunitEntity scoringunit) {
		this.scoringunit = scoringunit;
	}
	
	public Collection<CompetitionEntity> getCompetitions() {
		return competitions;
	}
	
	public void setCompetitions(Collection<CompetitionEntity> competitions) {
		this.competitions = competitions;
	}
	

}