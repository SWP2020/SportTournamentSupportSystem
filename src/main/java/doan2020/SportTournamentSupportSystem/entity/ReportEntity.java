
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

import doan2020.SportTournamentSupportSystem.config.Const;

@Entity
@Table(name = "reports")
@EntityListeners(AuditingEntityListener.class)
public class ReportEntity {

	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ColumnDefault("'Không có chủ đề'")
	private String subject;

	@ColumnDefault("'Không có nội dung'")
	private String content;

	private String status;

	private String url;

	@Column(nullable = false)
	private String type = Const.REPORT_SYSTEM_ERROR;

	@ManyToOne
	@JoinColumn(name = "senderId")
	private UserEntity sender;

	@ManyToOne
	@JoinColumn(name = "tournamentId")
	private TournamentEntity tournament;

	public Long getId() {
		return id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public UserEntity getSender() {
		return sender;
	}

	public void setSender(UserEntity sender) {
		this.sender = sender;
	}

	public TournamentEntity getTournament() {
		return tournament;
	}

	public void setTournament(TournamentEntity tournament) {
		this.tournament = tournament;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}