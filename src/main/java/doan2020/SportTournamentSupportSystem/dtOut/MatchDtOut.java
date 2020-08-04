package doan2020.SportTournamentSupportSystem.dtOut;

public class MatchDtOut {

	private Long id;

	private String name;

	private int numOfSet;

	private String expectedDate;

	private String expectedPlace;

	private String realDate;

	private String realPlace;

	private int roundNo;

	private String status;

	private String url;

	private Long competitionId;
	
	private Long authorId;

	public MatchDtOut() {

	}

	public MatchDtOut(Long id, String name, int numOfSet, String expectedDate, String expectedPlace, String realDate,
			String realPlace, int roundNo, String status, String url, Long competitionId, Long authorId) {
		super();
		this.id = id;
		this.name = name;
		this.numOfSet = numOfSet;
		this.expectedDate = expectedDate;
		this.expectedPlace = expectedPlace;
		this.realDate = realDate;
		this.realPlace = realPlace;
		this.roundNo = roundNo;
		this.status = status;
		this.url = url;
		this.competitionId = competitionId;
		this.authorId = authorId;
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

	public Long getCompetitionId() {
		return competitionId;
	}

	public void setCompetitionId(Long competitionId) {
		this.competitionId = competitionId;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}
	
	

	

}