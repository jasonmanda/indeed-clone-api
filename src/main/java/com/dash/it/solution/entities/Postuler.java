package com.dash.it.solution.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
// import org.springframework.data.annotation.CreatedBy;
// import org.springframework.data.annotation.CreatedDate;
// import org.springframework.data.annotation.LastModifiedBy;
// import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

// import java.util.ArrayList;
import java.util.Date;
// import java.util.List;

/**
 * The type Postuler.
 *
 * @author Jason Mandabrandja
 */
@Entity
@Table(name = "gs_postuler")
@EntityListeners(AuditingEntityListener.class)
public class Postuler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_postuler;
    @ManyToOne
    @JoinColumn(name = "id_offre", referencedColumnName = "id_offre")
    @Basic(fetch = FetchType.LAZY)
    private Offre offre;
    @ManyToOne
    @JoinColumn(name = "id_personne", referencedColumnName = "id_personne")
    @Basic(fetch = FetchType.LAZY)
    private Personne personne;
    @ManyToOne
    @JoinColumn(name = "id_entreprise", referencedColumnName = "id_entreprise")
    @Basic(fetch = FetchType.LAZY)
    private Entreprise entreprise;
    @ManyToOne
    @JoinColumn(name = "cv_uid", referencedColumnName = "cv_uid")
    @Basic(fetch = FetchType.LAZY)
    private Cv cv;
    @Column(nullable = true, columnDefinition = "TEXT")
    private String message;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date created_at;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = true)
    private Date updated_at;

    @Column(nullable = false, columnDefinition = "TINYINT(1) DEFAULT FALSE")
    private boolean retenue;

    public long getId_postuler() {
        return id_postuler;
    }

    public void setId_postuler(long id_postuler) {
        this.id_postuler = id_postuler;
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

    public boolean isRetenue() {
        return retenue;
    }

    public void setRetenue(boolean retenue) {
        this.retenue = retenue;
    }

    public Offre getOffre() {
        return offre;
    }

    public void setOffre(Offre offre) {
        this.offre = offre;
    }

    public Cv getCv() {
        return cv;
    }

    public void setCv(Cv cv) {
        this.cv = cv;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }

}