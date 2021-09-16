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
 * The type Contact.
 *
 * @author Jason Mandabrandja
 */
@Entity
@Table(name = "gs_contact")
@EntityListeners(AuditingEntityListener.class)
public class Contact
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_contact;
    @Column(nullable = true,unique = true)
    private String mail;
    @Column(nullable = true,unique = true)
    private String numero;
    @Column(nullable = true,unique = true)
    private String linkedin;
    @Column(nullable = true,unique = true)
    private String website;
    @Column(nullable = true,unique = true)
    private String twitter;
    @Column(nullable = true,unique = true)
    private String facebook;
 
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date created_at;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = true)
    private Date updated_at;

    @Column(nullable = false,columnDefinition = "TINYINT(1) DEFAULT FALSE")
    private boolean etat_contact;

  
   
    public long getId_contact() {
        return id_contact;
    }

    public void setId_contact(long id_contact) {
        this.id_contact = id_contact;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
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

    public boolean isEtat_contact() {
        return etat_contact;
    }

    public void setEtat_contact(boolean etat_contact) {
        this.etat_contact = etat_contact;
    }

   
 
    
 
}