package doan2020.SportTournamentSupportSystem.model;

import java.util.ArrayList;
import java.util.Collection;

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

	private Collection<MatchEntity> matches;
	@Autowired
	private IMatchService matchService;
	
	private Collection<TeamEntity> seeds;
	@Autowired
	private ITeamService teamService;

	private int numOfSet = 3;
	private int totalTeams;
	private int totalRound;
	private int totalMatch = 0;
	private int teamPerMatch = 2;
	
	private Match matchesStruct;

	
	public MatchTree(Collection<TeamEntity> seeds) {
		this.seeds = seeds;
		this.totalTeams = this.seeds.size();
		this.matchesStruct = this.createBinaryTree(null, 1, this.totalTeams);
		this.matches = this.createEntityByDownTree(this.matchesStruct);
	}

	public MatchTree(Long competitionId) {
		this.competition = competitionService.findOneById(competitionId);
		this.seeds = this.competition.getTeams();
		this.totalTeams = this.seeds.size();
		this.matchesStruct = this.createBinaryTree(null, 1, this.totalTeams);
		this.matches = this.createEntityByDownTree(this.matchesStruct);
	}

	public MatchTree(CompetitionEntity competition) {
		this.competition = competition;
		this.seeds = this.competition.getTeams();
		this.totalTeams = this.seeds.size();
		this.matchesStruct = this.createBinaryTree(null, 1, this.totalTeams);
		this.matches = this.createEntityByDownTree(this.matchesStruct);
	}
	
	public MatchTree(int totalTeams) { // for Test
		System.out.println("MatchTree: Constructor for Test: start");
		this.totalTeams = totalTeams;
		System.out.println("MatchTree: Constructor for Test: start createBinaryTree");
		this.matchesStruct = this.createBinaryTree(null, 1, this.totalTeams);
		System.out.println("MatchTree: Constructor for Test: start createEntityByDownTree");
		this.matches = this.createEntityByDownTree(this.matchesStruct);
		System.out.println("MatchTree: Constructor for Test: finish");
	}
	
	public Collection<MatchEntity> getMatches() {
		return this.matches;
	}
	
	private Collection<MatchEntity> createEntityByDownTree(Match node){
		Collection<MatchEntity> matches = new ArrayList<>();
		
		if (node == null)
			return matches;
		
		MatchEntity match = new MatchEntity();

		match.setCompetition(this.competition);
		
		Match next = node.getNext();
		MatchEntity nextMatch = null;
		
		if (next != null)
			nextMatch = next.getEntity();
		
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

		
		matches.addAll(createEntityByDownTree(node.getLeft()));
		matches.addAll(createEntityByDownTree(node.getRight()));
		
		return matches;
	}

	// Logic match tree
	// -----------------------------------------------------------------------------

	private Match createBinaryTree(Match parent, int left, int right) {

		Match root = new Match();

		if (right - left < 0) { // it hon 1 team
			return root;
		}

		if (right - left == 0) { // 1 teams
			root = createMatch(parent, 1);
			root.setTeam1(left);
			root.setTeam2(0);
			root.setWinner(left);
		}

		if (right - left == 1) { // 2 teams
			root = createMatch(parent, 1);
			root.setTeam1(left);
			root.setTeam2(right);
			root.setWinner(0);
		}

		if (right - left >= 2) { // tu 3 teams tro len

			int mid = (left + right) / 2;
			root.setLeft(createBinaryTree(root, left, mid));
			root.setRight(createBinaryTree(root, mid + 1, right));

			int roundNo = root.getLeft().getRoundNo() + 1;
			root.setRoundNo(roundNo);

			this.totalMatch++;
			root.setId((long) this.totalMatch);
			root.setNext(parent);

			root.setTeam1(0);
			root.setTeam2(0);
			root.setWinner(0);
		}

		return root;
	}

	private Match createMatch(Match parent, int roundNo) {
		Match match = new Match();

		this.totalMatch++;
		match.setId((long) this.totalMatch);
		match.setLeft(null);
		match.setRight(null);
		match.setNext(parent);
		match.setRoundNo(roundNo);
		return match;
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

	public int getTotalTeams() {
		return totalTeams;
	}

	public void setTotalTeams(int totalTeams) {
		this.totalTeams = totalTeams;
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
