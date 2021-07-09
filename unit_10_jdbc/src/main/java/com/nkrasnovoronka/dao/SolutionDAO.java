package com.nkrasnovoronka.dao;

import com.nkrasnovoronka.model.Solution;

import java.util.List;

public interface SolutionDAO {
    void addSolution(Solution solution);

    List<Solution> getAll();
}
