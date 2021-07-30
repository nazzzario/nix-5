package com.nkrasnovoronka.repository;

import com.nkrasnovoronka.model.entity.ExpenseCategory;
import com.nkrasnovoronka.model.entity.IncomeCategory;

import java.util.List;

public interface CategoryRepository {
    List<IncomeCategory> getIncomeCategories();

    List<ExpenseCategory> getExpenseCategories();

    IncomeCategory getIncomeCategoryId(Long id);

    ExpenseCategory getExpenseCategoryById(Long id);
}
