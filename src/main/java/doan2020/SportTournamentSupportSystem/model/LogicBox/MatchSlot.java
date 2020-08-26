package doan2020.SportTournamentSupportSystem.model.LogicBox;

import java.io.Serializable;

import doan2020.SportTournamentSupportSystem.model.Entity.Team;
import doan2020.SportTournamentSupportSystem.model.LogicStruct.TeamDescription;

public class MatchSlot implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Team team;
	private TeamDescription description;
	
	
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
	
	
	
}
