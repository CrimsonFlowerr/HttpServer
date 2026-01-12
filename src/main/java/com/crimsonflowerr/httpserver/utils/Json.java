package com.crimsonflowerr.httpserver.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.*;

public class Json {
    private static final ObjectMapper myObjectMapper = defaultObjectMapper();

    private static ObjectMapper defaultObjectMapper() {
        ObjectMapper defaultObjectMapper = new ObjectMapper();
        defaultObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return defaultObjectMapper;
    }

    public static JsonNode parse(String src) throws JsonProcessingException {
        return myObjectMapper.readTree(src);
    }

    public static <A> A fromJson(JsonNode node, Class<A> c) throws JsonProcessingException {
        return myObjectMapper.treeToValue(node, c);
    }

    public static JsonNode toJson(Object obj){
        return myObjectMapper.valueToTree(obj);
    }
    private static String generateJson(Object obj, boolean pretty) throws JsonProcessingException {
        ObjectWriter myObjectWriter = myObjectMapper.writer();
        if(pretty) {
            myObjectWriter = myObjectWriter.with(SerializationFeature.INDENT_OUTPUT);
        }
        return myObjectWriter.writeValueAsString(obj);
    }

    public String stringify(JsonNode node) throws JsonProcessingException {
        return generateJson(node, false);
    }

    public String stringifyPretty(JsonNode node) throws JsonProcessingException {
        return generateJson(node, true);
    }
}
