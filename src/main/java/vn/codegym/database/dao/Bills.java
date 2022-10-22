package vn.codegym.database.dao;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.codegym.database.DatabaseManagement;
import vn.codegym.database.model.Bill2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class Bills {
	private static final @NotNull Logger LOGGER = LoggerFactory.getLogger(Bills.class);

	
	private Bills() {
	}


	public static boolean add(@NotNull Bill2 bill) {
		try (
				final Connection connection = DatabaseManagement.createConnection();
				final PreparedStatement statement = connection.prepareStatement(
						"INSERT INTO `bills` (`userId`, `notes`) VALUES (?, ?)"
				)
		) {
			// get data from POJO and check null
			final Integer userId = bill.getUserId();
			final String detail = bill.getNotes();
			if (userId == null) {
				LOGGER.error("Cannot add this Bill: required value is null!");
				return false;
			}
			// set values for statement
			statement.setInt(1, userId);
			statement.setString(2, detail);
			LOGGER.info("Executing SQL statement: " + statement);
			if (statement.executeUpdate() != 1) {
				LOGGER.error("Cannot add this Bill: nothing was updated!");
				return false;
			}
			// update generated values
			try (final ResultSet resultSet = statement.getGeneratedKeys()) {
				bill.setId(resultSet.getInt("id"));
				bill.setActive(resultSet.getBoolean("isActive"));
				bill.setCreatedAt(resultSet.getTimestamp("createdAt"));
				bill.setUpdatedAt(resultSet.getTimestamp("updatedAt"));
			}
			return true;
		} catch (final SQLException exception) {
			LOGGER.error("Cannot add this Bill: Exception on execute!", exception);
			return false;
		}
	}

	public static boolean update(@NotNull Bill2 bill) {
		try (
				final Connection connection = DatabaseManagement.createConnection();
				final PreparedStatement statement = connection.prepareStatement(
						"UPDATE `bills` SET `isActive` = ?, `updatedAt` = DEFAULT WHERE `id` = ?"
				)
		) {
			// get data from POJO and check null
			final Integer id = bill.getId();
			final Boolean isActive = bill.getActive();
			if (id == null || isActive == null) {
				LOGGER.error("Cannot update this Bill: required value is null!");
				return false;
			}
			// set values for statement
			statement.setBoolean(1, isActive);
			statement.setInt(2, id);
			LOGGER.info("Executing SQL statement: " + statement);
			if (statement.executeUpdate() != 1) {
				LOGGER.error("Cannot update this Bill: nothing was updated!");
				return false;
			}
			// update generated values
			try (final ResultSet resultSet = statement.getGeneratedKeys()) {
				bill.setUpdatedAt(resultSet.getTimestamp("updatedAt"));
			}
			return true;
		} catch (final SQLException exception) {
			LOGGER.error("Cannot update this Bill: Exception on execute!", exception);
			return false;
		}
	}

	public static @Nullable Bill2 findById(int id) {
		try (
				final Connection connection = DatabaseManagement.createConnection();
				final PreparedStatement statement = connection.prepareStatement(
						"SELECT `id`, `userId`, `notes`, `isActive`, `createdAt`, `updatedAt`"
								+ " FROM `bills` WHERE `id` = ?"
				)
		) {
			// set values for statement
			statement.setInt(1, id);
			LOGGER.info("Executing SQL statement: " + statement);
			try (final ResultSet resultSet = statement.executeQuery()) {
				// check for empty return
				if (!resultSet.first()) return null;
				// create return value
				return createOne(resultSet);
			}
		} catch (final SQLException exception) {
			LOGGER.error("Cannot find this Bill: Exception on execute!", exception);
			return null;
		}
	}

	public static @NotNull List<@NotNull Bill2> findByUserId(int id) {
		return findByUserId(id, DatabaseManagement.DEFAULT_LIMIT);
	}

	public static @NotNull List<@NotNull Bill2> findByUserId(int id, int limit) {
		try (
				final Connection connection = DatabaseManagement.createConnection();
				final PreparedStatement statement = connection.prepareStatement(
						"SELECT `id`, `userId`, `notes`, `isActive`, `createdAt`, `updatedAt` FROM `bills` WHERE `userId` = ? LIMIT ?"
				)
		) {
			// set values for statement
			statement.setInt(1, id);
			statement.setInt(2, limit);
			LOGGER.info("Executing SQL statement: " + statement);
			try (final ResultSet resultSet = statement.executeQuery()) {
				// create return values
				return createMany(resultSet);
			}
		} catch (final SQLException exception) {
			LOGGER.error("Cannot find this Bill: Exception on execute!", exception);
			return List.of();
		}
	}

	public static @NotNull List<@NotNull Bill2> getAll() {
		return getAll(DatabaseManagement.DEFAULT_LIMIT);
	}

	public static @NotNull List<@NotNull Bill2> getAll(int limit) {
		try (
				final Connection connection = DatabaseManagement.createConnection();
				final PreparedStatement statement = connection.prepareStatement(
						"SELECT `id`, `userId`, `notes`, `isActive`, `createdAt`, `updatedAt` FROM `bills` LIMIT ?"
				)
		) {
			// set values for statement
			statement.setInt(1, limit);
			LOGGER.info("Executing SQL statement: " + statement);
			try (final ResultSet resultSet = statement.executeQuery()) {
				// create return values
				return createMany(resultSet);
			}
		} catch (final SQLException exception) {
			LOGGER.error("Cannot get Bill list: Exception on execute!", exception);
			return List.of();
		}
	}

	private static @NotNull Bill2 createOne(@NotNull ResultSet resultSet) throws SQLException {
		final Bill2 bill = new Bill2();
		bill.setId(resultSet.getInt("id"));
		bill.setUserId(resultSet.getInt("userId"));
		bill.setNotes(resultSet.getString("notes"));
		bill.setActive(resultSet.getBoolean("isActive"));
		bill.setCreatedAt(resultSet.getTimestamp("createdAt"));
		bill.setUpdatedAt(resultSet.getTimestamp("updatedAt"));
		return bill;
	}

	private static @NotNull List<@NotNull Bill2> createMany(@NotNull ResultSet resultSet) throws SQLException {
		final List<Bill2> bills = new ArrayList<>();
		while (resultSet.next()) {
			bills.add(createOne(resultSet));
		}
		return bills;
	}
}
