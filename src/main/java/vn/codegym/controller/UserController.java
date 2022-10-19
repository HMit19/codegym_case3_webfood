package vn.codegym.controller;

import vn.codegym.model.User;
import vn.codegym.service.user.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "Servlet3", value = "/users")
public class UserController extends HttpServlet {
	UserService userService;

	public UserController() {
		userService = new UserService();
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
				case "admin":
					doPost(request, response);
					break;
				case "user":
					listUser(request, response);
					break;
				case "delete":
					hideUser(request, response);
					break;
				default:
					listUser(request, response);
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void hideUser(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		User user = userService.findUserById(id);
		user.setActivate(false);
		RequestDispatcher dispatcher = request.getRequestDispatcher("users?action=list");
		System.out.println(user);
		if (userService.updateUser(user)) {
			request.setAttribute("status", "delete-success");
		} else {
			request.setAttribute("status", "delete-fail");
		}
		try {
			dispatcher.forward(request, response);
		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}
	}

	private void viewAdmin(HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/manager.jsp");
		try {
			requestDispatcher.forward(request, response);
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
					createUser(request, response);
					break;
				case "edit":
					saveChangeUser(request, response);
					break;
				case "user":
					listUser(request, response);
					break;
				case "admin":
					viewAdmin(request, response);
					break;
				default:
					listUser(request, response);
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void createUser(HttpServletRequest request, HttpServletResponse response) {
	}

	private void saveChangeUser(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String checkRole = request.getParameter("role");
		boolean role = checkRole.equals("admin");
		String checkActivate = request.getParameter("activate");
		boolean activate = checkActivate.equals("activate");
		User user = new User(id, name, email, password, phone, address, role, activate);
		System.out.println(user);
		RequestDispatcher dispatcher = null;
		if (userService.updateUser(user)) {
			request.setAttribute("status", "edit-success");
			System.out.println("Update user successfully");
			List<User> users = userService.getListUser();
			request.setAttribute("users", users);
			dispatcher = request.getRequestDispatcher("views/user/list.jsp");
		} else {
			request.setAttribute("status", "edit-fail");
			System.out.println("Update user failed");
			user = userService.findUserById(id);
			request.setAttribute("user", user);
			dispatcher = request.getRequestDispatcher("views/user/edit.jsp");
		}
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	private void showFormEdit(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		User user = userService.findUserById(id);
		RequestDispatcher dispatcher = null;
		if (user == null) {
			dispatcher = request.getRequestDispatcher("views/pages/ERROR.jsp");
		} else {
			request.setAttribute("user", user);
			dispatcher = request.getRequestDispatcher("views/user/edit.jsp");
		}
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	private void showFormCreate(HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/user/create.jsp");
		try {
			requestDispatcher.forward(request, response);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void listUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		// lay role cua user dang login
		RequestDispatcher requestDispatcher;
		boolean role = ((User) (request.getSession()).getAttribute("Member")).isRole();
		System.out.println("role = " + role);
		// neu la admin thi lay danh sach user
		if (role) {
			List<User> users = userService.getListUser();
			request.setAttribute("users", users);
			System.out.println(users);
			requestDispatcher = request.getRequestDispatcher("views/user/list.jsp");
		} else {
			requestDispatcher = request.getRequestDispatcher("views/pages/ERROR.jsp");
		}
		try {
			requestDispatcher.forward(request, response);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
