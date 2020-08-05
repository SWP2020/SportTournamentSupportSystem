
package doan2020.SportTournamentSupportSystem.dto;

public class ResultDTO{

	private Long id;
	private Long matchId;
	private Long teamId;
	private int setNo;
	private float score;
	private int rank;
	private String status;
	private String url;

	public ResultDTO(){
	}

	public ResultDTO(Long id, Long matchId, Long teamId, int setNo, float score, int rank, String status, String url){
		this.id = id;
		this.matchId = matchId;
		this.teamId = teamId;
		this.setNo = setNo;
		this.score = score;
		this.rank = rank;
		this.status = status;
		this.url = url;
	}


	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getMatchId() {
		return matchId;
	}
	
	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}
	
	public Long getTeamId() {
		return teamId;
	}
	
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
	
	public int getSetNo() {
		return setNo;
	}
	
	public void setSetNo(int setNo) {
		this.setNo = setNo;
	}
	
	public float getScore() {
		return score;
	}
	
	public void setScore(float score) {
		this.score = score;
	}
	
	public int getRank() {
		return rank;
	}
	
	public void setRank(int rank) {
		this.rank = rank;
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