package com.nkrasnovoronka.dao;

import com.nkrasnovoronka.model.Route;

import java.util.List;

public interface RouteDAO {
    List<Route> getAll();

    Route getRouteById(int id);
}
