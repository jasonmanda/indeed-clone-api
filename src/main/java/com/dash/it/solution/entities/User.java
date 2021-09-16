package com.dash.it.solution.entities;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
// import org.springframework.data.annotation.CreatedBy;
// import org.springframework.data.annotation.CreatedDate;
// import org.springframework.data.annotation.LastModifiedBy;
// import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * The type User.
 *
 * @author Jason Mandabrandja
 */
@Entity
@Table(name = "gs_utilisateur")
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_utilisateur;
    
    @Column(nullable = false,unique=true)
    private String email;
    
    @Column(length = 100,nullable = false)
    private String password;
    
    @Column(length = 100,nullable = false,unique = true)
    private String api_token;
    
	@Column(nullable = false,columnDefinition = "TINYINT(1) DEFAULT FALSE")
    private boolean etat_utilisateur;
    
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date created_at;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = true)
    private Date updated_at;
    @OneToOne
	@JoinColumn(name="id_personne",referencedColumnName = "id_personne")
	@Basic(fetch = FetchType.LAZY)
	private Personne personne;
 
	

	public long getId_utilisateur() {
		return id_utilisateur;
	}

	public void setId_utilisateur(long id_utilisateur) {
		this.id_utilisateur = id_utilisateur;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getApi_token() {
		return api_token;
	}

	public void setApi_token(String api_token) {
		this.api_token = api_token;
	}

	public boolean isEtat_utilisateur() {
		return etat_utilisateur;
	}

	public void setEtat_utilisateur(boolean etat_utilisateur) {
		this.etat_utilisateur = etat_utilisateur;
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

	public Personne getPersonne() {
		return personne; 
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

 
 

 

 
    
    
}
