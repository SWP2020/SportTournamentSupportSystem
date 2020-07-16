
package doan2020.SportTournamentSupportSystem.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.sun.istack.NotNull;



@Entity
@Table(name = "posts")
@EntityListeners(AuditingEntityListener.class)
public class PostEntity{

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String title;
	
	private String content;
	
	private String createdBy;
	
	private Date createdDate;
	
	private String modifiedBy;
	
	private Date modifiedDate;
	

	@ManyToOne
	@JoinColumn(name = "tournamentId")
	private TournamentEntity tournament;
	
	@ManyToOne
	@JoinColumn(name = "authorId")
	private UserEntity author;
	
	@ManyToMany(mappedBy = "postsList")
	private Collection<UserEntity> users;
	
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private Collection<CommentEntity> comments;


	public Long getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
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
	
	public TournamentEntity getTournament() {
		return tournament;
	}
	
	public void setTournament(TournamentEntity tournament) {
		this.tournament = tournament;
	}
	
	public UserEntity getAuthor() {
		return author;
	}
	
	public void setAuthor(UserEntity author) {
		this.author = author;
	}
	
	public Collection<UserEntity> getUserslist() {
		return users;
	}
	
	public void setUserslist(Collection<UserEntity> usersList) {
		this.users = usersList;
	}
	
	public Collection<CommentEntity> getComments() {
		return comments;
	}
	
	public void setComments(Collection<CommentEntity> comments) {
		this.comments = comments;
	}
	

}