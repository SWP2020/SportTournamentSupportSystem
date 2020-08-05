
package doan2020.SportTournamentSupportSystem.dto;

public class SportDTO{

	private Long id;
	private String fullName;
	private String shortName;
	private Long scoringUnitId;
	private String description;
	private String status;
	private String url;

	public SportDTO(){
	}

	public SportDTO(Long id, String fullName, String shortName, Long scoringUnitId, String description, String status, String url){
		this.id = id;
		this.fullName = fullName;
		this.shortName = shortName;
		this.scoringUnitId = scoringUnitId;
		this.description = description;
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
	
	public Long getScoringUnitId() {
		return scoringUnitId;
	}
	
	public void setScoringUnitId(Long scoringUnitId) {
		this.scoringUnitId = scoringUnitId;
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
	

}