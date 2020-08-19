package doan2020.SportTournamentSupportSystem.model.LogicEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import doan2020.SportTournamentSupportSystem.entity.TeamEntity;

public class SeedList extends ArrayList<Team> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3368100502342752403L;
	
	
	public SeedList() {
	}
	
	public SeedList(ArrayList<TeamEntity> teams) {
		
		java.util.Collections.sort(teams, new TeamEntity());
		
	}
	
}
