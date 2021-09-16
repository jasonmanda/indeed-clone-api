package com.dash.it.solution.repository;

 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dash.it.solution.entities.Ville;

/**
 * The interface Ville repository.
 *
 * @author Jason Mandabrandja
 */
@Repository
public interface VilleRepository extends JpaRepository<Ville, Long> {

   @Query("select v from Ville v where v.nom_ville = ?1")
  public Ville getVilleByName(String nom_ville);
}