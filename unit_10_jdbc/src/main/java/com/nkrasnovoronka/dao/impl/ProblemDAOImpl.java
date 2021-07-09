package com.nkrasnovoronka.dao.impl;

import com.nkrasnovoronka.dao.ProblemDAO;
import com.nkrasnovoronka.model.Problem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProblemDAOImpl implements ProblemDAO {

    private static final String SELECT_FROM_PROBLEMS = "SELECT * FROM problems";
    private static final String SELECT_FROM_PROBLEMS_BY_ID = "SELECT * FROM problems WHERE id = ?";
    private final Connection connection;

    public ProblemDAOImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public List<Problem> getAll() {
        List<Problem> allProblems = new ArrayList<>();
        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(SELECT_FROM_PROBLEMS);
            while (resultSet.next()) {
                Problem problem = new Problem();
                problem.setId(resultSet.getInt(1));
                problem.setFromId(resultSet.getInt(2));
                problem.setToId(resultSet.getInt(3));
                allProblems.add(problem);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allProblems;
    }

    @Override
    public Problem getProblemById(int id) {
        try (PreparedStatement ps = connection.prepareStatement(SELECT_FROM_PROBLEMS_BY_ID)) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            Problem problem = new Problem();
            problem.setId(resultSet.getInt(1));
            problem.setFromId(resultSet.getInt(2));
            problem.setToId(resultSet.getInt(3));
            return problem;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
