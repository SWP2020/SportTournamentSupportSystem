
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
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import java.util.Collection;
import java.util.Comparator;

import javax.persistence.OneToMany;

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

	private Integer seedNo;

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

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "team_match", joinColumns = @JoinColumn(name = "team_id"), inverseJoinColumns = @JoinColumn(name = "match_id"))
	private Collection<MatchEntity> matchesList;

	@OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
	private Collection<PlayerEntity> players;

	@OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
	private Collection<ResultEntity> results;

	@OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
	private Collection<RegisterFormEntity> register_forms;

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

	public Collection<MatchEntity> getMatchesList() {
		return matchesList;
	}

	public void setMatchesList(Collection<MatchEntity> matchesList) {
		this.matchesList = matchesList;
	}

	public Collection<PlayerEntity> getPlayers() {
		return players;
	}

	public void setPlayers(Collection<PlayerEntity> players) {
		this.players = players;
	}

	public Collection<ResultEntity> getResults() {
		return results;
	}

	public void setResults(Collection<ResultEntity> results) {
		this.results = results;
	}

	public Collection<RegisterFormEntity> getRegisterForms() {
		return register_forms;
	}

	public void setRegisterForms(Collection<RegisterFormEntity> register_forms) {
		this.register_forms = register_forms;
	}

	public Integer getSeedNo() {
		return seedNo;
	}

	public void setSeedNo(Integer seedNo) {
		this.seedNo = seedNo;
	}
	
	@Override
	public int compare(TeamEntity o1, TeamEntity o2) {
		return o1.getSeedNo() - o2.getSeedNo();
	}

}