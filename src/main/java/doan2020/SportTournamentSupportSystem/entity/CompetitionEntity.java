
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
@Table(name = "competitions")
@EntityListeners(AuditingEntityListener.class)
public class CompetitionEntity{

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String name;
	
	private String createdBy;
	
	private Date createdDate;
	
	private String modifiedBy;
	
	private Date modifiedDate;
	

	@ManyToOne
	@JoinColumn(name = "tournamentId")
	private TournamentEntity tournament;
	
	@ManyToOne
	@JoinColumn(name = "sportId")
	private SportEntity sport;
	
	@ManyToOne
	@JoinColumn(name = "scoringunitId")
	private ScoringunitEntity scoringunit;
	
	@ManyToMany(mappedBy = "competitionsList")
	private Collection<TeamEntity> teams;
	
	@OneToMany(mappedBy = "competition", cascade = CascadeType.ALL)
	private Collection<MatchEntity> matches;



}