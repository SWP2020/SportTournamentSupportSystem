package doan2020.SportTournamentSupportSystem.model.ScheduleFormat;

import doan2020.SportTournamentSupportSystem.config.Const;
import doan2020.SportTournamentSupportSystem.model.Entity.Match;
import doan2020.SportTournamentSupportSystem.model.EntityStruct.SeedList;
import doan2020.SportTournamentSupportSystem.model.LogicStruct.MatchSlot;
import doan2020.SportTournamentSupportSystem.model.LogicStruct.TeamDescription;
import doan2020.SportTournamentSupportSystem.model.Struct.BTree;
import doan2020.SportTournamentSupportSystem.model.Struct.Node;

public class SingleEliminationTree extends ScheduleStruct {

	protected BTree<Match> bracket;

	protected int completeTreeDegree;
	protected int firstRoundTotalMatch;
	protected int firstRoundCurrentMatch;
	protected Match match34;

	// ------------Constructor

	public SingleEliminationTree() {
	}

	public SingleEliminationTree(int totalTeam) {
		System.out.println("SingleEliminationTree: Contructor: start");

		this.totalTeam = totalTeam;
		this.totalRound = calTotalRound(totalTeam);
		this.completeTreeDegree = floorLog2(totalTeam);
		this.firstRoundTotalMatch = totalTeam - (int) Math.pow(2, this.completeTreeDegree);
		this.firstRoundCurrentMatch = 0;
		
		this.bracket = new BTree<>(this.buildBracket(null, 1, this.totalTeam, 1));
		this.matches = this.bracket.toArrayList();
		this.bracket.setName("Bracket");

		System.out.println("SingleEliminationTree: Contructor: finish");
	}

	public SingleEliminationTree(BTree<Match> bracket, int totalTeam) {
		this.bracket = bracket;
		this.totalTeam = totalTeam;
		this.totalRound = calTotalRound(this.totalTeam);
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
		System.out.println(index);
		System.out.println(totalSeed);

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
		info.setName(Const.WIN_BRANCH_NAMING + info.getMatchNo());
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
				info.getTeam2().setDescription(new TeamDescription(2l, node.getRight().getData().getMatchNo()));
				info.setStatus(2);
			} else {
				node.setLeft(buildBracket(node, index * 2, totalSeed, leftSeedIndex));
				node.setRight(buildBracket(node, index * 2 + 1, totalSeed, rightSeedIndex));

				info.setTeam1(node.getLeft().getData().getWinner());
				info.setTeam2(node.getRight().getData().getWinner());

				info.getTeam1().setDescription(new TeamDescription(2l, node.getLeft().getData().getMatchNo()));
				info.getTeam2().setDescription(new TeamDescription(2l, node.getRight().getData().getMatchNo()));

				info.setStatus(1);
			}
		}
		
		info.getWinner().setDescription(new TeamDescription(2l, info.getMatchNo()));
		info.getLoser().setDescription(new TeamDescription(4l, info.getMatchNo()));

		node.setData(info);

		System.out.println("SingleEliminationTree: buildBracket: finish");

		return node;
	}

	// ------------------ browse the tree

	protected void setMatch34() {
		this.match34.setMatchNo(this.bracket.getTotalNode() + 1);
		this.match34.setName(Const.WIN_BRANCH_NAMING + this.match34.getRoundNo());

		MatchSlot winner = new MatchSlot();
		MatchSlot loser = new MatchSlot();

		this.match34.setTeam1(this.bracket.getRoot().getLeft().getData().getLoser());
		this.match34.getTeam1()
				.setDescription(new TeamDescription(4l, this.bracket.getRoot().getLeft().getData().getMatchNo()));

		this.match34.setTeam2(this.bracket.getRoot().getRight().getData().getLoser());
		this.match34.getTeam2()
				.setDescription(new TeamDescription(4l, this.bracket.getRoot().getRight().getData().getMatchNo()));

		this.match34.setWinner(winner);
		this.match34.setLoser(loser);
		this.match34.setRoundNo(this.bracket.getRoot().getData().getRoundNo());
	}
	
	protected Match getMatch34() {
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
			int seedNo = description1.getUnitIndex();
			node.getData().getTeam1().setTeam(this.seedList.get(seedNo));
		} else {
			applySeedList(node.getLeft());
		}

		TeamDescription description2 = node.getData().getTeam2().getDescription();
		if (description2.getDescType() == 0) {
			int seedNo = description2.getUnitIndex();
			node.getData().getTeam2().setTeam(this.seedList.get(seedNo));
		} else {
			applySeedList(node.getRight());
		}
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

	protected int calMatchNo(int index) {
		int matchNo;
		int thisNodeDegree = this.ceilLog2(index + 1);
		int totalNodeBelow = (int) Math.pow(2,  this.completeTreeDegree) - (int) Math.pow(2, thisNodeDegree);
		int totalNodeAbove = (int) Math.pow(2, thisNodeDegree - 1) - 1;

		matchNo = index + totalNodeBelow - totalNodeAbove + this.firstRoundTotalMatch;

		return matchNo;
	}

//	public static void main(String[] args) {
//		int totalTeam = 14;
//		SingleEliminationTree tree = new SingleEliminationTree(totalTeam);
//		int index = 15;
//		
//		Node<Match> x = tree.bracket.getById(index);
//		System.out.println(x.getData().getMatchNo());
//	}

}
