package doan2020.SportTournamentSupportSystem.dtOut;

import java.util.Date;

public class UserDtOut {

	private Long userID;

	private String username;

	private String password;

	private String firstname;

	private String lastname;
	
	private Date dob;
	
	private boolean gender;
	
	private String email;
	
	private String imageprofile;
	
	private boolean active;
	
	private Date createdate;

	public UserDtOut() {
		
	}
	public UserDtOut(Long userID, String username, String password, String firstname, String lastname, Date dob,
			boolean gender, String email, String imageprofile, boolean active, Date createdate) {
		super();
		this.userID = userID;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.dob = dob;
		this.gender = gender;
		this.email = email;
		this.imageprofile = imageprofile;
		this.active = active;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getImageprofile() {
		return imageprofile;
	}

	public void setImageprofile(String imageprofile) {
		this.imageprofile = imageprofile;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	
	
}
