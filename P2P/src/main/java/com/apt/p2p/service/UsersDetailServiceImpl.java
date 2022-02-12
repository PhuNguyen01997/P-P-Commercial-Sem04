package com.apt.p2p.service;


import com.apt.p2p.common.modelMapper.UserMapper;
import com.apt.p2p.entity.Role;
import com.apt.p2p.entity.User;
import com.apt.p2p.model.view.UserModel;
import com.apt.p2p.repository.RoleRepository;
import com.apt.p2p.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsersDetailServiceImpl implements UserDetailsService,UserService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
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



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        List<Role> roles = roleRepository.FindRoleByUsername(username);
        List<GrantedAuthority> grantList = new ArrayList<>();
        if(roles != null && roles.size() > 0){
            for(Role role : roles){
                GrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
                grantList.add(authority);
            }
        }else {
            grantList.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        boolean enabled = user.isEnabled();
        boolean accountNonExpired = true;
        boolean credentialNonExpired = true;
        boolean accountNonLocked = true;

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), enabled, accountNonExpired,
                credentialNonExpired, accountNonLocked, grantList);
    }

    public UserModel findById(int userId){
        User user =  userRepository.findById(userId).orElse(null);
        return userMapper.userEntityToModel(user);
    }
}
