
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



@Entity
@Table(name = "permissions")
@EntityListeners(AuditingEntityListener.class)
public class PermissionEntity{

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String name;
	
	private String description;
	
	private boolean can_edit;
	
	private boolean can_delete;
	
	private String createdBy;
	
	private Date createdDate;
	
	private String modifiedBy;
	
	private Date modifiedDate;
	
	private String status;
	
	private String url;
	


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


}