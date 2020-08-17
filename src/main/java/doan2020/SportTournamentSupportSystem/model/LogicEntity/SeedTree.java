package doan2020.SportTournamentSupportSystem.model.LogicEntity;

import java.util.ArrayList;

import doan2020.SportTournamentSupportSystem.model.BTree;

public class SeedTree extends BTree<MatchInfo>{

	private SeedList seedList = new SeedList();
	
	private Integer totalWinBranchRound;
	private Integer totalLoseBranchRound;
	
	private ArrayList<MatchNode> matches = new ArrayList<>();
	
	private MatchNode winBranch;
	private MatchNode loseBranch;
	
	
	
	//------------Constructor
	
	public SeedTree() {
		super();
	}
	
	public SeedTree(MatchNode root) {
		super(root);
	}
	
	public SeedTree(int totalTeam) { // test
		System.out.println("SeedTree: Contructor test: start");
		
		for (int i=1; i<=totalTeam; i++) {
			Team t = new Team();
			t.setId(i);
			this.seedList.add(t);
		}
		
		this.totalWinBranchRound = calTotalWinBranchRound(this.seedList.size());
		
		this.winBranch = this.buildWinBranch(null, 1, this.seedList.size(), 1);
		System.out.println("SeedTree: Contructor test: finish");
	}
	
	//------------Getter setter

	public SeedList getSeedList() {
		return seedList;
	}

	public void setSeedList(SeedList seedList) {
		this.seedList = seedList;
	}
	
	public MatchNode getWinBranch() {
		return winBranch;
	}

	public void setWinBranch(MatchNode winBranch) {
		this.winBranch = winBranch;
	}

	public MatchNode getLoseBranch() {
		return loseBranch;
	}

	public void setLoseBranch(MatchNode loseBranch) {
		this.loseBranch = loseBranch;
	}
	
	//------------Logic code
	
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
		
		if (parent == null) { // this is root
			info.setRoundNo(this.totalWinBranchRound);
		} else {
			info.setRoundNo(parent.getData().getRoundNo() - 1);
		}
		
		System.out.println("SeedTree: buildWinBranch: CP2");
		
		Integer rightSeedIndex = (int) Math.pow(2, this.totalWinBranchRound - info.getRoundNo() + 1) + 1 - leftSeedIndex;
		
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
		
		if (totalSeed == 2) { // round 1
			info.team1 = this.seedList.get(leftSeedIndex - 1);
			info.team2 = this.seedList.get(rightSeedIndex - 1);
			
			info.team1Description = "seed #" + new Integer(leftSeedIndex).toString();
			info.team2Description = "seed #" + new Integer(rightSeedIndex).toString();
			
			info.setStatus(0);
		}
		
		System.out.println("SeedTree: buildWinBranch: CP5");
		
		if (totalSeed == 3) {
			info.team1 = this.seedList.get(leftSeedIndex - 1);
			node.right = buildWinBranch(node, index * 2 + 1, 2, rightSeedIndex);
			info.team2 = node.right.data.winner;
			
			info.team1Description = "seed #" + new Integer(leftSeedIndex).toString();
//			info.team2Description = "W-" + node.right.id.toString();
			
			
			info.setStatus(2);
		}
		
		System.out.println("SeedTree: buildWinBranch: CP6");
		
		if (totalSeed > 3) {
			node.left = buildWinBranch(node, index * 2, (totalSeed + 1) / 2, leftSeedIndex);
			node.right = buildWinBranch(node, index * 2 + 1, totalSeed / 2, rightSeedIndex);
			
			info.team1 = node.left.data.winner;
			info.team2 = node.right.data.winner;
			
//			info.team1Description = "W-" + node.left.id.toString();
//			info.team2Description = "W-" + node.right.id.toString();
			
			info.setStatus(1);
			
		}
		
		node.setData(info);
		
		this.matches.add(node);
		
		System.out.println("SeedTree: buildWinBranch: finish");
		
		return node;
	}
	
	/*
	 * level:
	 * 0: 
	 */
	public MatchNode initLoseBranch(MatchNode parent, Integer index, Integer level) {
		MatchNode node = new MatchNode();
//		if 
		return node;
	}
	
	//----------- support logic code
	
	public Integer calTotalWinBranchRound(Integer size) {
		int totalRound = 0;
		int x = 1;
		while (x < size) {
			x *= 2;
			totalRound ++;
		}
		return totalRound;
	}

	

}
