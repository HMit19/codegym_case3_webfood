package com.codegym.model;

import java.util.List;

public class User {
	private int id;
	private String name;
	private String email;
	private String password;
	private String phone;
	private String address;
	private boolean role;
	private boolean activate;
	private List<Bill> bills;

	public User() {
	}

	public User(int id, String name, String email, String password, String phone, String address, boolean role, boolean activate, List<Bill> bills) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.address = address;
		this.role = role;
		this.activate = activate;
		this.bills = bills;
	}

	public User(int id, String name, String email, String password, String phone, String address, boolean role, boolean activate) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.address = address;
		this.role = role;
		this.activate = activate;
	}

	public User(String name, String email, String password, String phone, String address, boolean role, boolean activate) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.address = address;
		this.role = role;
		this.activate = activate;
	}

	public User(int id, String name, String email, String password, String phone, String address) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.address = address;
	}

	public User(String name, String email, String password, String phone, String address) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isRole() {
		return role;
	}

	public void setRole(boolean role) {
		this.role = role;
	}

	public boolean isActivate() {
		return activate;
	}

	public void setActivate(boolean activate) {
		this.activate = activate;
	}

	public List<Bill> getBills() {
		return bills;
	}

	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", phone='" + phone + '\'' +
				", address='" + address + '\'' +
				", role=" + role +
				", activate=" + activate +
				", bills=" + bills +
				'}';
	}
}
