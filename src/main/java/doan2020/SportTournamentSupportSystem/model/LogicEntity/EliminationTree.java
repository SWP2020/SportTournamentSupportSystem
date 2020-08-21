package doan2020.SportTournamentSupportSystem.model.LogicEntity;

import java.util.ArrayList;

import doan2020.SportTournamentSupportSystem.config.Const;
import doan2020.SportTournamentSupportSystem.entity.TeamEntity;

public class EliminationTree {

	private SeedList seedList = new SeedList();

	private Integer totalWinBranchRound;
	private Integer totalLoseBranchRound;

	private ArrayList<Node<MatchInfo>> winBranchMatchList = new ArrayList<>();
	private ArrayList<Node<MatchInfo>> loseBranchMatchList = new ArrayList<>();

	private BTree<MatchInfo> winBranch;
	private BTree<MatchInfo> loseBranch;

	// ------------Constructor

	public EliminationTree() {
	}

	public EliminationTree(ArrayList<TeamEntity> teams, Long formatId) {
		System.out.println("EliminationTree: Contructor: start");

		this.seedList = new SeedList(teams);

		System.out.println("EliminationTree: Contructor: this.seedList.size(): " + this.seedList.size());

		this.totalWinBranchRound = calTotalWinBranchRound(this.seedList.size());
		this.winBranch = new BTree<>(this.buildWinBranch(null, 1, this.seedList.size(), 1));
		System.out.println("EliminationTree: Contructor: create Win Branch: done");
		this.winBranchMatchNoIndexing();

		if (formatId == 1l) {
			this.winBranch.setName("Branch");
		} else if (formatId == 2l) {
			this.winBranch.setName("WinBranch");
			
			this.totalLoseBranchRound = calTotalLoseBranchRound(this.seedList.size());
			this.loseBranch = new BTree<>(this.buildLoseBranch(null, 1, this.seedList.size() - 1, 1));
			System.out.println("EliminationTree: Contructor: create Lose Branch: done");
			this.loseBranchMatchNoIndexing();
			this.loseBranch.setName("LoseBranch");
		}
		System.out.println("EliminationTree: Contructor: completeTeamDescription: start");
		this.completeTeamDescription();
		System.out.println("EliminationTree: Contructor: completeTeamDescription: done");

		System.out.println("EliminationTree: Contructor: finish");
	}
	
	
	
