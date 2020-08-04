package doan2020.SportTournamentSupportSystem.dtIn;

public class CommentDtIn {
    private Long id;
	
	private String content;
	
	private String status;
	
	private String url;
	
	private Long authorId;
	
	private Long postId;
	
	public CommentDtIn() {
		
	}

	public CommentDtIn(Long id, String content, String status, String url, Long authorId, Long postId) {
		super();
		this.id = id;
		this.content = content;
		this.status = status;
		this.url = url;
		this.authorId = authorId;
		this.postId = postId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}
	
	
}
