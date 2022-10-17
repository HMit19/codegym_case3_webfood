package com.codegym.service.category;

import com.codegym.model.Category;

import java.sql.SQLException;
import java.util.List;

public interface ICategoryService {
    public boolean removeCategory(int id);

    public boolean updateCategory(Category category);

    boolean addCategory(Category category);

    public Category findCategoryById(int id);

    public Category findCategoryByName(String name);

    public List<Category> getListCategory() throws SQLException;
}
