package vn.codegym.controller;

import vn.codegym.database.dao.product.ProductService;
import vn.codegym.database.model.Item;
import vn.codegym.database.model.Product;
import vn.codegym.database.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@WebServlet(name = "Servlet5", urlPatterns = "/carts")
public class AddItemToCartController extends HttpServlet {
	ProductService productService = null;

	public void init() {
		productService = new ProductService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		String action = request.getParameter("action");
		if (action == null) {
			action = "";
		}
		switch (action) {
			case "add":
				addItemToCart(request, response);
				break;
			case "remove":
				removeItemFromCart(request, response);
				break;
//            case "update":
//                updateItemInCart(request, response);
//                break;
			case "view":
				viewCart(request, response);
				break;
			default:
				viewCart(request, response);
				break;
		}
	}

	private void removeItemFromCart(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		Map<Integer, List<Item>> order = (Map<Integer, List<Item>>) request.getSession().getAttribute("order");
		User user = (User) request.getSession().getAttribute("Member");
		for (int i = 0; i < (order.get(user.getId())).size(); i++) {
			if ((order.get(user.getId())).get(i).getProduct().getId_product() == id) {
				(order.get(user.getId())).remove(i);
			}
		}
		session.setAttribute("order", order);
		if (order.get(user.getId()).size() == 0) {
			request.setAttribute("status", "empty");
		}
		try {
			response.sendRedirect("/carts?action=view");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void viewCart(HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher dispatcher;
		try {
			Map<Integer, List<Item>> order = (Map<Integer, List<Item>>) request.getSession().getAttribute("order");
			User user = (User) request.getSession().getAttribute("Member");
			request.setAttribute("items", order.get(user.getId()));
			dispatcher = request.getRequestDispatcher("store/cart.jsp");
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
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
			if ("addToCart".equals(action)) {
				addItemToCart(request, response);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void addItemToCart(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		long price = Integer.parseInt(request.getParameter("price"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		Item item = new Item(id, quantity, price);
		request.setAttribute("item", item);
		Product product = productService.findFoodById(id);
		item.setProduct(product);
		item.setPrice(price);
		Map<Integer, List<Item>> order = (Map<Integer, List<Item>>) request.getSession().getAttribute("order");
		User user = (User) request.getSession().getAttribute("Member");
		if (order.get(user.getId()).isEmpty()) {
			order.get(user.getId()).add(item);
			System.out.println("Add item into orders!");
		} else {
			boolean check = true;
			for (Item i : order.get(user.getId())) {
				if (i.getId_product() == product.getId_product()) {
					System.out.println("Product is already in cart!");
					i.setQuantity(i.getQuantity() + quantity);
					check = false;
					break;
				}
			}
			if (check) {
				order.get(user.getId()).add(item);
				System.out.println("Add item into orders!");
			}
		}
		request.getSession().setAttribute("order", order);
		request.setAttribute("items", order.get(user.getId()));
		RequestDispatcher dispatcher = request.getRequestDispatcher("store/cart.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
}
