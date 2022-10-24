package vn.codegym.database.model;

import org.jetbrains.annotations.Nullable;

import java.sql.Timestamp;

public final class User2 {
	private @Nullable Integer id;
	private @Nullable String email;
	private byte @Nullable [] password;
	private @Nullable String name;
	private @Nullable String phone;
	private @Nullable String address;
	private @Nullable Boolean isAdmin;
	private @Nullable Boolean isActive;
	private @Nullable Timestamp createdAt;
	private @Nullable Timestamp updatedAt;


	public @Nullable Integer getId() {
		return id;
	}

	public void setId(@Nullable Integer id) {
		this.id = id;
	}

	public @Nullable String getEmail() {
		return email;
	}

	public void setEmail(@Nullable String email) {
		this.email = email;
	}

	public byte @Nullable [] getPassword() {
		return password;
	}

	public void setPassword(byte @Nullable [] password) {
		this.password = password;
	}

	public @Nullable String getName() {
		return name;
	}

	public void setName(@Nullable String name) {
		this.name = name;
	}

	public @Nullable String getPhone() {
		return phone;
	}

	public void setPhone(@Nullable String phone) {
		this.phone = phone;
	}

	public @Nullable String getAddress() {
		return address;
	}

	public void setAddress(@Nullable String address) {
		this.address = address;
	}

	public @Nullable Boolean getAdmin() {
		return isAdmin;
	}

	public void setAdmin(@Nullable Boolean admin) {
		isAdmin = admin;
	}

	public @Nullable Boolean getActive() {
		return isActive;
	}

	public void setActive(@Nullable Boolean active) {
		isActive = active;
	}

	public @Nullable Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(@Nullable Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public @Nullable Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(@Nullable Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
}
