package vn.codegym.database.model;

import org.jetbrains.annotations.Nullable;

import java.sql.Timestamp;

public final class Item2 {
	private @Nullable Integer id;
	private @Nullable Integer billId;
	private @Nullable Integer productId;
	private @Nullable Integer quantity;
	private @Nullable String notes;
	private @Nullable Integer price;
	private @Nullable Timestamp createdAt;
	private @Nullable Timestamp updatedAt;


	public @Nullable Integer getId() {
		return id;
	}

	public void setId(@Nullable Integer id) {
		this.id = id;
	}

	public @Nullable Integer getBillId() {
		return billId;
	}

	public void setBillId(@Nullable Integer billId) {
		this.billId = billId;
	}

	public @Nullable Integer getProductId() {
		return productId;
	}

	public void setProductId(@Nullable Integer productId) {
		this.productId = productId;
	}

	public @Nullable Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(@Nullable Integer quantity) {
		this.quantity = quantity;
	}

	public @Nullable String getNotes() {
		return notes;
	}

	public void setNotes(@Nullable String notes) {
		this.notes = notes;
	}

	public @Nullable Integer getPrice() {
		return price;
	}

	public void setPrice(@Nullable Integer price) {
		this.price = price;
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
