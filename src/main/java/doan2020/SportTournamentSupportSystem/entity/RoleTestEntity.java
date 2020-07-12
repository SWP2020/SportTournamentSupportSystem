package doan2020.SportTournamentSupportSystem.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "role")
@EntityListeners(AuditingEntityListener.class)
public class RoleTestEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long roleid;

	@Column
	private String rolename;

	@Column
	private String description;

	@OneToMany(mappedBy = "role")
	private List<UserTestEntity> users = new ArrayList<>();

	public RoleTestEntity() {
	}
	

	public RoleTestEntity(Long roleid, String rolename, String description) {
		super();
		this.roleid = roleid;
		this.rolename = rolename;
		this.description = description;
	}


	public Long getRoleID() {
		return roleid;
	}

	public void setRoleID(Long roleID) {
		this.roleid = roleID;
	}

	public List<UserTestEntity> getUsers() {
		return users;
	}

	public void setUsers(List<UserTestEntity> users) {
		this.users = users;
	}

	public String getRoleName() {
		return rolename;
	}

	public void setRoleName(String roleName) {
		this.rolename = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
