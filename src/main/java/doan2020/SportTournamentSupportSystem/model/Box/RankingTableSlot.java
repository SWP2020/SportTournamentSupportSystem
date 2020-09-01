package doan2020.SportTournamentSupportSystem.model.Box;

import java.io.Serializable;
import java.util.Comparator;

import doan2020.SportTournamentSupportSystem.model.Entity.Team;
import doan2020.SportTournamentSupportSystem.model.Indexing.BoxDescription;

public class RankingTableSlot implements Serializable, Comparator<RankingTableSlot>{

	private static final long serialVersionUID = 1L;
	
	private Team team;
	private BoxDescription description;
	private Double difference = 0.0;
	private Integer score = 0;

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
	
	public Double getDifference() {
		return difference;
	}

	public void setDifference(Double difference) {
		this.difference = difference;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@Override
	public int compare(RankingTableSlot o1, RankingTableSlot o2) {
		Integer score1 = o1.getScore();
		Integer score2 = o2.getScore();
		Double diff1 = o1.getDifference();
		Double diff2 = o2.getDifference();
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
