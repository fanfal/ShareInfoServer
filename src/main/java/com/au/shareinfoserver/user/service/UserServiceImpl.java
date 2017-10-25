package com.au.shareinfoserver.user.service;

import com.au.shareinfoserver.dao.User;
import com.au.shareinfoserver.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void saveUser(User user) {
        user.setPassWord(bCryptPasswordEncoder.encode(user.getPassWord()));
        userRepository.save(user);
    }

    @Override
    public User findByPhoneNum(String phoneNum) {
        return userRepository.findByPhoneNum(phoneNum);
    }
}
