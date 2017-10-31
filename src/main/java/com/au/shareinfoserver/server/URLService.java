package com.au.shareinfoserver.server;

import com.au.shareinfoserver.utils.JsonUtil;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class URLService {
    public static final String BASE_HOST = "http://10.0.2.2:8080";

    public String getJsonLinks() {
        HashMap links = Maps.newHashMap();
        links.put("_links", getLinks());
        return JsonUtil.asJsonString(links);
    }

    private Map<String, String> getLinks() {
        HashMap links = Maps.newHashMap();
        links.put("postShareLocationInfo", addHrefToLink(assembleLink("traffic/share")));
        links.put("getObtainLocationInfo", addHrefToLink(assembleLink("traffic/obtain")));
        links.put("userLogin", addHrefToLink(assembleLink("/user/login")));
        links.put("userRegister", addHrefToLink(assembleLink("/user/register")));
        links.put("refreshToken", addHrefToLink(assembleLink("/user/refresh")));
        return links;
    }

    public static String assembleLink(String urlPath) {
        return BASE_HOST.concat(urlPath);
    }

    private Map addHrefToLink(String url) {
        Map<String, String> link = Maps.newHashMap();
        link.put("href", url);
        return link;
    }
}
