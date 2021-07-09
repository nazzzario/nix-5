package com.nkrasnovoronka;

import com.nkrasnovoronka.dao.SolutionDAO;
import com.nkrasnovoronka.dao.impl.LocationDAOImpl;
import com.nkrasnovoronka.dao.impl.SolutionDAOImpl;
import com.nkrasnovoronka.db.DbConnection;
import com.nkrasnovoronka.model.Solution;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        Connection dbConnection = DbConnection.getDbConnection();
        SolutionDAO solutionDAO = new SolutionDAOImpl(dbConnection);
        solutionDAO.addSolution(new Solution(1, 42));

    }
}
