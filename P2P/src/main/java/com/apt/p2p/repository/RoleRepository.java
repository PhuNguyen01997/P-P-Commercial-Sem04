package com.apt.p2p.repository;



import com.apt.p2p.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    @Query("SELECT o.primaryKey.role FROM UserRole o WHERE o.primaryKey.user.username = :username")
    List<Role> FindRoleByUsername(@Param("username") String username);
}
