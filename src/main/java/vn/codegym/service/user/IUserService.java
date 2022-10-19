package vn.codegym.service.user;

import vn.codegym.model.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserService {
	boolean addUser(User user);

	boolean removeUser(int id);

	boolean updateUser(int id, String activate);

	boolean setRoleUser(int id, String role);

	boolean updateUser(User user);

	User findUserById(int id);

	User findUserByPhone(String phone);

	User findUserByName(String name);

	User findUserByEmail(String email);

	List<User> getListUser(String columns, String condition) throws SQLException;

	List<User> getListUser() throws SQLException;
}
