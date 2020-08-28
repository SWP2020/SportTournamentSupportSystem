package doan2020.SportTournamentSupportSystem.model.Box;

import java.io.Serializable;
import java.util.Comparator;

import doan2020.SportTournamentSupportSystem.model.Entity.Team;
import doan2020.SportTournamentSupportSystem.model.Naming.BoxDescription;

public class RankingTableSlot implements Serializable, Comparator<RankingTableSlot>{

	private static final long serialVersionUID = 1L;
	
	private Team team;
	private BoxDescription description;

	public RankingTableSlot(Team team) {
		this.team = team;
	}
	
	public RankingTableSlot() {
	}
	
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	
	@Override
	public int compare(RankingTableSlot o1, RankingTableSlot o2) {
		Team team1 = o1.getTeam();
		Team team2 = o2.getTeam();
		Integer score1 = team1.getScore();
		Integer score2 = team2.getScore();
		Double diff1 = team1.getDifference();
		Double diff2 = team2.getDifference();
		if (score1 == score2) {
			return (int)Math.ceil(diff1 - diff2);
		} else {
			return score1 - score2;
		}
	}

	public BoxDescription getDescription() {
		return description;
	}

	public void setDescription(BoxDescription description) {
		this.description = description;
	}
	
}
