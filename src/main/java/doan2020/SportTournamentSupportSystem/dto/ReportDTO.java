
package doan2020.SportTournamentSupportSystem.dto;

public class ReportDTO{

	private Long id;
	private Long senderId;
	private String type;
	private String subject;
	private String content;
	private String link;
	private String status;
	private String url;

	public ReportDTO(){
	}

	public ReportDTO(Long id, Long senderId, String type, String subject, String content, String link, String status, String url){
		this.id = id;
		this.senderId = senderId;
		this.type = type;
		this.subject = subject;
		this.content = content;
		this.link = link;
		this.status = status;
		this.url = url;
	}


	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getSenderId() {
		return senderId;
	}
	
	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
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
	
	public String getLink() {
		return link;
	}
	
	public void setLink(String link) {
		this.link = link;
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