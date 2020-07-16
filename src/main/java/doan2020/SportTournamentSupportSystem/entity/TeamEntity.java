
package doan2020.SportTournamentSupportSystem.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.sun.istack.NotNull;



@Entity
@Table(name = "teams")
@EntityListeners(AuditingEntityListener.class)
public class TeamEntity{

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
	@JoinColumn(name = "creatorId")
	private UserEntity creator;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
		name = "team_competition",
		joinColumns = @JoinColumn(name = "team_id"),
		inverseJoinColumns = @JoinColumn(name = "competition_id")
	)
	private Collection<CompetitionEntity> competitionsList;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
		name = "team_match",
		joinColumns = @JoinColumn(name = "team_id"),
		inverseJoinColumns = @JoinColumn(name = "match_id")
	)
	private Collection<MatchEntity> matchesList;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
		name = "team_player",
		joinColumns = @JoinColumn(name = "team_id"),
		inverseJoinColumns = @JoinColumn(name = "player_id")
	)
	private Collection<PlayerEntity> playersList;
	
	@OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
	private Collection<ResultEntity> results;


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
	
	public UserEntity getCreator() {
		return creator;
	}
	
	public void setCreator(UserEntity creator) {
		this.creator = creator;
	}
	
	public Collection<CompetitionEntity> getCompetitionslist() {
		return competitionsList;
	}
	
	public void setCompetitionslist(Collection<CompetitionEntity> competitionsList) {
		this.competitionsList = competitionsList;
	}
	
	public Collection<MatchEntity> getMatcheslist() {
		return matchesList;
	}
	
	public void setMatcheslist(Collection<MatchEntity> matchesList) {
		this.matchesList = matchesList;
	}
	
	public Collection<PlayerEntity> getPlayerslist() {
		return playersList;
	}
	
	public void setPlayerslist(Collection<PlayerEntity> playersList) {
		this.playersList = playersList;
	}
	
	public Collection<ResultEntity> getResults() {
		return results;
	}
	
	public void setResults(Collection<ResultEntity> results) {
		this.results = results;
	}
	

}