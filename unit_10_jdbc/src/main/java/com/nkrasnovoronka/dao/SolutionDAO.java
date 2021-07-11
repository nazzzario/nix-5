package com.nkrasnovoronka.dao;

import com.nkrasnovoronka.model.Solution;

import java.util.List;

public interface SolutionDAO {
    void addSolution(Solution solution);

    void addAllSolutions(List<Solution> allSolutions);

    List<Solution> getAll();
}
