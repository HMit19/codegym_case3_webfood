package vn.codegym.database.dao;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.codegym.database.DatabaseManagement;
import vn.codegym.database.model.Category2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class Categories {
	private static final @NotNull Logger LOGGER = LoggerFactory.getLogger(Categories.class);


	private Categories() {
	}


	public static boolean add(@NotNull Category2 category) {
		try (
				final Connection connection = DatabaseManagement.createConnection();
				final PreparedStatement statement = connection.prepareStatement(
						"INSERT INTO 'categories' (`name`, `description`) VALUES (?, ?)"
				)
		) {
			// get data from POJO and check null
			final String name = category.getName();
			final String description = category.getDescription();
			if (name == null || description == null) {
				LOGGER.error("Cannot add this Category: required value is null!");
				return false;
			}
			// set values for statement
			statement.setString(1, name);
			statement.setString(2, description);
			LOGGER.info("Executing SQL statement: " + statement);
			if (statement.executeUpdate() != 1) {
				LOGGER.error("Cannot add this Category: nothing was updated!");
				return false;
			}
			// update generated values
			try (final ResultSet resultSet = statement.getGeneratedKeys()) {
				category.setId(resultSet.getInt("id"));
				category.setAvailable(resultSet.getBoolean("isAvailable"));
				category.setCreatedAt(resultSet.getTimestamp("createdAt"));
				category.setUpdatedAt(resultSet.getTimestamp("updatedAt"));
			}
			return true;
		} catch (final SQLException exception) {
			LOGGER.error("Cannot add this Category: Exception on execute!", exception);
			return false;
		}
	}

	public static boolean update(@NotNull Category2 category) {
		try (
				final Connection connection = DatabaseManagement.createConnection();
				final PreparedStatement statement = connection.prepareStatement(
						"UPDATE 'categories' SET `name` = ?, `description` = ?, `isAvailable` = ?, "
								+ "`updatedAt` = DEFAULT WHERE `id` = ?"
				)
		) {
			// get data from POJO and check null
			final Integer id = category.getId();
			final String name = category.getName();
			final String description = category.getDescription();
			final Boolean isAvailable = category.getAvailable();
			if (id == null || name == null || description == null || isAvailable == null) {
				LOGGER.error("Cannot add this Category: required value is null!");
				return false;
			}
			// set values for statement
			statement.setString(1, name);
			statement.setString(2, description);
			statement.setBoolean(3, isAvailable);
			statement.setInt(4, id);
			LOGGER.info("Executing SQL statement: " + statement);
			if (statement.executeUpdate() != 1) {
				LOGGER.error("Cannot update this Category: nothing was updated!");
				return false;
			}
			// update generated values
			try (final ResultSet resultSet = statement.getGeneratedKeys()) {
				category.setUpdatedAt(resultSet.getTimestamp("updatedAt"));
			}
			return true;
		} catch (final SQLException exception) {
			LOGGER.error("Cannot update this Category: Exception on execute!", exception);
			return false;
		}
	}

	public static @Nullable Category2 findById(int id) {
		try (
				final Connection connection = DatabaseManagement.createConnection();
				final PreparedStatement statement = connection.prepareStatement(
						"SELECT `id`, `name`, `description`, `isAvailable`, `createdAt`, `updatedAt`"
								+ " FROM 'categories' WHERE `id` = ?"
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
			LOGGER.error("Cannot find this Category: Exception on execute!", exception);
			return null;
		}
	}

	public static @NotNull List<@NotNull Category2> getAll() {
		return getAll(DatabaseManagement.DEFAULT_LIMIT);
	}

	public static @NotNull List<@NotNull Category2> getAll(int limit) {
		try (
				final Connection connection = DatabaseManagement.createConnection();
				final PreparedStatement statement = connection.prepareStatement(
						"SELECT `id`, `name`, `description`, `isAvailable`, `createdAt`, `updatedAt`"
								+ " FROM 'categories' LIMIT ?"
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
			LOGGER.error("Cannot get Category list: Exception on execute!", exception);
			return List.of();
		}
	}

	private static @NotNull Category2 createOne(@NotNull ResultSet resultSet) throws SQLException {
		final Category2 category = new Category2();
		category.setId(resultSet.getInt("id"));
		category.setName(resultSet.getString("name"));
		category.setDescription(resultSet.getString("description"));
		category.setAvailable(resultSet.getBoolean("isAvailable"));
		category.setCreatedAt(resultSet.getTimestamp("createdAt"));
		category.setUpdatedAt(resultSet.getTimestamp("updatedAt"));
		return category;
	}

	private static @NotNull List<@NotNull Category2> createMany(@NotNull ResultSet resultSet) throws SQLException {
		final List<Category2> bills = new ArrayList<>();
		while (resultSet.next()) {
			bills.add(createOne(resultSet));
		}
		return bills;
	}
}
