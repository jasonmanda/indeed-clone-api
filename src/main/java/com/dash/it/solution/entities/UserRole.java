package com.dash.it.solution.entities;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
// import org.springframework.data.annotation.CreatedBy;
// import org.springframework.data.annotation.CreatedDate;
// import org.springframework.data.annotation.LastModifiedBy;
// import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
/**
 * The type User.
 *
 * @author Jason Mandabrandja
 */
@Entity
@Table(name = "gs_user_role")
@EntityListeners(AuditingEntityListener.class)
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_user_role;
	@ManyToOne
	@JoinColumn(name = "id_utilisateur",referencedColumnName = "id_utilisateur")
	@Basic(fetch = FetchType.LAZY)
    private User user;
	@ManyToOne
	@JoinColumn(name = "id_role",referencedColumnName = "id_role")
	@Basic(fetch = FetchType.LAZY)
    private Role role;
    @Column(nullable = false,columnDefinition = "TINYINT(1) DEFAULT FALSE")
    private boolean etat_user_role;
    
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date created_at;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = true)
    private Date updated_at;

	public long getId_user_role() {
		return id_user_role;
	}

	public void setId_user_role(long id_user_role) {
		this.id_user_role = id_user_role;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean isEtat_user_role() {
		return etat_user_role;
	}

	public void setEtat_user_role(boolean etat_user_role) {
		this.etat_user_role = etat_user_role;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

    
    
    
}