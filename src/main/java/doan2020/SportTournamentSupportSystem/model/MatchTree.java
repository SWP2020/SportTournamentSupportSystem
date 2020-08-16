package doan2020.SportTournamentSupportSystem.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;

import doan2020.SportTournamentSupportSystem.config.Const;
import doan2020.SportTournamentSupportSystem.entity.CompetitionEntity;
import doan2020.SportTournamentSupportSystem.entity.MatchEntity;
import doan2020.SportTournamentSupportSystem.entity.TeamEntity;
import doan2020.SportTournamentSupportSystem.model.LogicEntity.Match;
import doan2020.SportTournamentSupportSystem.service.ICompetitionService;
import doan2020.SportTournamentSupportSystem.service.IMatchService;
import doan2020.SportTournamentSupportSystem.service.ITeamService;

public class MatchTree {

	private CompetitionEntity competition;

	@Autowired
	private ICompetitionService competitionService;

	private Collection<MatchEntity> matches = new ArrayList<>();
	@Autowired
	private IMatchService matchService;

	private Collection<TeamEntity> seeds;
	@Autowired
	private ITeamService teamService;

	private int numOfSet = 3;
	private int totalTeam;
	private int totalRound;
	private int totalMatch = 0;
	private int teamPerMatch = 2;

	private Match matchesStruct;

//	public MatchTree(Collection<TeamEntity> seeds) {
//		this.seeds = seeds;
//		this.totalTeam = this.seeds.size();
//		this.totalRound = calTotalRound(this.totalTeam, 1l);
//		this.matchesStruct = this.createSingleEliminationTree(null, 1, this.totalTeam, 1l);
//	}
//
//	public MatchTree(Long competitionId) {
//		this.competition = competitionService.findOneById(competitionId);
//		this.seeds = this.competition.getTeams();
//		this.totalTeam = this.seeds.size();
//		this.totalRound = calTotalRound(this.totalTeam, 1l);
//		this.matchesStruct = this.createSingleEliminationTree(null, 1, this.totalTeam, 1l);
//	}
//
//	public MatchTree(CompetitionEntity competition) {
//		this.competition = competition;
//		this.seeds = this.competition.getTeams();
//		this.totalTeam = this.seeds.size();
//		this.totalRound = calTotalRound(this.totalTeam, 1l);
//		this.matchesStruct = this.createSingleEliminationTree(null, 1, this.totalTeam, 1l);
//	}

	public MatchTree(int totalTeam, Long formatId) { // for Test
		System.out.println("MatchTree: Constructor for Test: start");
		this.totalTeam = totalTeam;
		this.totalRound = calTotalRound(this.totalTeam, formatId);
		if (formatId == 1)
			this.matchesStruct = this.createSingleEliminationTree(null, 1, this.totalTeam, 1l);
		else 
			this.matchesStruct = this.createDoubleEliminationTree(this.totalTeam);
		System.out.println("MatchTree: Constructor for Test: finish");
	}

	public Collection<MatchEntity> getMatches() {
		return this.matches;
	}

	private MatchEntity createMatch(Match node) {
		MatchEntity match = new MatchEntity();

		match.setCompetition(this.competition);

//		Match next = node.getNext();
		MatchEntity nextMatch = null;

//		if (next != null)
//			nextMatch = next.getEntity();

		match.setNextMatch(nextMatch);

		String name = "match-" + node.getId().toString();

		match.setName(name);
		match.setNumOfSet(numOfSet);

		match.setExpectedDate(Const.DEFAULT_DATE);
		match.setExpectedPlace(Const.DEFAULT_PLACE);
		match.setRealDate(Const.DEFAULT_DATE);
		match.setRealPlace(Const.DEFAULT_PLACE);

		match.setRoundNo(node.getRoundNo());

		match.setStatus("scheduling");
		match.setUrl("url");

		return match;
	}

	// Logic match tree
	// -----------------------------------------------------------------------------

	private Match createSingleEliminationTree(Match parent, int left, int right, Long index) {
		System.out.println("MatchTree: createSingleEliminationTree: start");
		Match root = new Match();

		if (right - left < 1) { // it hon 2 team
			return null;
		}

		System.out.println("MatchTree: createSingleEliminationTree: CP0");
		
		root.setId(index);
		root.setMatchNo(null);
		
		if (parent == null) { // day la node goc
			root.setRoundNo(this.totalRound);
		} else {
			root.setRoundNo(parent.getRoundNo() - 1);
		}
		
		root.setNextAfterWin(parent);
		root.setNextAfterLose(null);
		
		root.setNextAfterWinId(null);
		root.setNextAfterLoseId(null);
		
		if (parent != null) {
			root.setNextAfterWinId(parent.getId());
		}
		
		root.setTeam1(null);
		root.setTeam2(null);

		root.setLeft(null);
		root.setRight(null);

		root.setWinner(null);

		System.out.println("MatchTree: createSingleEliminationTree: CP1");

		if (right - left == 1) { // 2 teams
			root.setTeam1(left);
			root.setTeam2(right);
		}
		
		if (right - left == 2) { // 3 teams
			root.setRight(createSingleEliminationTree(root, left + 1, right, index * 2 + 1));
			root.setTeam1(left);
		}

		System.out.println("MatchTree: createSingleEliminationTree: CP2");

		if (right - left > 2) { // tu 4 teams tro len
			int mid = (left + right) / 2;
			root.setLeft(createSingleEliminationTree(root, left, mid, index * 2));
			root.setRight(createSingleEliminationTree(root, mid + 1, right, index * 2 + 1));
		}

		System.out.println("MatchTree: createSingleEliminationTree: finish: roundNo:" + root.getRoundNo());
		return root;
	}

