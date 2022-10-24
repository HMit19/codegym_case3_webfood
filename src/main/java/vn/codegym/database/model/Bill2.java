package vn.codegym.database.model;

import org.jetbrains.annotations.Nullable;

import java.sql.Timestamp;

public final class Bill2 {
	private @Nullable Integer id;
	private @Nullable Integer userId;
	private @Nullable String notes;
	private @Nullable Boolean isActive;
	private @Nullable Timestamp createdAt;
	private @Nullable Timestamp updatedAt;

	public @Nullable Integer getId() {
		return id;
	}

	public void setId(@Nullable Integer id) {
		this.id = id;
	}

	public @Nullable Integer getUserId() {
		return userId;
	}

	public void setUserId(@Nullable Integer userId) {
		this.userId = userId;
	}

	public @Nullable String getNotes() {
		return notes;
	}

	public void setNotes(@Nullable String notes) {
		this.notes = notes;
	}

	public @Nullable Boolean getActive() {
		return isActive;
	}

	public void setActive(@Nullable Boolean isActive) {
		this.isActive = isActive;
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