package doan2020.SportTournamentSupportSystem.model;

import java.util.Collection;
import java.util.HashMap;

import doan2020.SportTournamentSupportSystem.entity.CompetitionEntity;
import doan2020.SportTournamentSupportSystem.entity.MatchEntity;
import doan2020.SportTournamentSupportSystem.entity.TeamEntity;

public class MatchTree {
	
	private CompetitionEntity competition;
	
	private int numOfSet;
	
	private int totalTeams;
	
	private Collection<MatchEntity> matches;
	
	private HashMap<String, TeamEntity> teams;
	
	
	public MatchTree() {
	}
	
	public MatchTree(int totalTeams) {
		this.totalTeams = totalTeams;
	}
	
	public MatchTree createTree(MatchEntity parent, int left, int right) {
		if (totalTeams < 1) {
			return null;
		}
		
		if (totalTeams == 1) {
			MatchEntity match = new MatchEntity();
			match.setCompetition(competition);
			match.setNextMatch(parent);
			String name = "match-" + Integer.toString(left);
			match.setName(name);
			match.setNumOfSet(numOfSet);
		}
	}

}
