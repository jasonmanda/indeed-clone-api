package com.dash.it.solution.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dash.it.solution.entities.Contact;

/**
 * The interface Contact repository.
 *
 * @author Jason Mandabrandja
 */
@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
  
  @Query("select c from Contact c where c.facebook = ?1")
  public Contact uniqueFacebookAdd(String facebook);

  @Query("select c from Contact c where c.facebook = ?1 AND c.id_contact != ?2")
  public Contact uniqueFacebookEdit(String facebook, Long id_contact);
 
  @Query("select c from Contact c where c.linkedin = ?1")
  public Contact uniqueLinkedinAdd(String linkedin);

  @Query("select c from Contact c where c.linkedin = ?1 AND c.id_contact != ?2")
  public Contact uniqueLinkedinEdit(String linkedin, Long id_contact);

  @Query("select c from Contact c where c.mail = ?1")
  public Contact uniqueMailAdd(String mail);

  @Query("select c from Contact c where c.mail = ?1 AND c.id_contact != ?2")
  public Contact uniqueMailEdit(String mail, Long id_contact);

  @Query("select c from Contact c where c.numero = ?1")
  public Contact uniqueNumeroAdd(String numero);

  @Query("select c from Contact c where c.numero = ?1 AND c.id_contact != ?2")
  public Contact uniqueNumeroEdit(String numero, Long id_contact);

  @Query("select c from Contact c where c.twitter = ?1")
  public Contact uniqueTwitterAdd(String twitter);

  @Query("select c from Contact c where c.twitter = ?1 AND c.id_contact != ?2")
  public Contact uniqueTwitterEdit(String twitter, Long id_contact);

  @Query("select c from Contact c where c.website = ?1")
  public Contact uniqueWebsiteAdd(String website);

  @Query("select c from Contact c where c.website = ?1 AND c.id_contact != ?2")
  public Contact uniqueWebsiteEdit(String website, Long id_contact);

}