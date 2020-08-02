
package doan2020.SportTournamentSupportSystem.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.EntityListeners;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.util.Date;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.sun.istack.NotNull;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import java.util.Collection;



@Entity
@Table(name = "competition_settings")
@EntityListeners(AuditingEntityListener.class)
public class CompetitionSettingEntity{

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int maxNumberOfTeam;
	
	private int maxMemberPerTeam;
	
	private int numberOfTable;
	
	private int numberOfTeamPassPerTable;
	
	private boolean homeGame;
	
	private String createdBy;
	
	private Date createdDate;
	
	private String modifiedBy;
	
	private Date modifiedDate;
	
	private String status;
	
	private String url;
	

	@ManyToOne
	@JoinColumn(name = "competitionId")
	private CompetitionEntity competition;
	
	@OneToMany(mappedBy = "competitionSetting", cascade = CascadeType.ALL)
	private Collection<RegisterForEntity> register_form;


	public Long getId() {
		return id;
	}
	
	public int getMaxNumberOfTeam() {
		return maxNumberOfTeam;
	}
	
	public void setMaxNumberOfTeam(int maxNumberOfTeam) {
		this.maxNumberOfTeam = maxNumberOfTeam;
	}
	
	public int getMaxMemberPerTeam() {
		return maxMemberPerTeam;
	}
	
	public void setMaxMemberPerTeam(int maxMemberPerTeam) {
		this.maxMemberPerTeam = maxMemberPerTeam;
	}
	
	public int getNumberOfTable() {
		return numberOfTable;
	}
	
	public void setNumberOfTable(int numberOfTable) {
		this.numberOfTable = numberOfTable;
	}
	
	public int getNumberOfTeamPassPerTable() {
		return numberOfTeamPassPerTable;
	}
	
	public void setNumberOfTeamPassPerTable(int numberOfTeamPassPerTable) {
		this.numberOfTeamPassPerTable = numberOfTeamPassPerTable;
	}
	
	public boolean getHomeGame() {
		return homeGame;
	}
	
	public void setHomeGame(boolean homeGame) {
		this.homeGame = homeGame;
	}
	
	public String getCreatedBy() {
		return createdBy;
	}
	
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}
	
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	public String getModifiedBy() {
		return modifiedBy;
	}
	
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	public Date getModifiedDate() {
		return modifiedDate;
	}
	
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
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
	
	public CompetitionEntity getCompetition() {
		return competition;
	}
	
	public void setCompetition(CompetitionEntity competition) {
		this.competition = competition;
	}
	
	public Collection<RegisterForEntity> getRegisterForm() {
		return register_form;
	}
	
	public void setRegisterForm(Collection<RegisterForEntity> register_form) {
		this.register_form = register_form;
	}
	

}