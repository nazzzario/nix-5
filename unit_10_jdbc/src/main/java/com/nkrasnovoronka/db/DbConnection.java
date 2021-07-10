package com.nkrasnovoronka.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {
    private static final Logger log = LoggerFactory.getLogger(DbConnection.class);
    private static final String POSTGRES_PROPERTIES = "/postgres.properties";

    public static Connection getDbConnection() {
        Properties properties = loadProperties();
        String url = properties.getProperty("url");
        Connection connection;
        try {
            connection = DriverManager.getConnection(url, properties);
            log.info("Connected to database successfully");
        } catch (SQLException e) {
            log.error("Cannot create connection with db");
            throw new RuntimeException(e);
        }

        return connection;
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        log.info("Reading properties");
        try (InputStream input = DbConnection.class.getResourceAsStream(POSTGRES_PROPERTIES)) {
            properties.load(input);
        } catch (IOException e) {
            log.error("Cannot read properties file");
            throw new UncheckedIOException(e);
        }
        log.info("Properties file read successfully");
        return properties;
    }
}
