package com.dash.it.solution.repository;

 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dash.it.solution.entities.User;

/**
 * The interface User repository.
 *
 * @author Jason Mandabrandja
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  @Query("select u from User u where u.email = ?1 AND u.password= ?2 AND u.etat_utilisateur= ?3")
  public User login(String email,String password,Boolean etat_utilisateur);
  @Query("select u from User u where u.email = ?1")
  public User uniqueEmailAdd(String email);
  @Query("select u from User u where u.email = ?1 AND u.id_utilisateur != ?2")
  public User uniqueEmailEdit(String email,Long id_utilisateur);
  @Query("select u from User u where u.api_token = ?1")
  public User getUserByApiToken(String api_token);


}