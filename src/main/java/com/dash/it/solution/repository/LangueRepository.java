package com.dash.it.solution.repository;

 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.dash.it.solution.entities.Langue;

/**
 * The interface Langue repository.
 *
 * @author Jason Mandabrandja
 */
@Repository
public interface LangueRepository extends JpaRepository<Langue, Long> {
    @Query("SELECT l FROM Langue l where l.cv.cv_uid=?1 AND l.etat_langue=?2")
    public List<Langue> getActivesLangue(String cv_uid,boolean etat_langue); 
}