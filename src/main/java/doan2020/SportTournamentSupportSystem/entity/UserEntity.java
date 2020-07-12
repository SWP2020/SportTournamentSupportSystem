
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
	private RoleEntity roleTemp;
	
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



}