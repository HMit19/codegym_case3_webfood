package com.codegym.service.user;

import com.codegym.model.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserService {
    public boolean addUser(User user);

    public boolean removeUser(int id);

    boolean updateUser(int id, String activate);

    boolean setRoleUser(int id, String role);

    public boolean updateUser(User user);

    public User findUserById(int id);
    public User findUserByPhone(String phone);

    public User findUserByName(String name);

    public User findUserByEmail(String email);

    public List<User> getListUser(String columns, String condition) throws SQLException;

    public List<User> getListUser() throws SQLException;
}
