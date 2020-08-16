package doan2020.SportTournamentSupportSystem.model.LogicEntity;

import com.fasterxml.jackson.annotation.JsonBackReference;

public class Match {
	
	private Long id;
	
	private Integer matchNo;
	private int roundNo;
	
	@JsonBackReference
	private Match nextAfterWin;
	
	@JsonBackReference
	private Match nextAfterLose;
	
	private Long nextAfterWinId;
	private Long nextAfterLoseId;
	
	private Integer team1;
	private Integer team2;
	private Match left;
	private Match right;
	private Integer winner;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getRoundNo() {
		return roundNo;
	}

	public void setRoundNo(Integer roundNo) {
		this.roundNo = roundNo;
	}

	public Match getNextAfterWin() {
		return nextAfterWin;
	}

	public void setNextAfterWin(Match afterWin) {
		this.nextAfterWin = afterWin;
	}

	public Match getNextAfterLose() {
		return nextAfterLose;
	}

	public void setNextAfterLose(Match afterLose) {
		this.nextAfterLose = afterLose;
	}

	public Long getNextAfterWinId() {
		return nextAfterWinId;
	}

	public void setNextAfterWinId(Long nextAfterWinId) {
		this.nextAfterWinId = nextAfterWinId;
	}

	public Long getNextAfterLoseId() {
		return nextAfterLoseId;
	}

	public void setNextAfterLoseId(Long nextAfterLoseId) {
		this.nextAfterLoseId = nextAfterLoseId;
	}

	public Integer getTeam1() {
		return team1;
	}

	public void setTeam1(Integer team1) {
		this.team1 = team1;
	}

	public Integer getTeam2() {
		return team2;
	}

	public void setTeam2(Integer team2) {
		this.team2 = team2;
	}

	public Match getLeft() {
		return left;
	}

	public void setLeft(Match left) {
		this.left = left;
	}

	public Match getRight() {
		return right;
	}

	public void setRight(Match right) {
		this.right = right;
	}

	public Integer getWinner() {
		return winner;
	}

	public void setWinner(Integer winner) {
		this.winner = winner;
	}

	public Integer getMatchNo() {
		return matchNo;
	}

	public void setMatchNo(Integer matchNo) {
		this.matchNo = matchNo;
	}
	
	
	
	
}
