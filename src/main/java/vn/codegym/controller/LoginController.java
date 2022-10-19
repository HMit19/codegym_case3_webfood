package vn.codegym.controller;

import vn.codegym.model.Item;
import vn.codegym.model.User;
import vn.codegym.service.user.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "login-signup", value = "/login-signup")
public class LoginController extends HttpServlet {
	UserService userService = null;

	public void init() {
		userService = new UserService();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String action = request.getParameter("action");
		if (action == null) {
			action = "";
		}
		switch (action) {
			case "signup":
				showFormSignup(request, response);
				break;
			case "logout":
				logoutUser(request, response);
				break;
			default:
				showFormLogin(request, response);
				break;
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action == null) {
			action = "";
		}
		switch (action) {
			case "signup":
				try {
					createAccount(request, response);
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
				break;
			case "login":
				try {
					loginAccount(request, response);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			default:
				error(response);
				break;
		}
	}

	private void error(HttpServletResponse response) throws IOException {
		response.sendRedirect("views/pages/ERROR.jsp");
	}

	private void createAccount(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		boolean checkExist = false;
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmPass = request.getParameter("re-password");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/login-form/signup.jsp");
		for (User user : userService.getListUser()) {
			if (user.getEmail().equals(email)) {
				checkExist = true;
				break;
			}
		}
		if (!password.equals(confirmPass) || checkExist) {
			request.setAttribute("status", "false");
		} else {
			request.setAttribute("status", "true");
			User user = new User(username, email, password, phone, address);
			userService.addUser(user);
			System.out.println(userService.addUser(user));
		}
		try {
			dispatcher.forward(request, response);
		} catch (Exception e) {
			showFormSignup(request, response);
			throw new RuntimeException(e);
		}
	}

	private void logoutUser(HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login-signup?action=signin");
		try {
			HttpSession session = request.getSession();
			session.removeAttribute("Member");
			System.out.println("Logout success");
			requestDispatcher.forward(request, response);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void loginAccount(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		// bien nay de kiem tra xem user da ton tai gio hang chua
		List<User> users;
		boolean checkOrder = false;
		RequestDispatcher dispatcher;
		String email = request.getParameter("_email");
		String password = request.getParameter("_password");
		HttpSession session = request.getSession();

		if ((session.getAttribute("order")) != null) {
			checkOrder = true;
			System.out.println("Order exist!");
		} else {
			Map<Integer, List<Item>> order = new HashMap<>();
			System.out.println("Created order for users");
		}
		try {
			// check xem co phai la tai khoan admin khong
			users = userService.getListUser("role", "1");
			for (User user : users) {
				if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
					dispatcher = request.getRequestDispatcher("/users?action=admin");
					if (!checkOrder) {
						session.setAttribute("order", session.getAttribute("order"));
						Map<Integer, List<Item>> order = new HashMap<>();
						order.put(user.getId(), new ArrayList<>());
					}
					session.setAttribute("Member", user);
					dispatcher.forward(request, response);
					return;
				}
			}

			// check xem co tai khoan co ton tai khong
			users = userService.getListUser("role", "0");
			for (User user : users) {
				System.out.println(user.getEmail() + " " + user.getPassword());
				if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
					dispatcher = request.getRequestDispatcher("/stores");
					if (!checkOrder) {
						session.setAttribute("order", session.getAttribute("order"));
						Map<Integer, List<Item>> order = new HashMap<>();
						session.setAttribute("order", order);
						order.put(user.getId(), new ArrayList<>());
						System.out.println("Created order for users");
					}
					boolean checkUser = true;
					for (Map.Entry<Integer, List<Item>> entry : ((Map<Integer, List<Item>>) session.getAttribute("order")).entrySet()) {
						if (entry.getKey() == user.getId()) {
							checkUser = false;
							System.out.println("User already order!");
							break;
						}
					}
					if (checkUser) {
						Map<Integer, List<Item>> order = (Map<Integer, List<Item>>) session.getAttribute("order");
						System.out.println("add order new user");
						order.put(user.getId(), new ArrayList<>());
						session.setAttribute("order", order);

					}
					session.setAttribute("Member", user);
					dispatcher.forward(request, response);
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Login failed");
		request.setAttribute("status", "false");
		dispatcher = request.getRequestDispatcher("/login-form/signin.jsp");
		dispatcher.forward(request, response);
	}

	private void showFormSignup(HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/login-form/signup.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void showFormLogin(HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/login-form/signin.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void destroy() {
	}
}