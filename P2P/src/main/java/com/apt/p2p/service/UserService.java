package com.apt.p2p.service;

import com.apt.p2p.entity.User;
import com.apt.p2p.model.view.UserModel;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserModel> findAll();

    UserModel findById(Integer id);

    UserModel save(User user);

    void deleteById(Integer id);

    String findByEmail(String email);
}
