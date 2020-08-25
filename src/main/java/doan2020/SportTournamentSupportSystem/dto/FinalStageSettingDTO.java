package doan2020.SportTournamentSupportSystem.dto;

public class FinalStageSettingDTO {
	
	private Long id;
	private boolean hasHomeMatch;
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

	public Long getFormatId() {
		return formatId;
	}

	public void setFormatId(Long formatId) {
		this.formatId = formatId;
	}

	public boolean isHasHomeMatch() {
		return hasHomeMatch;
	}

	public void setHasHomeMatch(boolean hasHomeMatch) {
		this.hasHomeMatch = hasHomeMatch;
	}
	
	
	

}
