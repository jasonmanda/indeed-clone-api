package com.dash.it.solution.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.dash.it.solution.entities.Certificat;

/**
 * The interface Certificat repository.
 *
 * @author Jason Mandabrandja
 */
@Repository
public interface CertificatRepository extends JpaRepository<Certificat, Long> {
    @Query("SELECT c FROM Certificat c where c.cv.cv_uid=?1 AND c.etat_certificat=?2")
    public List<Certificat> getActivesCertificat(String cv_uid,boolean etat_certificat);
}