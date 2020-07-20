
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
	
	private int setNumber;
	
	private float score;
	
	private boolean isWinner;
	
	private String createdBy;
	
	private Date createdDate;
	
	private String modifiedBy;
	
	private Date modifiedDate;
	

	@ManyToOne
	@JoinColumn(name = "matchId")
	private MatchEntity match;
	
	@ManyToOne
	@JoinColumn(name = "teamId")
	private TeamEntity team;
	

	public Long getId() {
		return id;
	}
	
	public int getSetnumber() {
		return setNumber;
	}
	
	public void setSetnumber(int setNumber) {
		this.setNumber = setNumber;
	}
	
	public float getScore() {
		return score;
	}
	
	public void setScore(float score) {
		this.score = score;
	}
	
	public boolean getIswinner() {
		return isWinner;
	}
	
	public void setIswinner(boolean isWinner) {
		this.isWinner = isWinner;
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