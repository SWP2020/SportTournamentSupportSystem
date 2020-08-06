
package doan2020.SportTournamentSupportSystem.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.EntityListeners;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.util.Date;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.sun.istack.NotNull;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import java.util.Collection;



@Entity
@Table(name = "scoring_unit")
@EntityListeners(AuditingEntityListener.class)
public class ScoringUnitEntity{

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
	
	private String status;
	
	private String url;
	

	@OneToMany(mappedBy = "scoringUnit", cascade = CascadeType.ALL)
	private Collection<SportEntity> sports;


public Long getId() {
	return id;
}

public String getFullName() {
	return fullName;
}

public void setFullName(String fullName) {
	this.fullName = fullName;
}

public String getShortName() {
	return shortName;
}

public void setShortName(String shortName) {
	this.shortName = shortName;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
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

public Collection<SportEntity> getSports() {
	return sports;
}

public void setSports(Collection<SportEntity> sports) {
	this.sports = sports;
}


}