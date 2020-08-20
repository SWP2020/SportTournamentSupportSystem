
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
@Table(name = "matches")
@EntityListeners(AuditingEntityListener.class)
public class MatchEntity{

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private int numOfSet;
	
	private Date expectedDate;
	
	private String expectedPlace;
	
	private Date realDate;
	
	private String realPlace;
	
	private int roundNo;
	
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
	@JoinColumn(name = "nextMatchId")
	private MatchEntity nextMatch;
	
	@ManyToMany(mappedBy = "matchesList")
	private Collection<TeamEntity> teamsList;
	
	@OneToMany(mappedBy = "nextMatch", cascade = CascadeType.ALL)
	private Collection<MatchEntity> matches;

	@OneToMany(mappedBy = "match", cascade = CascadeType.ALL)
	private Collection<ResultEntity> results;


public Long getId() {
	return id;
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

public int getRoundNo() {
	return roundNo;
}

public void setRoundNo(int roundNo) {
	this.roundNo = roundNo;
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

public MatchEntity getNextMatch() {
	return nextMatch;
}

public void setNextMatch(MatchEntity nextMatch) {
	this.nextMatch = nextMatch;
}

public Collection<TeamEntity> getTeamsList() {
	return teamsList;
}

public void setTeamsList(Collection<TeamEntity> teamsList) {
	this.teamsList = teamsList;
}

public Collection<MatchEntity> getMatches() {
	return matches;
}

public void setMatches(Collection<MatchEntity> matches) {
	this.matches = matches;
}

public Collection<ResultEntity> getResults() {
	return results;
}

public void setResults(Collection<ResultEntity> results) {
	this.results = results;
}


}