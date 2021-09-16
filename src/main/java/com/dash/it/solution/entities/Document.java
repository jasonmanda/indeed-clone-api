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
@Table(name = "gs_document")
@EntityListeners(AuditingEntityListener.class)
public class Document{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_document;
    @Column(nullable = false,columnDefinition = "TEXT")
    private String original_name;
    @Column(nullable = false,columnDefinition = "TEXT")
    private String path_document;
  
    @Column(nullable = false,columnDefinition = "TINYINT(1) DEFAULT FALSE")
    private boolean etat_document;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date created_at;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = true)
    private Date updated_at;

    public long getId_document() {
        return id_document;
    }

    public void setId_document(long id_document) {
        this.id_document = id_document;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public String getPath_document() {
        return path_document;
    }

    public void setPath_document(String path_document) {
        this.path_document = path_document;
    }
 

    public boolean isEtat_document() {
        return etat_document;
    }

    public void setEtat_document(boolean etat_document) {
        this.etat_document = etat_document;
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

  


}