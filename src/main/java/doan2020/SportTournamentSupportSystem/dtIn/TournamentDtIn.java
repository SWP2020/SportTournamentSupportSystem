package doan2020.SportTournamentSupportSystem.dtIn;

import java.util.Date;

import doan2020.SportTournamentSupportSystem.entity.UserEntity;

public class TournamentDtIn {

	private Long id;

	private String fullName;

	private String password;

	private String shortName;
	
	private String description;

	private UserEntity creator;
	
	private String openingLocation;
	
	private Date openingTime;
	
	private String closingLocation;
	
	private Date closingTime;
	
	private String donor;
	
	private String status;
	
	private String url;

	public TournamentDtIn() {
		
	}

	public TournamentDtIn(Long id, String fullName, String password, String shortName, String description,
			UserEntity creator, String openingLocation, Date openingTime, String closingLocation, Date closingTime,
			String donor, String status, String url) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.password = password;
		this.shortName = shortName;
		this.description = description;
		this.creator = creator;
		this.openingLocation = openingLocation;
		this.openingTime = openingTime;
		this.closingLocation = closingLocation;
		this.closingTime = closingTime;
		this.donor = donor;
		this.status = status;
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public UserEntity getCreator() {
		return creator;
	}

	public void setCreator(UserEntity creator) {
		this.creator = creator;
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

}