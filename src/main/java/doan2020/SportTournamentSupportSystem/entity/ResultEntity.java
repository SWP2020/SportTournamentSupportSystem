
package doan2020.SportTournamentSupportSystem.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.sun.istack.NotNull;

@Entity
@Table(name = "results")
@EntityListeners(AuditingEntityListener.class)
public class ResultEntity{

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@NotNull
//	private Long matchId;
//	
//	@NotNull
//	private Long teamId;
	
	private int setNumber;
	
	private float score;
	
	private boolean isWinner;
	
	private String createdBy;
	
	private Date createdDate;
	
	private String modifiedBy;
	
	private Date modifiedDate;
	

	@ManyToOne
	@JoinColumn(name = "matchId")
	private MatchEntity match;
	
	@ManyToOne
	@JoinColumn(name = "teamId")
	private TeamEntity team;
	


}