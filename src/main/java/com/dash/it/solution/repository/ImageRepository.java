package com.dash.it.solution.repository;

 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dash.it.solution.entities.Image;

/**
 * The interface Image repository.
 *
 * @author Jason Mandabrandja
 */
@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

  @Query("select i from Image i where i.entreprise.id_entreprise = ?1 AND i.etat_image= ?2 AND i.is_logo = ?3 AND i.is_temp = ?4")
  public Image getActiveEntrepriseImage(long id_entreprise,Boolean etat_image,Boolean is_logo,Boolean is_temp);
  @Query("select i from Image i where i.personne.id_personne = ?1 AND i.etat_image= ?2 AND i.is_logo = ?3 AND i.is_temp = ?4")
  public Image getActivePersonneImage(long id_personne,Boolean etat_image,Boolean is_logo,Boolean is_temp);
}