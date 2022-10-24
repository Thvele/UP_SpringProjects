package com.example.P50519.Models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, ADMIN, HRdep;

    @Override
    public String getAuthority() { return name(); }
}