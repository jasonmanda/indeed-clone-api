package com.dash.it.solution.repository;

 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dash.it.solution.entities.Cv;

/**
 * The interface Cv repository.
 *
 * @author Jason Mandabrandja
 */
@Repository
public interface CvRepository extends JpaRepository<Cv, String> {
 
  @Query("select i from Cv i where i.personne.id_personne = ?1")
  public Cv getCvByPersonneId(long id_personne);
 
}