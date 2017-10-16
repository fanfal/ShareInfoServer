package com.au.shareinfoserver.traffic.controller;

import com.au.shareinfoserver.dao.CarInfo;
import com.au.shareinfoserver.dao.CarInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/traffic", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
public class TrafficInfoObtainController {
    @Autowired
    CarInfoRepository carInfoRepository;

    @RequestMapping(value = "/obtain")
    public Iterable<CarInfo> shareLocation() {
        return carInfoRepository.findAll();
    }

}
