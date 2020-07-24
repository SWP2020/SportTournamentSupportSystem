package doan2020.SportTournamentSupportSystem.dtOut;

public class ViewUserInforOverviewDtOut extends ViewUserInforDtOut{
    private int numberOfTournament;
    private int numberOfTeam;
    
    public ViewUserInforOverviewDtOut() {
    	
    }
    
	public ViewUserInforOverviewDtOut(int numberOfTournament, int numberOfTeam) {
		super();
		this.numberOfTournament = numberOfTournament;
		this.numberOfTeam = numberOfTeam;
	}

	public int getNumberOfTournament() {
		return numberOfTournament;
	}

	public void setNumberOfTournament(int numberOfTournament) {
		this.numberOfTournament = numberOfTournament;
	}

	public int getNumberOfTeam() {
		return numberOfTeam;
	}

	public void setNumberOfTeam(int numberOfTeam) {
		this.numberOfTeam = numberOfTeam;
	}
    
    
}
