package com.au.shareinfoserver.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonUtil {
    private final static ObjectMapper MAPPER;
    private static Gson gson = new GsonBuilder().create();

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

    public static String asJsonString(List<Object> result) {
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

    public static <T> T parseJson(String jsonString, Class<T> type) {
        return gson.fromJson(jsonString, type);
    }

    public static <T> T parseJson(String jsonString, Type type) {
        return gson.fromJson(jsonString, type);
    }

    public static String toJson(Object object) {
        return gson.toJson(object);
    }


}
