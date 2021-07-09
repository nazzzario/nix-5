package com.nkrasnovoronka.dao.impl;

import com.nkrasnovoronka.dao.RouteDAO;
import com.nkrasnovoronka.model.Route;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RouteDAOImpl implements RouteDAO {

    private static final String SELECT_FROM_ROUTES = "SELECT * FROM routes";
    private static final String SELECT_FROM_ROUTES_BY_ID = "SELECT * FROM routes WHERE id = ?";

    private final Connection connection;

    public RouteDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Route> getAll() {
        List<Route> allRouts = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_FROM_ROUTES);
            while (resultSet.next()) {
                Route route = new Route();
                route.setId(resultSet.getInt(1));
                route.setFromId(resultSet.getInt(2));
                route.setToId(resultSet.getInt(3));
                allRouts.add(route);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allRouts;
    }

    @Override
    public Route getRouteById(int id) {
        try (PreparedStatement ps = connection.prepareStatement(SELECT_FROM_ROUTES_BY_ID)) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            Route route = new Route();
            route.setId(resultSet.getInt(1));
            route.setFromId(resultSet.getInt(2));
            route.setToId(resultSet.getInt(3));
            return route;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
