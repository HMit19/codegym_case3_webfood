package com.codegym.model;

import java.time.LocalDateTime;
import java.util.List;

public class Bill {
	private int id_bill;
	private List<Item> items;
	private LocalDateTime date_bill;
	private String detail;
	private boolean status;
	private User user;
	private long total;

	public Bill() {
	}


	public Bill(int id_bill, List<Item> items, LocalDateTime date_bill, String detail, boolean status, User user) {
		this.id_bill = id_bill;
		this.items = items;
		this.date_bill = date_bill;
		this.detail = detail;
		this.status = status;
		this.user = user;
	}

	public Bill(int id_bill, List<Item> items, LocalDateTime date_bill, String detail, boolean status) {
		this.id_bill = id_bill;
		this.items = items;
		this.date_bill = date_bill;
		this.detail = detail;
		this.status = status;
	}

	public Bill(List<Item> items, LocalDateTime date_bill, boolean status, User user) {
		this.items = items;
		this.date_bill = date_bill;
		this.status = status;
		this.user = user;
	}

	public Bill(List<Item> items, LocalDateTime date_bill, boolean status, User user, long total) {
		this.items = items;
		this.date_bill = date_bill;
		this.status = status;
		this.user = user;
		this.total = total;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public int getId_bill() {
		return id_bill;
	}

	public void setId_bill(int id_bill) {
		this.id_bill = id_bill;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public LocalDateTime getDate_bill() {
		return date_bill;
	}

	public void setDate_bill(LocalDateTime date_bill) {
		this.date_bill = date_bill;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Bill(List<Item> items, LocalDateTime date_bill, User user) {

		this.items = items;
		this.date_bill = date_bill;
		this.user = user;
	}
}