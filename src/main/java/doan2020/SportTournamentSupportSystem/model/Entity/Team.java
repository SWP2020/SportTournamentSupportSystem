package doan2020.SportTournamentSupportSystem.model.Entity;

import java.io.Serializable;

public class Team implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String fullName;
	private String shortName;
	
	private Double difference = 0.0;
	private Integer score = 0;
	private Integer totalWin = 0;
	private Integer totalLose = 0;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public Double getDifference() {
		return difference;
	}
	public void setDifference(Double difference) {
		this.difference = difference;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Integer getTotalWin() {
		return totalWin;
	}
	public void setTotalWin(Integer totalWin) {
		this.totalWin = totalWin;
	}
	public Integer getTotalLose() {
		return totalLose;
	}
	public void setTotalLose(Integer totalLose) {
		this.totalLose = totalLose;
	}
	
	
	
}