package doan2020.SportTournamentSupportSystem.model.Schedule.DTO;

import java.io.Serializable;
import java.util.HashMap;

import doan2020.SportTournamentSupportSystem.model.Schedule.RevertMapping;

public class ScheduleDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private FinalStageScheduleDTO finalStageSchedule;
	private GroupStageScheduleDTO groupStageSchedule;
	private boolean hasGroupStage;
	private int totalTeam;
	private HashMap<Long, RevertMapping> mapping;
	
	
	public FinalStageScheduleDTO getFinalStageSchedule() {
		return finalStageSchedule;
	}
	public void setFinalStageSchedule(FinalStageScheduleDTO finalStageSchedule) {
		this.finalStageSchedule = finalStageSchedule;
	}
	public GroupStageScheduleDTO getGroupStageSchedule() {
		return groupStageSchedule;
	}
	public void setGroupStageSchedule(GroupStageScheduleDTO groupStageSchedule) {
		this.groupStageSchedule = groupStageSchedule;
	}
	public boolean isHasGroupStage() {
		return hasGroupStage;
	}
	public void setHasGroupStage(boolean hasGroupStage) {
		this.hasGroupStage = hasGroupStage;
	}
	public int getTotalTeam() {
		return totalTeam;
	}
	public void setTotalTeam(int totalTeam) {
		this.totalTeam = totalTeam;
	}
	public HashMap<Long, RevertMapping> getMapping() {
		return mapping;
	}
	public void setMapping(HashMap<Long, RevertMapping> mapping) {
		this.mapping = mapping;
	}
	

	
}
