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
@Table(name = "gs_langue")
@EntityListeners(AuditingEntityListener.class)
public class Langue{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_langue;
    @Column(nullable = false)
    private String libelle;
    @Column(nullable = false)
    private String echelle;
 
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
    private boolean etat_langue;
  
        
 
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

 

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public long getId_langue() {
        return id_langue;
    }

    public void setId_langue(long id_langue) {
        this.id_langue = id_langue;
    }

    public String getEchelle() {
        return echelle;
    }

    public void setEchelle(String echelle) {
        this.echelle = echelle;
    }

    public boolean isEtat_langue() {
        return etat_langue;
    }

    public void setEtat_langue(boolean etat_langue) {
        this.etat_langue = etat_langue;
    }
 
    
}