	public EliminationTree(BTree<MatchInfo> winBranch, ArrayList<TeamEntity> teams) {
		this.winBranch = winBranch;
		this.loseBranch = null;
		this.seedList = new SeedList(teams);
		this.totalWinBranchRound = calTotalWinBranchRound(this.seedList.size());
		this.totalLoseBranchRound = 0;
		this.winBranchMatchList = this.winBranch.toArrayList();
	}
	
	
	public EliminationTree(BTree<MatchInfo> winBranch, BTree<MatchInfo> loseBranch, ArrayList<TeamEntity> teams) {
		this.winBranch = winBranch;
		this.loseBranch = loseBranch;
		this.seedList = new SeedList(teams);
		this.totalWinBranchRound = calTotalWinBranchRound(this.seedList.size());
		this.totalLoseBranchRound = calTotalLoseBranchRound(this.seedList.size());
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

	public BTree<MatchInfo> getWinBranch() {
		return winBranch;
	}

	public void setWinBranch(BTree<MatchInfo> winBranch) {
		this.winBranch = winBranch;
	}

	public BTree<MatchInfo> getLoseBranch() {
		return loseBranch;
	}

	public void setLoseBranch(BTree<MatchInfo> loseBranch) {
		this.loseBranch = loseBranch;
	}

	// ------------Logic code

	// -------------Win branch
	private Node<MatchInfo> buildWinBranch(Node<MatchInfo> parent, Integer index, Integer totalSeed, Integer leftSeedIndex) {

		System.out.println("EliminationTree: buildWinBranch: start");
		System.out.println(index);
		System.out.println(totalSeed);

		if (totalSeed < 2) // less than 2 seeds
			return null; // can't schedule

		Node<MatchInfo> node = new Node<MatchInfo>();

		node.setId(index);
		node.setParent(parent);

		MatchInfo info = new MatchInfo();

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
		info.setTeam1(null);
		info.setTeam2(null);
		info.setWinner(null);
		info.setLoser(null);
		info.setStatus(1);

		node.setLeft(null);
		node.setRight(null);

		node.setData(info);

		System.out.println("EliminationTree: buildWinBranch: CP4");

		Integer leftRightIndex = (int) Math.pow(2, node.getDegree() + 2) + 1 - leftSeedIndex;
		Integer rightRightIndex = (int) Math.pow(2, node.getDegree() + 2) + 1 - rightSeedIndex;

		if (rightRightIndex > totalSeed) {
//			info.team1 = this.seedList.get(leftSeedIndex - 1);
//			info.team2 = this.seedList.get(rightSeedIndex - 1);

			info.team1Description = new TeamDescription((long) leftSeedIndex);
			info.team2Description = new TeamDescription((long) rightSeedIndex);

			info.setStatus(0);
		} else {
			if (leftRightIndex > totalSeed) {
//				info.team1 = this.seedList.get(leftSeedIndex - 1);
				node.right = buildWinBranch(node, index * 2 + 1, totalSeed, rightSeedIndex);
				info.team2 = node.right.data.winner;

				info.team1Description = new TeamDescription((long) leftSeedIndex);
				info.setStatus(2);
			} else {
				node.left = buildWinBranch(node, index * 2, totalSeed, leftSeedIndex);
				node.right = buildWinBranch(node, index * 2 + 1, totalSeed, rightSeedIndex);

				info.team1 = node.left.data.winner;
				info.team2 = node.right.data.winner;

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
	private Node<MatchInfo> buildLoseBranch(Node<MatchInfo> parent, Integer index, Integer totalLoseTeam, Integer leftIndex) {
		System.out.println("EliminationTree: buildLoseBranch: start");

		Node<MatchInfo> node = new Node<MatchInfo>();
		MatchInfo info = new MatchInfo();

		if (totalLoseTeam < 2) {
			return null;
		}

		System.out.println("EliminationTree: buildLoseBranch: CP1: totalLoseTeam: " + totalLoseTeam.toString());

		node.setId(index);
		node.setParent(parent);

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

		node.left = this.winBranch.getById(leftIndex);
		info.team1 = this.winBranch.getById(leftIndex).data.loser;

		if (this.winBranch.getById(rightLeftIndex) == null) {
			System.out.println("EliminationTree: buildLoseBranch: CP3-1-1: ");

			node.right = this.winBranch.getById(rightIndex);

			info.team2 = this.winBranch.getById(rightIndex).data.loser;

			info.setStatus(3);

		} else {
			System.out.println("EliminationTree: buildLoseBranch: CP3-1-2: ");

			node.right = buildRightLoseBranch(node, index * 2 + 1, totalLoseTeam, rightLeftIndex);

			info.team1 = this.winBranch.getById(leftIndex).data.loser;
			info.team2 = node.right.data.winner;
			info.setStatus(4);

		}

		node.setData(info);

		System.out.println("EliminationTree: buildLoseBranch: CP4");

		this.loseBranchMatchList.add(node);

		System.out.println("EliminationTree: buildLoseBranch: finish");

		return node;
	}

	private Node<MatchInfo> buildRightLoseBranch(Node<MatchInfo> parent, Integer index, Integer totalLoseTeam, Integer leftIndex) {

		System.out.println("EliminationTree: buildRightLoseBranch: start");

		Node<MatchInfo> node = new Node<MatchInfo>();
		MatchInfo info = new MatchInfo();

		if (totalLoseTeam < 2) {
			return null;
		}

		System.out.println("EliminationTree: buildRightLoseBranch: CP1: totalLoseTeam: " + totalLoseTeam.toString());

		node.setId(index);
		node.setParent(parent);

		System.out.println("EliminationTree: buildRightLoseBranch: CP2: parent: " + parent.data.roundNo);

		info.setRoundNo(parent.data.roundNo - 1);

		node.setDegree(parent.getDegree() + 1);
		Integer rightIndex = leftIndex + 1;

		Integer leftRightIndex = reverseIndex(leftIndex) * 2 + 1;
		Integer rightRightIndex = reverseIndex(rightIndex) * 2 + 1;

		node.setData(info);

		System.out.println("EliminationTree: buildRightLoseBranch: CP3: rightRightIndex: " + rightRightIndex);

		if (this.winBranch.getById(rightRightIndex) == null && this.winBranch.getById(leftRightIndex) == null) {
			System.out.println("EliminationTree: buildRightLoseBranch: CP3-1: leftIndex: " + leftIndex);
			System.out.println("EliminationTree: buildRightLoseBranch: CP3-1: rightIndex: " + rightIndex);

			node.left = this.winBranch.getById(leftIndex);
			node.right = this.winBranch.getById(rightIndex);

			System.out.println(
					"EliminationTree: buildRightLoseBranch: CP3-2: getById(leftIndex): " + this.winBranch.getById(leftIndex));
			info.team1 = this.winBranch.getById(leftIndex).data.loser;
			info.team2 = this.winBranch.getById(rightIndex).data.loser;

			System.out.println("EliminationTree: buildRightLoseBranch: CP3-3: getById(rightIndex).data: "
					+ this.winBranch.getById(rightIndex).data);
			info.setStatus(3);
			
		} else {
			if (this.winBranch.getById(leftRightIndex) == null) {
				node.left = this.winBranch.getById(leftIndex);
				node.right = buildLoseBranch(node, index * 2 + 1, totalLoseTeam, rightIndex);

				info.team1 = this.winBranch.getById(leftIndex).data.loser;
				info.team2 = node.right.data.winner;
				info.setStatus(4);
			}

			if (this.winBranch.getById(rightRightIndex) == null) {
				node.left = buildLoseBranch(node, index * 2, totalLoseTeam, leftIndex);
				node.right = this.winBranch.getById(rightIndex);

				info.team1 = node.left.data.winner;
				info.team2 = this.winBranch.getById(rightIndex).data.loser;
				info.setStatus(4);
			}

			if (this.winBranch.getById(rightRightIndex) != null && this.winBranch.getById(leftRightIndex) != null) {
				node.left = buildLoseBranch(node, index * 2, totalLoseTeam, leftIndex);
				node.right = buildLoseBranch(node, index * 2 + 1, totalLoseTeam, rightIndex);

				info.team1 = node.left.data.winner;
				info.team2 = node.right.data.winner;
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
			for (Node<MatchInfo> match : this.winBranchMatchList) {
				if (match.getData().getRoundNo() == round) {
					matchNo++;
					match.data.setMatchNo(matchNo);
					match.data.setName(Const.WIN_BRANCH_NAMING + String.valueOf(matchNo));
					this.winBranch.setById(match.getId(), match.getData());
				}
			}
		}

	}

	private void loseBranchMatchNoIndexing() {

		int matchNo = 0;
		for (int round = 1; round <= this.totalLoseBranchRound; round++) {
			for (Node<MatchInfo> match : this.loseBranchMatchList) {
				if (match.getData().getRoundNo() == round) {
					matchNo++;
					match.data.setMatchNo(matchNo);
					match.data.setName(Const.LOSE_BRANCH_NAMING + String.valueOf(matchNo));
					this.loseBranch.setById(match.getId(), match.getData());
				}
			}
		}

	}

	private void completeTeamDescription() {

		for (Node<MatchInfo> match : this.winBranchMatchList) {
			if (match.left != null) {
				int matchNo = match.left.data.getMatchNo();
				TeamDescription description = new TeamDescription(2l, matchNo);
				match.data.setTeam1Description(description);
			}
			
			if (match.right != null) {
				int matchNo = match.right.data.getMatchNo();
				TeamDescription description = new TeamDescription(2l, matchNo);
				match.data.setTeam2Description(description);
			}
			
			this.winBranch.setById(match.getId(), match.getData());

		}
		
		for (Node<MatchInfo> match : this.loseBranchMatchList) {
			if (match.left != null) {
				
				int matchNo = match.left.data.getMatchNo();
				String name = match.left.data.getName();
				
				TeamDescription description = new TeamDescription();
				
				if (name.contains(Const.WIN_BRANCH_NAMING)) {
					description = new TeamDescription(4l, matchNo);
				}
				if (name.contains(Const.LOSE_BRANCH_NAMING)) {
					description = new TeamDescription(3l, matchNo);
				}
				match.data.setTeam1Description(description);
			}
			
			if (match.right != null) {
				int matchNo = match.right.data.getMatchNo();
				String name = match.right.data.getName();
				
				TeamDescription description = new TeamDescription();
				
				if (name.contains(Const.WIN_BRANCH_NAMING)) {
					description = new TeamDescription(4l, matchNo);
				}
				if (name.contains(Const.LOSE_BRANCH_NAMING)) {
					description = new TeamDescription(3l, matchNo);
				}
				
				match.data.setTeam2Description(description);
			}
			
			this.loseBranch.setById(match.getId(), match.getData());

		}
		System.out.println("EliminationTree: completeTeamDescription: lose tree done");

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
