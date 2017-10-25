package com.au.shareinfoserver.user.model;


import com.au.shareinfoserver.dao.User;

public class JwtUserFactory {
    private JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getPhoneNum(),
                user.getPassWord(),
                user.getEmailAddress(),
                user.getCredit(),
                user.getCash()
        );
    }

}
