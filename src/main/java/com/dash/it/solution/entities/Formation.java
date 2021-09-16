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
@Table(name = "gs_formation")
@EntityListeners(AuditingEntityListener.class)
public class Formation{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_formation;
    @Column(nullable = false)
    private String titre_formation;
    @Column(nullable = false)
    private String niveau_etude;
    @Column(nullable = true,columnDefinition = "TEXT")
    private String description;
  
    ///*Basz */
    @Temporal(TemporalType.DATE)
    private Date debut;
    @Temporal(TemporalType.DATE)
    private Date fin;

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
    @OneToOne
    @JoinColumn(name = "id_document",nullable = true,referencedColumnName = "id_document")
    @Basic(fetch = FetchType.LAZY)
    private Document document; 
    ///*Basz */
    @Column(columnDefinition = "TINYINT(1) DEFAULT FALSE")
    private boolean etat_formation;
  
        
    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
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
 
 

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Cv getCv() {
        return cv;
    }

    public void setCv(Cv cv) {
        this.cv = cv;
    }

    public long getId_formation() {
        return id_formation;
    }

    public void setId_formation(long id_formation) {
        this.id_formation = id_formation;
    }

    public String getTitre_formation() {
        return titre_formation;
    }

    public void setTitre_formation(String titre_formation) {
        this.titre_formation = titre_formation;
    }

    public String getNiveau_etude() {
        return niveau_etude;
    }

    public void setNiveau_etude(String niveau_etude) {
        this.niveau_etude = niveau_etude;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public boolean isEtat_formation() {
        return etat_formation;
    }

    public void setEtat_formation(boolean etat_formation) {
        this.etat_formation = etat_formation;
    }

    
}
