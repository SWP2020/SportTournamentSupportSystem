
package doan2020.SportTournamentSupportSystem.dto;

public class CompetitionDTO{

	private Long id;
	private String name;
	private String description;
	private Long tournamentId;
	private Long sportId;
	private Long mainFormatId;
	private boolean groupStage;
	private Long groupStageFormatId;
	private String status;
	private String url;

	public CompetitionDTO(){
	}

	public CompetitionDTO(Long id, String name, String description, Long tournamentId, Long sportId, Long mainFormatId, boolean groupStage, Long groupStageFormatId, String status, String url){
		this.id = id;
		this.name = name;
		this.description = description;
		this.tournamentId = tournamentId;
		this.sportId = sportId;
		this.mainFormatId = mainFormatId;
		this.groupStage = groupStage;
		this.groupStageFormatId = groupStageFormatId;
		this.status = status;
		this.url = url;
	}


	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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
	
	public Long getTournamentId() {
		return tournamentId;
	}
	
	public void setTournamentId(Long tournamentId) {
		this.tournamentId = tournamentId;
	}
	
	public Long getSportId() {
		return sportId;
	}
	
	public void setSportId(Long sportId) {
		this.sportId = sportId;
	}
	
	public Long getMainFormatId() {
		return mainFormatId;
	}
	
	public void setMainFormatId(Long mainFormatId) {
		this.mainFormatId = mainFormatId;
	}
	
	public boolean getGroupStage() {
		return groupStage;
	}
	
	public void setGroupStage(boolean groupStage) {
		this.groupStage = groupStage;
	}
	
	public Long getGroupStageFormatId() {
		return groupStageFormatId;
	}
	
	public void setGroupStageFormatId(Long groupStageFormatId) {
		this.groupStageFormatId = groupStageFormatId;
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