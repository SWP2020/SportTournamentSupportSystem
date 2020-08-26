package doan2020.SportTournamentSupportSystem.model.ContainerCollection;

import java.io.Serializable;
import java.util.ArrayList;

import doan2020.SportTournamentSupportSystem.entity.TeamEntity;
import doan2020.SportTournamentSupportSystem.model.Entity.Team;
import doan2020.SportTournamentSupportSystem.model.LogicStruct.Seed;
import doan2020.SportTournamentSupportSystem.model.LogicStruct.TeamDescription;

public class SeedList extends ArrayList<Seed> implements Serializable {

	private static final long serialVersionUID = 3368100502342752403L;

	public SeedList() {
	}

	public SeedList(ArrayList<TeamEntity> teams) {

		java.util.Collections.sort(teams, new TeamEntity());
		int seedNo = 0;
		for (TeamEntity team : teams) {
			seedNo ++;
			Seed s = new Seed(seedNo);
			Team t = new Team();
			t.setId(team.getId());
			t.setShortName(team.getShortName());
			t.setFullName(team.getFullName());
			s.setTeam(t);
			this.add(s);
		}
	}
	
	public SeedList(int totalTeam) {
		for (int i=1; i<=totalTeam; i++) {
			Seed s = new Seed(i);
			this.add(s);
		}
	}
	
	public SeedList(int totalTeam, int fisrtSeed) {
		
		for (int i=fisrtSeed; i<=totalTeam + fisrtSeed - 1; i++) {
			Seed s = new Seed(i);
			this.add(s);
		}
	}
	
	public SeedList(ArrayList<TeamDescription> descriptions, int totalTeam) {
		for (TeamDescription description: descriptions) {
			Seed s = new Seed();
			s.setDescription(description);
			this.add(s);
			
		}
	}

}
