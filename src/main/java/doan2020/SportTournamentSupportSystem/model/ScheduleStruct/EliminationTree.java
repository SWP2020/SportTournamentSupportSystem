package doan2020.SportTournamentSupportSystem.model.ScheduleStruct;

import java.util.ArrayList;

import doan2020.SportTournamentSupportSystem.config.Const;
import doan2020.SportTournamentSupportSystem.entity.TeamEntity;
import doan2020.SportTournamentSupportSystem.model.Entity.Match;
import doan2020.SportTournamentSupportSystem.model.EntityStruct.SeedList;
import doan2020.SportTournamentSupportSystem.model.LogicStruct.MatchSlot;
import doan2020.SportTournamentSupportSystem.model.LogicStruct.TeamDescription;
import doan2020.SportTournamentSupportSystem.model.Struct.BTree;
import doan2020.SportTournamentSupportSystem.model.Struct.Node;

public class EliminationTree {

	private int totalTeam;
	private SeedList seedList = new SeedList();

	private Integer totalWinBranchRound;
	private Integer totalLoseBranchRound;

	private ArrayList<Node<Match>> winBranchMatchList = new ArrayList<>();
	private ArrayList<Node<Match>> loseBranchMatchList = new ArrayList<>();

	private BTree<Match> winBranch;
	private BTree<Match> loseBranch;

	// ------------Constructor

	public EliminationTree() {
	}

	public EliminationTree(int totalTeam, Long formatId) {
		System.out.println("EliminationTree: Contructor: start");
		
		this.totalTeam = totalTeam;
		
		this.totalWinBranchRound = calTotalWinBranchRound(this.totalTeam);
		this.winBranch = new BTree<>(this.buildWinBranch(null, 1, this.totalTeam, 1));
		this.winBranchMatchNoIndexing();

		if (formatId == 1l) {
			this.winBranch.setName("Bracket");
		} else if (formatId == 2l) {
			this.winBranch.setName("WinBranch");
			this.totalLoseBranchRound = calTotalLoseBranchRound(this.totalTeam);
			this.loseBranch = new BTree<>(this.buildLoseBranch(null, 1, this.totalTeam - 1, 1));
			this.loseBranchMatchNoIndexing();
			this.loseBranch.setName("LoseBranch");
		}
		this.completeTeamDescription();

		System.out.println("EliminationTree: Contructor: finish");
	}
	
	
	
	public EliminationTree(BTree<Match> winBranch, int totalTeam) {
		this.winBranch = winBranch;
		this.loseBranch = null;
		this.totalTeam = totalTeam;
		this.totalWinBranchRound = calTotalWinBranchRound(this.totalTeam);
		this.totalLoseBranchRound = 0;
		this.winBranchMatchList = this.winBranch.toArrayList();
	}
	
	
	public EliminationTree(BTree<Match> winBranch, BTree<Match> loseBranch, int totalTeam) {
		this.winBranch = winBranch;
		this.loseBranch = loseBranch;
		this.totalTeam = totalTeam;
		this.totalWinBranchRound = calTotalWinBranchRound(this.totalTeam);
		this.totalLoseBranchRound = calTotalLoseBranchRound(this.totalTeam);
		this.winBranchMatchList = this.winBranch.toArrayList();
		this.loseBranchMatchList = this.loseBranch.toArrayList();
	}
	
	
	
	
	
	
	
	
	
	

	// ------------Getter setter

	public SeedList getSeedList() {
		return seedList;
	}

	public void setSeedList(SeedList seedList) {
		this.seedList = seedList;
	}

	public BTree<Match> getWinBranch() {
		return winBranch;
	}

	public void setWinBranch(BTree<Match> winBranch) {
		this.winBranch = winBranch;
	}

	public BTree<Match> getLoseBranch() {
		return loseBranch;
	}

	public void setLoseBranch(BTree<Match> loseBranch) {
		this.loseBranch = loseBranch;
	}

	// ------------Logic code

