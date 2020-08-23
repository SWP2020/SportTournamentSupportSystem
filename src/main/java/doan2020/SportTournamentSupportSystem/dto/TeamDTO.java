
package doan2020.SportTournamentSupportSystem.dto;

public class TeamDTO{

	private Long id;

	private String fullName;

	private String shortName;

	private String description;

	private Long seedNo;

	private String status;

	private String url;
	
	private Long creatorId;
	
	private Long competitionId;
	
	public TeamDTO() {
		
	}

	public TeamDTO(Long id, String fullName, String shortName, String description, Long seedNo, String status,
			String url, Long creatorId, Long competitionId) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.shortName = shortName;
		this.description = description;
		this.seedNo = seedNo;
		this.status = status;
		this.url = url;
		this.creatorId = creatorId;
		this.competitionId = competitionId;
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

	public Long getSeedNo() {
		return seedNo;
	}

	public void setSeedNo(Long seedNo) {
		this.seedNo = seedNo;
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

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public Long getCompetitionId() {
		return competitionId;
	}

	public void setCompetitionId(Long competitionId) {
		this.competitionId = competitionId;
	}
	
}