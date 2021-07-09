package com.nkrasnovoronka.dao.impl;

import com.nkrasnovoronka.dao.LocationDAO;
import com.nkrasnovoronka.db.DbConnection;
import com.nkrasnovoronka.model.Location;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocationDAOImpl implements LocationDAO {

    private static final String SELECT_FROM_LOCATIONS = "SELECT * FROM locations";
    private static final String SELECT_FROM_LOCATIONS_BY_ID = "SELECT * FROM locations WHERE id = ?";

    private final Connection connection;

    public LocationDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Location> getAll() {
        List<Location> allConnections = new ArrayList<>();
        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(SELECT_FROM_LOCATIONS);
            while (resultSet.next()) {
                Location location = new Location();
                location.setId(resultSet.getInt(1));
                location.setText(resultSet.getString(2));
                allConnections.add(location);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allConnections;
    }

    @Override
    public Location getLocationById(int id) {
        try (PreparedStatement ps = connection.prepareStatement(SELECT_FROM_LOCATIONS_BY_ID)) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            Location location = new Location();
            location.setId(resultSet.getInt(1));
            location.setText(resultSet.getString(2));
            return location;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
