package com.chat.gateway.models;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

public class TokenAuthentication implements Authentication {
    private String token;
    private boolean authenticated;
    private Object details;

    public TokenAuthentication(String token) {
        this.token = token;
    }

    public TokenAuthentication(String token, Object details) {
        this.token = token;
        this.details = details;
    }

    @Override
    public String getName() {
        return token;  // Replace with the actual username, if available
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getDetails() {
        return details;
    }

    public void setDetails(Object details) {
        this.details = details;
    }

    @Override
    public Object getPrincipal() {
        return token;  // Replace with the actual user, if available
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();  // Replace with the actual authorities, if available
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }
}
