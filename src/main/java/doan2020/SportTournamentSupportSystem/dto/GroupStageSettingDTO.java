package doan2020.SportTournamentSupportSystem.dto;

public class GroupStageSettingDTO {
	private Long id;

	private int maxTeamPerTable;

	private int advanceTeamPerTable;
	
	private boolean has_home_match;
	private Long formatId;

	public GroupStageSettingDTO() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getMaxTeamPerTable() {
		return maxTeamPerTable;
	}

	public void setMaxTeamPerTable(int maxTeamPerTable) {
		this.maxTeamPerTable = maxTeamPerTable;
	}

	public int getAdvanceTeamPerTable() {
		return advanceTeamPerTable;
	}

	public void setAdvanceTeamPerTable(int advanceTeamPerTable) {
		this.advanceTeamPerTable = advanceTeamPerTable;
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
