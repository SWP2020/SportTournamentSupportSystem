package doan2020.SportTournamentSupportSystem.dtIn;

public class EditCompetitionDtIn {
	private Long id;
	
	private String name;

	private String status;
	
	private Long tournamentID;
	
	private Long sportID;
	
	private String description;
	
	public EditCompetitionDtIn() {
		
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
