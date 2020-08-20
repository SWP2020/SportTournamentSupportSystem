package doan2020.SportTournamentSupportSystem.model.LogicEntity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;

import doan2020.SportTournamentSupportSystem.entity.MatchEntity;

public class MatchInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	Long id;
	Integer matchNo;
	Integer roundNo;
	String name;
	
	Team team1;
	String team1Description;
	
	Team team2;
	String team2Description;
	
	Team winner;
	Team loser;
	
	@JsonBackReference
	MatchEntity realMatch;
	
	/*
	 * status:
	 * 0: có sẵn 2 đội (round 1 win branch)
	 * 1: 2 đội lấy từ 2 trận thắng của left và right ( round 2+ )
	 * 2: có sẵn đội 1, đội 2 lấy từ trận thắng của right (round 2 win branch)
	 * 3: 2 đội lấy từ 2 trận thua của left và right (round 1 lose branch)
	 * 4: đội 1 lấy từ đội thua của trận left, đội 2 lấy từ trận thắng của right (round 2+ lose branch)
	 */
	private Integer status;
	
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Team getTeam1() {
		return team1;
	}
	public void setTeam1(Team team1) {
		this.team1 = team1;
	}
	public Team getTeam2() {
		return team2;
	}
	public void setTeam2(Team team2) {
		this.team2 = team2;
	}
	public Team getWinner() {
		return winner;
	}
	public void setWinner(Team winner) {
		this.winner = winner;
	}
	public Team getLoser() {
		return loser;
	}
	public void setLoser(Team loser) {
		this.loser = loser;
	}
	public Integer getMatchNo() {
		return matchNo;
	}
	public void setMatchNo(Integer matchNo) {
		this.matchNo = matchNo;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getRoundNo() {
		return roundNo;
	}
	public void setRoundNo(Integer roundNo) {
		this.roundNo = roundNo;
	}
	public String getTeam1Description() {
		return team1Description;
	}
	public void setTeam1Description(String team1Description) {
		this.team1Description = team1Description;
	}
	public String getTeam2Description() {
		return team2Description;
	}
	public void setTeam2Description(String team2Description) {
		this.team2Description = team2Description;
	}
	
	
	
	
	
}
