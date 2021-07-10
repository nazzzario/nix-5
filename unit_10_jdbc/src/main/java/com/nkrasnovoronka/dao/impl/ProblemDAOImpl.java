package com.nkrasnovoronka.dao.impl;

import com.nkrasnovoronka.dao.ProblemDAO;
import com.nkrasnovoronka.model.Problem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProblemDAOImpl implements ProblemDAO {
    private static final Logger log = LoggerFactory.getLogger(ProblemDAOImpl.class);
    private static final String SELECT_FROM_PROBLEMS = "SELECT * FROM problems";
    private static final String SELECT_FROM_PROBLEMS_BY_ID = "SELECT * FROM problems WHERE id = ?";

    private final Connection connection;

    public ProblemDAOImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public List<Problem> getAll() {
        List<Problem> allProblems = new ArrayList<>();
        log.info("Getting all problems from database");
        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(SELECT_FROM_PROBLEMS);
            while (resultSet.next()) {
                Problem problem = new Problem();
                problem.setId(resultSet.getInt(1));
                problem.setFromId(resultSet.getInt(2));
                problem.setToId(resultSet.getInt(3));
                allProblems.add(problem);
            }
            log.info("Get {} problems from database", allProblems.size());
        } catch (SQLException throwables) {
            log.error("Database exception");
            throw new RuntimeException(throwables);
        }
        return allProblems;
    }

    @Override
    public Problem getProblemById(int id) {
        Problem problem;
        log.info("Getting Problem by id {}", id);
        try (PreparedStatement ps = connection.prepareStatement(SELECT_FROM_PROBLEMS_BY_ID)) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            problem = new Problem();
            problem.setId(resultSet.getInt(1));
            problem.setFromId(resultSet.getInt(2));
            problem.setToId(resultSet.getInt(3));
        } catch (SQLException throwables) {
            log.error("Cannot get Problem with id {}", id);
            throw new RuntimeException(throwables);
        }
        return problem;
    }
}
