package com.dash.it.solution.repository;

 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.dash.it.solution.entities.Competence;

/**
 * The interface Competence repository.
 *
 * @author Jason Mandabrandja
 */
@Repository
public interface CompetenceRepository extends JpaRepository<Competence, Long> {
    @Query("SELECT c FROM Competence c where c.cv.cv_uid=?1 AND c.etat_competence=?2")
    public List<Competence> getActivesCompetence(String cv_uid,boolean etat_competence);
}