package com.codegym.controller;

import com.codegym.model.Category;
import com.codegym.model.User;
import com.codegym.service.category.CategoryService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "Servlet2", value = "/categories")
public class CategoryController extends HttpServlet {
    private CategoryService categoryService = null;

    public void init() {
        categoryService = new CategoryService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    showFormCreate(request, response);
                    break;
                case "edit":
                    showFormEdit(request, response);
                    break;
                case "delete":
//                    deleteCategory(request, response);
                    hideCategory(request, response);
                    break;
                default:
                    listCategory(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void hideCategory(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Category category = categoryService.findCategoryById(id);
        category.setExist(false);
        RequestDispatcher dispatcher;
        try {
            if (categoryService.updateCategory(category)) {

                request.setAttribute("status", "delete-success");
            }
            dispatcher = request.getRequestDispatcher("categories?action=list");
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    // khong the xoa vi foods co rang buoc boi categories
    private void deleteCategory(HttpServletRequest request, HttpServletResponse response) {
//        RequestDispatcher dispatcher = null;
//        List<Category> listCategory;
//        try {
//            listCategory = categoryService.getListCategory();
//            int id = Integer.parseInt(request.getParameter("id"));
//            for (Category i : listCategory) {
//                if (i.getId_category() == id) {
//                    request.setAttribute("status", categoryService.removeCategory(id) ? "success\n" : "fail\n");
//                    request.setAttribute("categories", listCategory);
//                    dispatcher.forward(request, response);
//                }
//            }
//            request.setAttribute("status", "fail\n");
//        } catch (ServletException | IOException | SQLException e) {
//            throw new RuntimeException(e);
//        }
    }

    private void showFormEdit(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/category/edit.jsp");
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Category category = categoryService.findCategoryById(id);
            request.setAttribute("category", category);
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showFormCreate(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/category/create.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void listCategory(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        RequestDispatcher requestDispatcher;
        boolean role = ((User) (request.getSession()).getAttribute("Member")).isRole();
        System.out.println("role = " + role);
        if (role) {
            List<Category> listCategory = categoryService.getListCategory();
            request.setAttribute("categories", listCategory);
            requestDispatcher = request.getRequestDispatcher("views/category/list.jsp");
        } else {
            requestDispatcher = request.getRequestDispatcher("views/pages/ERROR.jsp");
        }
        try {
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    createCategory(request, response);
                    break;
                case "edit":
                    saveCategory(request, response);
                    break;
                default:
                    listCategory(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveCategory(HttpServletRequest request, HttpServletResponse response) throws
            SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String checkExist = request.getParameter("exist");
        boolean exist = checkExist.equals("exist");
        RequestDispatcher dispatcher;
        Category category = new Category(id, name, exist);

        if (categoryService.updateCategory(category)) {
            System.out.println("\nUpdate category into table categories");
            System.out.println(category.toString());
            List<Category> listCategory = categoryService.getListCategory();
            request.setAttribute("categories", listCategory);
            request.setAttribute("status", "edit-success");
            dispatcher = request.getRequestDispatcher("views/category/list.jsp");
        } else {
            category = categoryService.findCategoryById(id);
            request.setAttribute("category", category);
            request.setAttribute("status", "fail");
            dispatcher = request.getRequestDispatcher("views/category/edit.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createCategory(HttpServletRequest request, HttpServletResponse response) throws
            SQLException, ServletException, IOException {
        String name = request.getParameter("name");
        RequestDispatcher dispatcher;
        Category category = new Category(name);
        if (categoryService.addCategory(category)) {
            request.setAttribute("status", "create-success");
            dispatcher = request.getRequestDispatcher("categories?action=list");
        }else{
            request.setAttribute("status", "fail");
            dispatcher = request.getRequestDispatcher("views/category/create.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
