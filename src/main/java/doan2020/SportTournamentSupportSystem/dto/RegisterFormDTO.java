
package doan2020.SportTournamentSupportSystem.dto;

public class RegisterFormDTO{

	private Long id;
	private Long competitionId;
	private Long teamId;
	private Long competitionSettingId;
	private String description;
	private String status;
	private String url;

	public RegisterFormDTO(){
	}

	public RegisterFormDTO(Long id, Long competitionId, Long teamId, Long competitionSettingId, String description, String status, String url){
		this.id = id;
		this.competitionId = competitionId;
		this.teamId = teamId;
		this.competitionSettingId = competitionSettingId;
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
	
	public Long getCompetitionId() {
		return competitionId;
	}
	
	public void setCompetitionId(Long competitionId) {
		this.competitionId = competitionId;
	}
	
	public Long getTeamId() {
		return teamId;
	}
	
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
	
	public Long getCompetitionSettingId() {
		return competitionSettingId;
	}
	
	public void setCompetitionSettingId(Long competitionSettingId) {
		this.competitionSettingId = competitionSettingId;
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