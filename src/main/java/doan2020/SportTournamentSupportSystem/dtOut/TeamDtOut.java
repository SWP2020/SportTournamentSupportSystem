package doan2020.SportTournamentSupportSystem.dtOut;

import java.util.Date;

import doan2020.SportTournamentSupportSystem.entity.UserEntity;

public class TeamDtOut {

	private Long id;

	private String fullName;

	private String password;

	private String shortName;
	
	private String description;

	private Long creatorId;
	
	private String status;

	public TeamDtOut() {
		
	}

	public TeamDtOut(Long id, String fullName, String password, String shortName, String description, Long creatorId,
			String status) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.password = password;
		this.shortName = shortName;
		this.description = description;
		this.creatorId = creatorId;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}