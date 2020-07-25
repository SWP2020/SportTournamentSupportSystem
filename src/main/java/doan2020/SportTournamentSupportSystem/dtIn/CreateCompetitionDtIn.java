package doan2020.SportTournamentSupportSystem.dtIn;

public class CreateCompetitionDtIn {
	private String name;

	private String status;
	
	private Long tournamentID;
	
	private Long sportID;
	
	private String description;
	
	public CreateCompetitionDtIn(){
		
	}


	public Long getTournamentID() {
		return tournamentID;
	}


	public void setTournamentID(Long tournamentID) {
		this.tournamentID = tournamentID;
	}


	public Long getSportID() {
		return sportID;
	}


	public void setSportID(Long sportID) {
		this.sportID = sportID;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
 
}
