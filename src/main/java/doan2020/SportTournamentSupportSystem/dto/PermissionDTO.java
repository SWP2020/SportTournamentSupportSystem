
package doan2020.SportTournamentSupportSystem.dto;

public class PermissionDTO{

	private Long id;
	private String name;
	private String description;
	private boolean can_edit;
	private boolean can_delete;
	private String status;
	private String url;

	public PermissionDTO(){
	}

	public PermissionDTO(Long id, String name, String description, boolean can_edit, boolean can_delete, String status, String url){
		this.id = id;
		this.name = name;
		this.description = description;
		this.can_edit = can_edit;
		this.can_delete = can_delete;
		this.status = status;
		this.url = url;
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
	
	public boolean getCanEdit() {
		return can_edit;
	}
	
	public void setCanEdit(boolean can_edit) {
		this.can_edit = can_edit;
	}
	
	public boolean getCanDelete() {
		return can_delete;
	}
	
	public void setCanDelete(boolean can_delete) {
		this.can_delete = can_delete;
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