package doan2020.SportTournamentSupportSystem.model.ScheduleFormat;

import java.io.Serializable;
import java.util.ArrayList;

import doan2020.SportTournamentSupportSystem.config.Const;
import doan2020.SportTournamentSupportSystem.model.Entity.Match;
import doan2020.SportTournamentSupportSystem.model.LogicStruct.MatchSlot;
import doan2020.SportTournamentSupportSystem.model.LogicStruct.TeamDescription;
import doan2020.SportTournamentSupportSystem.model.Struct.ScheduleStruct;

public class RoundRobinTable extends ScheduleStruct implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name = "RoundRobinTable";
	private String matchNaming = Const.ROUND_ROBIN_MATCH_NAMING; 

	private int totalMatch;
	private boolean homeMatch = false;

	private int totalTeam;

	public RoundRobinTable() {
		super();
	}

	public RoundRobinTable(Long id, int totalTeam, boolean homeMatch) {
		super(totalTeam, id.intValue());
		this.id = id;
		if (id >= 0) {
			this.matchNaming = Const.TABLE_NAMING.charAt(this.id.intValue()) + "-";
			this.name = "Table " + Const.TABLE_NAMING.charAt(this.id.intValue());
		}
		this.homeMatch = homeMatch;
		this.totalTeam = totalTeam;
		this.totalRound = totalTeam - ((totalTeam + 1) % 2);
		this.matches = this.createMatches(this.totalTeam, homeMatch);
		this.setTotalMatch(this.matches.size());
	}

	public RoundRobinTable(int totalTeam, boolean homeMatch) {
		super(totalTeam);
		this.id = -1l;
		this.homeMatch = homeMatch;
		this.totalTeam = totalTeam;
		this.totalRound = totalTeam - ((totalTeam + 1) % 2);
		this.matches = this.createMatches(this.totalTeam, homeMatch);
		this.setTotalMatch(this.matches.size());
	}

	public RoundRobinTable(int totalTeam) {
		super(totalTeam);
		this.id = -1l;
		this.homeMatch = false;
		this.totalTeam = totalTeam;
		this.totalRound = totalTeam - ((totalTeam + 1) % 2);
		this.matches = this.createMatches(this.totalTeam, homeMatch);
		this.setTotalMatch(this.matches.size());
	}

	private ArrayList<Match> createMatches(int totalTeam, boolean homeMatch) {
		ArrayList<Match> matches = new ArrayList<>();

		if (homeMatch) {
			matches.addAll(createMatches(totalTeam, false));
		}

		int totalSeed = totalTeam + totalTeam % 2; // totalSeed % 2 == 0 (always)

		int runSeed = totalSeed;
		int rouletteTotal = totalSeed - 1;
		int coupleTotal = (rouletteTotal - 1) / 2;
		int totalMatch = matches.size();

		for (int round = 1; round <= rouletteTotal; round++) {
			int freeSeed = round;

			if (runSeed == totalTeam) {
				totalMatch++;
				Match info = new Match();
				info.setMatchNo(totalMatch);
				info.setName(this.matchNaming + info.getMatchNo());
				info.setRoundNo(round);
				info.setTeam1(new MatchSlot());
				info.setTeam2(new MatchSlot());
				info.setWinner(new MatchSlot());
				info.setLoser(new MatchSlot());
				info.setLocation(null);
				info.setTime(null);
				info.setStatus(0);
				if ((freeSeed % 2 != 0 && !homeMatch) || (freeSeed % 2 == 0 && homeMatch)) {
					info.getTeam1().setDescription(new TeamDescription((long) freeSeed));
					info.getTeam2().setDescription(new TeamDescription((long) runSeed));
				} else {
					info.getTeam1().setDescription(new TeamDescription((long) runSeed));
					info.getTeam2().setDescription(new TeamDescription((long) freeSeed));
				}
				matches.add(info);

			}

			for (int i = 1; i <= coupleTotal; i++) {
				int left = (freeSeed - 1 + i + rouletteTotal) % rouletteTotal + 1;
				int right = (freeSeed - 1 - i + rouletteTotal) % rouletteTotal + 1;

				totalMatch++;
				Match info = new Match();
				info.setMatchNo(totalMatch);
				info.setName(this.matchNaming + info.getMatchNo());
				info.setRoundNo(round);
				info.setTeam1(new MatchSlot());
				info.setTeam2(new MatchSlot());
				info.setWinner(new MatchSlot());
				info.setLoser(new MatchSlot());
				info.setLocation(null);
				info.setTime(null);
				info.setStatus(0);
				if (homeMatch) {
					info.getTeam1().setDescription(new TeamDescription((long) left));
					info.getTeam2().setDescription(new TeamDescription((long) right));
				} else {
					info.getTeam1().setDescription(new TeamDescription((long) right));
					info.getTeam2().setDescription(new TeamDescription((long) left));
				}

				matches.add(info);
			}

		}

		return matches;
	}

	@Override
	protected void applySeedList() {
		for (Match match : this.matches) {
			TeamDescription description1 = match.getTeam1().getDescription();
			match.getTeam1().setTeam(this.seedList.get(description1.getUnitIndex() - 1));

			TeamDescription description2 = match.getTeam2().getDescription();
			match.getTeam2().setTeam(this.seedList.get(description2.getUnitIndex() - 1));
		}
	}

	@Override
	protected void applyDescriptions() {
		for (Match match : this.matches) {
			TeamDescription description1 = match.getTeam1().getDescription();
			match.getTeam1().setDescription(this.descriptions.get(description1.getUnitIndex() - 1));

			TeamDescription description2 = match.getTeam2().getDescription();
			match.getTeam2().setDescription(this.descriptions.get(description2.getUnitIndex() - 1));
		}
	}

	public boolean isHomeMatch() {
		return homeMatch;
	}

	public void setHomeMatch(boolean homeMatch) {
		this.homeMatch = homeMatch;
	}

	public int getTotalMatch() {
		return totalMatch;
	}

	public void setTotalMatch(int totalMatch) {
		this.totalMatch = totalMatch;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Match> getMatches() {
		return matches;
	}

}
