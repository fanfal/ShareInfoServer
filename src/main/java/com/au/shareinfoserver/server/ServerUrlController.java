package com.au.shareinfoserver.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class ServerUrlController {
    @Autowired
    URLService urlService;

    @RequestMapping(value = "/configuration/urls", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getServerUrls() {
        return urlService.getJsonLinks();
    }
}
