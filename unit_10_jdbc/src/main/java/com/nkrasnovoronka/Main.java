package com.nkrasnovoronka;

import com.nkrasnovoronka.db.DbConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        Connection dbConnection = DbConnection.getDbConnection();
        try {
            Statement statement = dbConnection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM routes");
            while (resultSet.next()){
                System.out.println(resultSet.getString("name"));
            }
            dbConnection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
