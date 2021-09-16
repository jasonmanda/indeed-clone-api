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
@Table(name = "gs_exp_pro")
@EntityListeners(AuditingEntityListener.class)
public class ExpPro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_exp_pro;
    @Column(nullable = false)
    private String nom_soc;
    @Column(nullable = false)
    private String poste;
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
    @ManyToOne
    @JoinColumn(name = "id_ville",referencedColumnName = "id_ville")
    @Basic(fetch = FetchType.LAZY)
    private Ville ville; 
    @Column(columnDefinition = "TINYINT(1) DEFAULT FALSE")
    private boolean etat_exp_pro;
    ///*Basz */

    public long getId_exp_pro() {
        return id_exp_pro;
    }

    public void setId_exp_pro(long id_exp_pro) {
        this.id_exp_pro = id_exp_pro;
    }

        
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

    public String getNom_soc() {
        return nom_soc;
    }

    public void setNom_soc(String nom_soc) {
        this.nom_soc = nom_soc;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
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

    public boolean isEtat_exp_pro() {
        return etat_exp_pro;
    }

    public void setEtat_exp_pro(boolean etat_exp_pro) {
        this.etat_exp_pro = etat_exp_pro;
    }

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    
}
