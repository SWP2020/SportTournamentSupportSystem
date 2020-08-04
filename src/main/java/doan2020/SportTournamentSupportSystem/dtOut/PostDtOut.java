package doan2020.SportTournamentSupportSystem.dtOut;

public class PostDtOut {

	private Long id;

	private String title;

	private boolean systemPost;

	private String content;

	private String status;

	private String url;

	private Long tournamentId;

	private Long authorId;

	public PostDtOut() {

	}

	

	public PostDtOut(Long id, String title, boolean systemPost, String content, String status, String url,
			Long tournamentId, Long authorId) {
		super();
		this.id = id;
		this.title = title;
		this.systemPost = systemPost;
		this.content = content;
		this.status = status;
		this.url = url;
		this.tournamentId = tournamentId;
		this.authorId = authorId;
	}

    
	public boolean isSystemPost() {
		return systemPost;
	}


	public void setSystemPost(boolean systemPost) {
		this.systemPost = systemPost;
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