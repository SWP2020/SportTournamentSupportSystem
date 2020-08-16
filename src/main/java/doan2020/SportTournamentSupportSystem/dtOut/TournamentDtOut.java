package doan2020.SportTournamentSupportSystem.dtOut;

public class TournamentDtOut {

    private Long id;
	
	private String fullName;
	
	private String shortName;
	
	private String description;
	
	private String openingLocation;
	
	private String openingTime;
	
	private String closingLocation;
	
	private String closingTime;
	
	private String donor;
	
	private String status;
	
	private int process;
	
	private String url;
	
    private Long creatorId;
    
    private int countOfPlayer;
    
    private String listSport;

	public TournamentDtOut() {
		
	}

	public TournamentDtOut(Long id, String fullName, String shortName, String description,
			Long creatorId, String openingLocation, String openingTime, String closingLocation, String closingTime,
			String donor, String status, String url, int process) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.shortName = shortName;
		this.description = description;
		this.creatorId = creatorId;
		this.openingLocation = openingLocation;
		this.openingTime = openingTime;
		this.closingLocation = closingLocation;
		this.closingTime = closingTime;
		this.donor = donor;
		this.status = status;
		this.url = url;
		this.process = process;
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public String getOpeningLocation() {
		return openingLocation;
	}

	public void setOpeningLocation(String openingLocation) {
		this.openingLocation = openingLocation;
	}

	public String getOpeningTime() {
		return openingTime;
	}

	public void setOpeningTime(String string) {
		this.openingTime = string;
	}

	public String getClosingLocation() {
		return closingLocation;
	}

	public void setClosingLocation(String closingLocation) {
		this.closingLocation = closingLocation;
	}

	public String getClosingTime() {
		return closingTime;
	}

	public void setClosingTime(String date) {
		this.closingTime = date;
	}

	public String getDonor() {
		return donor;
	}

	public void setDonor(String donor) {
		this.donor = donor;
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

	public int getProcess() {
		return process;
	}

	public void setProcess(int process) {
		this.process = process;
	}

	public int getCountOfPlayer() {
		return countOfPlayer;
	}

	public void setCountOfPlayer(int countOfPlayer) {
		this.countOfPlayer = countOfPlayer;
	}

	public String getListSport() {
		return listSport;
	}

	public void setListSport(String listSport) {
		this.listSport = listSport;
	}
	
	
	
	

}