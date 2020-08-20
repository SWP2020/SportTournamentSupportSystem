
package doan2020.SportTournamentSupportSystem.dto;

public class CompetitionSettingDTO{

	private Long id;
	private Long competitionId;
	private int maxNumberOfTeam;
	private int maxMemberPerTeam;
	private int numberOfTable;
	private int numberOfTeamPassPerTable;
	private boolean homeGame;
	private String status;
	private String url;

	public CompetitionSettingDTO(){
	}

	public CompetitionSettingDTO(Long id, Long competitionId, int maxNumberOfTeam, int maxMemberPerTeam, int numberOfTable, int numberOfTeamPassPerTable, boolean homeGame, String status, String url){
		this.id = id;
		this.competitionId = competitionId;
		this.maxNumberOfTeam = maxNumberOfTeam;
		this.maxMemberPerTeam = maxMemberPerTeam;
		this.numberOfTable = numberOfTable;
		this.numberOfTeamPassPerTable = numberOfTeamPassPerTable;
		this.homeGame = homeGame;
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
	
	public int getMaxNumberOfTeam() {
		return maxNumberOfTeam;
	}
	
	public void setMaxNumberOfTeam(int maxNumberOfTeam) {
		this.maxNumberOfTeam = maxNumberOfTeam;
	}
	
	public int getMaxMemberPerTeam() {
		return maxMemberPerTeam;
	}
	
	public void setMaxMemberPerTeam(int maxMemberPerTeam) {
		this.maxMemberPerTeam = maxMemberPerTeam;
	}
	
	public int getNumberOfTable() {
		return numberOfTable;
	}
	
	public void setNumberOfTable(int numberOfTable) {
		this.numberOfTable = numberOfTable;
	}
	
	public int getNumberOfTeamPassPerTable() {
		return numberOfTeamPassPerTable;
	}
	
	public void setNumberOfTeamPassPerTable(int numberOfTeamPassPerTable) {
		this.numberOfTeamPassPerTable = numberOfTeamPassPerTable;
	}
	
	public boolean getHomeGame() {
		return homeGame;
	}
	
	public void setHomeGame(boolean homeGame) {
		this.homeGame = homeGame;
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