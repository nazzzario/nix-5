package com.nkrasnovoronka.repository;

import com.nkrasnovoronka.entity.Category;

import java.util.List;

public interface CategoryRepository {
    List<Category> getCategoryByType(String type);
}
