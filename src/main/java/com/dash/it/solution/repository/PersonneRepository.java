package com.dash.it.solution.repository;

 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dash.it.solution.entities.Personne;

/**
 * The interface Personne repository.
 *
 * @author Jason Mandabrandja
 */
@Repository
public interface PersonneRepository extends JpaRepository<Personne, Long> {

  //  @Query("select v from Personne v where v.nom_Personne = ?1")
  // public Personne getPersonneByName(String nom_Personne);
}