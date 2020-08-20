package doan2020.SportTournamentSupportSystem.model.LogicEntity;

import java.io.Serializable;

public class Team implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	Long id;
	String name;
	
	
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
	
	
	
}
