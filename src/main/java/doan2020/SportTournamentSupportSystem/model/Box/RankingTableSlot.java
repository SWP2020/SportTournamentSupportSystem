package doan2020.SportTournamentSupportSystem.model.Box;

import java.io.Serializable;
import java.util.Comparator;

import doan2020.SportTournamentSupportSystem.config.Const;
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
	private Double elo = Const.DEFAULT_ELO;
	private String note = "";

	public RankingTableSlot(Team team) {
		this.team = team;
	}

	public RankingTableSlot(Team team, int seedNo) {
		this.team = team;
//		elo -= new Double(seedNo);
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

	public void updateDifference(Double difference) {
		this.difference += difference;
	}

	public Integer getScore() {
		return score;
	}

	public void updateScore(Integer score) {
		this.score += score;
	}

	@Override
	public int compare(RankingTableSlot o1, RankingTableSlot o2) {

		Double elo1 = o1.getElo();
		Double elo2 = o2.getElo();

		Integer totalWin1 = o1.getTotalWin();
		Integer totalWin2 = o2.getTotalWin();

		Integer score1 = o1.getScore();
		Integer score2 = o2.getScore();

		Double diff1 = o1.getDifference();
		Double diff2 = o2.getDifference();
		
//		System.out.println("Compare: ");
//		System.out.println("team1: " + o1.getTeam());
//		System.out.println("team2: " + o2.getTeam());
//
//		System.out.println("elo1: " + elo1);
//		System.out.println("elo2: " + elo2);
//		System.out.println("totalWin1: " + totalWin1);
//		System.out.println("totalWin2: " + totalWin2);
//		System.out.println("score1: " + score1);
//		System.out.println("score2: " + score2);
//		System.out.println("diff1: " + diff1);
//		System.out.println("diff2: " + diff2);

		if (Math.abs(elo1 - elo2) <= Const.EPSILON) {
//			System.out.println("elo1 == elo2");
			if (totalWin1.intValue() == totalWin2.intValue()) {
//				System.out.println("totalWin1 == totalWin2");
				if (Math.abs(diff2 - diff1) <= Const.EPSILON) {
//					System.out.println("score1 == score2");
					if (score1.intValue() == score2.intValue()) {
//						System.out.println("diff1 == diff2");
						return 0;
					} else {
						return score2 - score1;
					}
				} else {
					return (int) (diff2 - diff1);
				}
			} else {
				return totalWin2 - totalWin1;
			}
		} else {
			return (int) (elo2 - elo1);
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

	public void updateTotalWin() {
		System.out.println("totalWin before "+this.totalWin);
		this.totalWin++;
		System.out.println("totalWin after "+this.totalWin);
	}

	public Integer getTotalLose() {
		return totalLose;
	}

	public void updateTotalLose() {
		System.out.println("totalLose before "+this.totalLose);
		this.totalLose++;
		System.out.println("totalLose after "+this.totalLose);
	}

	public Double getElo() {
		return elo;
	}

	public void updateElo(Double update) {
		this.elo += update;
	}

	public void setElo(Double elo) {
		this.elo = elo;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
