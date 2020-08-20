
package doan2020.SportTournamentSupportSystem.dto;

public class ReportDTO{

	private Long id;
	private Long senderId;
	private String subject;
	private String content;
	private Long tournamentId;
	private String status;
	private String url;

	public ReportDTO(){
	}

	public ReportDTO(Long id, Long senderId, String subject, String content, Long tournamentId, String status, String url){
		this.id = id;
		this.senderId = senderId;
		this.subject = subject;
		this.content = content;
		this.tournamentId = tournamentId;
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
	
	public Long getTournamentId() {
		return tournamentId;
	}
	
	public void setTournamentId(Long tournamentId) {
		this.tournamentId = tournamentId;
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