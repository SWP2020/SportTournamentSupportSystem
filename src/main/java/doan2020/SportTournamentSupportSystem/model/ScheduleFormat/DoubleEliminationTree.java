package doan2020.SportTournamentSupportSystem.model.ScheduleFormat;

import java.io.Serializable;

import doan2020.SportTournamentSupportSystem.config.Const;
import doan2020.SportTournamentSupportSystem.model.ContainerCollection.SeedList;
import doan2020.SportTournamentSupportSystem.model.Entity.Match;
import doan2020.SportTournamentSupportSystem.model.LogicBox.MatchSlot;
import doan2020.SportTournamentSupportSystem.model.LogicStruct.TeamDescription;
import doan2020.SportTournamentSupportSystem.model.Struct.BTree;
import doan2020.SportTournamentSupportSystem.model.Struct.DoubleBTree;
import doan2020.SportTournamentSupportSystem.model.Struct.Node;

public class DoubleEliminationTree extends SingleEliminationTree implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String loseBranchNaming = Const.LOSE_BRANCH_NAMING;
	
	private Integer totalLoseBranchRound;
	protected int completeLoseTreeDegree;
	protected int firstRoundTotalLoseMatch;

	private DoubleBTree<Match> loseBranch = new DoubleBTree<>();
	private Match summaryFinal;
	private Match optionFinal;

	// ------------Constructor

	public DoubleEliminationTree() {
		super(Const.WIN_BRANCH_NAMING);
	}

	public DoubleEliminationTree(int totalTeam) {
		super(totalTeam, Const.WIN_BRANCH_NAMING);
		
		System.out.println("DoubleEliminationTree: Contructor: winTree OK");
		this.bracket.setName("WinBranch");
		
		this.firstRoundTotalLoseMatch = this.calFirstRoundTotalLoseMatch(totalTeam);
		this.firstRoundCurrentMatch = 0;
		this.totalLoseBranchRound = calTotalLoseBranchRound(this.totalTeam);
		System.out.println("DoubleEliminationTree: Contructor: etc number OK");
		
		if (totalTeam > 2)
			this.loseBranch = new DoubleBTree<Match>(this.buildLoseBranch(null, 1, this.totalTeam - 1, 1));
		else 
			this.loseBranch = new DoubleBTree<>();
		this.loseBranch.setName("LoseBranch");
		
		System.out.println("DoubleEliminationTree: Contructor: loseTree OK");
		
		this.summaryFinal = getSummaryFinal();
		System.out.println("DoubleEliminationTree: Contructor: get summary final OK");
		this.optionFinal = gettOptionFinal();
		System.out.println("DoubleEliminationTree: Contructor: get option final OK");
		
		this.matches.addAll(this.loseBranch.toArrayList());
		System.out.println("DoubleEliminationTree: Contructor: finish");
	}

	public DoubleEliminationTree(BTree<Match> winBranch, DoubleBTree<Match> loseBranch, int totalTeam) {
		super(winBranch, totalTeam, Const.WIN_BRANCH_NAMING);
		this.bracket.setName("WinBranch");
		
		this.firstRoundTotalLoseMatch = this.calFirstRoundTotalLoseMatch(totalTeam);
		this.firstRoundCurrentMatch = 0;
		this.totalLoseBranchRound = calTotalLoseBranchRound(this.totalTeam);
		
		this.loseBranch = loseBranch;
		this.loseBranch.setName("LoseBranch");
		
		this.summaryFinal = getSummaryFinal();
		this.optionFinal = gettOptionFinal();
		
		this.matches.addAll(this.loseBranch.toArrayList());
		
	}

	// ------------Getter setter

	public SeedList getSeedList() {
		return seedList;
	}

	public BTree<Match> getWinBranch() {
		return this.bracket;
	}

	public DoubleBTree<Match> getLoseBranch() {
		return loseBranch;
	}

	// ------------Logic code

	// -----------------Lose branch
	private Node<Match> buildLoseBranch(Node<Match> parent, Integer index, Integer totalLoseTeam, Integer leftIndex) {
		System.out.println("EliminationTree: buildLoseBranch: start");

		Node<Match> node = new Node<Match>();
		Match info = new Match();

		if (totalLoseTeam < 2) {
			return null;
		}

		System.out.println("EliminationTree: buildLoseBranch: CP1: totalLoseTeam: " + totalLoseTeam.toString());

		node.setId(index);
		node.setParent(parent);
		info.setTeam1(new MatchSlot());
		info.setTeam2(new MatchSlot());
		info.setWinner(new MatchSlot());
		info.setLoser(new MatchSlot());

		System.out.println("EliminationTree: buildLoseBranch: CP2: parent: " + parent);

		if (parent == null) { // this is root
			info.setRoundNo(this.totalLoseBranchRound);
			node.setDegree(0);
		} else {
			info.setRoundNo(parent.getData().getRoundNo() - 1);
			node.setDegree(parent.getDegree() + 1);
		}
		
		int matchNo = calLoseMatchNo(index, node.getDegree() + 1);
		info.setMatchNo(matchNo);
		
		info.getWinner().setDescription(new TeamDescription(3l, matchNo));
		info.getLoser().setDescription(new TeamDescription(5l, matchNo));

		Integer rightIndex = reverseIndex(leftIndex) * 2 + 1;

		node.setData(info);

		System.out.println("EliminationTree: buildLoseBranch: CP3: leftIndex: " + leftIndex);

		Integer rightLeftIndex = rightIndex - 1;

		node.setLeft(this.bracket.getById(leftIndex));
		info.setTeam1(this.bracket.getById(leftIndex).getData().getLoser());

		if (this.bracket.getById(rightLeftIndex) == null) {
			node.setRight(this.bracket.getById(rightIndex));

			info.setTeam2(this.bracket.getById(rightIndex).getData().getLoser());

			info.setStatus(3);

		} else {
			System.out.println("EliminationTree: buildLoseBranch: CP3-1-2: ");

			node.setRight(buildRightLoseBranch(node, index, totalLoseTeam, rightLeftIndex));

			info.setTeam1(this.bracket.getById(leftIndex).getData().getLoser());
			info.setTeam2(node.getRight().getData().getLoser());
			info.setStatus(4);

		}
		
		info.setName(this.loseBranchNaming + matchNo);
		node.setData(info);

		System.out.println("EliminationTree: buildLoseBranch: finish");

		return node;
	}

	private Node<Match> buildRightLoseBranch(Node<Match> parent, Integer index, Integer totalLoseTeam,
			Integer leftIndex) {

		System.out.println("EliminationTree: buildRightLoseBranch: start");

		Node<Match> node = new Node<Match>();
		Match info = new Match();

		if (totalLoseTeam < 2) {
			return null;
		}

		System.out.println("EliminationTree: buildRightLoseBranch: CP1: totalLoseTeam: " + totalLoseTeam.toString());

		node.setId(index);
		node.setParent(parent);
		info.setTeam1(new MatchSlot());
		info.setTeam2(new MatchSlot());
		info.setWinner(new MatchSlot());
		info.setLoser(new MatchSlot());

		System.out.println("EliminationTree: buildRightLoseBranch: CP2: parent: " + parent.getData().getRoundNo());

		info.setRoundNo(parent.getData().getRoundNo() - 1);

		node.setDegree(parent.getDegree() + 1);
		
		int matchNo = calLoseMatchNo(index, node.getDegree() + 1);
		info.setMatchNo(matchNo);
		
		info.getWinner().setDescription(new TeamDescription(3l, matchNo));
		info.getLoser().setDescription(new TeamDescription(5l, matchNo));
		
		Integer rightIndex = leftIndex + 1;

		Integer leftRightIndex = reverseIndex(leftIndex) * 2 + 1;
		Integer rightRightIndex = reverseIndex(rightIndex) * 2 + 1;

		node.setData(info);

		System.out.println("EliminationTree: buildRightLoseBranch: CP3: rightRightIndex: " + rightRightIndex);

		if (this.bracket.getById(rightRightIndex) == null && this.bracket.getById(leftRightIndex) == null) {
			System.out.println("EliminationTree: buildRightLoseBranch: CP3-1: leftIndex: " + leftIndex);
			System.out.println("EliminationTree: buildRightLoseBranch: CP3-1: rightIndex: " + rightIndex);

			node.setLeft(this.bracket.getById(leftIndex));
			node.setRight(this.bracket.getById(rightIndex));

			System.out.println("EliminationTree: buildRightLoseBranch: CP3-2: getById(leftIndex): "
					+ this.bracket.getById(leftIndex));
			info.setTeam1(this.bracket.getById(leftIndex).getData().getLoser());
			info.setTeam2(this.bracket.getById(rightIndex).getData().getLoser());

			System.out.println("EliminationTree: buildRightLoseBranch: CP3-3: getById(rightIndex).data: "
					+ this.bracket.getById(rightIndex).getData());
			info.setStatus(3);

		} else {
			if (this.bracket.getById(leftRightIndex) == null) {
				node.setLeft(this.bracket.getById(leftIndex));
				node.setRight(buildLoseBranch(node, index * 2 + 1, totalLoseTeam, rightIndex));

				info.setTeam1(this.bracket.getById(leftIndex).getData().getLoser());
				info.setTeam2(node.getRight().getData().getWinner());
				info.setStatus(4);
			}

			if (this.bracket.getById(rightRightIndex) == null) {
				node.setLeft(buildLoseBranch(node, index * 2, totalLoseTeam, leftIndex));
				node.setRight(this.bracket.getById(rightIndex));

				info.setTeam1(node.getLeft().getData().getWinner());
				info.setTeam2(this.bracket.getById(rightIndex).getData().getLoser());
				info.setStatus(4);
			}

			if (this.bracket.getById(rightRightIndex) != null && this.bracket.getById(leftRightIndex) != null) {
				node.setLeft(buildLoseBranch(node, index * 2, totalLoseTeam, leftIndex));
				node.setRight(buildLoseBranch(node, index * 2 + 1, totalLoseTeam, rightIndex));

				info.setTeam1(node.getLeft().getData().getWinner());
				info.setTeam2(node.getRight().getData().getWinner());
				info.setStatus(1);
			}
		}
		
		info.setName(this.loseBranchNaming + matchNo);
		node.setData(info);

		System.out.println("EliminationTree: buildRightLoseBranch: finish");
		return node;
	}

	// ------------------ browse the tree
	
	protected void setSummaryFinal() {
		
		this.summaryFinal = new Match();
		this.summaryFinal.setMatchNo(1);
		this.summaryFinal.setRoundNo(1);
		this.summaryFinal.setName(Const.SUMMARY_FINAL + 1);

		MatchSlot winner = new MatchSlot();
		MatchSlot loser = new MatchSlot();
		
		winner.setDescription(new TeamDescription(-1l, Const.WIN_MATCH, 0l, Const.SUMMARY_FINAL, 1));
		loser.setDescription(new TeamDescription(-1l, Const.LOSE_MATCH, 0l, Const.SUMMARY_FINAL, 1));
		
		this.summaryFinal.setWinner(winner);
		this.summaryFinal.setLoser(loser);

		this.summaryFinal.setTeam1(this.bracket.getRoot().getData().getWinner());
		if (totalTeam > 2)
			this.summaryFinal.setTeam2(this.loseBranch.getRoot().getData().getWinner());
		else
			this.summaryFinal.setTeam2(this.bracket.getRoot().getData().getLoser());
		
		
	}
	
	protected void setOptionFinal() {
		this.optionFinal = new Match();
		this.optionFinal.setMatchNo(2);
		this.optionFinal.setName(Const.SUMMARY_FINAL + 2);

		MatchSlot winner = new MatchSlot();
		MatchSlot loser = new MatchSlot();
		
		winner.setDescription(new TeamDescription(-1l, Const.WIN_MATCH, 0l, Const.SUMMARY_FINAL, 2));
		loser.setDescription(new TeamDescription(-1l, Const.LOSE_MATCH, 0l, Const.SUMMARY_FINAL, 2));
		
		this.optionFinal.setWinner(winner);
		this.optionFinal.setLoser(loser);

		this.optionFinal.setTeam1(this.getSummaryFinal().getWinner());
		this.optionFinal.setTeam2(this.getSummaryFinal().getLoser());

		
		this.optionFinal.setRoundNo(2);
	}
	
	public Match getSummaryFinal() {
		if (this.summaryFinal == null) {
			this.setSummaryFinal();
		}
		
		return this.summaryFinal;
	}
	
	public Match gettOptionFinal() {
		if (this.optionFinal == null) {
			this.setOptionFinal();
		}
		
		return this.optionFinal;
	}

	// ----------- support logic code

	private Integer reverseIndex(Integer index) {
		Integer k = ceilLog2(index + 1);
		Integer _2powk = (int) Math.pow(2, k);
		return _2powk + _2powk / 2 - 1 - index;
	}

	private Integer calTotalLoseBranchRound(Integer size) {
		int totalRound = 0;
		int x = 1;
		while (x < size) {
			x *= 2;
			totalRound++;
		}

		x /= 2;
		totalRound = (totalRound - 1) * 2;
		if (x + x / 2 >= size)
			totalRound -= 1;

		return totalRound;

	}

	//

	private int calLoseTreeCompleteDegree(int totalTeam) {

		int totalDegree = calTotalLoseBranchRound(totalTeam);
		int logicDegree = totalDegree / 2;

		int completeTreeTotalNode = (int) Math.pow(2, logicDegree + 1);
		if (totalDegree % 2 == 1) {
			completeTreeTotalNode += (int) Math.pow(2, logicDegree);
		}

		if (completeTreeTotalNode == totalTeam) {
			return totalDegree;
		} else {
			return totalDegree - 1;
		}
	}

	private int calTotalCompleteLoseTreeNode(int degree) {
		int totalNode = 2 * ((int) Math.pow(2, degree / 2) - 1);
		
		
		if (degree % 2 == 1) {
			totalNode += (int)Math.pow(2, degree / 2);
		}
		
		return totalNode;
	}
	
	private int calTotalTeamInCompleteTree(int degree) {
		int realDegree = (degree + 1) / 2;
		int totalTeam = (int)Math.pow(2, realDegree) - 1;
		if (degree % 2 == 0) {
			totalTeam += (int)Math.pow(2, realDegree);
		} else {
			totalTeam += (int)Math.pow(2, realDegree - 1);
		}
		return totalTeam;
	}
	
	private int calFirstRoundTotalLoseMatch(int totalTeam) {
		int degree = calLoseTreeCompleteDegree(totalTeam);
		return totalTeam - calTotalTeamInCompleteTree(degree) - 1;
	}

	private int calLoseMatchNo(int index, int nodeDegree) {
		int matchNo = 0;
		int completeTreeDegree = calLoseTreeCompleteDegree(this.totalTeam);
		int totalNodeBelow = (int) calTotalCompleteLoseTreeNode(completeTreeDegree) - calTotalCompleteLoseTreeNode(nodeDegree);
		int totalNodeAbove = (int) Math.pow(2, (nodeDegree - 1) / 2) - 1;

		matchNo = index + totalNodeBelow - totalNodeAbove + this.firstRoundTotalLoseMatch;
		
		return matchNo;

	}

//	public static void main(String[] args) {
//		int totalTeam = 15;
//		DoubleEliminationTree tree = new DoubleEliminationTree(totalTeam);
//		System.out.println("matchNo: " + tree.getLoseBranch().getByIdAndDegree(1, 2).getData().getTeam1().getDescription().getDescription());
//	}

}
