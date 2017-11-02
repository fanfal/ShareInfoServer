package com.au.shareinfoserver.user.controller;

import com.au.shareinfoserver.dao.User;
import com.au.shareinfoserver.dao.UserRepository;
import com.au.shareinfoserver.security.model.JwtAuthenticationRequest;
import com.au.shareinfoserver.security.model.JwtAuthenticationResponse;
import com.au.shareinfoserver.user.service.UserService;
import com.au.shareinfoserver.user.service.UserValidator;
import com.au.shareinfoserver.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@EnableAutoConfiguration
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    @Autowired
    private UserService userService;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public ResponseEntity registration(@RequestBody JwtAuthenticationRequest request, BindingResult bindingResult) {
        userValidator.validate(request, bindingResult);

        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException("Password or Name not follow the rule.");
        }
        User user = new User();
        user.setPhoneNum(request.getPhoneNum());
        user.setPassWord(request.getPassword());
        userService.register(user);

        final String token = userService.login(request.getPhoneNum(), request.getPassword());
        return ResponseEntity.ok().body(JsonUtil.toJson(new JwtAuthenticationResponse(token)));
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody JwtAuthenticationRequest request, BindingResult bindingResult) {
        if (request.getPhoneNum() == null || request.getPassword() == null) {
            return ResponseEntity.badRequest().body(null);
        }
        final String token = userService.login(request.getPhoneNum(), request.getPassword());
        return ResponseEntity.ok().body(JsonUtil.toJson(new JwtAuthenticationResponse(token)));
    }

    @RequestMapping(value = "/user/refresh", method = RequestMethod.GET)
    public ResponseEntity login(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshedToken = userService.refresh(token);
        if (refreshedToken == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok().body(JsonUtil.toJson(new JwtAuthenticationResponse(refreshedToken)));
        }
    }

}
