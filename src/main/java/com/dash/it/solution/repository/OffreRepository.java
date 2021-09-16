package com.dash.it.solution.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.dash.it.solution.entities.Offre;

/**
 * The interface Offre Repository.
 *
 * @author Jason Mandabrandja
 */
@Repository
public interface OffreRepository extends JpaRepository<Offre, Long> {
    @Query("SELECT o FROM Offre o where o.etat_offre=?1")
    public List<Offre> getAllOffres(boolean etat_offre); 
    @Query("SELECT o FROM Offre o where o.entreprise.personne.id_personne=?1 AND o.etat_offre=?2")
    public List<Offre> getActivesOffreForEnt(long id_personne,boolean etat_offre); 

    @Query("SELECT o FROM Offre o where o.personne.id_personne=?1 AND o.etat_offre=?2")
    public List<Offre> getActivesOffreForPers(long id_personne,boolean etat_offre); 

    @Query("SELECT o FROM Offre o where o.poste LIKE %?1% AND o.etat_offre=?2")
    public List<Offre> getOffreByPoste(String poste,boolean etat_offre); 

    @Query("SELECT o FROM Offre o where o.description LIKE %?1% AND o.etat_offre=?2")
    public List<Offre> getOffreByDescription(String description,boolean etat_offre); 

    @Query("SELECT o FROM Offre o where o.localite.nom_ville LIKE %?1% AND o.etat_offre=?2")
    public List<Offre> getOffreByVille(String nom_ville,boolean etat_offre); 
}
