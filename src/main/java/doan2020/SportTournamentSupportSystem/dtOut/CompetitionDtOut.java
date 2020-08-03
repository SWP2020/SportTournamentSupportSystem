package doan2020.SportTournamentSupportSystem.dtOut;

import java.util.Date;

public class CompetitionDtOut {

private Long id;
	
	private String name;
	
	private String description;
	
	private boolean groupStage;
	
	private String status;
	
	private Long url;
	
	private Long tournamentId;
	
	private Long sportId;
	
	private Long mainFormatId;
	
	private Long groupStageFormatId;
	
	public CompetitionDtOut() {
		
	}

	public CompetitionDtOut(Long id, String name, String description, boolean groupStage, String status, Long url,
			Long tournamentId, Long sportId, Long mainFormatId, Long groupStageFormatId) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.groupStage = groupStage;
		this.status = status;
		this.url = url;
		this.tournamentId = tournamentId;
		this.sportId = sportId;
		this.mainFormatId = mainFormatId;
		this.groupStageFormatId = groupStageFormatId;
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

	public boolean isGroupStage() {
		return groupStage;
	}

	public void setGroupStage(boolean groupStage) {
		this.groupStage = groupStage;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getUrl() {
		return url;
	}

	public void setUrl(Long url) {
		this.url = url;
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

	public Long getGroupStageFormatId() {
		return groupStageFormatId;
	}

	public void setGroupStageFormatId(Long groupStageFormatId) {
		this.groupStageFormatId = groupStageFormatId;
	}
	
	

	
}
