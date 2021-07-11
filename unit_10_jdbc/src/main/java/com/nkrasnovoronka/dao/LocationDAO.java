package com.nkrasnovoronka.dao;

import com.nkrasnovoronka.model.Location;

import java.util.List;

public interface LocationDAO {

    List<Location> getAll();

    Location getLocationById(int id);

}
