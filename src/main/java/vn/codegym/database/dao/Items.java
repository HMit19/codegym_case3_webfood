package vn.codegym.database.dao;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.codegym.database.DatabaseManagement;
import vn.codegym.database.model.Item2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class Items {
	private static final @NotNull Logger LOGGER = LoggerFactory.getLogger(Items.class);


	private Items() {
	}


	public static boolean add(@NotNull Item2 item) {
		try (
				final Connection connection = DatabaseManagement.createConnection();
				final PreparedStatement statement = connection.prepareStatement(
						"INSERT INTO 'items' (`billId`, `productId`, `quantity`, `notes`, `price`)"
								+ " VALUES (?, ?, ?, ?, ?)"
				)
		) {
			// get data from POJO and check null
			final Integer billId = item.getBillId();
			final Integer productId = item.getProductId();
			final Integer quantity = item.getQuantity();
			final String notes = item.getNotes();
			final Integer price = item.getPrice();
			if (billId == null || productId == null || quantity == null || price == null) {
				LOGGER.error("Cannot add this Item: required value is null!");
				return false;
			}
			// set values for statement
			statement.setInt(1, billId);
			statement.setInt(2, productId);
			statement.setInt(3, quantity);
			statement.setString(4, notes);
			statement.setInt(5, price);
			LOGGER.info("Executing SQL statement: " + statement);
			if (statement.executeUpdate() != 1) {
				LOGGER.error("Cannot add this Item: nothing was updated!");
				return false;
			}
			// update generated values
			try (final ResultSet resultSet = statement.getGeneratedKeys()) {
				item.setId(resultSet.getInt("id"));
				item.setCreatedAt(resultSet.getTimestamp("createdAt"));
				item.setUpdatedAt(resultSet.getTimestamp("updatedAt"));
			}
			return true;
		} catch (final SQLException exception) {
			LOGGER.error("Cannot add this Item: Exception on execute!", exception);
			return false;
		}
	}

	public static boolean update(@NotNull Item2 item) {
		try (
				final Connection connection = DatabaseManagement.createConnection();
				final PreparedStatement statement = connection.prepareStatement(
						"UPDATE 'items' SET `billId` = ?, `productId` = ?,`quantity` = ?, `notes` = ?, `price` = ?,"
								+ " `updatedAt` = DEFAULT WHERE `id` = ?"
				)
		) {
			// get data from POJO and check null
			final Integer id = item.getId();
			final Integer billId = item.getBillId();
			final Integer productId = item.getProductId();
			final Integer quantity = item.getQuantity();
			final String notes = item.getNotes();
			final Integer price = item.getPrice();
			if (id == null || billId == null || productId == null || quantity == null || price == null) {
				LOGGER.error("Cannot add this Item: required value is null!");
				return false;
			}
			// set values for statement
			statement.setInt(1, billId);
			statement.setInt(2, productId);
			statement.setInt(3, quantity);
			statement.setString(4, notes);
			statement.setInt(5, price);
			statement.setInt(6, id);
			LOGGER.info("Executing SQL statement: " + statement);
			if (statement.executeUpdate() != 1) {
				LOGGER.error("Cannot update this Item: nothing was updated!");
				return false;
			}
			// update generated values
			try (final ResultSet resultSet = statement.getGeneratedKeys()) {
				item.setUpdatedAt(resultSet.getTimestamp("updatedAt"));
			}
			return true;
		} catch (final SQLException exception) {
			LOGGER.error("Cannot update this Item: Exception on execute!", exception);
			return false;
		}
	}

	public static @Nullable Item2 findById(int id) {
		try (
				final Connection connection = DatabaseManagement.createConnection();
				final PreparedStatement statement = connection.prepareStatement(
						"SELECT `id`, `billId`, `productId`, `quantity`, `notes`, `price`, `createdAt`, `updatedAt`"
								+ " FROM 'items' WHERE `id` = ?"
				)
		) {
			// set values for statement
			statement.setInt(1, id);
			LOGGER.info("Executing SQL statement: " + statement);
			try (final ResultSet resultSet = statement.executeQuery()) {
				// check for empty return
				if (!resultSet.first()) return null;
				// return result value
				return createOne(resultSet);
			}
		} catch (final SQLException exception) {
			LOGGER.error("Cannot find this Item: Exception on execute!", exception);
			return null;
		}
	}

	public static @NotNull List<@NotNull Item2> findByBillId(int billId) {
		return findByBillId(billId, DatabaseManagement.DEFAULT_LIMIT);
	}

	public static @NotNull List<@NotNull Item2> findByBillId(int billId, int limit) {
		try (
				final Connection connection = DatabaseManagement.createConnection();
				final PreparedStatement statement = connection.prepareStatement(
						"SELECT `id`, `billId`, `productId`, `quantity`, `notes`, `price`, `createdAt`, `updatedAt`"
								+ " FROM 'items' WHERE `billId` = ? LIMIT ?"
				)
		) {
			// set values for statement
			statement.setInt(1, billId);
			statement.setInt(2, limit);
			LOGGER.info("Executing SQL statement: " + statement);
			try (final ResultSet resultSet = statement.executeQuery()) {
				// create return values
				return createMany(resultSet);
			}
		} catch (final SQLException exception) {
			LOGGER.error("Cannot get Item list: Exception on execute!", exception);
			return List.of();
		}
	}

	public static @NotNull List<@NotNull Item2> getAll() {
		return getAll(DatabaseManagement.DEFAULT_LIMIT);
	}

	public static @NotNull List<@NotNull Item2> getAll(int limit) {
		try (
				final Connection connection = DatabaseManagement.createConnection();
				final PreparedStatement statement = connection.prepareStatement(
						"SELECT `id`, `billId`, `productId`, `quantity`, `notes`, `price`, `createdAt`, `updatedAt`"
								+ " FROM 'items' LIMIT ?"
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
			LOGGER.error("Cannot get Item list: Exception on execute!", exception);
			return List.of();
		}
	}

	private static @NotNull Item2 createOne(@NotNull ResultSet resultSet) throws SQLException {
		final Item2 item = new Item2();
		item.setId(resultSet.getInt("id"));
		item.setBillId(resultSet.getInt("billId"));
		item.setProductId(resultSet.getInt("productId"));
		item.setQuantity(resultSet.getInt("quantity"));
		item.setNotes(resultSet.getString("notes"));
		item.setPrice(resultSet.getInt("price"));
		item.setCreatedAt(resultSet.getTimestamp("createdAt"));
		item.setUpdatedAt(resultSet.getTimestamp("updatedAt"));
		return item;
	}

	private static @NotNull List<@NotNull Item2> createMany(@NotNull ResultSet resultSet) throws SQLException {
		final List<Item2> items = new ArrayList<>();
		while (resultSet.next()) {
			items.add(createOne(resultSet));
		}
		return items;
	}
}
