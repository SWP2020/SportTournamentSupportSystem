
package doan2020.SportTournamentSupportSystem.dto;

public class CommentDTO{

	private Long id;
	private Long authorId;
	private Long postId;
	private String content;
	private String status;
	private String url;

	public CommentDTO(){
	}

	public CommentDTO(Long id, Long authorId, Long postId, String content, String status, String url){
		this.id = id;
		this.authorId = authorId;
		this.postId = postId;
		this.content = content;
		this.status = status;
		this.url = url;
	}


	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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
	

}