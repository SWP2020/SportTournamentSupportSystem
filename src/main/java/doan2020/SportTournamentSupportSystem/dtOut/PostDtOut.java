package doan2020.SportTournamentSupportSystem.dtOut;

import java.util.Date;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.sun.istack.NotNull;

import doan2020.SportTournamentSupportSystem.entity.CompetitionEntity;
import doan2020.SportTournamentSupportSystem.entity.TournamentEntity;
import doan2020.SportTournamentSupportSystem.entity.UserEntity;

public class PostDtOut {

	private Long id;
	
	private String title;
	
	private String content;

	private Long tournamentId;

	private Long authorId;

	public PostDtOut() {
		
	}

	public PostDtOut(Long id, String title, String content, Long tournamentId, Long authorId) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.tournamentId = tournamentId;
		this.authorId = authorId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getTournamentId() {
		return tournamentId;
	}

	public void setTournamentId(Long tournamentId) {
		this.tournamentId = tournamentId;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	
}