package doan2020.SportTournamentSupportSystem.model.LogicEntity;

import java.util.ArrayList;

import doan2020.SportTournamentSupportSystem.entity.TeamEntity;

public class EliminationTree {

	private SeedList seedList = new SeedList();
	
	private Integer totalWinBranchRound;
	private Integer totalLoseBranchRound;
	
	private ArrayList<MatchNode> winBranchMatchList = new ArrayList<>();
	private ArrayList<MatchNode> loseBranchMatchList = new ArrayList<>();
	
	private BTree<MatchInfo> winBranch;
	private BTree<MatchInfo> loseBranch;
	
	
	
	//------------Constructor
	
	public EliminationTree() {
	}
	
	
	public EliminationTree(int totalTeam, int formatId) { // test
		System.out.println("SeedTree: Contructor test: start");
		
		for (int i=1; i<=totalTeam; i++) {
			Team t = new Team();
			t.setId((long)i);
			this.seedList.add(t);
		}
		
		this.totalWinBranchRound = calTotalWinBranchRound(this.seedList.size());
		this.winBranch = new BTree<>(this.buildWinBranch(null, 1, this.seedList.size(), 1));
		this.winBranchMatchNoIndexing();
		
		if (formatId == 1l) {
			this.winBranch.setName("Bracket");
		} else if (formatId == 2l) {
			this.winBranch.setName("WinBranch");
			this.totalLoseBranchRound = calTotalLoseBranchRound(this.seedList.size());
			this.loseBranch = new BTree<>(this.buildLoseBranch(null, 1, this.seedList.size() - 1, 1));
			this.loseBranchMatchNoIndexing();
			this.loseBranch.setName("LoseBranch");
		}

	}
	
	public EliminationTree(ArrayList<TeamEntity> teams, Long formatId) {
		System.out.println("SeedTree: Contructor: start");
		
		
		this.seedList = new SeedList(teams);
		
		System.out.println("SeedTree: Contructor: this.seedList.size(): " + this.seedList.size());
		
		this.totalWinBranchRound = calTotalWinBranchRound(this.seedList.size());
		this.winBranch = new BTree<>(this.buildWinBranch(null, 1, this.seedList.size(), 1));
		this.winBranchMatchNoIndexing();
		
		if (formatId == 1l) {
			this.winBranch.setName("Branch");
		} else if (formatId == 2l) {
			this.winBranch.setName("Win Branch");
			this.totalLoseBranchRound = calTotalLoseBranchRound(this.seedList.size());
			this.loseBranch = new BTree<>(this.buildLoseBranch(null, 1, this.seedList.size() - 1, 1));
			this.loseBranchMatchNoIndexing();
			this.loseBranch.setName("Lose Branch");
		}
		
		
		
		
		System.out.println("SeedTree: Contructor: finish");
	}
	
	//------------Getter setter

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
	
	//------------Logic code
	
	
	//-------------Win branch
	public MatchNode buildWinBranch(MatchNode parent, Integer index, Integer totalSeed, Integer leftSeedIndex) {
		
		System.out.println("SeedTree: buildWinBranch: start");
		System.out.println(index);
		System.out.println(totalSeed);
		
		if (totalSeed < 2) // less than 2 seeds
			return null;   // can't schedule
		
		MatchNode node = new MatchNode();
		
		node.setId(index);
		node.setParent(parent);
		
		MatchInfo info = new MatchInfo();
		
		System.out.println("SeedTree: buildWinBranch: CP1");
		
		if (index == 1) { // this is root
			info.setRoundNo(this.totalWinBranchRound);
			node.setDegree(0);
		} else {
			info.setRoundNo(parent.getData().getRoundNo() - 1);
			node.setDegree(parent.getDegree() + 1); 
		}
		
		Integer rightSeedIndex = (int) Math.pow(2, node.getDegree() + 1) + 1 - leftSeedIndex;
		
		System.out.println("SeedTree: buildWinBranch: CP2");
		
		System.out.println("SeedTree: buildWinBranch: CP3");
		info.setName("");
		info.setTeam1(null);
		info.setTeam2(null);
		info.setWinner(null);
		info.setLoser(null);
		info.setStatus(1);
		
		node.setLeft(null);
		node.setRight(null);
		
		node.setData(info);
		
		System.out.println("SeedTree: buildWinBranch: CP4");
		
		Integer leftRightIndex = (int) Math.pow(2, node.getDegree() + 2) + 1 - leftSeedIndex;
		Integer rightRightIndex = (int) Math.pow(2, node.getDegree() + 2) + 1 - rightSeedIndex;
		
		if (rightRightIndex > totalSeed) {
			info.team1 = this.seedList.get(leftSeedIndex - 1);
			info.team2 = this.seedList.get(rightSeedIndex - 1);
			
			
			
			info.team1Description = "seed #" + new Integer(leftSeedIndex).toString();
			info.team2Description = "seed #" + new Integer(rightSeedIndex).toString();
			
			info.setStatus(0);
		} else {
			if (leftRightIndex > totalSeed) {
				info.team1 = this.seedList.get(leftSeedIndex - 1);
				node.right = buildWinBranch(node, index * 2 + 1, totalSeed, rightSeedIndex);
				info.team2 = node.right.data.winner;
				
				info.team1Description = "seed #" + new Integer(leftSeedIndex).toString();
				info.setStatus(2);
			} else {
				node.left = buildWinBranch(node, index * 2, totalSeed, leftSeedIndex);
				node.right = buildWinBranch(node, index * 2 + 1, totalSeed, rightSeedIndex);
				
				info.team1 = node.left.data.winner;
				info.team2 = node.right.data.winner;
				
//				info.team1Description = "W-" + node.left.id.toString();
//				info.team2Description = "W-" + node.right.id.toString();
				
				info.setStatus(1);
			}
		}
		
		System.out.println("SeedTree: buildWinBranch: CP5");
		
		node.setData(info);
		
		this.winBranchMatchList.add(node);
		
		System.out.println("SeedTree: buildWinBranch: finish");
		
		return node;
	}
	
	
	public void winBranchMatchNoIndexing() {
		
		int matchNo = 0;
		for (int round=1; round <= this.totalWinBranchRound; round ++) {
			for (MatchNode match: this.winBranchMatchList) {
				if (match.getData().getRoundNo() == round) {
					matchNo ++;
					match.data.setMatchNo(matchNo);
					match.data.setName("A-" + String.valueOf(matchNo));
					this.winBranch.setById(match.getId(), match.getData());
				}
			}
		}
		
	}
	
	
	public void loseBranchMatchNoIndexing() {
		
		int matchNo = 0;
		for (int round=1; round <= this.totalLoseBranchRound; round ++) {
			for (MatchNode match: this.loseBranchMatchList) {
				if (match.getData().getRoundNo() == round) {
					matchNo ++;
					match.data.setMatchNo(matchNo);
					match.data.setName("B-" + String.valueOf(matchNo));
					this.loseBranch.setById(match.getId(), match.getData());
				}
			}
		}
		
	}
	
	
	
	
	
	
	
	
	
