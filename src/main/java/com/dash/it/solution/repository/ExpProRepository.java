package com.dash.it.solution.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.dash.it.solution.entities.ExpPro;

/**
 * The interface ExpPro repository.
 *
 * @author Jason Mandabrandja
 */
@Repository
public interface ExpProRepository extends JpaRepository<ExpPro, Long> {
  @Query("SELECT ep FROM ExpPro ep where ep.cv.cv_uid=?1 AND ep.etat_exp_pro=?2")
  public List<ExpPro> getActivesExPro(String cv_uid,boolean etat_exp_pro);
}