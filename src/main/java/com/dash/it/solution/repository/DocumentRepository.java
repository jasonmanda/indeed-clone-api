package com.dash.it.solution.repository;

 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dash.it.solution.entities.Document;

/**
 * The interface Document repository.
 *
 * @author Jason Mandabrandja
 */
@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
 
}