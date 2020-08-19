package doan2020.SportTournamentSupportSystem.model.LogicEntity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import doan2020.SportTournamentSupportSystem.entity.TeamEntity;

public class Team {
	
	Long id;
	String name;
	
	@JsonBackReference
	TeamEntity realTeam;
	

	
	
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
