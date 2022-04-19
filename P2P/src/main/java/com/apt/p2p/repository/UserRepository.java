package com.apt.p2p.repository;

import com.apt.p2p.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    @Query("SELECT distinct (u.email) FROM User u WHERE u.email = :email")
    String findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE User u set u.resetPasswordToken = :token WHERE u.email = :email")
    void updateToken(String token , String email);

    User findByResetPasswordToken(String token);

    @Transactional
    @Modifying
    @Query("UPDATE User u set u.email = :newEmail WHERE u.email = :oldEmail")
    void updateEmail(String oldEmail , String newEmail);

    @Transactional
    @Modifying
    @Query("UPDATE User u set u.password = :password WHERE u.email = :email")
    void updatePassword(String email , String password);
}
