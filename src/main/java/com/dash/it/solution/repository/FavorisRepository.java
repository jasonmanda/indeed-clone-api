package com.dash.it.solution.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.dash.it.solution.entities.Favoris;

/**
 * The interface Favoris Repository.
 *
 * @author Jason Mandabrandja
 */
@Repository
public interface FavorisRepository extends JpaRepository<Favoris, Long> {
 
    @Query("SELECT f FROM Favoris f where f.offre.id_offre=?1")
    public List<Favoris> getActivesFavorisForEnt(long id_offre); 

    @Query("SELECT f FROM Favoris f where f.cv.cv_uid=?1")
    public List<Favoris> getActivesFavorisForPers(String cv_uid); 

    
    @Query("SELECT f FROM Favoris f where f.cv.personne.id_personne=?1 AND f.etat_favoris=?2")
    public List<Favoris> getActivesFavorisByIdPers(long id_personne,boolean etat_favoris); 

    @Query("SELECT COUNT(f) FROM Favoris f where f.offre.id_offre=?1")
    public Long getActivesFavorisForEntCount(long id_offre); 

    @Query("SELECT COUNT(f) FROM Favoris f where f.cv.cv_uid=?1")
    public Long getActivesFavorisForPersCount(String cv_uid); 
 
    
    @Query("SELECT COUNT(f) FROM Favoris f where f.cv.cv_uid=?1 AND f.offre.id_offre=?2 AND f.etat_favoris=?3")
    public Long getActivesFavorisForPersCount(String cv_uid,long id_offre,boolean etat_favoris); 

    @Query("SELECT f FROM Favoris f where f.cv.cv_uid=?1 AND f.offre.id_offre=?2 AND f.etat_favoris=?3")
    public Favoris getActiveFavorisForPersCount(String cv_uid,long id_offre,boolean etat_favoris); 
 
}