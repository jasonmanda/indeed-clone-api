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
 * The type Ville.
 *
 * @author Jason Mandabrandja
 */
@Entity
@Table(name = "gs_ville")
@EntityListeners(AuditingEntityListener.class)
public class Ville
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_ville;
    @Column(nullable = false,unique = true)
    private String nom_ville;
 
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date created_at;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = true)
    private Date updated_at;

    @Column(nullable = false,columnDefinition = "TINYINT(1) DEFAULT FALSE")
    private boolean etat_ville;
    

    public long getId_ville() {
        return id_ville;
    }

    public void setId_ville(long id_ville) {
        this.id_ville = id_ville;
    }

    public String getNom_ville() {
        return nom_ville;
    }

    public void setNom_ville(String nom_ville) {
        this.nom_ville = nom_ville;
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

    public boolean isEtat_ville() {
        return etat_ville;
    }

    public void setEtat_ville(boolean etat_ville) {
        this.etat_ville = etat_ville;
    }

 
 
}