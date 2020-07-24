
package doan2020.SportTournamentSupportSystem.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.EntityListeners;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.util.Date;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.sun.istack.NotNull;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import java.util.Collection;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;



@Entity
@Table(name = "tournaments")
@EntityListeners(AuditingEntityListener.class)
public class TournamentEntity{

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String fullName;
	
	private String shortName;
	
	private String description;
	
	private String openingLocation;
	
	private Date openingTime;
	
	private String closingLocation;
	
	private Date closingTime;
	
	private String donor;
	
	private String status;
	
	private String url;
	
	private String createdBy;
	
	private Date createdDate;
	
	private String modifiedBy;
	
	private Date modifiedDate;
	

	@ManyToOne
	@JoinColumn(name = "creatorId")
	private UserEntity creator;
	
	@ManyToMany(mappedBy = "tournamentsList")
	private Collection<UserEntity> usersList;
	
	@OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL)
	private Collection<CompetitionEntity> competitions;

	@OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL)
	private Collection<PostEntity> posts;


	public Long getId() {
		return id;
	}
	
	public String getFullname() {
		return fullName;
	}
	
	public void setFullname(String fullName) {
		this.fullName = fullName;
	}
	
	public String getShortname() {
		return shortName;
	}
	
	public void setShortname(String shortName) {
		this.shortName = shortName;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getOpeninglocation() {
		return openingLocation;
	}
	
	public void setOpeninglocation(String openingLocation) {
		this.openingLocation = openingLocation;
	}
	
	public Date getOpeningtime() {
		return openingTime;
	}
	
	public void setOpeningtime(Date openingTime) {
		this.openingTime = openingTime;
	}
	
	public String getClosinglocation() {
		return closingLocation;
	}
	
	public void setClosinglocation(String closingLocation) {
		this.closingLocation = closingLocation;
	}
	
	public Date getClosingtime() {
		return closingTime;
	}
	
	public void setClosingtime(Date closingTime) {
		this.closingTime = closingTime;
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
	
	public UserEntity getCreator() {
		return creator;
	}
	
	public void setCreator(UserEntity creator) {
		this.creator = creator;
	}
	
	public Collection<UserEntity> getUserslist() {
		return usersList;
	}
	
	public void setUserslist(Collection<UserEntity> usersList) {
		this.usersList = usersList;
	}
	
	public Collection<CompetitionEntity> getCompetitions() {
		return competitions;
	}
	
	public void setCompetitions(Collection<CompetitionEntity> competitions) {
		this.competitions = competitions;
	}
	
	public Collection<PostEntity> getPosts() {
		return posts;
	}
	
	public void setPosts(Collection<PostEntity> posts) {
		this.posts = posts;
	}
	

}