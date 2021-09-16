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
 * The type Document.
 *
 * @author Jason Mandabrandja
 */
@Entity
@Table(name = "gs_cv")
@EntityListeners(AuditingEntityListener.class)
public class Cv{

    @Id
    private String cv_uid;
    @Column(nullable = true,columnDefinition = "TEXT")
    private String description;
    @Column(nullable = true)
    private long pret_salary_min;
    @Column(nullable = true)
    private long pret_salary_max;
    @OneToOne
    @JoinColumn(name = "id_personne",referencedColumnName = "id_personne")
    @Basic(fetch = FetchType.LAZY)
    private Personne personne; 
    @OneToOne
    @JoinColumn(name = "id_document",referencedColumnName = "id_document")
    @Basic(fetch = FetchType.LAZY)
    private Document document; 

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date created_at;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = true)
    private Date updated_at;

    @OneToOne
    @JoinColumn(name = "id_contact",referencedColumnName = "id_contact")
    @Basic(fetch = FetchType.LAZY)
    private Contact contact; 
    public String getCv_uid() {
        return cv_uid;
    }

    public void setCv_uid(String cv_uid) {
        this.cv_uid = cv_uid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }
    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
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

    public long getPret_salary_min() {
        return pret_salary_min;
    }

    public void setPret_salary_min(long pret_salary_min) {
        this.pret_salary_min = pret_salary_min;
    }

    public long getPret_salary_max() {
        return pret_salary_max;
    }

    public void setPret_salary_max(long pret_salary_max) {
        this.pret_salary_max = pret_salary_max;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

 
 
    
    
}