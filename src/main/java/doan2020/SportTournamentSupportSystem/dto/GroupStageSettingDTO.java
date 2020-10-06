package doan2020.SportTournamentSupportSystem.dto;

public class GroupStageSettingDTO {
	private Long id;
	
	private Long TournamentId;

	private Integer maxTeamPerTable;

	private Integer advanceTeamPerTable;
	
	private Integer bo;
	
	private boolean hasHomeMatch;
	private Long formatId;
	
	private String status;

	private String url;

	public GroupStageSettingDTO() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getMaxTeamPerTable() {
		return maxTeamPerTable;
	}

	public void setMaxTeamPerTable(Integer maxTeamPerTable) {
		this.maxTeamPerTable = maxTeamPerTable;
	}

	public Integer getAdvanceTeamPerTable() {
		return advanceTeamPerTable;
	}

	public void setAdvanceTeamPerTable(Integer advanceTeamPerTable) {
		this.advanceTeamPerTable = advanceTeamPerTable;
	}



	public boolean isHasHomeMatch() {
		return hasHomeMatch;
	}

	public void setHasHomeMatch(boolean hasHomeMatch) {
		this.hasHomeMatch = hasHomeMatch;
	}

	public Long getFormatId() {
		return formatId;
	}

	public void setFormatId(Long formatId) {
		this.formatId = formatId;
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

	@Override
	public String toString() {
		return "GroupStageSettingDTO [id=" + id + ", TournamentId=" + TournamentId + ", maxTeamPerTable="
				+ maxTeamPerTable + ", advanceTeamPerTable=" + advanceTeamPerTable + ", bo=" + bo + ", hasHomeMatch="
				+ hasHomeMatch + ", formatId=" + formatId + ", status=" + status + ", url=" + url + "]";
	}
	
	
	
	
}
