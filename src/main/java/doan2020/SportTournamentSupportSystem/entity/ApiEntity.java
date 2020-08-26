
package doan2020.SportTournamentSupportSystem.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "apis")
public class ApiEntity {

	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@ColumnDefault("'Chưa có mô tả.'")
	private String description;

	private String method;

	@ColumnDefault("'unknown'")
	private String status;

	private String url = "/?";

	@ManyToMany(mappedBy = "apisList")
	private Collection<RoleEntity> rolesList;

	public Long getId() {
		return id;
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

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
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

	public Collection<RoleEntity> getRolesList() {
		return rolesList;
	}

	public void setRolesList(Collection<RoleEntity> rolesList) {
		this.rolesList = rolesList;
	}

}