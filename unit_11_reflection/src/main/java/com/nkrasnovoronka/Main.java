package com.nkrasnovoronka;

import com.nkrasnovoronka.mapper.PropertiesMapper;
import com.nkrasnovoronka.model.AppProperties;
import com.nkrasnovoronka.util.PropertiesLoader;

import java.util.Properties;

public class Main {
    private static final String PROPERTIES = "/app.properties";

    public static void main(String[] args) {
        PropertiesLoader propertiesLoader = new PropertiesLoader();
        Properties properties = propertiesLoader.loadProperties(PROPERTIES);
        PropertiesMapper propertiesMapper = new PropertiesMapper();
        AppProperties config = propertiesMapper.map(AppProperties.class, properties);
        System.out.println(config);
    }
}
