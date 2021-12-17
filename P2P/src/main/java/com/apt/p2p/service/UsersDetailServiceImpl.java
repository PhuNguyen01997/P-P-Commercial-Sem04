package com.apt.p2p.service;


import com.apt.p2p.entity.Role;
import com.apt.p2p.entity.User;
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

@Service
public class UsersDetailServiceImpl implements UserDetailsService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

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
}
