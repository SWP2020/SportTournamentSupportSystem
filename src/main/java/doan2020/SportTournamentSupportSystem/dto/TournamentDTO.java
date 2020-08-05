
package doan2020.SportTournamentSupportSystem.dto;

public class TournamentDTO{

	private Long id;
	private String fullName;
	private String shortName;
	private String description;
	private Long creatorId;
	private String openingLocation;
	private String openingTime;
	private String closingLocation;
	private String closingTime;
	private String donor;
	private String status;
	private String url;

	public TournamentDTO(){
	}

	public TournamentDTO(Long id, String fullName, String shortName, String description, Long creatorId, String openingLocation, String openingTime, String closingLocation, String closingTime, String donor, String status, String url){
		this.id = id;
		this.fullName = fullName;
		this.shortName = shortName;
		this.description = description;
		this.creatorId = creatorId;
		this.openingLocation = openingLocation;
		this.openingTime = openingTime;
		this.closingLocation = closingLocation;
		this.closingTime = closingTime;
		this.donor = donor;
		this.status = status;
		this.url = url;
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
	
	public Long getCreatorId() {
		return creatorId;
	}
	
	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
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
	

}