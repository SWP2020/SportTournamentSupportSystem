package doan2020.SportTournamentSupportSystem.model.ScheduleFormat;

import doan2020.SportTournamentSupportSystem.config.Const;
import doan2020.SportTournamentSupportSystem.model.ContainerCollection.SeedList;
import doan2020.SportTournamentSupportSystem.model.Entity.Match;
import doan2020.SportTournamentSupportSystem.model.LogicBox.MatchSlot;
import doan2020.SportTournamentSupportSystem.model.LogicStruct.TeamDescription;
import doan2020.SportTournamentSupportSystem.model.Struct.BTree;
import doan2020.SportTournamentSupportSystem.model.Struct.Node;
import doan2020.SportTournamentSupportSystem.model.Struct.ScheduleStruct;

public class SingleEliminationTree extends ScheduleStruct {

	private static final long serialVersionUID = 1L;

	protected BTree<Match> bracket;
	protected String bracketMatchNaming = Const.SINGLE_BRACKET_NAMING;

	protected int firstRoundTotalMatch;
	protected int firstRoundCurrentMatch;
	protected Match match34;

	// ------------Constructor

	public SingleEliminationTree() {
	}

	public SingleEliminationTree(int totalTeam) {
		super(totalTeam);
		System.out.println("SingleEliminationTree: Contructor: start");
		
		this.totalRound = calTotalRound(totalTeam);
		this.firstRoundTotalMatch = calFirstRoundTotalMatch(totalTeam);
		this.firstRoundCurrentMatch = 0;
		
		this.bracket = new BTree<>(this.buildBracket(null, 1, this.totalTeam, 1));
		System.out.println("SingleEliminationTree: Contructor: build tree OK");
		this.bracket.setName("Bracket");
		
		this.match34 = getMatch34();
		System.out.println("SingleEliminationTree: Contructor: get match34 OK");
		
		this.matches = this.bracket.toArrayList();

		System.out.println("SingleEliminationTree: Contructor: finish");
	}

	public SingleEliminationTree(BTree<Match> bracket, int totalTeam) {
		super(totalTeam);
		
		this.totalRound = calTotalRound(totalTeam);
		this.firstRoundTotalMatch = calFirstRoundTotalMatch(totalTeam);
		this.firstRoundCurrentMatch = 0;
		
		this.bracket = bracket;
		this.bracket.setName("Bracket");
		
		this.match34 = getMatch34();

		this.matches = this.bracket.toArrayList();

	}
	
	public SingleEliminationTree(String naming) {
		super();
		this.bracketMatchNaming = naming;
	}

	public SingleEliminationTree(int totalTeam, String naming) {
		super(totalTeam);
		System.out.println("SingleEliminationTree: Contructor: start");
		
		this.bracketMatchNaming = naming;
		this.totalRound = calTotalRound(totalTeam);
		this.firstRoundTotalMatch = calFirstRoundTotalMatch(totalTeam);
		this.firstRoundCurrentMatch = 0;
		
		this.bracket = new BTree<>(this.buildBracket(null, 1, this.totalTeam, 1));
		this.bracket.setName("Bracket");
		
		this.match34 = getMatch34();
		
		this.matches = this.bracket.toArrayList();

		System.out.println("SingleEliminationTree: Contructor: finish");
	}

	public SingleEliminationTree(BTree<Match> bracket, int totalTeam, String naming) {
		super(totalTeam);
		
		this.bracketMatchNaming = naming;
		this.totalRound = calTotalRound(totalTeam);
		this.firstRoundTotalMatch = calFirstRoundTotalMatch(totalTeam);
		this.firstRoundCurrentMatch = 0;
		
		this.bracket = bracket;
		this.bracket.setName("Bracket");
		
		this.match34 = getMatch34();

		this.matches = this.bracket.toArrayList();

	}

	// ------------Getter setter

	public SeedList getSeedList() {
		return seedList;
	}

	public BTree<Match> getBracket() {
		return bracket;
	}

	// ------------Logic code

