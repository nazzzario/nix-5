package com.nkrasnovoronka.config;

import com.nkrasnovoronka.annotation.PropertyKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Properties;

import static com.nkrasnovoronka.util.PropertyLoader.loadProperties;

public class PropertyKeyConfiguration {
    private static final Logger log = LoggerFactory.getLogger(PropertyKeyConfiguration.class);
    private static final String PROPERTIES = "/app.properties";

    public static void config(Object o) {
        Properties properties = loadProperties(PROPERTIES);
        Class<?> aClass = o.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field f : declaredFields) {
            if (f.isAnnotationPresent(PropertyKey.class)) {
                PropertyKey annotation = f.getAnnotation(PropertyKey.class);
                String property = properties.getProperty(annotation.value());
                try {
                    setFiled(o, f, property);
                } catch (Exception e) {
                    log.error("Cannot set filed {} value", f.getName());
                    throw new RuntimeException();
                }
            }
        }

    }

    private static void setFiled(Object o, Field filed, String value) throws Exception {
        filed.setAccessible(true);
        Class<?> filedType = filed.getType();
        if (Boolean.class == filedType || Boolean.TYPE == filedType) filed.set(o, Boolean.parseBoolean(value));
        else if (Byte.class == filedType || Byte.TYPE == filedType) filed.set(o, Byte.parseByte(value));
        else if (Short.class == filedType || Short.TYPE == filedType) filed.set(o, Short.parseShort(value));
        else if (Integer.class == filedType || Integer.TYPE == filedType) filed.set(o, Integer.parseInt(value));
        else if (Long.class == filedType || Long.TYPE == filedType) filed.set(o, Long.parseLong(value));
        else if (Float.class == filedType || Float.TYPE == filedType) filed.set(o, Float.parseFloat(value));
        else if (Double.class == filedType || Double.TYPE == filedType) filed.set(o, Double.parseDouble(value));
        else if (filedType.isEnum()) filed.set(o, Enum.valueOf((Class<Enum>) filedType, value));
        else if (String.class == filedType) filed.set(o, value);
    }


}
