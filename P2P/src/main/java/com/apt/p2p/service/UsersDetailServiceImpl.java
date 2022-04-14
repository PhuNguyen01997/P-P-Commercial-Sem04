package com.apt.p2p.service;
import com.apt.p2p.common.modelMapper.UserMapper;
import com.apt.p2p.entity.Role;
import com.apt.p2p.entity.User;
import com.apt.p2p.model.view.UserModel;
import com.apt.p2p.repository.RoleRepository;
import com.apt.p2p.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

@Configuration
@Service
public class UsersDetailServiceImpl implements UserDetailsService,UserService {
    @Value("${spring.mail.host}")
    private String mailHost;

    @Value("${spring.mail.port}")
    private String mailPort;

    @Value("${spring.mail.username}")
    private String mailUsername;

    @Value("${spring.mail.password}")
    private String mailPassword;

    @Value("${spring.mail.properties.mail.smtp.auth}")
    private String mailAuth;

    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private String mailTls;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserModel> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(e -> userMapper.userEntityToModel(e)).collect(Collectors.toList());
    }

    @Override
    public UserModel findById(Integer userId) {
        User user = userRepository.findById(userId).get();
        return userMapper.userEntityToModel(user);
    }

    @Override
    public User save(User user) {
        user.setProvider("LOCAL");
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public String findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailHost);
        mailSender.setPort(Integer.parseInt(mailPort));

        mailSender.setUsername(mailUsername);
        mailSender.setPassword(mailPassword);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", mailAuth);
        props.put("mail.smtp.starttls.enable", mailTls);
//        props.put("mail.debug", "true");
        return mailSender;
    }

    @Override
    public UserModel getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.isAuthenticated()) {
            return null;
        }
        User user = userRepository.findByUsername(auth.getName());
        return userMapper.userEntityToModel(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        List<Role> roles = roleRepository.FindRoleByEmail(user.getEmail());
        List<GrantedAuthority> grantList = new ArrayList<>();
        if (roles != null && roles.size() > 0) {
            for (Role role : roles) {
                GrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
                grantList.add(authority);
            }
        } else {
            grantList.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        boolean enabled = user.isEnabled();
        boolean accountNonExpired = true;
        boolean credentialNonExpired = true;
        boolean accountNonLocked = true;

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), enabled, accountNonExpired,
                credentialNonExpired, accountNonLocked, grantList);
    }

    // sau khi login bang google chung ta se tao ra 1 tai khoang local tuong ung
    public void processOAuthPostLogin(String username) {
        User user = userRepository.findByUsername((username));
        //truong hop chua co tai khoan => tao moi
        if (user == null) {
            User u = new User();
            u.setEmail(username);
            u.setUsername(username);
            u.setPhone("");
            u.setPassword("");
            u.setEnabled(true);
            u.setProvider("GOOGLE");
            userRepository.save(u);
        }
    }
}
