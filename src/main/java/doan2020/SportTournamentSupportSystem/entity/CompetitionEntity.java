
package doan2020.SportTournamentSupportSystem.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "competitions")
public class CompetitionEntity {

	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@ColumnDefault("'Chưa có mô tả.'")
	private String description;
	
	@ColumnDefault("0")
	@Column(nullable = false)
	private boolean hasGroupStage;

	@ColumnDefault("'unknown'")
	private String status;

	private String url = "/?";

	@ManyToOne
	@JoinColumn(name = "tournamentId", nullable = false)
	private TournamentEntity tournament;

	@ManyToOne
	@JoinColumn(name = "sportId", nullable = false)
	private SportEntity sport;

	@OneToMany(mappedBy = "competition", cascade = CascadeType.ALL)
	private Collection<MatchEntity> matches;

	@OneToMany(mappedBy = "competition", cascade = CascadeType.ALL)
	private Collection<TeamEntity> teams;

	@OneToOne(mappedBy = "competition")
	private GroupStageSettingEntity groupStageSetting;

	@OneToOne(mappedBy = "competition")
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

	public boolean isHasGroupStage() {
		return hasGroupStage;
	}

	public void setHasGroupStage(boolean hasGroupStage) {
		this.hasGroupStage = hasGroupStage;
	}

}