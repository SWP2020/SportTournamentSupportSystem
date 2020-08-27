package doan2020.SportTournamentSupportSystem.model.Schedule.Stage;


import doan2020.SportTournamentSupportSystem.model.ContainerCollection.RankingTable;
import doan2020.SportTournamentSupportSystem.model.Schedule.ScheduleStruct;
import doan2020.SportTournamentSupportSystem.model.Schedule.Format.DoubleEliminationTree;
import doan2020.SportTournamentSupportSystem.model.Schedule.Format.RoundRobinTable;
import doan2020.SportTournamentSupportSystem.model.Schedule.Format.SingleEliminationTree;

public class FinalStageSchedule {
	private int totalTeam;
	private String formatName;
	private int tableId;
	private RankingTable rankingTable;
	private boolean hasHomeMatch;
	private boolean hasMatch34;
	
	private ScheduleStruct schedule;
	
	
	
	public FinalStageSchedule(SingleEliminationTree schedule) {
		this.schedule = schedule;
	}
	
	public FinalStageSchedule(DoubleEliminationTree schedule) {
		this.schedule = schedule;
	}
	
	public FinalStageSchedule(RoundRobinTable schedule) {
		this.schedule = schedule;
	}

	public int getTotalTeam() {
		return totalTeam;
	}

	public void setTotalTeam(int totalTeam) {
		this.totalTeam = totalTeam;
	}

	public String getFormatName() {
		return formatName;
	}

	public void setFormatName(String formatName) {
		this.formatName = formatName;
	}

	public int getTableId() {
		return tableId;
	}

	public void setTableId(int tableId) {
		this.tableId = tableId;
	}

	public RankingTable getRankingTable() {
		return rankingTable;
	}

	public void setRankingTable(RankingTable rankingTable) {
		this.rankingTable = rankingTable;
	}

	public boolean isHasHomeMatch() {
		return hasHomeMatch;
	}

	public void setHasHomeMatch(boolean hasHomeMatch) {
		this.hasHomeMatch = hasHomeMatch;
	}

	public boolean isHasMatch34() {
		return hasMatch34;
	}

	public void setHasMatch34(boolean hasMatch34) {
		this.hasMatch34 = hasMatch34;
	}

	public ScheduleStruct getSchedule() {
		return schedule;
	}

	public void setSchedule(ScheduleStruct schedule) {
		this.schedule = schedule;
	}
	
	
	
}