	// -------------Win branch
	private Node<Match> buildWinBranch(Node<Match> parent, Integer index, Integer totalSeed, Integer leftSeedIndex) {

		System.out.println("EliminationTree: buildWinBranch: start");
		System.out.println(index);
		System.out.println(totalSeed);

		if (totalSeed < 2) // less than 2 seeds
			return null; // can't schedule

		Node<Match> node = new Node<Match>();

		node.setId(index);
		node.setParent(parent);

		Match info = new Match();

		System.out.println("EliminationTree: buildWinBranch: CP1");

		if (index == 1) { // this is root
			info.setRoundNo(this.totalWinBranchRound);
			node.setDegree(0);
		} else {
			info.setRoundNo(parent.getData().getRoundNo() - 1);
			node.setDegree(parent.getDegree() + 1);
		}

		Integer rightSeedIndex = (int) Math.pow(2, node.getDegree() + 1) + 1 - leftSeedIndex;

		System.out.println("EliminationTree: buildWinBranch: CP2");

		System.out.println("EliminationTree: buildWinBranch: CP3");
		info.setName("");
		info.setTeam1(new MatchSlot());
		info.setTeam2(new MatchSlot());
		info.setWinner(new MatchSlot());
		info.setLoser(new MatchSlot());
		info.setStatus(1);

		node.setLeft(null);
		node.setRight(null);

		node.setData(info);

		System.out.println("EliminationTree: buildWinBranch: CP4");

		Integer leftRightIndex = (int) Math.pow(2, node.getDegree() + 2) + 1 - leftSeedIndex;
		Integer rightRightIndex = (int) Math.pow(2, node.getDegree() + 2) + 1 - rightSeedIndex;

		if (rightRightIndex > totalSeed) {

			info.getTeam1().setDescription(new TeamDescription((long) leftSeedIndex));
			info.getTeam2().setDescription(new TeamDescription((long) rightSeedIndex));

			info.setStatus(0);
		} else {
			if (leftRightIndex > totalSeed) {
				node.setRight(buildWinBranch(node, index * 2 + 1, totalSeed, rightSeedIndex));
				info.setTeam2(node.getRight().getData().getWinner());

				info.getTeam1().setDescription(new TeamDescription((long) leftSeedIndex));
				info.setStatus(2);
			} else {
				node.setLeft(buildWinBranch(node, index * 2, totalSeed, leftSeedIndex));
				node.setRight(buildWinBranch(node, index * 2 + 1, totalSeed, rightSeedIndex));

				info.setTeam1(node.getLeft().getData().getWinner());
				info.setTeam2(node.getRight().getData().getWinner());

				info.setStatus(1);
			}
		}

		System.out.println("EliminationTree: buildWinBranch: CP5");

		node.setData(info);

		this.winBranchMatchList.add(node);

		System.out.println("EliminationTree: buildWinBranch: finish");

		return node;
	}

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

		node.setLeft(this.winBranch.getById(leftIndex));
		info.setTeam1(this.winBranch.getById(leftIndex).getData().getLoser());

		if (this.winBranch.getById(rightLeftIndex) == null) {
			System.out.println("EliminationTree: buildLoseBranch: CP3-1-1: ");

			node.setRight(this.winBranch.getById(rightIndex));

			info.setTeam2(this.winBranch.getById(rightIndex).getData().getLoser());

			info.setStatus(3);

		} else {
			System.out.println("EliminationTree: buildLoseBranch: CP3-1-2: ");

			node.setRight(buildRightLoseBranch(node, index * 2 + 1, totalLoseTeam, rightLeftIndex));

			info.setTeam1(this.winBranch.getById(leftIndex).getData().getLoser());
			info.setTeam2(node.getRight().getData().getLoser());
			info.setStatus(4);

		}

		node.setData(info);

		System.out.println("EliminationTree: buildLoseBranch: CP4");

		this.loseBranchMatchList.add(node);

		System.out.println("EliminationTree: buildLoseBranch: finish");

