package com.au.shareinfoserver.user.service;

import com.au.shareinfoserver.dao.User;

public interface UserService {
    void register(User user);

    String login(String phoneNum, String password);

    String refresh(String oldToken);

    String encodePassWord(String passWord);
}
