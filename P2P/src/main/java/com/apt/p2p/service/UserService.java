package com.apt.p2p.service;

import com.apt.p2p.entity.User;
import com.apt.p2p.model.view.UserModel;

import java.util.List;

public interface UserService {
    List<UserModel> findAll();

    UserModel findById(Integer id);

    User save(User user);

    void deleteById(Integer id);

    String findByEmail(String email);

    UserModel getCurrentUser();
}