		return node;
	}

	private Node<Match> buildRightLoseBranch(Node<Match> parent, Integer index, Integer totalLoseTeam, Integer leftIndex) {

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

		if (this.winBranch.getById(rightRightIndex) == null && this.winBranch.getById(leftRightIndex) == null) {
			System.out.println("EliminationTree: buildRightLoseBranch: CP3-1: leftIndex: " + leftIndex);
			System.out.println("EliminationTree: buildRightLoseBranch: CP3-1: rightIndex: " + rightIndex);

			node.setLeft(this.winBranch.getById(leftIndex));
			node.setRight(this.winBranch.getById(rightIndex));

			System.out.println(
					"EliminationTree: buildRightLoseBranch: CP3-2: getById(leftIndex): " + this.winBranch.getById(leftIndex));
			info.setTeam1(this.winBranch.getById(leftIndex).getData().getLoser());
			info.setTeam2(this.winBranch.getById(rightIndex).getData().getLoser());

			System.out.println("EliminationTree: buildRightLoseBranch: CP3-3: getById(rightIndex).data: "
					+ this.winBranch.getById(rightIndex).getData());
			info.setStatus(3);
			
		} else {
			if (this.winBranch.getById(leftRightIndex) == null) {
				node.setLeft(this.winBranch.getById(leftIndex));
				node.setRight(buildLoseBranch(node, index * 2 + 1, totalLoseTeam, rightIndex));

				info.setTeam1(this.winBranch.getById(leftIndex).getData().getLoser());
				info.setTeam2(node.getRight().getData().getWinner());
				info.setStatus(4);
			}

			if (this.winBranch.getById(rightRightIndex) == null) {
				node.setLeft(buildLoseBranch(node, index * 2, totalLoseTeam, leftIndex));
				node.setRight(this.winBranch.getById(rightIndex));

				info.setTeam1(node.getLeft().getData().getWinner());
				info.setTeam2(this.winBranch.getById(rightIndex).getData().getLoser());
				info.setStatus(4);
			}

			if (this.winBranch.getById(rightRightIndex) != null && this.winBranch.getById(leftRightIndex) != null) {
				node.setLeft(buildLoseBranch(node, index * 2, totalLoseTeam, leftIndex));
				node.setRight(buildLoseBranch(node, index * 2 + 1, totalLoseTeam, rightIndex));

				info.setTeam1(node.getLeft().getData().getWinner());
				info.setTeam2(node.getRight().getData().getWinner());
				info.setStatus(1);
			}
		}
		node.setData(info);

		System.out.println("EliminationTree: buildRightLoseBranch: CP5");

		this.loseBranchMatchList.add(node);

		System.out.println("EliminationTree: buildRightLoseBranch: finish");
		return node;
	}

	// ------------------ browse the tree
	

	private void winBranchMatchNoIndexing() {

		int matchNo = 0;
		for (int round = 1; round <= this.totalWinBranchRound; round++) {
			for (Node<Match> match : this.winBranchMatchList) {
				if (match.getData().getRoundNo() == round) {
					matchNo++;
					match.getData().setMatchNo(matchNo);
					match.getData().setName(Const.WIN_BRANCH_NAMING + String.valueOf(matchNo));
					this.winBranch.setById(match.getId(), match.getData());
				}
			}
		}

	}

	private void loseBranchMatchNoIndexing() {

		int matchNo = 0;
		for (int round = 1; round <= this.totalLoseBranchRound; round++) {
			for (Node<Match> match : this.loseBranchMatchList) {
				if (match.getData().getRoundNo() == round) {
					matchNo++;
					match.getData().setMatchNo(matchNo);
					match.getData().setName(Const.LOSE_BRANCH_NAMING + String.valueOf(matchNo));
					this.loseBranch.setById(match.getId(), match.getData());
				}
			}
		}

	}

	private void completeTeamDescription() {

		for (Node<Match> match : this.winBranchMatchList) {
			if (match.getLeft() != null) {
				int matchNo = match.getLeft().getData().getMatchNo();
				TeamDescription description = new TeamDescription(2l, matchNo);
				match.getData().getTeam1().setDescription(description);
			}
			
			if (match.getRight() != null) {
				int matchNo = match.getRight().getData().getMatchNo();
				TeamDescription description = new TeamDescription(2l, matchNo);
				match.getData().getTeam2().setDescription(description);
			}
			
			this.winBranch.setById(match.getId(), match.getData());

		}
		
		for (Node<Match> match : this.loseBranchMatchList) {
			if (match.getLeft() != null) {
				
				int matchNo = match.getLeft().getData().getMatchNo();
				String name = match.getLeft().getData().getName();
				
				TeamDescription description = new TeamDescription();
				
				if (name.contains(Const.WIN_BRANCH_NAMING)) {
					description = new TeamDescription(4l, matchNo);
				}
				if (name.contains(Const.LOSE_BRANCH_NAMING)) {
					description = new TeamDescription(3l, matchNo);
				}
				match.getData().getTeam1().setDescription(description);
			}
			
			if (match.getRight() != null) {
				int matchNo = match.getRight().getData().getMatchNo();
				String name = match.getRight().getData().getName();
				
				TeamDescription description = new TeamDescription();
				
				if (name.contains(Const.WIN_BRANCH_NAMING)) {
					description = new TeamDescription(4l, matchNo);
				}
				if (name.contains(Const.LOSE_BRANCH_NAMING)) {
					description = new TeamDescription(3l, matchNo);
				}
				
				match.getData().getTeam2().setDescription(description);
			}
			
			this.loseBranch.setById(match.getId(), match.getData());

		}
		System.out.println("EliminationTree: completeTeamDescription: lose tree done");

	}
	
	
	private void updateSeedList(ArrayList<TeamEntity> teams) {
		this.seedList = new SeedList(teams);
		this.totalTeam = this.seedList.size();
	}
	
	private void applySeedList() {
		for (Node<Match> node: this.winBranchMatchList) {
			if (node.getLeft() == null) { // a seed
				int seedNo = node.getData().getTeam1().getDescription().getUnitIndex();
				node.getData().getTeam1().setTeam(this.seedList.get(seedNo));
			}
			
			if (node.getRight() == null) { // a seed
				int seedNo = node.getData().getTeam2().getDescription().getUnitIndex();
				node.getData().getTeam2().setTeam(this.seedList.get(seedNo));
			}
		}
	}
	
	public void applySeedList(ArrayList<TeamEntity> teams) {
		this.updateSeedList(teams);
		this.applySeedList();
	}

	// ----------- support logic code

	private Integer reverseIndex(Integer index) {
		Integer k = floorLog2(index + 1);
		Integer _2powk = (int) Math.pow(2, k);
		return _2powk + _2powk / 2 - 1 - index;
	}

	private int floorLog2(int x) {
		int k = 0;
		int y = 1;
		while (y < x) {
			y *= 2;
			k++;
		}

		return k;

	}

	private Integer calTotalWinBranchRound(Integer size) {
		int totalRound = 0;
		int x = 1;
		while (x < size) {
			x *= 2;
			totalRound++;
		}
		return totalRound;
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

}
