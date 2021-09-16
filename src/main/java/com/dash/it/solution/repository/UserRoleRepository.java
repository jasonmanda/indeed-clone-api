package com.dash.it.solution.repository;

import java.util.List;

import com.dash.it.solution.entities.UserRole;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


/**
 * The interface User repository.
 *
 * @author Jason Mandabrandja
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

  @Query("select ur from UserRole ur where ur.user.id_utilisateur = ?1 AND ur.etat_user_role = ?2")
  public UserRole getUserActiveRole(long id_utilisateur,Boolean etat_user_role);

  @Query("select ur from UserRole ur where ur.user.id_utilisateur = ?1 AND ur.etat_user_role = ?2")
  public List<UserRole> getAllRoleUser(long id_utilisateur,Boolean etat_user_role);

}