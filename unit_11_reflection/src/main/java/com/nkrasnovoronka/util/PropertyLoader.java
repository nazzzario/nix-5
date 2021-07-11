package com.nkrasnovoronka.util;

import com.nkrasnovoronka.config.PropertyKeyConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Properties;

public class PropertyLoader {
    private static final Logger log = LoggerFactory.getLogger(PropertyLoader.class);

    public static Properties loadProperties(String propertiesUrl) {
        Properties properties = new Properties();
        log.info("Reading properties");
        try (InputStream input = PropertyKeyConfiguration.class.getResourceAsStream(propertiesUrl)) {
            properties.load(input);
        } catch (IOException e) {
            log.error("Cannot read properties file");
            throw new UncheckedIOException(e);
        }
        log.info("Properties file read successfully");
        return properties;
    }

}
