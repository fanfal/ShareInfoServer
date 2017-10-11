package com.au.shareinfoserver;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class HelloWorldController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String HelloWorld() {
        return "Hello World";
    }

}
