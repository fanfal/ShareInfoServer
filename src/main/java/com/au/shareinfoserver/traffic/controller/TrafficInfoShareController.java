package com.au.shareinfoserver.traffic.controller;

import com.au.shareinfoserver.traffic.model.ShareBusInfoResponse;
import com.au.shareinfoserver.traffic.model.ShareInfo;
import com.au.shareinfoserver.traffic.service.TrafficInfoShareService;
import com.au.shareinfoserver.utils.JsonUtil;
import com.au.shareinfoserver.utils.JwtTokenUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/traffic", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public class TrafficInfoShareController {
    @Autowired
    TrafficInfoShareService shareService;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @RequestMapping(value = "/share")
    public ResponseEntity shareLocation(@RequestHeader("${jwt.header}") String token, @RequestBody final ShareInfo shareInfo) throws JsonProcessingException {
        if (token != null && token.startsWith(tokenHead)) {
            final String authToken = token.substring(tokenHead.length());
            String phoneNum = jwtTokenUtil.getUsernameFromToken(authToken);
            ShareBusInfoResponse response = shareService.handleShareInfo(phoneNum, shareInfo);
            return ResponseEntity.ok(JsonUtil.toJson(response));
        }
        return ResponseEntity.badRequest().body(null);
    }


    @RequestMapping(value = "/remove")
    public ResponseEntity shareLocation(@RequestHeader("${jwt.header}") String token, @RequestParam("uuid") String messageUuid) throws JsonProcessingException {
        if (token != null && token.startsWith(tokenHead)) {
            final String authToken = token.substring(tokenHead.length());
            String phoneNum = jwtTokenUtil.getUsernameFromToken(authToken);
            shareService.removeInfo(phoneNum, messageUuid);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().body(null);
    }
}