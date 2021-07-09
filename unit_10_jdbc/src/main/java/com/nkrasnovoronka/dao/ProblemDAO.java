package com.nkrasnovoronka.dao;

import com.nkrasnovoronka.model.Problem;

import java.util.List;

public interface ProblemDAO {
    List<Problem> getAll();
    Problem getProblemById(int id);
}