	// -------------bracket
	protected Node<Match> buildBracket(Node<Match> parent, Integer index, Integer totalSeed, Integer leftSeedIndex) {

		System.out.println("SingleEliminationTree: buildBracket: start");

		if (totalSeed < 2) // less than 2 seeds
			return null; // can't schedule

		Node<Match> node = new Node<Match>();

		node.setId(index);
		node.setParent(parent);

		Match info = new Match();

		System.out.println("SingleEliminationTree: buildBracket: CP1");

		if (index == 1) { // this is root
			info.setRoundNo(this.totalRound);
			node.setDegree(0);
		} else {
			info.setRoundNo(parent.getData().getRoundNo() - 1);
			node.setDegree(parent.getDegree() + 1);
		}

		Integer rightSeedIndex = (int) Math.pow(2, node.getDegree() + 1) + 1 - leftSeedIndex;

		System.out.println("SingleEliminationTree: buildBracket: CP2");

		info.setMatchNo(calMatchNo(index));
		info.setTeam1(new MatchSlot());
		info.setTeam2(new MatchSlot());
		info.setWinner(new MatchSlot());
		info.setLoser(new MatchSlot());
		info.setStatus(1);

		node.setLeft(null);
		node.setRight(null);

		node.setData(info);

		System.out.println("SingleEliminationTree: buildBracket: CP4");

		Integer leftRightIndex = (int) Math.pow(2, node.getDegree() + 2) + 1 - leftSeedIndex;
		Integer rightRightIndex = (int) Math.pow(2, node.getDegree() + 2) + 1 - rightSeedIndex;

		if (rightRightIndex > totalSeed) { // round 1

			info.getTeam1().setDescription(new TeamDescription((long) leftSeedIndex));
			info.getTeam2().setDescription(new TeamDescription((long) rightSeedIndex));
			this.firstRoundCurrentMatch++;
			info.setMatchNo(this.firstRoundCurrentMatch);
			info.setStatus(0);
		} else {
			if (leftRightIndex > totalSeed) {
				node.setRight(buildBracket(node, index * 2 + 1, totalSeed, rightSeedIndex));
				info.setTeam2(node.getRight().getData().getWinner());

				info.getTeam1().setDescription(new TeamDescription((long) leftSeedIndex));
//				info.getTeam2().setDescription(new TeamDescription(2l, node.getRight().getData().getMatchNo()));
				info.setStatus(2);
			} else {
				node.setLeft(buildBracket(node, index * 2, totalSeed, leftSeedIndex));
				node.setRight(buildBracket(node, index * 2 + 1, totalSeed, rightSeedIndex));

				info.setTeam1(node.getLeft().getData().getWinner());
				info.setTeam2(node.getRight().getData().getWinner());

//				info.getTeam1().setDescription(new TeamDescription(2l, node.getLeft().getData().getMatchNo()));
//				info.getTeam2().setDescription(new TeamDescription(2l, node.getRight().getData().getMatchNo()));

				info.setStatus(1);
			}
		}
		
		info.setName(this.bracketMatchNaming + info.getMatchNo());
		
		info.getWinner().setDescription(new TeamDescription(2l, info.getMatchNo()));
		info.getLoser().setDescription(new TeamDescription(4l, info.getMatchNo()));

		node.setData(info);

		System.out.println("SingleEliminationTree: buildBracket: finish");

		return node;
	}

	// ------------------ browse the tree

	protected void setMatch34() {
		System.out.println("SingleEliminationTree: setMatch34: start");
		if (this.totalTeam < 4) {
			System.out.println("SingleEliminationTree: setMatch34: total team < 4: finish");
			this.match34 = null;
			return;
		}
		this.match34 = new Match();
		
		System.out.println("SingleEliminationTree: setMatch34: total team > 4");
		System.out.println(this.bracket);
		System.out.println(this.bracket.getTotalNode());
		this.match34.setMatchNo(this.bracket.getTotalNode() + 1);
		System.out.println("SingleEliminationTree: setMatch34: setMatchNo OK");
		this.match34.setName(this.bracketMatchNaming + this.match34.getMatchNo());
		System.out.println("SingleEliminationTree: setMatch34: setMatchNo OK");

		MatchSlot winner = new MatchSlot();
		MatchSlot loser = new MatchSlot();
		
		winner.setDescription(new TeamDescription(2l, this.match34.getMatchNo()));
		loser.setDescription(new TeamDescription(4l, this.match34.getMatchNo()));
		
		this.match34.setWinner(winner);
		System.out.println("SingleEliminationTree: setMatch34: setWinner OK");
		this.match34.setLoser(loser);
		System.out.println("SingleEliminationTree: setMatch34: setLoser OK");

		this.match34.setTeam1(this.bracket.getRoot().getLeft().getData().getLoser());
		System.out.println("SingleEliminationTree: setMatch34: setTeam1 OK");
		this.match34.setTeam2(this.bracket.getRoot().getRight().getData().getLoser());
		System.out.println("SingleEliminationTree: setMatch34: setTeam2 OK");

		
		this.match34.setRoundNo(this.bracket.getRoot().getData().getRoundNo());
		System.out.println("SingleEliminationTree: setMatch34: finish");
	}
	
