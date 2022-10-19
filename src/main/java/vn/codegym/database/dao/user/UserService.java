package vn.codegym.database.dao.user;

import vn.codegym.database.dao.DatabaseHandle;
import vn.codegym.database.model.Bill;
import vn.codegym.database.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserService {
	DatabaseHandle databaseHandle = null;

	public UserService() {
		databaseHandle = new DatabaseHandle();
	}

	@Override
	public boolean addUser(User user) {
		boolean checkAdd = false;
		try {
			checkAdd = databaseHandle.addRecord("user", "name, email, password, phone, address",
					"'" + user.getName() + "', '" + user.getEmail() + "', '" + user.getPassword() + "', '" + user.getPhone() + "', '" + user.getAddress() + "'");
			System.out.println("Add user status: " + checkAdd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return checkAdd;
	}

	@Override
	public boolean removeUser(int id) {
		boolean checkDelete = false;
		try {
			checkDelete = databaseHandle.deleteRecord("user", "id = " + id);
			System.out.println("Delete user status: " + checkDelete);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return checkDelete;
	}

	// update trang thai
	@Override
	public boolean updateUser(int id, String activate) {
		boolean checkUpdate = false;
		try {
			checkUpdate = databaseHandle.updateRecord("user", "activate = " + activate, "id = " + id);
			System.out.println("Update user status: " + checkUpdate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return checkUpdate;
	}

	@Override
	public boolean setRoleUser(int id, String role) {
		boolean checkUpdate = false;
		try {
			checkUpdate = databaseHandle.updateRecord("user", "role = " + role, "id = " + id);
			System.out.println("Update user status: " + checkUpdate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return checkUpdate;
	}

	@Override
	public boolean updateUser(User user) {
		boolean checkUpdate = false;
		try {
			checkUpdate = databaseHandle.updateRecord("user", "name = '" + user.getName()
					+ "', email = '" + user.getEmail() + "', password = '" + user.getPassword() + "', phone = '"
					+ user.getPhone() + "', address = '" + user.getAddress() + "', role = " + user.isRole() + ", activate = "
					+ user.isActivate(), "id = " + user.getId());
			System.out.println("Update user status: " + checkUpdate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return checkUpdate;
	}


	@Override
	public User findUserById(int id) {
		User user = null;
		try {
			ResultSet result = databaseHandle.getRecords("*", "user", "id = " + id);
			while (result.next()) {
				int idUser = result.getInt("id");
				String name = result.getString("name");
				String email = result.getString("email");
				String password = result.getString("password");
				String phone = result.getString("phone");
				user = getUser(phone, user, result, idUser, name, email, password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public User findUserByPhone(String phone) {
		User user = null;
		try {
			ResultSet result = databaseHandle.getRecords("*", "user", "phone = '" + phone + "'");
			while (result.next()) {
				int idUser = result.getInt("id");
				String name = result.getString("name");
				String email = result.getString("email");
				String password = result.getString("password");
				user = getUser(phone, user, result, idUser, name, email, password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	// tranh lap code
	private User getUser(String phone, User user, ResultSet result, int idUser, String name, String email, String password) throws SQLException {
		String address = result.getString("address");
		boolean role = Integer.valueOf(result.getString("role")) > 0;
		boolean activate = Integer.valueOf(result.getString("activate")) > 0;
		user = new User(idUser, name, email, password, phone, address, role, activate);
		return user;
	}

	@Override
	public User findUserByName(String name) {
		return null;
	}

	@Override
	public User findUserByEmail(String email) {
		User user = null;
		try {
			ResultSet result = databaseHandle.getRecords("*", "user", "email = '" + email + "'");
			while (result.next()) {
				int idUser = result.getInt("id");
				String name = result.getString("name");
				String phone = result.getString("phone");
				String password = result.getString("password");
				user = getUser(phone, user, result, idUser, name, email, password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public List<User> getListUser(String columns, String condition) throws SQLException {
		List<User> listUser = null;
		try {
			listUser = new ArrayList<>();
			ResultSet result = databaseHandle.getRecords("*", "user", columns + "= " + condition);
			while (result.next()) {
				int idUser = result.getInt("id");
				String name = result.getString("name");
				String password = result.getString("password");
				String email = result.getString("email");
				String phone = result.getString("phone");
				String address = result.getString("address");
				boolean role = Integer.valueOf(result.getString("role")) > 0;
				boolean activate = Integer.valueOf(result.getString("activate")) > 0;
				listUser.add(new User(idUser, name, email, password, phone, address, role, activate));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return listUser;
	}


	@Override
	public List<User> getListUser() throws SQLException {
		List<User> listUser = null;
		try {
			listUser = new ArrayList<>();
			ResultSet result = databaseHandle.getRecords("*", "user", "");
			while (result.next()) {
				int id = result.getInt("id");
				String name = result.getString("name");
				String password = result.getString("password");
				String email = result.getString("email");
				String phone = result.getString("phone");
				String address = result.getString("address");
				boolean role = Integer.valueOf(result.getString("role")) > 0;
				boolean activate = Integer.valueOf(result.getString("activate")) > 0;
				List<Bill> listBill = new ArrayList<>();
				listUser.add(new User(id, name, email, password, phone, address, role, activate, listBill));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return listUser;
	}

	//test
	public static void main(String[] args) throws SQLException {
		UserService userService = new UserService();

		// test get list user
		List<User> listUser = userService.getListUser();
		for (User user : listUser) {
			System.out.println(user);
		}
		// test findUserById
//        User user = userService.findUserById(1);
////        System.out.println(user);

		// test update user
//        User user = new User(5, "Nguyen Van An", "an@gmail", "123456", "0123456789", "Ha Noi", true, false);
//        System.out.println(userService.updateUser(user));
	}
}
