package com.au.shareinfoserver.user.model;


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
    private String emailAddress;
    private Integer credit;
    private Integer cash;
    private Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

    public JwtUser(Integer id, String phoneNum, String passWord, String emailAddress, Integer credit, Integer cash) {
        this.id = id;
        this.phoneNum = phoneNum;
        this.passWord = passWord;
        this.emailAddress = emailAddress;
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

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
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
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
