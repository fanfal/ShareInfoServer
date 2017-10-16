package com.au.shareinfoserver.traffic.controller;

import com.au.shareinfoserver.traffic.model.ShareInfo;
import com.au.shareinfoserver.traffic.service.TrafficInfoShareService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/traffic", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public class TrafficInfoShareController {

    @Autowired
    TrafficInfoShareService shareService;
    @RequestMapping(value = "/share")
    public ResponseEntity shareLocation(@RequestBody final ShareInfo shareInfo) throws JsonProcessingException {
        return shareService.saveShareInfo(shareInfo);
    }
}
