package doan2020.SportTournamentSupportSystem.model.ScheduleFormat;

import java.util.ArrayList;

import doan2020.SportTournamentSupportSystem.entity.TeamEntity;
import doan2020.SportTournamentSupportSystem.model.Entity.Match;
import doan2020.SportTournamentSupportSystem.model.EntityStruct.SeedList;

abstract public class ScheduleStruct {
	protected int totalTeam;
	protected SeedList seedList = new SeedList();
	protected Integer totalRound;
	protected ArrayList<Match> matches = new ArrayList<>();
	
	protected void updateSeedList(ArrayList<TeamEntity> teams) {
		this.seedList = new SeedList(teams);
		this.totalTeam = this.seedList.size();
	}
	
	abstract protected void applySeedList();
	
	public void applySeedList(ArrayList<TeamEntity> teams) {
		this.updateSeedList(teams);
		this.applySeedList();
	}
}
