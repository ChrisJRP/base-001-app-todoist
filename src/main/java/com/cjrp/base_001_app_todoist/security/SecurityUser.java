package com.cjrp.base_001_app_todoist.security;

import com.cjrp.base_001_app_todoist.entity.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

//(3)
public class SecurityUser implements UserDetails {

    private final Usuario usuario;

    public SecurityUser(Usuario usuario) {
        this.usuario = usuario;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //return Collections.emptyList();
        return Collections.singletonList(() -> "ROLE_" + usuario.getRole().name());
    }

    @Override
    public String getPassword() {
        return usuario.getPassword();
    }


    @Override
    public String getUsername() {
        return usuario.getEmail();
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

