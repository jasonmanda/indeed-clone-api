package com.dash.it.solution.repository;

 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dash.it.solution.entities.Role;

/**
 * The interface Role repository.
 *
 * @author Jason Mandabrandja
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

   @Query("select r from Role r where r.role_name = ?1")
  public Role getRoleByName(String role_name);
}