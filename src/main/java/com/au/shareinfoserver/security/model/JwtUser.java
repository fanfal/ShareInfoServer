package com.au.shareinfoserver.security.model;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class JwtUser implements UserDetails {
    private Integer id;
    private String phoneNum;
    private String passWord;
    private Integer credit;
    private Integer cash;
    private Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

    public JwtUser(Integer id, String phoneNum, String passWord, Integer credit, Integer cash) {
        this.id = id;
        this.phoneNum = phoneNum;
        this.passWord = passWord;
        this.credit = credit;
        this.cash = cash;
        grantedAuthorities.add(new SimpleGrantedAuthority("custom"));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Integer getCash() {
        return cash;
    }

    public void setCash(Integer cash) {
        this.cash = cash;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return passWord;
    }

    @Override
    public String getUsername() {
        return phoneNum;
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
