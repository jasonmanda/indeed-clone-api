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
 * The type Entreprise.
 *
 * @author Jason Mandabrandja
 */
@Entity
@Table(name = "gs_entreprise")
@EntityListeners(AuditingEntityListener.class)
public class Entreprise
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_entreprise;
    @Column(nullable = false,unique = true)
    private String nom;
    @Column(nullable=true)
    private long capital;
    @OneToOne
    @JoinColumn(name = "id_personne",nullable = true,referencedColumnName = "id_personne")
    @Basic(fetch = FetchType.LAZY) 
    private Personne personne; 
    @OneToOne
    @JoinColumn(name = "id_contact",nullable = true,referencedColumnName = "id_contact")
    @Basic(fetch = FetchType.LAZY) 
    private Contact contact; 
  
    @Column(nullable=true,columnDefinition = "TEXT")
    private String description;
    @Column(nullable=true,columnDefinition = "TINYINT(1) DEFAULT FALSE")
    private boolean cab_recrutement;
      //[50-149],[150-249],[250-499],[500-749],[750-999],[1000+]
    @Column(nullable = true)
    private String taille;
    @Column(nullable = true)
    private String domaine;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date created_at;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = true)
    private Date updated_at;

    @Column(nullable = false,columnDefinition = "TINYINT(1) DEFAULT FALSE")
    private boolean etat_entreprise;
    
    @ManyToOne 
    @JoinColumn(name = "id_ville",nullable = true,referencedColumnName = "id_ville")
    @Basic(fetch = FetchType.LAZY)
    private Ville ville; 
 

    public long getId_entreprise() {
        return id_entreprise;
    }

    public void setId_entreprise(long id_entreprise) {
        this.id_entreprise = id_entreprise;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

  
 

    public long getCapital() {
        return capital;
    }

    public void setCapital(long capital) {
        this.capital = capital;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
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

    public boolean isEtat_entreprise() {
        return etat_entreprise;
    }

    public void setEtat_entreprise(boolean etat_entreprise) {
        this.etat_entreprise = etat_entreprise;
    }

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

 

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public boolean isCab_recrutement() {
        return cab_recrutement;
    }

    public void setCab_recrutement(boolean cab_recrutement) {
        this.cab_recrutement = cab_recrutement;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
 
    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    
}