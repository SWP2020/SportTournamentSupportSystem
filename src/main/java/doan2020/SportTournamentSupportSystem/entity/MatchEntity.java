
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
@Table(name = "matches")
@EntityListeners(AuditingEntityListener.class)
public class MatchEntity {

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private Date time;

	private String location;

	private String createdBy;

	private Date createdDate;

	private String modifiedBy;

	private Date modifiedDate;

	private String status;

	private String url;

	@ManyToOne
	@JoinColumn(name = "competitionId")
	private CompetitionEntity competition;

	@ManyToOne
	@JoinColumn(name = "team1Id")
	private TeamEntity team1;

	@ManyToOne
	@JoinColumn(name = "team2Id")
	private TeamEntity team2;

	@ManyToOne
	@JoinColumn(name = "winnerId")
	private TeamEntity winner;

	@ManyToOne
	@JoinColumn(name = "loserId")
	private TeamEntity loser;

	private float team1Bonus;

	private float team2Bonus;

	@OneToMany(mappedBy = "match", cascade = CascadeType.ALL)
	private Collection<ResultEntity> results;

	public MatchEntity() {
		
	}
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public CompetitionEntity getCompetition() {
		return competition;
	}

	public void setCompetition(CompetitionEntity competition) {
		this.competition = competition;
	}

	public TeamEntity getTeam1() {
		return team1;
	}

	public void setTeam1(TeamEntity team1) {
		this.team1 = team1;
	}

	public TeamEntity getTeam2() {
		return team2;
	}

	public void setTeam2(TeamEntity team2) {
		this.team2 = team2;
	}

	public TeamEntity getWinnner() {
		return winner;
	}

	public void setWinnner(TeamEntity winnner) {
		this.winner = winnner;
	}

	public TeamEntity getLoser() {
		return loser;
	}

	public void setLoser(TeamEntity loser) {
		this.loser = loser;
	}

	public float getTeam1Bonus() {
		return team1Bonus;
	}

	public void setTeam1Bonus(float team1Bonus) {
		this.team1Bonus = team1Bonus;
	}

	public float getTeam2Bonus() {
		return team2Bonus;
	}

	public void setTeam2Bonus(float team2Bonus) {
		this.team2Bonus = team2Bonus;
	}

	public Collection<ResultEntity> getResults() {
		return results;
	}

	public void setResults(Collection<ResultEntity> results) {
		this.results = results;
	}
	
	

}