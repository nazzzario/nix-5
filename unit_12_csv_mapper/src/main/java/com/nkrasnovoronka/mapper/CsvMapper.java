package com.nkrasnovoronka.mapper;

import com.nkrasnovoronka.parser.CSVData;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CsvMapper {

    public <T> List<T> createObjectFromCsv(Class<T> tClass, CSVData csvData) {
        List<T> objects = new ArrayList<>();
        try {
            Constructor<T> constructor = tClass.getConstructor();
            T inst;
            for (int i = 0; i < csvData.getRows(); i++) {
                inst = constructor.newInstance();
                for (Field f : tClass.getDeclaredFields()) {
                    if (f.isAnnotationPresent(com.nkrasnovoronka.annotation.CsvMapper.class)) {
                        com.nkrasnovoronka.annotation.CsvMapper annotation = f.getAnnotation(com.nkrasnovoronka.annotation.CsvMapper.class);
                        String cell = csvData.getCell(i, annotation.value());
                        setFiled(inst, f, cell);
                    }
                }
                objects.add(inst);
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return objects;
    }

    private void setFiled(Object o, Field filed, String value) throws Exception {
        filed.setAccessible(true);
        Class<?> filedType = filed.getType();
        if (Boolean.class == filedType || Boolean.TYPE == filedType) {
            filed.set(o, Boolean.parseBoolean(value));
        } else if (Byte.class == filedType || Byte.TYPE == filedType) {
            filed.set(o, Byte.parseByte(value));
        } else if (Short.class == filedType || Short.TYPE == filedType) {
            filed.set(o, Short.parseShort(value));
        } else if (Integer.class == filedType || Integer.TYPE == filedType) {
            filed.set(o, Integer.parseInt(value));
        } else if (Long.class == filedType || Long.TYPE == filedType) {
            filed.set(o, Long.parseLong(value));
        } else if (Float.class == filedType || Float.TYPE == filedType) {
            filed.set(o, Float.parseFloat(value));
        } else if (Double.class == filedType || Double.TYPE == filedType) {
            filed.set(o, Double.parseDouble(value));
        } else if (filedType.isEnum()) {
            filed.set(o, Enum.valueOf((Class<Enum>) filedType, value));
        } else if (String.class == filedType) {
            filed.set(o, value);
        }
    }
}
