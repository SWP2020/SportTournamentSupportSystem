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
		System.out.println("RankingTable: applyTeams: start");
		java.util.Collections.sort(teams, new TeamEntity());
		int seedNo = 0;
		for (RankingTableSlot slot : this) {
			Team t = new Team();
			t.setId(teams.get(seedNo).getId());
			t.setShortName(teams.get(seedNo).getShortName());
			t.setFullName(teams.get(seedNo).getFullName());
			
			slot.setTotalLose(0);
			slot.setTotalWin(0);
			
			slot.setTeam(t);
			slot.setDifference(0.0);
			slot.setScore(0);
			seedNo++;
		}
		System.out.println("RankingTable: applyTeams: finish");
	}
	
	public void applyDescriptions(int tableId) {
		for(int i=0; i<this.totalTeam; i++) {
			BoxDescription description = new BoxDescription(tableId, (long) (i + 1));
			RankingTableSlot slot = this.get(i);
			slot.setDescription(description);
		}
	}
	
	public void updateByTeamId(Long teamId, Integer score, Double diff, boolean isWin) {
		for(RankingTableSlot slot: this) {
			if (slot.getTeam() != null && slot.getTeam().getId().longValue() == teamId.longValue()) {
				slot.setScore(slot.getScore() + score);
				slot.setDifference(slot.getDifference() + diff);
				if (isWin) {
					slot.setTotalWin(slot.getTotalWin() + 1);
				} else {
					slot.setTotalLose(slot.getTotalLose() + 1);
				}
				break;
			}
		}
		Collections.sort(this, new RankingTableSlot());
	}
//	public static void main(String[] args) {
//		RankingTable x = new RankingTable(4, 9);
//		for (RankingTableSlot y: x) {
//			System.out.println(y.getDescription().getDescription());
//		}
//	}

}
