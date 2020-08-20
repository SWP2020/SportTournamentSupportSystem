
package doan2020.SportTournamentSupportSystem.dto;

public class MatchDTO{

	private Long id;
	private String name;
	private int numOfSet;
	private String expectedDate;
	private String expectedPlace;
	private String realDate;
	private String realPlace;
	private Long competitionId;
	private Long nextMatchId;
	private int roundNo;
	private String status;
	private String url;

	public MatchDTO(){
	}

	public MatchDTO(Long id, String name, int numOfSet, String expectedDate, String expectedPlace, String realDate, String realPlace, Long competitionId, Long nextMatchId, int roundNo, String status, String url){
		this.id = id;
		this.name = name;
		this.numOfSet = numOfSet;
		this.expectedDate = expectedDate;
		this.expectedPlace = expectedPlace;
		this.realDate = realDate;
		this.realPlace = realPlace;
		this.competitionId = competitionId;
		this.nextMatchId = nextMatchId;
		this.roundNo = roundNo;
		this.status = status;
		this.url = url;
	}


	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getNumOfSet() {
		return numOfSet;
	}
	
	public void setNumOfSet(int numOfSet) {
		this.numOfSet = numOfSet;
	}
	
	public String getExpectedDate() {
		return expectedDate;
	}
	
	public void setExpectedDate(String expectedDate) {
		this.expectedDate = expectedDate;
	}
	
	public String getExpectedPlace() {
		return expectedPlace;
	}
	
	public void setExpectedPlace(String expectedPlace) {
		this.expectedPlace = expectedPlace;
	}
	
	public String getRealDate() {
		return realDate;
	}
	
	public void setRealDate(String realDate) {
		this.realDate = realDate;
	}
	
	public String getRealPlace() {
		return realPlace;
	}
	
	public void setRealPlace(String realPlace) {
		this.realPlace = realPlace;
	}
	
	public Long getCompetitionId() {
		return competitionId;
	}
	
	public void setCompetitionId(Long competitionId) {
		this.competitionId = competitionId;
	}
	
	public Long getNextMatchId() {
		return nextMatchId;
	}
	
	public void setNextMatchId(Long nextMatchId) {
		this.nextMatchId = nextMatchId;
	}
	
	public int getRoundNo() {
		return roundNo;
	}
	
	public void setRoundNo(int roundNo) {
		this.roundNo = roundNo;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	

}