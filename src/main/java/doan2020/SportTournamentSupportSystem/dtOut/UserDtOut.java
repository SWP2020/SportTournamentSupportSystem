package doan2020.SportTournamentSupportSystem.dtOut;

public class UserDtOut {

	private Long userID;

	private String username;

	private String password;

	private String firstname;

	private String lastname;
	
	private String dob;
	
	private boolean gender;
	
	private String email;
	
	private String imageprofile;
	
	private String status;
	
	private String createdate;
	
	private String address;
	
	private int age;

	public UserDtOut() {
		
	}
	public UserDtOut(Long userID, String username, String password, String firstname, String lastname, String dob,
			boolean gender, String email, String imageprofile, String status, String createdate, int age, String address) {
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
		this.status = status;
		this.createdate = createdate;
		this.age = age;
		this.address = address;
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

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
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

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}
