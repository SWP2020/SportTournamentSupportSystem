package doan2020.SportTournamentSupportSystem.model.Box;

import java.io.Serializable;
import java.util.Comparator;

import doan2020.SportTournamentSupportSystem.model.Entity.Team;
import doan2020.SportTournamentSupportSystem.model.Indexing.BoxDescription;

public class RankingTableSlot implements Serializable, Comparator<RankingTableSlot> {

	private static final long serialVersionUID = 1L;

	private Team team;
	private BoxDescription description;
	private Double difference = 0.0;
	private Integer score = 0;
	private Integer totalWin = 0;
	private Integer totalLose = 0;

	public RankingTableSlot(Team team) {
		this.team = team;
	}

	public RankingTableSlot() {
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
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

	@Override
	public int compare(RankingTableSlot o1, RankingTableSlot o2) {
		Integer totalWin1 = o1.getTotalWin();
		Integer totalWin2 = o2.getTotalWin();
		Integer score1 = o1.getScore();
		Integer score2 = o2.getScore();
		Double diff1 = o1.getDifference();
		Double diff2 = o2.getDifference();
		if (totalWin1 == totalWin2) {
			if (score1 == score2) {
				return (int) Math.ceil(diff2 - diff1);
			} else {
				return score2 - score1;
			}
		} else {
			return totalWin2 - totalWin1;
		}
	}

	public BoxDescription getDescription() {
		return description;
	}

	public void setDescription(BoxDescription description) {
		this.description = description;
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
