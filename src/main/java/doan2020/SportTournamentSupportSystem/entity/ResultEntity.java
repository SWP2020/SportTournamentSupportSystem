
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
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;



@Entity
@Table(name = "results")
@EntityListeners(AuditingEntityListener.class)
public class ResultEntity{

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int setNo;
	
	private float score;
	
	private int ranking;
	
	private String createdBy;
	
	private Date createdDate;
	
	private String modifiedBy;
	
	private Date modifiedDate;
	
	private String status;
	
	private String url;
	

	@ManyToOne
	@JoinColumn(name = "matchId")
	private MatchEntity match;
	
	@ManyToOne
	@JoinColumn(name = "teamId")
	private TeamEntity team;
	

public Long getId() {
	return id;
}

public int getSetNo() {
	return setNo;
}

public void setSetNo(int setNo) {
	this.setNo = setNo;
}

public float getScore() {
	return score;
}

public void setScore(float score) {
	this.score = score;
}

public int getRank() {
	return ranking;
}

public void setRank(int rank) {
	this.ranking = rank;
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

public MatchEntity getMatch() {
	return match;
}

public void setMatch(MatchEntity match) {
	this.match = match;
}

public TeamEntity getTeam() {
	return team;
}

public void setTeam(TeamEntity team) {
	this.team = team;
}


}