package com.dash.it.solution.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * The type ExpPro.
 *
 * @author Jason Mandabrandja
 */
@Entity
@Table(name = "gs_competence")
@EntityListeners(AuditingEntityListener.class)
public class Competence{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_competence;
    @Column(nullable = false)
    private String libelle;
    @Column
    private long  annee;
    @Column
    private long  mois;
    @Column
    private long  semaine;
    @Column
    private long  jour;
    @Column
    private long  echelle;
    ///*Basz */
 
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date created_at;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = true)
    private Date updated_at;
    @ManyToOne
    @JoinColumn(name = "cv_uid",referencedColumnName = "cv_uid")
    @Basic(fetch = FetchType.LAZY)
    private Cv cv; 
    ///*Basz */
    @Column(columnDefinition = "TINYINT(1) DEFAULT FALSE")
    private boolean etat_competence;
  
        
 
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
 
 

   

    public Cv getCv() {
        return cv;
    }

    public void setCv(Cv cv) {
        this.cv = cv;
    }

    public long getId_competence() {
        return id_competence;
    }

    public void setId_competence(long id_competence) {
        this.id_competence = id_competence;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public long getAnnee() {
        return annee;
    }

    public void setAnnee(long annee) {
        this.annee = annee;
    }

    public long getMois() {
        return mois;
    }

    public void setMois(long mois) {
        this.mois = mois;
    }

    public long getSemaine() {
        return semaine;
    }

    public void setSemaine(long semaine) {
        this.semaine = semaine;
    }

    public long getJour() {
        return jour;
    }

    public void setJour(long jour) {
        this.jour = jour;
    }

    public long getEchelle() {
        return echelle;
    }

    public void setEchelle(long echelle) {
        this.echelle = echelle;
    }

    public boolean isEtat_competence() {
        return etat_competence;
    }

    public void setEtat_competence(boolean etat_competence) {
        this.etat_competence = etat_competence;
    }

  

 
  

    
}