	public Match getMatch34() {
		if (this.match34 == null) {
			this.setMatch34();
		}
		return this.match34;

	}

	@Override
	protected void applySeedList() {
		applySeedList(this.bracket.getRoot());
	}

	protected void applySeedList(Node<Match> node) {
		TeamDescription description1 = node.getData().getTeam1().getDescription();
		if (description1.getDescType() == 0) {
			int seedNo = description1.getUnitIndex() - 1;
			node.getData().getTeam1().setTeam(this.seedList.get(seedNo).getTeam());
		} else {
			applySeedList(node.getLeft());
		}

		TeamDescription description2 = node.getData().getTeam2().getDescription();
		if (description2.getDescType() == 0) {
			int seedNo = description2.getUnitIndex() - 1;
			node.getData().getTeam2().setTeam(this.seedList.get(seedNo).getTeam());
		} else {
			applySeedList(node.getRight());
		}
	}
	
	protected void applyDescriptionsDownTree(Node<Match> node) {
		TeamDescription description1 = node.getData().getTeam1().getDescription();
		if (description1.getDescType() == 0) {
			int seedNo = description1.getUnitIndex() - 1;
			node.getData().getTeam1().setDescription(this.seedList.get(seedNo).getDescription());
		} else {
			applyDescriptionsDownTree(node.getLeft());
		}

		TeamDescription description2 = node.getData().getTeam2().getDescription();
		if (description2.getDescType() == 0) {
			int seedNo = description2.getUnitIndex() - 1;
			node.getData().getTeam2().setDescription(this.seedList.get(seedNo).getDescription());
		} else {
			applyDescriptionsDownTree(node.getRight());
		}
	}
	
	@Override
	protected void applyDescriptions() {
		applyDescriptionsDownTree(this.getBracket().getRoot());
		
	}

	// ----------- support logic code

	protected int ceilLog2(int x) {
		int k = 0;
		int y = 1;
		while (y < x) {
			y *= 2;
			k++;
		}

		return k;

	}

	protected int floorLog2(int x) {
		int k = 0;
		int y = 1;
		while (y <= x) {
			y *= 2;
			k++;
		}

		return k - 1;

	}

	protected Integer calTotalRound(Integer size) {
		int totalRound = 0;
		int x = 1;
		while (x < size) {
			x *= 2;
			totalRound++;
		}
		return totalRound;
	}
	
	protected int calCompleteDegree(int totalTeam) {
		return floorLog2(totalTeam);
	}
	
	protected int calFirstRoundTotalMatch(int totalTeam) {
		return totalTeam - (int) Math.pow(2, calCompleteDegree(totalTeam));
	}

	protected int calMatchNo(int index) {
		int matchNo;
		int thisNodeDegree = this.ceilLog2(index + 1);
		int totalNodeBelow = (int) Math.pow(2,  calCompleteDegree(this.totalTeam)) - (int) Math.pow(2, thisNodeDegree);
		int totalNodeAbove = (int) Math.pow(2, thisNodeDegree - 1) - 1;

		matchNo = index + totalNodeBelow - totalNodeAbove + this.firstRoundTotalMatch;

		return matchNo;
	}

//	public static void main(String[] args) {
//		int totalTeam = 14;
//		SingleEliminationTree tree = new SingleEliminationTree(totalTeam);
//		int index = 1;
//		
//		Node<Match> x = tree.bracket.getById(index);
//		System.out.println(x.getData().getTeam1().getDescription().getDescription());
//	}

}
