package com.dash.it.solution.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
// import org.springframework.data.annotation.CreatedBy;
// import org.springframework.data.annotation.CreatedDate;
// import org.springframework.data.annotation.LastModifiedBy;
// import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * The type Image.
 *
 * @author Jason Mandabrandja
 */
@Entity
@Table(name = "gs_image")
@EntityListeners(AuditingEntityListener.class)
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_image;
    @Column(nullable = false,columnDefinition = "TEXT")
    private String original_name;
    @Column(nullable = false,columnDefinition = "TEXT")
    private String path_image;
    @ManyToOne
    @JoinColumn(name = "id_personne",referencedColumnName = "id_personne")
    @Basic(fetch = FetchType.LAZY)
    private Personne personne; 
    @ManyToOne
    @JoinColumn(name="id_entreprise",referencedColumnName = "id_entreprise")
    @Basic(fetch = FetchType.LAZY)
    private Entreprise entreprise;  
    @Column(nullable = false,columnDefinition = "TINYINT(1) DEFAULT FALSE")
    private boolean is_logo;
    @Column(nullable = false,columnDefinition = "TINYINT(1) DEFAULT FALSE")
    private boolean is_temp;
    @Column(nullable = false,columnDefinition = "TINYINT(1) DEFAULT FALSE")
    private boolean etat_image;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date created_at;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = true)
    private Date updated_at;
    
    public long getId_image() {
        return id_image;
    }

    public void setId_image(long id_image) {
        this.id_image = id_image;
    }

 

    public String getPath_image() {
        return path_image;
    }

    public void setPath_image(String path_image) {
        this.path_image = path_image;
    }

 
    public boolean isIs_logo() {
        return is_logo;
    }

    public void setIs_logo(boolean is_logo) {
        this.is_logo = is_logo;
    }

    public boolean isEtat_image() {
        return etat_image;
    }

    public void setEtat_image(boolean etat_image) {
        this.etat_image = etat_image;
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

    public boolean isIs_temp() {
        return is_temp;
    }

    public void setIs_temp(boolean is_temp) {
        this.is_temp = is_temp;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }



}