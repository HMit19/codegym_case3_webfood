package vn.codegym.database.model;

import org.jetbrains.annotations.Nullable;

import java.sql.Timestamp;

public final class Product2 {
	private @Nullable Integer id;
	private @Nullable Integer categoryId;
	private @Nullable String name;
	private @Nullable Integer price;
	private @Nullable String image;
	private @Nullable String description;
	private @Nullable Boolean isAvailable;
	private @Nullable Boolean isHidden;
	private @Nullable Timestamp createdAt;
	private @Nullable Timestamp updatedAt;

	public @Nullable Integer getId() {
		return id;
	}

	public void setId(@Nullable Integer id) {
		this.id = id;
	}

	public @Nullable Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(@Nullable Integer categoryId) {
		this.categoryId = categoryId;
	}

	public @Nullable String getName() {
		return name;
	}

	public void setName(@Nullable String name) {
		this.name = name;
	}

	public @Nullable Integer getPrice() {
		return price;
	}

	public void setPrice(@Nullable Integer price) {
		this.price = price;
	}

	public @Nullable String getImage() {
		return image;
	}

	public void setImage(@Nullable String image) {
		this.image = image;
	}

	public @Nullable String getDescription() {
		return description;
	}

	public void setDescription(@Nullable String description) {
		this.description = description;
	}

	public @Nullable Boolean getAvailable() {
		return isAvailable;
	}

	public void setAvailable(@Nullable Boolean available) {
		isAvailable = available;
	}

	public @Nullable Boolean getHidden() {
		return isHidden;
	}

	public void setHidden(@Nullable Boolean hidden) {
		isHidden = hidden;
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
