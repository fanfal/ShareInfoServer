package com.au.shareinfoserver.server;

import com.au.shareinfoserver.utils.JsonUtil;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class URLService {
    public static final String BASE_HOST = "https://share-future.herokuapp.com/";

    public String getJsonLinks() {
        HashMap links = Maps.newHashMap();
        links.put("_links", getLinks());
        return JsonUtil.asJsonString(links);
    }

    private Map<String, String> getLinks() {
        HashMap links = Maps.newHashMap();
        links.put("post_share_location_info", addHrefToLink(assembleLink("traffic/share")));
        links.put("get_obtain_location_info", addHrefToLink(assembleLink("traffic/obtain")));
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
