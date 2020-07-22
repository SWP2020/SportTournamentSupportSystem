package doan2020.SportTournamentSupportSystem.dtIn;

import java.util.Date;

public class EditProfileDtIn {
	private Long userID;
	private String avartar;
	private String background;
	private Date dob;
    private boolean gender;
	private String address;
	private String email;
	
	public EditProfileDtIn() {
		
	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public String getAvartar() {
		return avartar;
	}

	public void setAvartar(String avartar) {
		this.avartar = avartar;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
}
