package doan2020.SportTournamentSupportSystem.model.LogicEntity;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonBackReference;

import doan2020.SportTournamentSupportSystem.entity.MatchEntity;

public class Match {
	
	private Long id;
	private int roundNo;
	
	@JsonBackReference
	private Match next;
	private int team1;
	private int team2;
//	private ArrayList<Match> children;
	private Match left;
	private Match right;
	private int winner;
	private MatchEntity entity;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getRoundNo() {
		return roundNo;
	}
	public void setRoundNo(int roundNo) {
		this.roundNo = roundNo;
	}
	public Match getNext() {
		return next;
	}
	public void setNext(Match next) {
		this.next = next;
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
	public int getTeam1() {
		return team1;
	}
	public void setTeam1(int team1) {
		this.team1 = team1;
	}
	public int getTeam2() {
		return team2;
	}
	public void setTeam2(int team2) {
		this.team2 = team2;
	}
	public int getWinner() {
		return winner;
	}
	public void setWinner(int winner) {
		this.winner = winner;
	}
	public MatchEntity getEntity() {
		return entity;
	}
	public void setEntity(MatchEntity entity) {
		this.entity = entity;
	}
	
	
}
