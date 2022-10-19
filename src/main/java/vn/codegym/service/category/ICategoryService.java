package vn.codegym.service.category;

import vn.codegym.model.Category;

import java.sql.SQLException;
import java.util.List;

public interface ICategoryService {
	boolean removeCategory(int id);

    boolean updateCategory(Category category);

    boolean addCategory(Category category);

    Category findCategoryById(int id);

    Category findCategoryByName(String name);

    List<Category> getListCategory() throws SQLException;
}
