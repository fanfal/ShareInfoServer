package com.au.shareinfoserver.user.service;

import com.au.shareinfoserver.dao.User;

public interface UserService {
    void saveUser(User user);

    User findByPhoneNum(String phoneNum);
}
