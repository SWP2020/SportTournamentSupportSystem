package doan2020.SportTournamentSupportSystem.model.LogicEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

import doan2020.SportTournamentSupportSystem.config.Const;
import doan2020.SportTournamentSupportSystem.entity.TeamEntity;

public class RoundRobinTable implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	
	private Long id;
	private String name;
	private int totalMatch;
	private boolean homeMatch = false;
	
	private SeedList seedList = new SeedList();
	
	private LinkedList<TeamRanking> ranking = new LinkedList<>();
	
	
	private ArrayList<MatchInfo> matches;


	public RoundRobinTable() {
		super();
	}
	
	
	public RoundRobinTable(Long id, ArrayList<TeamEntity> teams, boolean homeMatch) {
		this.id = id;
		this.homeMatch = homeMatch;
		this.name = "" + Const.TABLE_NAMING.charAt(this.id.intValue());
		this.seedList = new SeedList(teams);
		for (Team team: this.seedList) {
			this.ranking.add(new TeamRanking(team));
		}
		this.matches = this.createMatches(this.seedList.size(), homeMatch);
		this.totalMatch = this.matches.size();
	}


	private ArrayList<MatchInfo> createMatches(int totalTeam, boolean homeMatch) {
		ArrayList<MatchInfo> matches = new ArrayList<>();
		
		if (homeMatch) {
			matches.addAll(createMatches(totalTeam, false));
		}
		
		int totalSeed = totalTeam + totalTeam % 2;
		
		// totalSeed % 2 == 0 (always)
		
		int runSeed = totalSeed;
		int rouletteTotal = totalSeed - 1;
		int coupleTotal = (rouletteTotal - 1) / 2;
		int totalMatch = matches.size();

		for (int round = 1; round <= rouletteTotal; round ++) {
			int freeSeed = round;
			
			if (runSeed == totalTeam) {
				totalMatch ++;
				MatchInfo info = new MatchInfo();
				info.matchNo = totalMatch;
				info.name = this.name + '-' + info.matchNo;
				info.roundNo = round;
				info.team1 = null;
				info.team2 = null;
				info.winner = null;
				info.loser = null;
				info.location = null;
				info.time = null;
				info.setStatus(0);
				if ((freeSeed % 2 != 0 && !homeMatch) || (freeSeed % 2 == 0 && homeMatch)){
					info.setTeam1Description(new TeamDescription((long)freeSeed));
					info.setTeam2Description(new TeamDescription((long)runSeed));
				} else {
					info.setTeam1Description(new TeamDescription((long)runSeed));
					info.setTeam2Description(new TeamDescription((long)freeSeed));
				}
				matches.add(info);
				
			}
			
			for (int i = 1; i <= coupleTotal; i++) {
				int left = (freeSeed - 1 + i + rouletteTotal) % rouletteTotal + 1;
				int right = (freeSeed - 1 - i + rouletteTotal) % rouletteTotal + 1;
				
				totalMatch ++;
				MatchInfo info = new MatchInfo();
				info.matchNo = totalMatch;
				info.name = this.name + '-' + info.matchNo;
				info.roundNo = round;
				info.team1 = null;
				info.team2 = null;
				info.winner = null;
				info.loser = null;
				info.location = null;
				info.time = null;
				info.setStatus(0);
				if (homeMatch) {
					info.setTeam1Description(new TeamDescription((long)left));
					info.setTeam2Description(new TeamDescription((long)right));
				} else {
					info.setTeam1Description(new TeamDescription((long)right));
					info.setTeam2Description(new TeamDescription((long)left));
				}
				
				matches.add(info);
			}

		}

		return matches;
	}
	
	
}
