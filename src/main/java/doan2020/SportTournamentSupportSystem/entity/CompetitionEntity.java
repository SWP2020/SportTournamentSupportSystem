
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.sun.istack.NotNull;

@Entity
@Table(name = "competitions")
@EntityListeners(AuditingEntityListener.class)
public class CompetitionEntity {

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String description;

	private String createdBy;

	private Date createdDate;

	private String modifiedBy;

	private Date modifiedDate;

	private String status;

	private String url;

	@ManyToOne
	@JoinColumn(name = "tournamentId")
	private TournamentEntity tournament;

	@ManyToOne
	@JoinColumn(name = "sportId")
	private SportEntity sport;

	@ManyToOne
	@JoinColumn(name = "finalStageFormatId")
	private CompetitionFormatEntity finalStageFormat;

	@ManyToOne
	@JoinColumn(name = "groupStageFormatId")
	private CompetitionFormatEntity groupStageFormat;

	@OneToMany(mappedBy = "competition", cascade = CascadeType.ALL)
	private Collection<MatchEntity> matches;

	@OneToMany(mappedBy = "competition", cascade = CascadeType.ALL)
	private Collection<TeamEntity> teams;
	
	@OneToOne
	@JoinColumn(name = "groupStageSettingId")
	private GroupStageSettingEntity groupStageSetting;
	
	@OneToOne
	@JoinColumn(name = "finalStageSettingId")
	private FinalStageSettingEntity finalStageSetting;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public CompetitionFormatEntity getFinalStageFormat() {
		return finalStageFormat;
	}

	public void setFinalStageFormat(CompetitionFormatEntity finalStageFormat) {
		this.finalStageFormat = finalStageFormat;
	}

	public CompetitionFormatEntity getGroupStageFormat() {
		return groupStageFormat;
	}

	public void setGroupStageFormat(CompetitionFormatEntity groupStageFormat) {
		this.groupStageFormat = groupStageFormat;
	}

	public Collection<MatchEntity> getMatches() {
		return matches;
	}

	public void setMatches(Collection<MatchEntity> matches) {
		this.matches = matches;
	}

	public Collection<TeamEntity> getTeams() {
		return teams;
	}

	public void setTeams(Collection<TeamEntity> teams) {
		this.teams = teams;
	}

	public GroupStageSettingEntity getGroupStageSetting() {
		return groupStageSetting;
	}

	public void setGroupStageSetting(GroupStageSettingEntity groupStageSetting) {
		this.groupStageSetting = groupStageSetting;
	}

	public FinalStageSettingEntity getFinalStageSetting() {
		return finalStageSetting;
	}

	public void setFinalStageSetting(FinalStageSettingEntity finalStageSetting) {
		this.finalStageSetting = finalStageSetting;
	}
	
}