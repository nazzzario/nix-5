package com.nkrasnovoronka.dao.impl;

import com.nkrasnovoronka.dao.LocationDAO;
import com.nkrasnovoronka.model.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocationDAOImpl implements LocationDAO {

    private static final Logger log = LoggerFactory.getLogger(LocationDAOImpl.class);

    private static final String SELECT_FROM_LOCATIONS = "SELECT * FROM locations";
    private static final String SELECT_FROM_LOCATIONS_BY_ID = "SELECT * FROM locations WHERE id = ?";

    private final Connection connection;

    public LocationDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Location> getAll() {
        List<Location> allConnections = new ArrayList<>();
        log.info("Getting all Locations from database");
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_FROM_LOCATIONS);
            while (resultSet.next()) {
                Location location = new Location();
                location.setId(resultSet.getInt(1));
                location.setText(resultSet.getString(2));
                allConnections.add(location);
            }
            log.info("Get {} cites from database", allConnections.size());
        } catch (SQLException throwables) {
            log.error("Database exception");
            throw new RuntimeException(throwables);
        }
        return allConnections;
    }

    @Override
    public Location getLocationById(int id) {
        log.info("Getting Location by id {}", id);
        Location location;
        try (PreparedStatement ps = connection.prepareStatement(SELECT_FROM_LOCATIONS_BY_ID)) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            location = new Location();
            location.setId(resultSet.getInt(1));
            location.setText(resultSet.getString(2));
        } catch (SQLException throwables) {
            log.error("Cannot get Location with id {}", id);
            throw new RuntimeException(throwables);
        }
        log.info("Get Location with id {}", id);
        return location;
    }
}
