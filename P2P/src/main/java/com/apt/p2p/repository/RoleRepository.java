package com.apt.p2p.repository;



import com.apt.p2p.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    // đây là custom method của người dùng
    // -> Spring boot không sinh mã câu truy vấn được, chúng ta cần cung cấp câu truy vấn
    @Query("SELECT o.role FROM UserRole o WHERE o.user.username = :username")
    List<Role> FindRoleByUsername(@Param("username") String username);
}
