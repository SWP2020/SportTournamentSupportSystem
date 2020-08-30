package doan2020.SportTournamentSupportSystem.model.Schedule.DTO;

import java.io.Serializable;
import java.util.ArrayList;

import doan2020.SportTournamentSupportSystem.model.BoxCollection.RankingTable;
import doan2020.SportTournamentSupportSystem.model.Entity.Match;

abstract public class FinalStageScheduleDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	protected int totalTeam;
	protected int totalRound;
	protected String formatName;
	protected int tableId;
	protected String tableName;
	protected int firstSeed;
	protected RankingTable rankingTable;
	protected ArrayList<Match> matches;
	protected String status;
	
	protected ArrayList<String> roundsNaming;

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

	public ArrayList<Match> getMatches() {
		return matches;
	}

	public void setMatches(ArrayList<Match> matches) {
		this.matches = matches;
	}

	public int getTotalRound() {
		return totalRound;
	}

	public void setTotalRound(int totalRound) {
		this.totalRound = totalRound;
	}

	public int getFirstSeed() {
		return firstSeed;
	}

	public void setFirstSeed(int firstSeed) {
		this.firstSeed = firstSeed;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public ArrayList<String> getRoundsNaming() {
		if (this.roundsNaming == null) {
			setRoundsNaming();
		}
		return roundsNaming;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public abstract void setRoundsNaming();

}
