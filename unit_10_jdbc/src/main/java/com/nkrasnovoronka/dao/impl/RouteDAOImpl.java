package com.nkrasnovoronka.dao.impl;

import com.nkrasnovoronka.dao.RouteDAO;
import com.nkrasnovoronka.model.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RouteDAOImpl implements RouteDAO {
    private static final Logger log = LoggerFactory.getLogger(RouteDAOImpl.class);

    private static final String SELECT_FROM_ROUTES = "SELECT * FROM routes";
    private static final String SELECT_FROM_ROUTES_BY_ID = "SELECT * FROM routes WHERE id = ?";

    private final Connection connection;

    public RouteDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Route> getAll() {
        List<Route> allRouts = new ArrayList<>();
        log.info("Getting all Routes from database");

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_FROM_ROUTES);
            while (resultSet.next()) {
                Route route = new Route();
                route.setId(resultSet.getInt(1));
                route.setFromId(resultSet.getInt(2));
                route.setToId(resultSet.getInt(3));
                route.setCost(resultSet.getInt(4));
                allRouts.add(route);
            }
            log.info("Get {} routes from database", allRouts.size());

        } catch (SQLException throwables) {
            log.error("Database exception");
            throw new RuntimeException(throwables);
        }
        return allRouts;
    }

    @Override
    public Route getRouteById(int id) {
        Route route;
        try (PreparedStatement ps = connection.prepareStatement(SELECT_FROM_ROUTES_BY_ID)) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            route = new Route();
            route.setId(resultSet.getInt(1));
            route.setFromId(resultSet.getInt(2));
            route.setToId(resultSet.getInt(3));
        } catch (SQLException throwables) {
            log.error("Cannot get Route with id {}", id);
            throw new RuntimeException(throwables);
        }
        log.info("Get Route with id {}", id);
        return route;
    }
}
