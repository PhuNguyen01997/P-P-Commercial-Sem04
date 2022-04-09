package com.apt.p2p.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

public class Custom0Auth2User implements OAuth2User {
    private OAuth2User user;

    public Custom0Auth2User(OAuth2User user) {
        this.user = user;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return user.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities();
    }

    @Override
    public String getName() {
        return user.getName();
    }

    public String getEmail() {
        return user.<String>getAttribute("email");
    }
}
