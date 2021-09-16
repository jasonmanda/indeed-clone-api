package com.dash.it.solution.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.dash.it.solution.entities.Postuler;

/**
 * The interface Postuler Repository.
 *
 * @author Jason Mandabrandja
 */
@Repository
public interface PostulerRepository extends JpaRepository<Postuler, Long> {
 
    @Query("SELECT p FROM Postuler p where p.offre.id_offre=?1")
    public List<Postuler> getActivesPostulerForEnt(long id_offre); 

    @Query("SELECT p FROM Postuler p where p.cv.cv_uid=?1")
    public List<Postuler> getActivesPostulerForPers(String cv_uid); 

    @Query("SELECT p FROM Postuler p where p.cv.personne.id_personne=?1")
    public List<Postuler> getActivesPostulerByIdPers(long id_personne); 

    @Query("SELECT COUNT(p) FROM Postuler p where p.offre.id_offre=?1")
    public Long getActivesPostulerForEntCount(long id_offre); 

    @Query("SELECT COUNT(p) FROM Postuler p where p.cv.cv_uid=?1")
    public Long getActivesPostulerForPersCount(String cv_uid); 
 
    
    @Query("SELECT COUNT(p) FROM Postuler p where p.cv.cv_uid=?1 AND p.offre.id_offre=?2")
    public Long getActivesPostulerForPersCount(String cv_uid,long id_offre); 
 
}