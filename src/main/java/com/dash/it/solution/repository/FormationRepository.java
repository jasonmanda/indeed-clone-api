package com.dash.it.solution.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.dash.it.solution.entities.Formation;

/**
 * The interface Formation repository.
 *
 * @author Jason Mandabrandja
 */
@Repository
public interface FormationRepository extends JpaRepository<Formation, Long> {
    @Query("SELECT f FROM Formation f where f.cv.cv_uid=?1 AND f.etat_formation=?2")
    public List<Formation> getActivesFormation(String cv_uid,boolean etat_formation);
}