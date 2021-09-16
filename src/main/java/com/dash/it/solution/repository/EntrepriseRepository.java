package com.dash.it.solution.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dash.it.solution.entities.Entreprise;

/**
 * The interface Entreprise repository.
 *
 * @author Jason Mandabrandja
 */
@Repository
public interface EntrepriseRepository extends JpaRepository<Entreprise, Long> {

  @Query("select e from Entreprise e where e.nom = ?1")
  public Entreprise uniqueNomEntAdd(String nomEnt);

  @Query("select e from Entreprise e where e.nom = ?1 AND e.id_entreprise != ?2")
  public Entreprise uniqueNomEntEdit(String nomEnt, long id_entreprise);
  @Query("select e from Entreprise e where e.personne.id_personne = ?1")
  public Entreprise getEntrepriseByIdPersonne(long id_personne);
}