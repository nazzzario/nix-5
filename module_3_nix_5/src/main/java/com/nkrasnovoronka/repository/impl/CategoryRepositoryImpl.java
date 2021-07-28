package com.nkrasnovoronka.repository.impl;

import com.nkrasnovoronka.entity.Category;
import com.nkrasnovoronka.repository.CategoryRepository;
import org.hibernate.Session;

import java.util.List;

public class CategoryRepositoryImpl implements CategoryRepository {

    private Session session;

    public CategoryRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    public List<Category> getCategoryByType(String type) {
        return null;
    }
}
