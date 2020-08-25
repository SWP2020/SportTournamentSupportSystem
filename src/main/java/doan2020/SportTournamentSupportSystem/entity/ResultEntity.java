
package doan2020.SportTournamentSupportSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "results")
@EntityListeners(AuditingEntityListener.class)
public class ResultEntity {

	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@ColumnDefault("1")
	private int setNo;

	@ColumnDefault("0")
	private int team1Score;

	@ColumnDefault("0")
	private int team2Score;

	private String status;

	private String url;

	@ManyToOne
	@JoinColumn(name = "matchId", nullable = false)
	private MatchEntity match;

	public Long getId() {
		return id;
	}

	public int getSetNo() {
		return setNo;
	}

	public void setSetNo(int setNo) {
		this.setNo = setNo;
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

	public MatchEntity getMatch() {
		return match;
	}

	public void setMatch(MatchEntity match) {
		this.match = match;
	}

	public int getTeam1Score() {
		return team1Score;
	}

	public void setTeam1Score(int team1Score) {
		this.team1Score = team1Score;
	}

	public int getTeam2Score() {
		return team2Score;
	}

	public void setTeam2Score(int team2Score) {
		this.team2Score = team2Score;
	}

}