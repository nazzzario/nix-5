package com.nkrasnovoronka.service;

import com.nkrasnovoronka.dao.LocationDAO;
import com.nkrasnovoronka.dao.ProblemDAO;
import com.nkrasnovoronka.dao.RouteDAO;
import com.nkrasnovoronka.dao.SolutionDAO;
import com.nkrasnovoronka.dao.impl.LocationDAOImpl;
import com.nkrasnovoronka.dao.impl.ProblemDAOImpl;
import com.nkrasnovoronka.dao.impl.RouteDAOImpl;
import com.nkrasnovoronka.dao.impl.SolutionDAOImpl;
import com.nkrasnovoronka.db.DbConnection;
import com.nkrasnovoronka.model.Problem;
import com.nkrasnovoronka.model.Route;
import com.nkrasnovoronka.model.Solution;
import com.nkrasnovoronka.util.ShortestPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShortestPathService {
    private static final Logger log = LoggerFactory.getLogger(ShortestPathService.class);

    public static void run() {
        Connection dbConnection = DbConnection.getDbConnection();

        LocationDAO locationDAO = new LocationDAOImpl(dbConnection);
        SolutionDAO solutionDAO = new SolutionDAOImpl(dbConnection);
        RouteDAO routeDAO = new RouteDAOImpl(dbConnection);
        ProblemDAO problemDAO = new ProblemDAOImpl(dbConnection);

        int numberOfLocations = locationDAO.getAll().size();

        int[][] linkMatrix = createLinkMatrix(routeDAO, numberOfLocations);

        List<Problem> all = problemDAO.getAll();

        List<Solution> solutionList = findSolutions(numberOfLocations, linkMatrix, all);
        solutionDAO.addAllSolutions(solutionList);

        closeConnection(dbConnection);
    }

    private static List<Solution> findSolutions(int numberOfLocations, int[][] linkMatrix, List<Problem> all) {
        List<Solution> solutionList = new ArrayList<>();
        for (Problem p : all) {
            int id = p.getId();
            int res = ShortestPath.findShortestPaths(numberOfLocations, p, linkMatrix);
            solutionList.add(new Solution(id, res));
        }
        return solutionList;
    }

    private static int[][] createLinkMatrix(RouteDAO routeDAO, int numberOfLocations) {
        int[][] linkMatrix = new int[numberOfLocations][numberOfLocations];
        List<Route> routes = routeDAO.getAll();
        for (Route r : routes) {
            int i = r.getFromId();
            int j = r.getToId();
            linkMatrix[i - 1][j - 1] = r.getCost();
        }
        return linkMatrix;
    }

    private static void closeConnection(Connection dbConnection) {
        try {
            dbConnection.close();
            log.info("Connection with database is closed");
        } catch (SQLException throwables) {
            log.error("Database exception");
            throw new RuntimeException();
        }
    }
}
