package com.nkrasnovoronka;

import com.nkrasnovoronka.config.PropertyKeyConfiguration;
import com.nkrasnovoronka.model.AppProperties;

public class Main {
    private static final String PROPERTIES = "/app.properties";

    public static void main(String[] args) {
        PropertyKeyConfiguration propertyKeyConfiguration = new PropertyKeyConfiguration();
        AppProperties config = propertyKeyConfiguration.config(AppProperties.class, PROPERTIES);
        System.out.println(config);
    }
}
