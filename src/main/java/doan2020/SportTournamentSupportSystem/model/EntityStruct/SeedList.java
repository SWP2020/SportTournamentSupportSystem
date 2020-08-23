package doan2020.SportTournamentSupportSystem.model.EntityStruct;

import java.io.Serializable;
import java.util.ArrayList;

import doan2020.SportTournamentSupportSystem.entity.TeamEntity;
import doan2020.SportTournamentSupportSystem.model.Entity.Team;

public class SeedList extends ArrayList<Team> implements Serializable{

	private static final long serialVersionUID = 3368100502342752403L;
	
	
	public SeedList() {
	}
	
	public SeedList(ArrayList<TeamEntity> teams) {
		
		java.util.Collections.sort(teams, new TeamEntity());
		for (TeamEntity team: teams) {
			Team t = new Team();
			t.setId(team.getId());
			t.setShortName(team.getShortName());
			t.setFullName(team.getFullName());
			this.add(t);
		}
	}
	
}
