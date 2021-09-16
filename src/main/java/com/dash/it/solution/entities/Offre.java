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
 * The type Offre.
 *
 * @author Jason Mandabrandja
 */
@Entity
@Table(name = "gs_offre")
@EntityListeners(AuditingEntityListener.class)
public class Offre
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_offre;
    @Column(nullable = false,columnDefinition = "TINYINT(1) DEFAULT FALSE")
    private boolean cab_recrutement;
    @Column(nullable = true)
    private String nom_ent;
    @Column(nullable = true)
    private String poste;
    @ManyToOne 
    @JoinColumn(name = "id_ville",nullable = true,referencedColumnName = "id_ville")
    @Basic(fetch = FetchType.LAZY)
    private Ville localite; 
    @ManyToOne 
    @JoinColumn(name = "id_personne",nullable = true,referencedColumnName = "id_personne")
    @Basic(fetch = FetchType.LAZY)
    private Personne personne; 
    @ManyToOne 
    @JoinColumn(name = "id_entreprise",nullable = true,referencedColumnName = "id_entreprise")
    @Basic(fetch = FetchType.LAZY)
    private Entreprise entreprise; 
    @Column(nullable = true)
    private String taille;
    @Column(nullable = true)
    private String type_emp;
    @Column(nullable = true)
    private String type_contrat;
    @Column(nullable = true)
    private int salaire;
    @Column(nullable = true)
    private String salaire_type;
    @Column(nullable = true)
    private int salaire_min;
    @Column(nullable = true)
    private int salaire_max;
    @Column(nullable = true,columnDefinition = "TINYINT(1) DEFAULT FALSE")
    private boolean salaire_model;
    @Column(nullable = true,columnDefinition = "TINYINT(1) DEFAULT FALSE")
    private boolean no_salaire;
    @Column(nullable = true)
    private String avantages;
    @Column(nullable = true,columnDefinition = "TINYINT(1) DEFAULT FALSE")
    private boolean cv_require;
    @Column(nullable = true,columnDefinition = "TINYINT(1) DEFAULT FALSE")
    private boolean test_compet;
    @Column(nullable = true,columnDefinition = "TEXT")
    private String description;
    @Column(nullable = true)
    private Date date_limit;
    @Column(nullable = true)
    private Date date_debut;
    @Column(nullable = true,columnDefinition = "TINYINT(1) DEFAULT FALSE")
    private boolean date_immediat;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date created_at;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = true)
    private Date updated_at;

    @Column(nullable = false,columnDefinition = "TINYINT(1) DEFAULT FALSE")
    private boolean etat_offre;

  
   
    public long getId_offre() {
        return id_offre;
    }

    public void setId_offre(long id_offre) {
        this.id_offre = id_offre;
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

    public boolean isEtat_offre() {
        return etat_offre;
    }

    public void setEtat_offre(boolean etat_offre) {
        this.etat_offre = etat_offre;
    }

    public boolean isCab_recrutement() {
        return cab_recrutement;
    }

    public void setCab_recrutement(boolean cab_recrutement) {
        this.cab_recrutement = cab_recrutement;
    }

    public String getNom_ent() {
        return nom_ent;
    }

    public void setNom_ent(String nom_ent) {
        this.nom_ent = nom_ent;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public Ville getLocalite() {
        return localite;
    }

    public void setLocalite(Ville localite) {
        this.localite = localite;
    }

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public String getType_emp() {
        return type_emp;
    }

    public void setType_emp(String type_emp) {
        this.type_emp = type_emp;
    }

    public String getType_contrat() {
        return type_contrat;
    }

    public void setType_contrat(String type_contrat) {
        this.type_contrat = type_contrat;
    }

    public int getSalaire() {
        return salaire;
    }

    public void setSalaire(int salaire) {
        this.salaire = salaire;
    }

    public String getSalaire_type() {
        return salaire_type;
    }

    public void setSalaire_type(String salaire_type) {
        this.salaire_type = salaire_type;
    }

    public int getSalaire_min() {
        return salaire_min;
    }

    public void setSalaire_min(int salaire_min) {
        this.salaire_min = salaire_min;
    }

    public int getSalaire_max() {
        return salaire_max;
    }

    public void setSalaire_max(int salaire_max) {
        this.salaire_max = salaire_max;
    }

    public boolean isSalaire_model() {
        return salaire_model;
    }

    public void setSalaire_model(boolean salaire_model) {
        this.salaire_model = salaire_model;
    }

    public boolean isNo_salaire() {
        return no_salaire;
    }

    public void setNo_salaire(boolean no_salaire) {
        this.no_salaire = no_salaire;
    }

    public String getAvantages() {
        return avantages;
    }

    public void setAvantages(String avantages) {
        this.avantages = avantages;
    }

    public boolean isCv_require() {
        return cv_require;
    }

    public void setCv_require(boolean cv_require) {
        this.cv_require = cv_require;
    }

    public boolean isTest_compet() {
        return test_compet;
    }

    public void setTest_compet(boolean test_compet) {
        this.test_compet = test_compet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_limit() {
        return date_limit;
    }

    public void setDate_limit(Date date_limit) {
        this.date_limit = date_limit;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public boolean isDate_immediat() {
        return date_immediat;
    }

    public void setDate_immediat(boolean date_immediat) {
        this.date_immediat = date_immediat;
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