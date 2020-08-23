package doan2020.SportTournamentSupportSystem.dto;

public class FinalStageSettingDTO {
	
	private Long id;
	private boolean has_home_match;
	private Long formatId;
	
	public FinalStageSettingDTO() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FinalStageSettingDTO(Long id) {
		super();
		this.id = id;
	}

	public boolean isHas_home_match() {
		return has_home_match;
	}

	public void setHas_home_match(boolean has_home_match) {
		this.has_home_match = has_home_match;
	}

	public Long getFormatId() {
		return formatId;
	}

	public void setFormatId(Long formatId) {
		this.formatId = formatId;
	}
	
	

}
