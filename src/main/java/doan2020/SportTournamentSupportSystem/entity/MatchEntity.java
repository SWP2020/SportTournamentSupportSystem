
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
@Table(name = "matches")
@EntityListeners(AuditingEntityListener.class)
public class MatchEntity{

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private int numOfSet;
	
	private Date expectedDate;
	
	private String expectedPlace;
	
	private Date realDate;
	
	private String realPlace;
	
	private String createdBy;
	
	private Date createdDate;
	
	private String modifiedBy;
	
	private Date modifiedDate;
	

	@ManyToOne
	@JoinColumn(name = "competitionId")
	private CompetitionEntity competition;
	
	@ManyToMany(mappedBy = "matchesList")
	private Collection<TeamEntity> teams;
	
	@OneToMany(mappedBy = "match", cascade = CascadeType.ALL)
	private Collection<ResultEntity> results;



}