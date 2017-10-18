package com.au.shareinfoserver.traffic.controller;

import com.au.shareinfoserver.traffic.convertor.TrafficInfoConvertor;
import com.au.shareinfoserver.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/traffic", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
public class TrafficInfoObtainController {
    @Autowired
    TrafficInfoConvertor trafficInfoConvertor;

    @RequestMapping(value = "/obtain")
    public String shareLocation(@RequestParam("carNumber") String carNumber) {
        HashMap result = new HashMap();
        result.put("result", trafficInfoConvertor.convertTrafficInfoListToCarInfoList(carNumber));
        return JsonUtil.asJsonString(result);
    }

}
