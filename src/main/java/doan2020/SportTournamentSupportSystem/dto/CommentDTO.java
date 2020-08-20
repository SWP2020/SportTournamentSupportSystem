
package doan2020.SportTournamentSupportSystem.dto;

public class CommentDTO{

	private Long id;
	private Long creatorId;
	private Long postId;
	private String content;
	private String status;
	private String url;

	public CommentDTO(){
	}

	public CommentDTO(Long id, Long creatorId, Long postId, String content, String status, String url){
		this.id = id;
		this.creatorId = creatorId;
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
	
	public Long getCreatorId() {
		return creatorId;
	}
	
	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
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