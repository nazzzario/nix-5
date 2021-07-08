package com.nkrasnovoronka.db;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {
    public static Connection getDbConnection(){
        Properties properties = loadProperties();
        String url = properties.getProperty("url");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, properties);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    private static Properties loadProperties(){
        Properties properties = new Properties();
        try(InputStream input = DbConnection.class.getResourceAsStream("/postgres.properties")){
            properties.load(input);
        }catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return properties;
    }
}
