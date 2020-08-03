package doan2020.SportTournamentSupportSystem.dtOut;

public class PlayerDtOut {
private Long playerId;
	
	private Long teamId;
	
    private String firstName;
	
	private String lastName;
	
	private boolean gender;
	
	private String dob;
	
	private String email;
	
	private String status;
	
	private String url;
	
	public PlayerDtOut() {
		
	}

	public PlayerDtOut(Long playerId, Long teamId, String firstName, String lastName, boolean gender, String dob,
			String email, String status, String url) {
		super();
		this.playerId = playerId;
		this.teamId = teamId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.dob = dob;
		this.email = email;
		this.status = status;
		this.url = url;
	}

	public Long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
