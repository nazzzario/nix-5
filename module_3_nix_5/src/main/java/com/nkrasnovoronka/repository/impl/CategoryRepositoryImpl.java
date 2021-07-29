package com.nkrasnovoronka.repository.impl;

import com.nkrasnovoronka.entity.Category;
import com.nkrasnovoronka.entity.ExpenseCategory;
import com.nkrasnovoronka.entity.IncomeCategory;
import com.nkrasnovoronka.exception.InvalidCategoryException;
import com.nkrasnovoronka.repository.CategoryRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class CategoryRepositoryImpl implements CategoryRepository {

    private Session session;

    public CategoryRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    public List<Category> getCategoryByType(String type) {
        Query<Category> query = session.createQuery("select c from Category c where type(c.class) = :type");
        switch (type) {
            case "income":
                query.setParameter("type", IncomeCategory.class);
                break;
            case "expense":
                query.setParameter("type", ExpenseCategory.class);
                break;
            default:
                throw new InvalidCategoryException("Category with name " + type + " doesn't exists");
        }
        return query.getResultList();
    }
}
