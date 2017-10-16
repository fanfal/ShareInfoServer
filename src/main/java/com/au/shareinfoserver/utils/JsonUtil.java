package com.au.shareinfoserver.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonUtil {
    private final static ObjectMapper MAPPER;

    static {
        MAPPER = new ObjectMapper();
        MAPPER.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
    }

    public static String asJsonString(Map<String, Object> result) {
        try {
            return MAPPER.writeValueAsString(result).replace("\u2028", "").replace("\u2029", "");
        } catch (IOException e) {
            throw new RuntimeException("Transfer json error");
        }
    }

    public static Map<String, Object> toJsonMap(String jsonString) {
        try {
            return MAPPER.readValue(jsonString, new TypeReference<HashMap<String, Object>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException("Convert json error" + jsonString);
        }
    }

}
