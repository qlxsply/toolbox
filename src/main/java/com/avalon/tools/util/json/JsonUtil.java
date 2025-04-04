package com.avalon.tools.util.json;

import com.avalon.tools.common.exception.BaseError;
import com.avalon.tools.common.exception.SysException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;

/**
 * author: avalon
 * date: 2025-03-16
 */
public class JsonUtil {

    private static final ObjectMapper BASE_OBJ_MAPPER;

    static {
        BASE_OBJ_MAPPER = JsonMapper.builder()
                                    // 对象属性按照字典序进行序列化，无法对map类型生效
                                    .enable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY)
                                    // 按照 Map 的键的字典序对键值对进行排序
                                    .enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS)
                                    // 输出 JSON 时进行缩进
                                    // .disable(SerializationFeature.INDENT_OUTPUT)
                                    // 反序列化JSON时忽略未知属性，不抛出异常
                                    .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                                    // 注册Java Time模块
                                    .addModule(new AvalonJavaTimeModule()).build();
    }

    public static ObjectMapper getMapper() {
        return BASE_OBJ_MAPPER;
    }

    public static String toStr(Object obj) {
        ObjectMapper mapper = getMapper();
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new SysException(e, BaseError.ERROR);
        }
    }

    public static byte[] toBytes(Object obj) {
        ObjectMapper mapper = getMapper();
        try {
            return mapper.writeValueAsBytes(obj);
        } catch (JsonProcessingException e) {
            throw new SysException(e, BaseError.ERROR);
        }
    }

    public static <T> T parse(String json, Class<T> valueType) {
        ObjectMapper mapper = getMapper();
        try {
            return mapper.readValue(json, valueType);
        } catch (JsonProcessingException e) {
            throw new SysException(e, BaseError.ERROR);
        }
    }

    public static <T> T parse(String json, TypeReference<T> valueTypeRef) {
        ObjectMapper mapper = getMapper();
        try {
            return mapper.readValue(json, valueTypeRef);
        } catch (JsonProcessingException e) {
            throw new SysException(e, BaseError.ERROR);
        }
    }

    public static <T> T convert(Object obj, TypeReference<T> valueTypeRef) {
        ObjectMapper mapper = getMapper();
        try {
            return mapper.convertValue(obj, valueTypeRef);
        } catch (IllegalArgumentException e) {
            throw new SysException(e, BaseError.ERROR);
        }
    }

}
