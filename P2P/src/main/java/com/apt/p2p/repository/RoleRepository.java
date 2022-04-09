package com.apt.p2p.repository;



import com.apt.p2p.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    @Query("SELECT u.roles from User u WHERE u.email = :email")
    List<Role> FindRoleByEmail(@Param("email") String email);

    @Query("SELECT r FROM Role r WHERE r.name = :name")
    Role findRoleByName(String name);

    @Query("SELECT o.roles FROM User o WHERE o.username = :username")
    List<Role> FindRoleByUsername(@Param("username") String username);
}
