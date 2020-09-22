package org.agm.sbtest.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonUtils {

    private static ObjectMapper mapper = new ObjectMapper();

    public static String toJsonObject(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            mapper.writeValue(out, obj);
            byte[] data = out.toByteArray();
            return new String(data);
        } catch (IOException e) {
            log.warn("Exception transforming object into object: " + e.getMessage());
            return "{}";
        }
    }

    public static <T> T toObject(String json, TypeReference<T> returnType) {
        try {
            return mapper.readValue(json, returnType);
        } catch (JsonProcessingException | IllegalArgumentException e) {
            return null;
        }
    }
}
