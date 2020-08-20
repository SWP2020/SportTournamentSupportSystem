
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



@Entity
@Table(name = "verification_tokens")
@EntityListeners(AuditingEntityListener.class)
public class VerificationTokenEntity{

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String token;
	
	private Date expiredDateTime;
	
	private Date issuedDateTime;
	
	private Date confirmedDateTime;
	
	private String createdBy;
	
	private Date createdDate;
	
	private String modifiedBy;
	
	private Date modifiedDate;
	
	private String status;
	
	private String url;
	

	@ManyToOne
	@JoinColumn(name = "userId")
	private UserEntity user;
	

public Long getId() {
	return id;
}

public String getToken() {
	return token;
}

public void setToken(String token) {
	this.token = token;
}

public Date getExpiredDateTime() {
	return expiredDateTime;
}

public void setExpiredDateTime(Date expiredDateTime) {
	this.expiredDateTime = expiredDateTime;
}

public Date getIssuedDateTime() {
	return issuedDateTime;
}

public void setIssuedDateTime(Date issuedDateTime) {
	this.issuedDateTime = issuedDateTime;
}

public Date getConfirmedDateTime() {
	return confirmedDateTime;
}

public void setConfirmedDateTime(Date confirmedDateTime) {
	this.confirmedDateTime = confirmedDateTime;
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

public UserEntity getUser() {
	return user;
}

public void setUser(UserEntity user) {
	this.user = user;
}


}