package com.codegym.controller;

import com.codegym.model.Category;
import com.codegym.model.Product;
import com.codegym.model.User;
import com.codegym.service.category.CategoryService;
import com.codegym.service.product.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "Servlet", value = "/products")
public class ProductController extends HttpServlet {

	private ProductService productService = null;
	CategoryService categoryService = null;

	public void init() {
		productService = new ProductService();
		categoryService = new CategoryService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		String action = request.getParameter("action");
		if (action == null) {
			action = "";
		}
		try {
			switch (action) {
				case "create":
					showCreateForm(request, response);
					break;
				case "edit":
					showEditForm(request, response);
					break;
				case "detail":
					showDetailProduct(request, response);
					break;
				case "delete":
					hideProduct(request, response);
					break;
				case "products":
					listProductOfCategory(request, response);
					break;
				default:
					listFood(request, response);
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void listProductOfCategory(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		RequestDispatcher dispatcher = request.getRequestDispatcher("views/product/list.jsp");
		List<Category> listCategory;
		try {
			List<Product> products = productService.getListFoodByCategory(id);
			request.setAttribute("products", products);
			listCategory = categoryService.getListCategory();
			request.setAttribute("categories", listCategory);
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void hideProduct(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		Product product = productService.findFoodById(id);
		product.setAvailable(false);
		RequestDispatcher dispatcher = request.getRequestDispatcher("products?action=list");
		System.out.println(product);
		if (productService.updateProduct(product)) {
			request.setAttribute("status", "delete-success");
		} else {
			request.setAttribute("status", "delete-fail");
		}
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	private void showDetailProduct(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		Product product = productService.findFoodById(id);
		request.setAttribute("product", product);
		RequestDispatcher dispatcher = request.getRequestDispatcher("store/food-detail.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		RequestDispatcher dispatcher;
		List<Category> listCategory;
		try {
			listCategory = categoryService.getListCategory();
			int id = Integer.parseInt(request.getParameter("id"));
			Product product = productService.findFoodById(id);
			request.setAttribute("product", product);
			request.setAttribute("categories", listCategory);
			dispatcher = request.getRequestDispatcher("views/product/edit.jsp");
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	private void showCreateForm(HttpServletRequest request, HttpServletResponse response) {
		List<Category> listCategory;
		RequestDispatcher dispatcher = request.getRequestDispatcher("views/product/create.jsp");
		try {
			listCategory = categoryService.getListCategory();
			request.setAttribute("categories", listCategory);
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void listFood(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		boolean role = ((User) (request.getSession()).getAttribute("Member")).isRole();
		System.out.println("role = " + role);
		List<Product> listProduct;
		List<Category> listCategory;

		RequestDispatcher dispatcher;
		if (role) {
			listCategory = categoryService.getListCategory();
			listProduct = productService.getListFood();
			request.setAttribute("products", listProduct);
			request.setAttribute("categories", listCategory);
			dispatcher = request.getRequestDispatcher("views/product/list.jsp");
		} else {
			dispatcher = request.getRequestDispatcher("views/pages/ERROR.jsp");
		}
		try {
			dispatcher.forward(request, response);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		String action = request.getParameter("action");
		if (action == null) {
			action = "";
		}
		try {
			switch (action) {
				case "create":
					createFood(request, response);
					break;
				case "edit":
					saveFood(request, response);
					break;
				default:
					listFood(request, response);
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void saveFood(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		long price = Integer.parseInt(request.getParameter("price"));
		String image = request.getParameter("image");
		boolean available = request.getParameter("available").equals("available");
		String description = request.getParameter("description");
		int idCategory = Integer.parseInt(request.getParameter("id_category"));
		RequestDispatcher dispatcher;
		Product product = new Product(id, name, price, image, description, available, idCategory);
		System.out.println(product);
		if (productService.updateProduct(product)) {
			request.setAttribute("status", "edit-success");
			dispatcher = request.getRequestDispatcher("products?action=list");
		} else {
			request.setAttribute("status", "fail");
			request.setAttribute("product", product);
			dispatcher = request.getRequestDispatcher("products?action=edit");
		}
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	private void createFood(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		long price = Long.parseLong(request.getParameter("price"));
		String image = request.getParameter("image");
		boolean available = request.getParameter("available").equals("available");
		String description = request.getParameter("description");
		int idCategory = Integer.parseInt(request.getParameter("id_category"));
		RequestDispatcher dispatcher;
		Product product = new Product(name, price, image, description, available, idCategory);
		if (productService.addProduct(product)) {
			request.setAttribute("status", "create-success");
			System.out.println("add product " + product);
			dispatcher = request.getRequestDispatcher("/products?action=list");
		} else {
			request.setAttribute("status", "fail");
			dispatcher = request.getRequestDispatcher("products?action=create");
		}
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
}
