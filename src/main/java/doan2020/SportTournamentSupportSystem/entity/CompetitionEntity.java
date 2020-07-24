
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
import javax.persistence.ManyToMany;
import java.util.Collection;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;



@Entity
@Table(name = "competitions")
@EntityListeners(AuditingEntityListener.class)
public class CompetitionEntity{

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String name;
	
	private String status;
	
	private String createdBy;
	
	private Date createdDate;
	
	private String modifiedBy;
	
	private Date modifiedDate;
	

	@ManyToOne
	@JoinColumn(name = "tournamentId")
	private TournamentEntity tournament;
	
	@ManyToOne
	@JoinColumn(name = "sportId")
	private SportEntity sport;
	
	@ManyToMany(mappedBy = "competitionsList")
	private Collection<TeamEntity> teamsList;
	
	@OneToMany(mappedBy = "competition", cascade = CascadeType.ALL)
	private Collection<MatchEntity> matches;


	public Long getId() {
		return id;
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
	
	public TournamentEntity getTournament() {
		return tournament;
	}
	
	public void setTournament(TournamentEntity tournament) {
		this.tournament = tournament;
	}
	
	public SportEntity getSport() {
		return sport;
	}
	
	public void setSport(SportEntity sport) {
		this.sport = sport;
	}
	
	public Collection<TeamEntity> getTeamslist() {
		return teamsList;
	}
	
	public void setTeamslist(Collection<TeamEntity> teamsList) {
		this.teamsList = teamsList;
	}
	
	public Collection<MatchEntity> getMatches() {
		return matches;
	}
	
	public void setMatches(Collection<MatchEntity> matches) {
		this.matches = matches;
	}
	

}