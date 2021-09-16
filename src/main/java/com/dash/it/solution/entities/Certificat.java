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
@Table(name = "gs_certificat")
@EntityListeners(AuditingEntityListener.class)
public class Certificat{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_certificat;
    @Column(nullable = false)
    private String titre_certificat;
    @Column(nullable = true,columnDefinition = "TEXT")
    private String description;
    @Column(nullable=true,columnDefinition = "TEXT")
    private String uid_certificat;
    @Column(nullable=true,columnDefinition = "TEXT")
    private String url_certificat;
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
    private boolean etat_certificat;
  
        
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

    

     public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public long getId_certificat() {
        return id_certificat;
    }

    public void setId_certificat(long id_certificat) {
        this.id_certificat = id_certificat;
    }

    public String getTitre_certificat() {
        return titre_certificat;
    }

    public void setTitre_certificat(String titre_certificat) {
        this.titre_certificat = titre_certificat;
    }

    public String getUid_certificat() {
        return uid_certificat;
    }

    public void setUid_certificat(String uid_certificat) {
        this.uid_certificat = uid_certificat;
    }

    public String getUrl_certificat() {
        return url_certificat;
    }

    public void setUrl_certificat(String url_certificat) {
        this.url_certificat = url_certificat;
    }

    public boolean isEtat_certificat() {
        return etat_certificat;
    }

    public void setEtat_certificat(boolean etat_certificat) {
        this.etat_certificat = etat_certificat;
    }

  

    
}
