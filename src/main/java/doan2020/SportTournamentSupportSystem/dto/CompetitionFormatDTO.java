
package doan2020.SportTournamentSupportSystem.dto;

public class CompetitionFormatDTO{

	private Long id;
	private String name;
	private String description;
	private String status;
	private String url;
	private boolean hasHomeMatch;

	public CompetitionFormatDTO(){
	}

	public CompetitionFormatDTO(Long id, String name, String description, String status, String url, boolean hasHomeMatch){
		this.id = id;
		this.name = name;
		this.description = description;
		this.status = status;
		this.url = url;
		this.hasHomeMatch = hasHomeMatch;
	}


	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
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

	public boolean isHasHomeMatch() {
		return hasHomeMatch;
	}

	public void setHasHomeMatch(boolean hasHomeMatch) {
		this.hasHomeMatch = hasHomeMatch;
	}
	

}