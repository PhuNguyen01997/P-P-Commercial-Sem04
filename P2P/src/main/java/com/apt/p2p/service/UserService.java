package com.apt.p2p.service;

import com.apt.p2p.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();

    Optional<User> findById(Integer id);

    User save(User user);

    void deleteById(Integer id);
}
