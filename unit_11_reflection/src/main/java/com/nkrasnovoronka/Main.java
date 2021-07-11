package com.nkrasnovoronka;

import com.nkrasnovoronka.config.PropertyKeyConfiguration;
import com.nkrasnovoronka.model.AppProperties;

public class Main {
    public static void main(String[] args) {
        AppProperties appProperties = new AppProperties();
        PropertyKeyConfiguration.config(appProperties);
        System.out.println(appProperties);
    }
}
