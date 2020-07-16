
package doan2020.SportTournamentSupportSystem.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.sun.istack.NotNull;



@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class UserEntity{

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String username;
	
	@NotNull
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	private String address;
	
	private boolean gender;
	
	private Date dob;
	
	private String email;
	
	private String avatar;
	
	private String background;
	
	private boolean active;
	
	private String createdBy;
	
	private Date createdDate;
	
	private String modifiedBy;
	
	private Date modifiedDate;
	

	@ManyToOne
	@JoinColumn(name = "roleId")
	private RoleEntity role;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
		name = "user_tournament",
		joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "tournament_id")
	)
	private Collection<TournamentEntity> tournamentsList;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
		name = "user_post",
		joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "post_id")
	)
	private Collection<PostEntity> postsList;
	
	@OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
	private Collection<ReportEntity> reports;

	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
	private Collection<CommentEntity> comments;

	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
	private Collection<PostEntity> posts;

	@OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
	private Collection<TeamEntity> teams;

	@OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
	private Collection<TournamentEntity> tournaments;


	public Long getId() {
		return id;
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
		return firstName;
	}
	
	public void setFirstname(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastname() {
		return lastName;
	}
	
	public void setLastname(String lastName) {
		this.lastName = lastName;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public boolean getGender() {
		return gender;
	}
	
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	
	public Date getDob() {
		return dob;
	}
	
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getAvatar() {
		return avatar;
	}
	
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	public String getBackground() {
		return background;
	}
	
	public void setBackground(String background) {
		this.background = background;
	}
	
	public boolean getActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public String getCreatedby() {
		return createdBy;
	}
	
	public void setCreatedby(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public Date getCreateddate() {
		return createdDate;
	}
	
	public void setCreateddate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	public String getModifiedby() {
		return modifiedBy;
	}
	
	public void setModifiedby(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	public Date getModifieddate() {
		return modifiedDate;
	}
	
	public void setModifieddate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	public RoleEntity getRole() {
		return role;
	}
	
	public void setRole(RoleEntity role) {
		this.role = role;
	}
	
	public Collection<TournamentEntity> getTournamentslist() {
		return tournamentsList;
	}
	
	public void setTournamentslist(Collection<TournamentEntity> tournamentsList) {
		this.tournamentsList = tournamentsList;
	}
	
	public Collection<PostEntity> getPostslist() {
		return postsList;
	}
	
	public void setPostslist(Collection<PostEntity> postsList) {
		this.postsList = postsList;
	}
	
	public Collection<ReportEntity> getReports() {
		return reports;
	}
	
	public void setReports(Collection<ReportEntity> reports) {
		this.reports = reports;
	}
	
	public Collection<CommentEntity> getComments() {
		return comments;
	}
	
	public void setComments(Collection<CommentEntity> comments) {
		this.comments = comments;
	}
	
	public Collection<PostEntity> getPosts() {
		return posts;
	}
	
	public void setPosts(Collection<PostEntity> posts) {
		this.posts = posts;
	}
	
	public Collection<TeamEntity> getTeams() {
		return teams;
	}
	
	public void setTeams(Collection<TeamEntity> teams) {
		this.teams = teams;
	}
	
	public Collection<TournamentEntity> getTournaments() {
		return tournaments;
	}
	
	public void setTournaments(Collection<TournamentEntity> tournaments) {
		this.tournaments = tournaments;
	}
	

}