package com.nkrasnovoronka.dao.impl;

import com.nkrasnovoronka.dao.SolutionDAO;
import com.nkrasnovoronka.model.Solution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SolutionDAOImpl implements SolutionDAO {
    private static final Logger log = LoggerFactory.getLogger(SolutionDAOImpl.class);

    private static final String INSERT_INTO_SOLUTIONS = "INSERT INTO solutions (problem_id, cost) VALUES (?, ?)";
    private static final String SELECT_FROM_SOLUTIONS = "SELECT * FROM solutions";
    private final Connection connection;

    public SolutionDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addSolution(Solution solution) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_INTO_SOLUTIONS)) {
            ps.setInt(1, solution.getId());
            ps.setInt(2, solution.getCost());
            ps.executeUpdate();
            log.info("Solution successfully added to database");
        } catch (SQLException throwables) {
            log.error("Database exception");
            throw new RuntimeException(throwables);
        }
    }

    @Override
    public void addAllSolutions(List<Solution> allSolutions) {
        for (Solution s : allSolutions) {
            addSolution(s);
        }
    }

    @Override
    public List<Solution> getAll() {
        List<Solution> allSolutions = new ArrayList<>();
        log.info("Getting all Solutions from database");
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_FROM_SOLUTIONS);
            while (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt(1));
                solution.setCost(resultSet.getInt(2));
                allSolutions.add(solution);
            }
            log.info("Get {} solutions from database", allSolutions.size());
        } catch (SQLException throwables) {
            log.error("Database exception");
            throw new RuntimeException(throwables);
        }
        return allSolutions;
    }
}
