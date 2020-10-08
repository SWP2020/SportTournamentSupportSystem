
package doan2020.SportTournamentSupportSystem.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "tournaments")
public class TournamentEntity {

	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String fullName = "unknown";

	@Column(nullable = false)
	private String shortName = "unknown";

	@ColumnDefault("'Chưa có mô tả.'")
	private String description;

	private String openingLocation;

	private Date openingTime;

	private String closingLocation;

	private Date closingTime;

	@ColumnDefault("'Không có nhà tài trợ'")
	private String donor;

	private String avatar;

	private String background;

	private Date openRegistrationTime;
	private Date closeRegistrationTime;
	
	private int totalTeam;
	
	@ColumnDefault("0")
	@Column(nullable = false)
	private boolean hasGroupStage = false;

	@ColumnDefault("'unknown'")
	private String status;

	private String url = "/?";

	@ManyToOne
	@JoinColumn(name = "creatorId", nullable = false)
	private UserEntity creator;

	@OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL)
	private Collection<ReportEntity> reports;

	@ManyToOne
	@JoinColumn(name = "sportId", nullable = false)
	private SportEntity sport;

	@OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL)
	private Collection<MatchEntity> matches;

	@OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL)
	private Collection<TeamEntity> teams;

	@OneToOne(mappedBy = "tournament")
	private GroupStageSettingEntity groupStageSetting;

	@OneToOne(mappedBy = "tournament")
	private FinalStageSettingEntity finalStageSetting;

	public Long getId() {
		return id;
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

	public String getOpeningLocation() {
		return openingLocation;
	}

	public void setOpeningLocation(String openingLocation) {
		this.openingLocation = openingLocation;
	}

	public Date getOpeningTime() {
		return openingTime;
	}

	public void setOpeningTime(Date openingTime) {
		this.openingTime = openingTime;
	}

	public String getClosingLocation() {
		return closingLocation;
	}

	public void setClosingLocation(String closingLocation) {
		this.closingLocation = closingLocation;
	}

	public Date getClosingTime() {
		return closingTime;
	}

	public void setClosingTime(Date closingTime) {
		this.closingTime = closingTime;
	}

	public String getDonor() {
		return donor;
	}

	public void setDonor(String donor) {
		this.donor = donor;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public Date getOpenRegistrationTime() {
		return openRegistrationTime;
	}

	public void setOpenRegistrationTime(Date openRegistrationTime) {
		this.openRegistrationTime = openRegistrationTime;
	}

	public Date getCloseRegistrationTime() {
		return closeRegistrationTime;
	}

	public void setCloseRegistrationTime(Date closeRegistrationTime) {
		this.closeRegistrationTime = closeRegistrationTime;
	}

	public UserEntity getCreator() {
		return creator;
	}

	public void setCreator(UserEntity creator) {
		this.creator = creator;
	}

	public Collection<ReportEntity> getReports() {
		return reports;
	}

	public void setReports(Collection<ReportEntity> reports) {
		this.reports = reports;
	}

	public int getTotalTeam() {
		return totalTeam;
	}

	public void setTotalTeam(int totalTeam) {
		this.totalTeam = totalTeam;
	}
	
}