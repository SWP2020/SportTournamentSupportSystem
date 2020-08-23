
package doan2020.SportTournamentSupportSystem.dto;

import java.util.Date;

public class CompetitionDTO {

	private Long id;

	private String name;

	private String description;

	private String status;

	private String url;
	
	private Long tournamentId;

	private Long sportId;

	private Long finalStageFormatId;

	private Long groupStageFormatId;

	private Long groupStageSettingId;

	private Long finalStageSettingId;

	public CompetitionDTO() {
	}
    
	

	public CompetitionDTO(Long id, String name, String description, String status, String url, Long tournamentId,
			Long sportId, Long finalStageFormatId, Long groupStageFormatId, Long groupStageSettingId,
			Long finalStageSettingId) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.status = status;
		this.url = url;
		this.tournamentId = tournamentId;
		this.sportId = sportId;
		this.finalStageFormatId = finalStageFormatId;
		this.groupStageFormatId = groupStageFormatId;
		this.groupStageSettingId = groupStageSettingId;
		this.finalStageSettingId = finalStageSettingId;
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

	public Long getSportId() {
		return sportId;
	}

	public void setSportId(Long sportId) {
		this.sportId = sportId;
	}

	public Long getFinalStageFormatId() {
		return finalStageFormatId;
	}

	public void setFinalStageFormatId(Long finalStageFormatId) {
		this.finalStageFormatId = finalStageFormatId;
	}

	public Long getGroupStageFormatId() {
		return groupStageFormatId;
	}

	public void setGroupStageFormatId(Long groupStageFormatId) {
		this.groupStageFormatId = groupStageFormatId;
	}

	public Long getGroupStageSettingId() {
		return groupStageSettingId;
	}

	public void setGroupStageSettingId(Long groupStageSettingId) {
		this.groupStageSettingId = groupStageSettingId;
	}

	public Long getFinalStageSettingId() {
		return finalStageSettingId;
	}

	public void setFinalStageSettingId(Long finalStageSettingId) {
		this.finalStageSettingId = finalStageSettingId;
	}



	public Long getTournamentId() {
		return tournamentId;
	}



	public void setTournamentId(Long tournamentId) {
		this.tournamentId = tournamentId;
	}
	
	

}