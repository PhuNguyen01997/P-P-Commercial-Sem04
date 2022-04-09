package com.apt.p2p.model;

import com.apt.p2p.entity.Role;
import com.apt.p2p.entity.User;
import com.apt.p2p.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyUserDetails implements UserDetails {
    @Autowired
    private RoleRepository repoRole;

    private User user;

    public MyUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Role> roles = repoRole.FindRoleByUsername(user.getUsername());
        List<GrantedAuthority> grantList = new ArrayList<>();
        if (roles != null && roles.size() > 0) {
            // trường hợp user có role
            for(Role r: roles) {
                // tạo đối tượng GrantedAuthority theo role
                GrantedAuthority authority = new SimpleGrantedAuthority(r.getName());
                grantList.add(authority);
            }
        }
        return grantList;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
