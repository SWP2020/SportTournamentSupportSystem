package doan2020.SportTournamentSupportSystem.model.EntityStruct;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import org.hibernate.mapping.Collection;

import doan2020.SportTournamentSupportSystem.model.Entity.Team;
import doan2020.SportTournamentSupportSystem.model.LogicStruct.RankingTableSlot;
import doan2020.SportTournamentSupportSystem.model.LogicStruct.TeamDescription;

public class RankingTable extends ArrayList<RankingTableSlot> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RankingTable() {
		super();
	}	

	public RankingTable(int totalTeam) {
		super();
		for(int i=0; i<totalTeam; i++) {
			TeamDescription description = new TeamDescription(6l, i + 1);
			RankingTableSlot slot = new RankingTableSlot();
			slot.setDescription(description);
			this.add(slot);
			
		}
	}
	
	public RankingTable(ArrayList<Team> teams) {
		super();
		for (Team team: teams) {
			RankingTableSlot slot = new RankingTableSlot(team);
			this.add(slot);
		}
		Collections.sort(this, new RankingTableSlot());
	}
	
	public RankingTable(int totalTeam, int tableId) {
		super();
		for(int i=0; i<totalTeam; i++) {
			TeamDescription description = new TeamDescription(tableId, (long) (i + 1));
			RankingTableSlot slot = new RankingTableSlot();
			slot.setDescription(description);
			this.add(slot);
		}
	}
	
	
//	public static void main(String[] args) {
//		RankingTable x = new RankingTable(4, 9);
//		for (RankingTableSlot y: x) {
//			System.out.println(y.getDescription().getDescription());
//		}
//	}

}
