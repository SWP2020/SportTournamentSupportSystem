package doan2020.SportTournamentSupportSystem.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
public class UserTestEntity implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userID;

	@Column
	private String username;

	@Column
	private String password;

	@Column
	private String firstname;

	@Column
	private String lastname;
	
	@Column
	private Date dob;
	
	@Column
	private boolean gender;
	
	@Column
	private String email;
	
	@Column
	private String imageprofile;
	
	@Column
	private boolean active;
	
	@Column(name = "createdate")
	@CreatedDate
	private Date createdate;
	

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roleid")
    private RoleTestEntity role;

    
	public Long getUserID() {
		return userID;
	}


	public String getUserName() {
		return username;
	}


	public void setUserName(String userName) {
		this.username = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getFirstName() {
		return firstname;
	}


	public void setFirstName(String firstName) {
		this.firstname = firstName;
	}


	public String getLastName() {
		return lastname;
	}


	public void setLastName(String lastName) {
		this.lastname = lastName;
	}


	public Date getDOB() {
		return dob;
	}


	public void setDOB(Date dOB) {
		dob = dOB;
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


	public String getImageProfile() {
		return imageprofile;
	}


	public void setImageProfile(String imageProfile) {
		this.imageprofile = imageProfile;
	}


	public boolean getActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	public Date getCreateDate() {
		return createdate;
	}


	public void setCreateDate(Date createDate) {
		this.createdate = createDate;
	}


	public RoleTestEntity getRole() {
		return role;
	}


	public void setRole(RoleTestEntity role) {
		this.role = role;
	}
	
//	@Transient
//	  public List<GrantedAuthority> getAuthorities() {
//	    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
////	    for (RoleEntity usersRoles: this.usersRoleses) {
////	    authorities.add(new SimpleGrantedAuthority(this.role.getRoleName()));
//	    authorities.add(new SimpleGrantedAuthority("ROLE_MANAGER"));
//	    authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
////	    }
//	    return authorities;
//	  }
}
