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
 * The type Personne.
 *
 * @author Jason Mandabrandja
 */
@Entity
@Table(name = "gs_personne")
@EntityListeners(AuditingEntityListener.class)
public class Personne
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_personne;
    @Column(nullable = false)
    private String nom_personne;
    @Column(nullable = false)
    private String prenom_personne;
    
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date created_at;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = true)
    private Date updated_at;

 
    @ManyToOne
    @Basic(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ville",nullable = true,referencedColumnName = "id_ville")
    private Ville ville;
    
    
    public long getId_personne() {
        return id_personne;
    }

    public void setId_personne(long id_personne) {
        this.id_personne = id_personne;
    }

    public String getNom_personne() {
        return nom_personne;
    }

    public void setNom_personne(String nom_personne) {
        this.nom_personne = nom_personne;
    }

    public String getPrenom_personne() {
        return prenom_personne;
    }

    public void setPrenom_personne(String prenom_personne) {
        this.prenom_personne = prenom_personne;
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


    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    
}