	// -----------------Lose branch 
	public MatchNode buildLoseBranch(MatchNode parent, Integer index, Integer totalLoseTeam, Integer leftIndex) {
		System.out.println("SeedTree: buildLoseBranch: start");
		
		MatchNode node = new MatchNode();
		MatchInfo info = new MatchInfo();
		
		
		if (totalLoseTeam < 2) {
			return null;
		}
		
		System.out.println("SeedTree: buildLoseBranch: CP1: totalLoseTeam: " + totalLoseTeam.toString());
		
		node.setId(index);
		node.setParent(parent);
		
		System.out.println("SeedTree: buildLoseBranch: CP2: parent: " + parent);
		
		if (index == 1) { // this is root
			info.setRoundNo(this.totalLoseBranchRound);
			node.setDegree(0);
		} else {
			info.setRoundNo(parent.getData().getRoundNo() - 1);
			node.setDegree(parent.getDegree() + 1);
		}

		Integer rightIndex = reverseIndex(leftIndex) * 2 + 1;
		
//		int leftChild = rightIndex - 1;
		
		node.setData(info);
		
		System.out.println("SeedTree: buildLoseBranch: CP3: leftIndex: " + leftIndex);
		
		Integer rightLeftIndex = rightIndex - 1;
		
		node.left = this.winBranch.getById(leftIndex);
		info.team1 = this.winBranch.getById(leftIndex).data.loser;
		info.setTeam1Description("Lose " + this.winBranch.getById(leftIndex).data.name);
		
		if (this.winBranch.getById(rightLeftIndex) == null) {
			System.out.println("SeedTree: buildLoseBranch: CP3-1-1: ");
			
			node.right = this.winBranch.getById(rightIndex);
			
			
			info.team2 = this.winBranch.getById(rightIndex).data.loser;
			
			info.setStatus(3);
			
			
			info.setTeam2Description("Lose " + this.winBranch.getById(rightIndex).data.name);
			
			node.setData(info);
		} else {
			System.out.println("SeedTree: buildLoseBranch: CP3-1-2: ");

			node.right = buildRightLoseBranch(node, index * 2 + 1 , totalLoseTeam, rightLeftIndex);
			
			info.team1 = this.winBranch.getById(leftIndex).data.loser;
			info.team2 = node.right.data.winner;
			info.setStatus(4);
			
//			info.setTeam2Description("Win B-" + rightIndex.toString());
			
			
		}
		
		node.setData(info);
		
		
		System.out.println("SeedTree: buildLoseBranch: CP4");
		
		this.loseBranchMatchList.add(node);
		
		System.out.println("SeedTree: buildLoseBranch: finish");
		
		return node;
	}
	
