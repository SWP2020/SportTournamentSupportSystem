package doan2020.SportTournamentSupportSystem.model.Struct;

import java.io.Serializable;
import java.util.ArrayList;

import doan2020.SportTournamentSupportSystem.entity.TeamEntity;
import doan2020.SportTournamentSupportSystem.model.Entity.Match;
import doan2020.SportTournamentSupportSystem.model.EntityStruct.RankingTable;
import doan2020.SportTournamentSupportSystem.model.EntityStruct.SeedList;
import doan2020.SportTournamentSupportSystem.model.LogicStruct.RankingTableSlot;
import doan2020.SportTournamentSupportSystem.model.LogicStruct.TeamDescription;

abstract public class ScheduleStruct implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int totalTeam;
	protected SeedList seedList = new SeedList();
	protected Integer totalRound;
	protected ArrayList<Match> matches = new ArrayList<>();
	protected ArrayList<TeamDescription> descriptions;
	protected RankingTable rankingTable;
	protected int tableId = -1;
	
	public ScheduleStruct() {
	}
	
	public ScheduleStruct(int totalTeam) {
		this.totalTeam = totalTeam;
		this.rankingTable = new RankingTable(totalTeam);
	}
	
	public ScheduleStruct(int totalTeam, int tableId) {
		this.tableId = tableId;
		this.totalTeam = totalTeam;
		this.rankingTable = new RankingTable(totalTeam, tableId);
	}

	protected void updateSeedList(ArrayList<TeamEntity> teams) {
		this.seedList = new SeedList(teams);
		this.totalTeam = this.seedList.size();

	}
	
	protected void updateDescriptions(ArrayList<TeamDescription> descriptions) {
		this.descriptions = descriptions;
	}
	
	abstract protected void applyDescriptions();
	
	abstract protected void applySeedList();
	
	public void applySeedList(ArrayList<TeamEntity> teams) {
		this.updateSeedList(teams);
		this.applySeedList();
	}
	
	public void applyDescriptions(ArrayList<TeamDescription> descriptions) {
		if (descriptions.size() != this.totalTeam) {
			return;
		}
		this.updateDescriptions(descriptions);
		this.applyDescriptions();
	}
	
	public RankingTable getRankingTable() {
		return this.rankingTable;
	}

	public int getTotalTeam() {
		return totalTeam;
	}

	public Integer getTotalRound() {
		return totalRound;
	}

	public ArrayList<Match> getMatches() {
		return matches;
	}

	public ArrayList<TeamDescription> getDescriptions() {
		return descriptions;
	}

	public int getTableId() {
		return tableId;
	}
	
	public void setTableId(int tableId) {
		if (tableId < 0) {
			return;
		}
		this.tableId = tableId;
		this.rankingTable = new RankingTable(this.totalTeam, this.tableId);
	}
}
