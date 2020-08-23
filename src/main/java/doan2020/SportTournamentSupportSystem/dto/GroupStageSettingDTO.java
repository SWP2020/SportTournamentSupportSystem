package doan2020.SportTournamentSupportSystem.dto;

public class GroupStageSettingDTO {
	private Long id;

	private int maxTeamPerTable;

	private int advanceTeamPerTable;

	public GroupStageSettingDTO(Long id, int maxTeamPerTable, int advanceTeamPerTable) {
		super();
		this.id = id;
		this.maxTeamPerTable = maxTeamPerTable;
		this.advanceTeamPerTable = advanceTeamPerTable;
	}

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
	
	
}
