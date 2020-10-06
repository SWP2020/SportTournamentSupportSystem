package doan2020.SportTournamentSupportSystem.model.BoxCollection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import doan2020.SportTournamentSupportSystem.config.Const;
import doan2020.SportTournamentSupportSystem.entity.TeamEntity;
import doan2020.SportTournamentSupportSystem.model.Box.RankingTableSlot;
import doan2020.SportTournamentSupportSystem.model.Box.Seed;
import doan2020.SportTournamentSupportSystem.model.Entity.Team;
import doan2020.SportTournamentSupportSystem.model.Indexing.BoxDescription;

public class RankingTable extends ArrayList<RankingTableSlot> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int totalTeam;

	public RankingTable() {
		super();
	}

	public RankingTable(int totalTeam) {
		this.totalTeam = totalTeam;
		for (int i = 0; i < totalTeam; i++) {
			BoxDescription description = new BoxDescription(6l, i + 1);
			RankingTableSlot slot = new RankingTableSlot();
			slot.setDescription(description);
			this.add(slot);

		}
	}

	public RankingTable(ArrayList<Team> teams) {
		this.totalTeam = teams.size();
		int seedNo = 0;
		for (Team team : teams) {
			RankingTableSlot slot = new RankingTableSlot(team, seedNo);
			this.add(slot);
			seedNo++;
		}
		Collections.sort(this, new RankingTableSlot());
	}

	public RankingTable(int totalTeam, int tableId) {
		this.totalTeam = totalTeam;
		for (int i = 0; i < totalTeam; i++) {
			BoxDescription description = new BoxDescription(tableId, (long) (i + 1));
			RankingTableSlot slot = new RankingTableSlot();
			slot.setDescription(description);
			this.add(slot);
		}
	}

	public void applyTeams(ArrayList<TeamEntity> teams) {
		System.out.println("RankingTable: applyTeams: start");
		java.util.Collections.sort(teams, new TeamEntity());
		int seedNo = 0;
		for (RankingTableSlot slot : this) {
			Team t = new Team();
			t.setId(teams.get(seedNo).getId());
			t.setShortName(teams.get(seedNo).getShortName());
			t.setFullName(teams.get(seedNo).getFullName());

			slot.setTeam(t);
//			slot.updateElo(new Double(-1*seedNo));
			seedNo++;
		}
		System.out.println("RankingTable: applyTeams: finish");
	}

	public void applyDescriptions(int tableId) {
		for (int i = 0; i < this.totalTeam; i++) {
			BoxDescription description = new BoxDescription(tableId, (long) (i + 1));
			RankingTableSlot slot = this.get(i);
			slot.setDescription(description);
		}
	}

	public Double getEloByTeamId(Long teamId) {
		for (RankingTableSlot slot : this) {
			if (slot.getTeam() != null && slot.getTeam().getId().longValue() == teamId.longValue()) {
				return slot.getElo();
			}
		}
		return 0.0;
	}

	public void swapEloOfTwoTeams(Long team1Id, Long team2Id) {
		double eloTeam1 = 0, eloTeam2 = 0;
		int unitIndex1 = -1;
		Long unitRank1 = null;
		int unitIndex2 = -1;
		Long unitRank2 = null;
//		team1Id = (long) 54;
//		team2Id = (long) 55;
		for (int i = 0; i < this.size(); i++) {
			System.out.println("test: " + i);
			System.out.println(this.get(i).getTeam().getId());
			System.out.println(team1Id);
			System.out.println(this.get(i).getTeam().getId() == (long) team1Id);
			System.out.println(this.get(i).getDescription().getUnitIndex());
			System.out.println(this.get(i).getDescription().getUnitRank());
//			System.out.println(this.get(i).getTeam().getId() == 54);
			if (this.get(i).getTeam().getId() == (long) team1Id) {
				eloTeam1 = this.get(i).getElo();
				unitIndex1 = this.get(i).getDescription().getUnitIndex();
				unitRank1 = this.get(i).getDescription().getUnitRank();
				System.out.println(this.get(i).getElo());
				System.out.println(this.get(i).getDescription().getUnitIndex());
				System.out.println(this.get(i).getDescription().getUnitRank());
			}
			System.out.println(this.get(i).getTeam().getId());
			System.out.println(team2Id);
			System.out.println(this.get(i).getTeam().getId() == (long) team2Id);
			if (this.get(i).getTeam().getId() == (long) team2Id) {
				eloTeam2 = this.get(i).getElo();
				unitIndex2 = this.get(i).getDescription().getUnitIndex();
				unitRank2 = this.get(i).getDescription().getUnitRank();
				System.out.println(this.get(i).getElo());
				System.out.println(this.get(i).getDescription().getUnitIndex());
				System.out.println(this.get(i).getDescription().getUnitRank());
			}
		}

		for (int i = 0; i < this.size(); i++) {
			System.out.println("test_2: " + i);
			System.out.println(this.get(i).getTeam().getId());
			if (this.get(i).getTeam().getId() == (long) team1Id) {
				this.get(i).setElo(eloTeam2);
				BoxDescription description = this.get(i).getDescription();
				description.setUnitIndex(unitIndex2);
				description.setUnitRank(unitRank2);
				this.get(i).setDescription(description);
				System.out.println(unitIndex2);
				System.out.println(unitRank2);
				System.out.println(eloTeam2);
			}
			if (this.get(i).getTeam().getId() == (long) team2Id) {
				this.get(i).setElo(eloTeam1 + 1);
				BoxDescription description = this.get(i).getDescription();
				description.setUnitIndex(unitIndex1);
				description.setUnitRank(unitRank1);
				this.get(i).setDescription(description);
				System.out.println(unitIndex1);
				System.out.println(unitRank1);
				System.out.println(eloTeam1);
			}
		}
		Collections.sort(this, new RankingTableSlot());

		System.out.println("success");
	}

	public void updateByTeamId(Long teamId, Integer score, Double diff, boolean isWin, Double eloBonus) {
		System.out.println("RankingTable: updateByTeamId: teamId: " + teamId);
		for (RankingTableSlot slot : this) {
			if (slot.getTeam() != null && slot.getTeam().getId().longValue() == teamId.longValue()) {
				System.out.println("RankingTable: updateByTeamId: team: " + slot.getTeam());
				System.out.println("Slot totalWin: " + slot.getTotalWin());
				slot.updateScore(score);
				slot.updateDifference(diff);
				System.out.println("isWin " + isWin);
				if (isWin) {
					slot.updateTotalWin();
				} else {
					slot.updateTotalLose();
				}
				slot.updateElo(eloBonus);
				Collections.sort(this, new RankingTableSlot());
				break;
			}
		}
		Collections.sort(this, new RankingTableSlot());
	}

	public void update34ByTeamId(Long team3Id, Long team4Id) {
		System.out.println("RankingTable: updateByTeamId: teamId: " + team3Id);
		RankingTableSlot temp3 = this.get(2);
		RankingTableSlot temp4 = this.get(3);
		for (RankingTableSlot slot : this) {
			if (slot.getTeam() != null && slot.getTeam().getId().longValue() == team3Id.longValue()) {
				temp3 = slot;
				System.out.println("temp3"+temp3.getTeam().getId());
			}
			if (slot.getTeam() != null && slot.getTeam().getId().longValue() == team4Id.longValue()) {
				temp4 = slot;
				System.out.println("temp4"+temp4.getTeam().getId());
			}
		}
		
		this.set(2, temp3);
		this.set(3, temp4);
		
		System.out.println(this.get(2));
		System.out.println(this.get(3));
		

	}
	
	public void updateNoteByTeamId(Long teamId,String note) {
		for (RankingTableSlot slot : this) {
			if (slot.getTeam() != null && slot.getTeam().getId().longValue() == teamId.longValue()) {
				slot.setNote(note);
				break;
			}
		}
	}

	public void addTeam(Team team) {
		for (RankingTableSlot slot : this) {
			if (slot.getTeam() == null) {
				slot.setTeam(team);
				return;
			}
		}
	}
//	public static void main(String[] args) {
//		RankingTable x = new RankingTable(4, 0);
//		for (RankingTableSlot y: x) {
////			System.out.println(y.getTeam().getId()+": "+y.getElo());
//			System.out.println(y.getDescription().getDescription()+": "+y.getDifference()+": "+y.getTeam()+": "+y.getTotalWin()+": "+y.getTotalLose());
//		}
//	}

}
