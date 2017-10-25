package com.au.shareinfoserver.user.service;

import com.au.shareinfoserver.dao.UserRepository;
import com.au.shareinfoserver.security.model.JwtAuthenticationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return JwtAuthenticationRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        JwtAuthenticationRequest request = (JwtAuthenticationRequest) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNum", "NotEmpty");
        if (request.getPhoneNum().length() < 6 || request.getPhoneNum().length() > 32) {
            errors.rejectValue("phoneNum", "Size.userForm.phoneNum");
        }
        if (userRepository.findByPhoneNum(request.getPhoneNum()) != null) {
            errors.rejectValue("phoneNum", "Duplicate.userForm.phoneNum");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (request.getPassword().length() < 8 || request.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }
    }
}
