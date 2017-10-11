package com.au.shareinfoserver.traffice.controller;

import com.au.shareinfoserver.dao.CarInfo;
import com.au.shareinfoserver.dao.CarInfoRepository;
import com.au.shareinfoserver.traffice.model.ShareInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    CarInfoRepository carInfoRepository;

    @RequestMapping(value = "/share")
    public ResponseEntity shareLocation(@RequestBody final ShareInfo shareInfo) throws JsonProcessingException {
        CarInfo carInfo = new CarInfo();
        carInfo.setCardNumber(shareInfo.getCarInfo().getCardNumber());
        carInfo.setLocation(new ObjectMapper().writeValueAsString(shareInfo.getLocation()));
        carInfoRepository.save(carInfo);
        return ResponseEntity.ok().build();
    }
}