	public MatchNode buildRightLoseBranch(MatchNode parent, Integer index, Integer totalLoseTeam, Integer leftIndex) {
		
		System.out.println("SeedTree: buildRightLoseBranch: start");
		
		MatchNode node = new MatchNode();
		MatchInfo info = new MatchInfo();
		
		
		if (totalLoseTeam < 2) {
			return null;
		}
		
		System.out.println("SeedTree: buildRightLoseBranch: CP1: totalLoseTeam: " + totalLoseTeam.toString());
		
		node.setId(index);
		node.setParent(parent);
		
		System.out.println("SeedTree: buildRightLoseBranch: CP2: parent: " + parent.data.roundNo);
		
		info.setRoundNo(parent.data.roundNo - 1);

		node.setDegree(parent.getDegree() + 1);
		Integer rightIndex = leftIndex + 1;
		
		Integer leftRightIndex = reverseIndex(leftIndex) * 2 + 1;
		Integer rightRightIndex = reverseIndex(rightIndex) * 2 + 1;
		
		node.setData(info);
		
		System.out.println("SeedTree: buildRightLoseBranch: CP3: rightRightIndex: " + rightRightIndex);
		
		if (this.winBranch.getById(rightRightIndex) == null && this.winBranch.getById(leftRightIndex) == null) {
			System.out.println("SeedTree: buildRightLoseBranch: CP3-1: leftIndex: " + leftIndex);
			System.out.println("SeedTree: buildRightLoseBranch: CP3-1: rightIndex: " + rightIndex);
			
			node.left = this.winBranch.getById(leftIndex);
			node.right = this.winBranch.getById(rightIndex);
			
			System.out.println("SeedTree: buildRightLoseBranch: CP3-2: getById(leftIndex): " + this.winBranch.getById(leftIndex));
			info.team1 = this.winBranch.getById(leftIndex).data.loser;
			info.team2 = this.winBranch.getById(rightIndex).data.loser;
			
			System.out.println("SeedTree: buildRightLoseBranch: CP3-3: getById(rightIndex).data: " + this.winBranch.getById(rightIndex).data);
			info.setStatus(3);
			
			info.setTeam1Description("Lose " + this.winBranch.getById(leftIndex).data.name);
			info.setTeam2Description("Lose " + this.winBranch.getById(rightIndex).data.name);
			
			node.setData(info);
		} else {
			if (this.winBranch.getById(leftRightIndex) == null) {
				node.left = this.winBranch.getById(leftIndex);
				node.right = buildLoseBranch(node, index * 2 + 1, totalLoseTeam, rightIndex);
				
				info.team1 = this.winBranch.getById(leftIndex).data.loser;
				info.team2 = node.right.data.winner;
				info.setStatus(4);
				
				info.setTeam1Description("Lose " + this.winBranch.getById(leftIndex).data.name);
//				info.setTeam2Description("Win " + rightIndex.toString());
				
				node.setData(info);
			}
			
			if (this.winBranch.getById(rightRightIndex) == null) {
				node.left = buildLoseBranch(node, index * 2, totalLoseTeam, leftIndex);
				node.right = this.winBranch.getById(rightIndex);
				
				info.team1 = node.left.data.winner;
				info.team2 = this.winBranch.getById(rightIndex).data.loser;
				info.setStatus(4);
				
//				info.setTeam1Description("Lose " + this.winBranch.getById(leftIndex).data.name);
				info.setTeam2Description("Lose " + this.winBranch.getById(rightIndex).data.name);
				
				node.setData(info);
			}
			
			if (this.winBranch.getById(rightRightIndex) != null && this.winBranch.getById(leftRightIndex) != null) {
				node.left = buildLoseBranch(node, index * 2, totalLoseTeam, leftIndex);
				node.right = buildLoseBranch(node, index * 2 + 1, totalLoseTeam, rightIndex);
				
				info.team1 = node.left.data.winner;
				info.team2 = node.right.data.winner;
				info.setStatus(1);
			}
		}
		
		System.out.println("SeedTree: buildRightLoseBranch: CP5");
		
		this.loseBranchMatchList.add(node);
		
		System.out.println("SeedTree: buildRightLoseBranch: finish");
		return node;
	}
	
	//----------- support logic code
	
	public Integer reverseIndex(Integer index) {
		Integer k = floorLog2(index + 1);
		Integer _2powk = (int) Math.pow(2, k);
		return _2powk + _2powk / 2 - 1 - index;
	}
	
	
	public int floorLog2(int x) {
		int k = 0;
		int y = 1;
		while (y < x) {
			y *= 2;
			k ++;
		}
		
		return k;
		
	}
	
	
	public Integer calTotalWinBranchRound(Integer size) {
		int totalRound = 0;
		int x = 1;
		while (x < size) {
			x *= 2;
			totalRound ++;
		}
		return totalRound;
	}
	
	
	public Integer calTotalLoseBranchRound(Integer size) {
		int totalRound = 0;
		int x = 1;
		while (x < size) {
			x *= 2;
			totalRound ++;
		}
		
		x /= 2;
		totalRound = (totalRound - 1) * 2;
		if (x + x / 2 >= size)
			totalRound -= 1;
		
		return totalRound;
			
		
	}
	

}
