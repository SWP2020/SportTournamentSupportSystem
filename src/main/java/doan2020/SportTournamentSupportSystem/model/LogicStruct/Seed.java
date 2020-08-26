package doan2020.SportTournamentSupportSystem.model.LogicStruct;

import java.io.Serializable;

import doan2020.SportTournamentSupportSystem.model.Entity.Team;

public class Seed implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int seedNo;
	private Team team;
	private TeamDescription description;
	
	public Seed() {
	}
	
	public Seed(int seedNo) {
		this.description = new TeamDescription(0l, seedNo);
	}
	
	
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	public TeamDescription getDescription() {
		return description;
	}
	public void setDescription(TeamDescription description) {
		this.description = description;
	}
	public int getSeedNo() {
		return seedNo;
	}
	public void setSeedNo(int seedNo) {
		this.seedNo = seedNo;
	}
	
}
