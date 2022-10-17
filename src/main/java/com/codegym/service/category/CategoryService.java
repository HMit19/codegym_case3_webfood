package com.codegym.service.category;

import com.codegym.model.Category;
import com.codegym.model.Product;
import com.codegym.service.DatabaseHandle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryService implements ICategoryService {
    DatabaseHandle databaseHandle = null;

    public CategoryService() {
        databaseHandle = new DatabaseHandle();
    }

    @Override
    public boolean addCategory(Category category) {
        boolean check = false;
        try {
            check = databaseHandle.addRecord("category", "name_category", "'" + category.getName_category() + "'");
            System.out.println("Add category status: " + check);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    @Override
    public boolean removeCategory(int id) {
        boolean check = false;
        try {
            check = databaseHandle.deleteRecord("category", "id_category = '" + id + "'");
            System.out.println("Delete category status: " + check);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    @Override
    public boolean updateCategory(Category category) {
        boolean check = false;
        try {
            check = databaseHandle.updateRecord("category", "name_category = '" + category.getName_category()
                            + "', exist = " + category.isExist() + "", "id_category = '" + category.getId_category() + "'");
            System.out.println("Update category status: " + check);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    @Override
    public Category findCategoryById(int Id) {
        Category category = null;
        try {
            ResultSet result = databaseHandle.getRecords("*", "category", "id_category = '" + Id + "'");
            while (result.next()) {
                int id = Integer.valueOf(result.getString("id_category"));
                String name = result.getString("name_category");
                boolean exist = Integer.valueOf(result.getString("exist")) > 0;
                System.out.println(id + " " + name + " " + exist);
                category = new Category(id, name, exist);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return category;
    }

    @Override
    public Category findCategoryByName(String name) {
        return null;
    }

    @Override
    public List<Category> getListCategory() throws SQLException {
        List<Category> listCategories = new ArrayList<Category>();
        try {
            ResultSet result = databaseHandle.getRecords("*", "category", "");
            while (result.next()) {
                int id = Integer.valueOf(result.getString("id_category"));
                String name = result.getString("name_category");
                boolean exist = Integer.valueOf(result.getString("exist")) > 0;
                List<Product> listProducts = new ArrayList<Product>();
                System.out.println(id + " " + name + " " + exist);
                listCategories.add(new Category(id, name, exist, listProducts));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listCategories;
    }

    //test
    public static void main(String[] args) throws SQLException {
//        test add category
//        Category category = new Category("Category 1");
//        System.out.println(new CategoryService().addCategory(category));
//        List<Category> l = new CategoryService().getListCategory();
//        for (Category c : l) {
//            System.out.println(c);
//        }
//        test update category
        Category category = new Category(1, "Category 1", true);
        System.out.println(new CategoryService().updateCategory(category));
    }
}
