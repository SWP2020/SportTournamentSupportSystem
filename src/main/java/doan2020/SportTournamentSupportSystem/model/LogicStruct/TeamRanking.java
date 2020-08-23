package doan2020.SportTournamentSupportSystem.model.LogicStruct;

import java.io.Serializable;
import java.util.Comparator;

import doan2020.SportTournamentSupportSystem.model.Entity.Team;

public class TeamRanking implements Serializable, Comparator<TeamRanking>{

	private static final long serialVersionUID = 1L;
	
	private Team team;
	private Number score;
	private int rank;
	
	
	
	
	public TeamRanking(Team team, Number score) {

		this.team = team;
		this.score = score;
	}
	
	public TeamRanking(Team team) {

		this.team = team;
		this.score = 0;
	}
	
	public TeamRanking() {

	}
	
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	public Number getScore() {
		return score;
	}
	public void setScore(Number score) {
		this.score = score;
	}
	
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	@Override
	public int compare(TeamRanking o1, TeamRanking o2) {
		return (int) Math.ceil(o1.getScore().doubleValue() - o2.getScore().doubleValue());
	}
	
}
