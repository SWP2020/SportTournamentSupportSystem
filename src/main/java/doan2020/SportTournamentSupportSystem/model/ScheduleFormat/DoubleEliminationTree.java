package doan2020.SportTournamentSupportSystem.model.ScheduleFormat;

import java.util.ArrayList;

import doan2020.SportTournamentSupportSystem.config.Const;
import doan2020.SportTournamentSupportSystem.entity.TeamEntity;
import doan2020.SportTournamentSupportSystem.model.Entity.Match;
import doan2020.SportTournamentSupportSystem.model.EntityStruct.SeedList;
import doan2020.SportTournamentSupportSystem.model.LogicStruct.MatchSlot;
import doan2020.SportTournamentSupportSystem.model.LogicStruct.TeamDescription;
import doan2020.SportTournamentSupportSystem.model.Struct.BTree;
import doan2020.SportTournamentSupportSystem.model.Struct.Node;

public class DoubleEliminationTree extends SingleEliminationTree {

	private Integer totalLoseBranchRound;
	protected int completeLoseTreeDegree;
	protected int firstRoundTotalLoseMatch;

	private BTree<Match> loseBranch;

	// ------------Constructor

	public DoubleEliminationTree() {
	}

	public DoubleEliminationTree(int totalTeam) {
		super(totalTeam);

		System.out.println("EliminationTree: Contructor: start");
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++" + this.totalTeam);
		this.bracket.setName("WinBranch");
		this.firstRoundTotalLoseMatch = this.calFirstRoundTotalLoseMatch(totalTeam);
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++" + this.firstRoundTotalLoseMatch);
		this.totalLoseBranchRound = calTotalLoseBranchRound(this.totalTeam);
		this.loseBranch = new BTree<>(this.buildLoseBranch(null, 1, this.totalTeam - 1, 1));
		this.matches.addAll(this.loseBranch.toArrayList());
		this.loseBranch.setName("LoseBranch");

		System.out.println("EliminationTree: Contructor: finish");
	}

	public DoubleEliminationTree(BTree<Match> winBranch, BTree<Match> loseBranch, int totalTeam) {
		super(winBranch, totalTeam);
		this.loseBranch = loseBranch;
		this.totalLoseBranchRound = calTotalLoseBranchRound(this.totalTeam);
		this.loseBranch.setName("LoseBranch");
		this.matches.addAll(this.loseBranch.toArrayList());
		this.bracket.setName("WinBranch");
	}

	// ------------Getter setter

	public SeedList getSeedList() {
		return seedList;
	}

	public BTree<Match> getWinBranch() {
		return this.bracket;
	}

	public BTree<Match> getLoseBranch() {
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

		if (index == 1) { // this is root
			info.setRoundNo(this.totalLoseBranchRound);
			node.setDegree(0);
		} else {
			info.setRoundNo(parent.getData().getRoundNo() - 1);
			node.setDegree(parent.getDegree() + 1);
		}

		Integer rightIndex = reverseIndex(leftIndex) * 2 + 1;

		node.setData(info);

		System.out.println("EliminationTree: buildLoseBranch: CP3: leftIndex: " + leftIndex);

		Integer rightLeftIndex = rightIndex - 1;

		node.setLeft(this.bracket.getById(leftIndex));
		info.setTeam1(this.bracket.getById(leftIndex).getData().getLoser());

		if (this.bracket.getById(rightLeftIndex) == null) {
			System.out.println("EliminationTree: buildLoseBranch: CP3-1-1: ");

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
		node.setData(info);

		System.out.println("EliminationTree: buildRightLoseBranch: finish");
		return node;
	}

	// ------------------ browse the tree

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
		System.out.println("+++++++++++++++++++ total node below: " + totalNodeBelow);
		int totalNodeAbove = (int) Math.pow(2, (nodeDegree - 1) / 2) - 1;
		System.out.println("+++++++++++++++++++ total node above: " + totalNodeAbove);
		matchNo = index + totalNodeBelow - totalNodeAbove + this.firstRoundTotalLoseMatch;
		
		return matchNo;

	}

	public static void main(String[] args) {
		int totalTeam = 14;
		DoubleEliminationTree tree = new DoubleEliminationTree(totalTeam);
		System.out.println("matchNo " + tree.calLoseMatchNo(5, 5));
	}

}
