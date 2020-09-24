package doan2020.SportTournamentSupportSystem.dto;

public class FinalStageSettingDTO {
	
	private Long id;
	private Long TournamentId;
	private boolean hasHomeMatch;
	private Long formatId;
	private Integer bo;
	private String status;
	private String url;
	
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

	public Long getTournamentId() {
		return TournamentId;
	}

	public void setTournamentId(Long TournamentId) {
		this.TournamentId = TournamentId;
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

	public Integer getBo() {
		return bo;
	}

	public void setBo(Integer bo) {
		this.bo = bo;
	}
	
	
	

}
