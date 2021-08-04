package com.nkrasnovoronka.repository.impl;

import com.nkrasnovoronka.model.entity.ExpenseCategory;
import com.nkrasnovoronka.model.entity.IncomeCategory;
import com.nkrasnovoronka.repository.CategoryRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class CategoryRepositoryImpl implements CategoryRepository {

    private final Session session;

    public CategoryRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    public List<IncomeCategory> getIncomeCategories() {
        try {
            Query<IncomeCategory> query = session.createQuery("select c from Category c where type(c) = IncomeCategory");
            List<IncomeCategory> resultList = query.getResultList();
            return resultList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ExpenseCategory> getExpenseCategories() {
        try {
            Query<ExpenseCategory> query = session.createQuery("select c from Category c where type(c) = ExpenseCategory ");
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public IncomeCategory getIncomeCategoryId(Long id) {
        Query<IncomeCategory> query = session.createQuery("select c from Category c where type(c) = IncomeCategory and c.id = :id");
        query.setParameter("id", id);
        return query.getResultStream().findFirst().orElseThrow(RuntimeException::new);
    }

    @Override
    public ExpenseCategory getExpenseCategoryById(Long id) {
        Query<ExpenseCategory> query = session.createQuery("select c from Category c where type(c) = ExpenseCategory and c.id = :id");
        query.setParameter("id", id);
        return query.getResultStream().findFirst().orElseThrow(RuntimeException::new);
    }
}
