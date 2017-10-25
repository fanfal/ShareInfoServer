package com.au.shareinfoserver.user.service;


public interface SecurityService {
    String findLoggedInUser();

    void autologin(String phoneNum, String password);
}
