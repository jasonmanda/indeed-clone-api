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
 * The type Favoris.
 *
 * @author Jason Mandabrandja
 */
@Entity
@Table(name = "gs_favoris")
@EntityListeners(AuditingEntityListener.class)
public class Favoris
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_favoris;
    @ManyToOne
    @JoinColumn(name = "id_offre",referencedColumnName = "id_offre")
    @Basic(fetch = FetchType.LAZY)
    private Offre offre; 

    @ManyToOne
    @JoinColumn(name = "cv_uid",referencedColumnName = "cv_uid")
    @Basic(fetch = FetchType.LAZY)
    private Cv cv; 
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date created_at;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = true)
    private Date updated_at;

    @Column(nullable = false,columnDefinition = "TINYINT(1) DEFAULT FALSE")
    private boolean etat_favoris;

  
   
    public long getId_favoris() {
        return id_favoris;
    }

    public void setId_favoris(long id_favoris) {
        this.id_favoris = id_favoris;
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

    public boolean isEtat_favoris() {
        return etat_favoris;
    }

    public void setEtat_favoris(boolean etat_favoris) {
        this.etat_favoris = etat_favoris;
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

   
 
    
 
}