
package doan2020.SportTournamentSupportSystem.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "sports")
@EntityListeners(AuditingEntityListener.class)
public class SportEntity {

	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String fullName;

	@Column(nullable = false)
	private String shortName;

	@ColumnDefault("'Chưa có mô tả.'")
	private String description;

	@ColumnDefault("'unknown'")
	private String status;

	private String url = "/?";

	@OneToMany(mappedBy = "sport", cascade = CascadeType.ALL)
	private Collection<CompetitionEntity> competitions;

	public Long getId() {
		return id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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

	public Collection<CompetitionEntity> getCompetitions() {
		return competitions;
	}

	public void setCompetitions(Collection<CompetitionEntity> competitions) {
		this.competitions = competitions;
	}

}