
package doan2020.SportTournamentSupportSystem.entity;

import java.util.Collection;
import java.util.Comparator;
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
@Table(name = "teams")
@EntityListeners(AuditingEntityListener.class)
public class TeamEntity implements Comparator<TeamEntity> {

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String fullName;

	@NotNull
	private String shortName;

	private String description;

	private Long seedNo;

	private String createdBy;

	private Date createdDate;

	private String modifiedBy;

	private Date modifiedDate;

	private String status;

	private String url;

	@ManyToOne
	@JoinColumn(name = "creatorId")
	private UserEntity creator;

	@ManyToOne
	@JoinColumn(name = "competitionId")
	private CompetitionEntity competition;

	@OneToMany(mappedBy = "team1", cascade = CascadeType.ALL)
	private Collection<MatchEntity> team1Matches;
	
	@OneToMany(mappedBy = "team2", cascade = CascadeType.ALL)
	private Collection<MatchEntity> team2Matches;
	
	@OneToMany(mappedBy = "winner", cascade = CascadeType.ALL)
	private Collection<MatchEntity> winnerMatches;
	
	@OneToMany(mappedBy = "loser", cascade = CascadeType.ALL)
	private Collection<MatchEntity> loserMatches;

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

	public UserEntity getCreator() {
		return creator;
	}

	public void setCreator(UserEntity creator) {
		this.creator = creator;
	}

	public CompetitionEntity getCompetition() {
		return competition;
	}

	public void setCompetition(CompetitionEntity competition) {
		this.competition = competition;
	}

	public Long getSeedNo() {
		if (seedNo == null)
			return -1l;
		return seedNo;
	}

	public void setSeedNo(Long seedNo) {
		this.seedNo = seedNo;
	}

	public Collection<MatchEntity> getTeam1Matches() {
		return team1Matches;
	}

	public void setTeam1Matches(Collection<MatchEntity> team1Matches) {
		this.team1Matches = team1Matches;
	}

	public Collection<MatchEntity> getTeam2Matches() {
		return team2Matches;
	}

	public void setTeam2Matches(Collection<MatchEntity> team2Matches) {
		this.team2Matches = team2Matches;
	}

	public Collection<MatchEntity> getWinnerMatches() {
		return winnerMatches;
	}

	public void setWinnerMatches(Collection<MatchEntity> winnerMatches) {
		this.winnerMatches = winnerMatches;
	}

	public Collection<MatchEntity> getLoserMatches() {
		return loserMatches;
	}

	public void setLoserMatches(Collection<MatchEntity> loserMatches) {
		this.loserMatches = loserMatches;
	}
	
	@Override
	public int compare(TeamEntity o1, TeamEntity o2) {
		return new Long(o1.getSeedNo() - o2.getSeedNo()).intValue();
	}

}