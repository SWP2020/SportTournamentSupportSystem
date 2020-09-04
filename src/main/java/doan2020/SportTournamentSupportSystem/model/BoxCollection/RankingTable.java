package doan2020.SportTournamentSupportSystem.model.BoxCollection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import doan2020.SportTournamentSupportSystem.entity.TeamEntity;
import doan2020.SportTournamentSupportSystem.model.Box.RankingTableSlot;
import doan2020.SportTournamentSupportSystem.model.Box.Seed;
import doan2020.SportTournamentSupportSystem.model.Entity.Team;
import doan2020.SportTournamentSupportSystem.model.Indexing.BoxDescription;

public class RankingTable extends ArrayList<RankingTableSlot> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int totalTeam;

	public RankingTable() {
		super();
	}	

	public RankingTable(int totalTeam) {
		this.totalTeam = totalTeam;
		for(int i=0; i<totalTeam; i++) {
			BoxDescription description = new BoxDescription(6l, i + 1);
			RankingTableSlot slot = new RankingTableSlot();
			slot.setDescription(description);
			this.add(slot);
			
		}
	}
	
	public RankingTable(ArrayList<Team> teams) {
		this.totalTeam = teams.size();
		for (Team team: teams) {
			RankingTableSlot slot = new RankingTableSlot(team);
			this.add(slot);
		}
		Collections.sort(this, new RankingTableSlot());
	}
	
	public RankingTable(int totalTeam, int tableId) {
		this.totalTeam = totalTeam;
		for(int i=0; i<totalTeam; i++) {
			BoxDescription description = new BoxDescription(tableId, (long) (i + 1));
			RankingTableSlot slot = new RankingTableSlot();
			slot.setDescription(description);
			this.add(slot);
		}
	}
	
	public void applyTeams(ArrayList<TeamEntity> teams) {
		java.util.Collections.sort(teams, new TeamEntity());
		int seedNo = 0;
		for (RankingTableSlot slot : this) {
			Team t = new Team();
			t.setId(teams.get(seedNo).getId());
			t.setShortName(teams.get(seedNo).getShortName());
			t.setFullName(teams.get(seedNo).getFullName());
			t.setTotalLose(0);
			t.setTotalWin(0);
			
			slot.setTeam(t);
			slot.setDifference(0.0);
			slot.setScore(0);
			seedNo++;
		}
	}
	
	public void applyDescriptions(int tableId) {
		for(int i=0; i<this.totalTeam; i++) {
			BoxDescription description = new BoxDescription(tableId, (long) (i + 1));
			RankingTableSlot slot = this.get(i);
			slot.setDescription(description);
		}
	}
	
	
//	public static void main(String[] args) {
//		RankingTable x = new RankingTable(4, 9);
//		for (RankingTableSlot y: x) {
//			System.out.println(y.getDescription().getDescription());
//		}
//	}

}
