package com.apt.p2p.repository;

import com.apt.p2p.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    @Query("SELECT distinct (u.email) FROM User u WHERE u.email = :email")
    String findByEmail(String email);


}