	private Match createDoubleEliminationTree(int numOfTeam) {
		System.out.println("MatchTree: createDoubleEliminationTree: start");
		Match root = new Match();
		
		root.setId(1l);
		root.setMatchNo(null);
		
		root.setRoundNo(this.totalRound);
		
		if (!isTwoExp(numOfTeam)) {
			return null;
		}

		Match winBranch = createSingleEliminationTree(root, 1, numOfTeam, 2l);
		
		Match loseBranch = createLoseBranch(root, numOfTeam, 3l);
		
		root.setLeft(winBranch);
		root.setRight(loseBranch);
		System.out.println("MatchTree: createDoubleEliminationTree: finish");
		return root;
	}

	private boolean isTwoExp(Integer x) {
		while (x % 2 == 0)
			x = x / 2;
		return (x == 1);
	}
	
	private Integer calTotalRound(Integer totalTeam, Long formatId) {
		
		int x = 1;
		int k = 0;
		
		while (totalTeam > x) {
			x *= 2;
			k ++;
		}
		if (formatId == 2) {
			k *= 2;
		}
		return k;
	}
	
	private Match createLoseBranch(Match parent, int numOfTeam, Long index) {
		Match root = new Match();
		
		if (numOfTeam < 4) {
			return null;
		}
		
		root.setId(index);
		root.setMatchNo(null);
		
		if (parent == null) { // day la node goc
			root.setRoundNo(this.totalRound);
		} else {
			root.setRoundNo(parent.getRoundNo() - 1);
		}
		
		root.setNextAfterWin(parent);
		root.setNextAfterLose(null);
		
		root.setNextAfterWinId(null);
		root.setNextAfterLoseId(null);
		
		if (parent != null) {
			root.setNextAfterWinId(parent.getId());
		}
		
		root.setTeam1(null);
		root.setTeam2(null);
		
		root.setWinner(null);
		
		root.setLeft(null);
		root.setRight(createLoseRightBranch(root, numOfTeam, index * 2 + 1));
		
		return root;
	}
	
	private Match createLoseRightBranch(Match parent, int numOfTeam, Long index) {
		Match root = new Match();
		
		if (numOfTeam < 4) {
			return null;
		}
		
		root.setId(index);
		root.setMatchNo(null);
		
		if (parent == null) { // day la node goc
			root.setRoundNo(this.totalRound);
		} else {
			root.setRoundNo(parent.getRoundNo() - 1);
		}
		
		root.setNextAfterWin(parent);
		root.setNextAfterLose(null);
		
		root.setNextAfterWinId(null);
		root.setNextAfterLoseId(null);
		
		if (parent != null) {
			root.setNextAfterWinId(parent.getId());
		}
		
		root.setTeam1(null);
		root.setTeam2(null);

		root.setLeft(null);
		root.setRight(null);

		root.setWinner(null);
		
		if (numOfTeam == 4) {
			root.setLeft(null);
			root.setRight(null);
		} else {
			root.setLeft(createLoseBranch(root, numOfTeam / 2, index * 2));
			root.setRight(createLoseBranch(root, numOfTeam / 2, index * 2 + 1));
		}
		
		return root;
	}
	
	
	private Match Indexing(Match root) {
		Queue<Match> q = new LinkedList<Match>();
		
		
		return root;
	}

	// -----------------------------------------------------------------------------------------------

	public CompetitionEntity getCompetition() {
		return competition;
	}

	public void setCompetition(CompetitionEntity competition) {
		this.competition = competition;
	}

	public ICompetitionService getCompetitionService() {
		return competitionService;
	}

	public void setCompetitionService(ICompetitionService competitionService) {
		this.competitionService = competitionService;
	}

	public IMatchService getMatchService() {
		return matchService;
	}

	public void setMatchService(IMatchService matchService) {
		this.matchService = matchService;
	}

	public Collection<TeamEntity> getSeeds() {
		return seeds;
	}

	public void setSeeds(Collection<TeamEntity> seeds) {
		this.seeds = seeds;
	}

	public ITeamService getTeamService() {
		return teamService;
	}

	public void setTeamService(ITeamService teamService) {
		this.teamService = teamService;
	}

	public int getNumOfSet() {
		return numOfSet;
	}

	public void setNumOfSet(int numOfSet) {
		this.numOfSet = numOfSet;
	}

	public int getTotalTeam() {
		return totalTeam;
	}

	public void setTotalTeam(int totalTeam) {
		this.totalTeam = totalTeam;
	}

	public int getTotalRound() {
		return totalRound;
	}

	public void setTotalRound(int totalRound) {
		this.totalRound = totalRound;
	}

	public int getTotalMatch() {
		return totalMatch;
	}

	public void setTotalMatch(int totalMatch) {
		this.totalMatch = totalMatch;
	}

	public int getTeamPerMatch() {
		return teamPerMatch;
	}

	public void setTeamPerMatch(int teamPerMatch) {
		this.teamPerMatch = teamPerMatch;
	}

	public Match getMatchesStruct() {
		return matchesStruct;
	}

	public void setMatchesStruct(Match matchStruct) {
		this.matchesStruct = matchStruct;
	}

	public void setMatches(Collection<MatchEntity> matches) {
		this.matches = matches;
	}

}
