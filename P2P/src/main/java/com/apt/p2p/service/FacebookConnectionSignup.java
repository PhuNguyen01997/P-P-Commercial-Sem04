package com.apt.p2p.service;

import com.apt.p2p.entity.User;
import com.apt.p2p.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Service;

@Service
public class FacebookConnectionSignup implements ConnectionSignUp {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String execute(Connection<?> connection) {
        User user = userRepository.findByUsername((connection.getDisplayName()));
        //truong hop chua co tai khoan => tao moi
        if (user == null) {
            User u = new User();
            u.setEmail(connection.getDisplayName());
            u.setUsername(connection.getDisplayName());
            u.setPhone("");
            u.setPassword("");
            u.setEnabled(true);
            u.setProvider("FACEBOOK");
            userRepository.save(u);
        }
        return user.getUsername();
    }
}
