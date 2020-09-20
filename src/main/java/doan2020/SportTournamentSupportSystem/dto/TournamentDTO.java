
package doan2020.SportTournamentSupportSystem.dto;

import com.sun.istack.NotNull;

public class TournamentDTO {

	private Long id;
	
	private String fullName;

	private String shortName;

	private String description;

	private String openingLocation;

	private String openingTime;

	private String closingLocation;

	private String closingTime;

	private String donor;

	private String status;

	private String url;

	private String avatar;

	private String background;

	private String openRegistrationTime;
	
	private String closeRegistrationTime;
	
	private Long creatorId;
	
	private String createDate;

	private Long sportId;

	private boolean hasGroupStage;

	public TournamentDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getOpeningLocation() {
		return openingLocation;
	}

	public void setOpeningLocation(String openingLocation) {
		this.openingLocation = openingLocation;
	}

	public String getOpeningTime() {
		return openingTime;
	}

	public void setOpeningTime(String openingTime) {
		this.openingTime = openingTime;
	}

	public String getClosingLocation() {
		return closingLocation;
	}

	public void setClosingLocation(String closingLocation) {
		this.closingLocation = closingLocation;
	}

	public String getClosingTime() {
		return closingTime;
	}

	public void setClosingTime(String closingTime) {
		this.closingTime = closingTime;
	}

	public String getDonor() {
		return donor;
	}

	public void setDonor(String donor) {
		this.donor = donor;
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

	public String getOpenRegistrationTime() {
		return openRegistrationTime;
	}

	public void setOpenRegistrationTime(String openRegistrationTime) {
		this.openRegistrationTime = openRegistrationTime;
	}

	public String getCloseRegistrationTime() {
		return closeRegistrationTime;
	}

	public void setCloseRegistrationTime(String closeRegistrationTime) {
		this.closeRegistrationTime = closeRegistrationTime;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public Long getSportId() {
		return sportId;
	}

	public void setSportId(Long sportId) {
		this.sportId = sportId;
	}

	public boolean isHasGroupStage() {
		return hasGroupStage;
	}

	public void setHasGroupStage(boolean hasGroupStage) {
		this.hasGroupStage = hasGroupStage;
	}

	@Override
	public String toString() {
		return "TournamentDTO [id=" + id + ", fullName=" + fullName + ", shortName=" + shortName + ", description="
				+ description + ", openingLocation=" + openingLocation + ", openingTime=" + openingTime
				+ ", closingLocation=" + closingLocation + ", closingTime=" + closingTime + ", donor=" + donor
				+ ", status=" + status + ", url=" + url + ", avatar=" + avatar + ", background=" + background
				+ ", openRegistrationTime=" + openRegistrationTime + ", closeRegistrationTime=" + closeRegistrationTime
				+ ", creatorId=" + creatorId + ", createDate=" + createDate + ", sportId=" + sportId
				+ ", hasGroupStage=" + hasGroupStage + "]";
	}
	
	

}