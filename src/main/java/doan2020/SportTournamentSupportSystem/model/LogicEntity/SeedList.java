package doan2020.SportTournamentSupportSystem.model.LogicEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import doan2020.SportTournamentSupportSystem.entity.TeamEntity;

public class SeedList extends ArrayList<Team> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3368100502342752403L;
	
	
	public SeedList() {
	}
	
	public SeedList(Collection<TeamEntity> teams) {
		
	}
	
}
