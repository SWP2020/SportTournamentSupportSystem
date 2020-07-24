package doan2020.SportTournamentSupportSystem.dtOut;

import java.util.Date;

public abstract class ViewUserInforDtOut {
	private Long userID;

	private String username;

	private String firstname;

	private String lastname;
	
	private Date dob;
	
	private boolean gender;
	
	private String email;
	
	private String avartar;
	
	private boolean background;
	
	private Date createdate;
	
	public ViewUserInforDtOut() {
		
	}

	public ViewUserInforDtOut(Long userID, String username, String firstname, String lastname, Date dob, boolean gender,
			String email, String avartar, boolean background, Date createdate) {
		super();
		this.userID = userID;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.dob = dob;
		this.gender = gender;
		this.email = email;
		this.avartar = avartar;
		this.background = background;
		this.createdate = createdate;
	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAvartar() {
		return avartar;
	}

	public void setAvartar(String avartar) {
		this.avartar = avartar;
	}

	public boolean isBackground() {
		return background;
	}

	public void setBackground(boolean background) {
		this.background = background;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	
	
	
}
