package vn.codegym.database.dao;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.codegym.database.DatabaseManagement;
import vn.codegym.database.model.Product2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class Products {
	private static final @NotNull Logger LOGGER = LoggerFactory.getLogger(Products.class);


	private Products() {
	}


	public static boolean add(@NotNull Product2 product) {
		try (
				final Connection connection = DatabaseManagement.createConnection();
				final PreparedStatement statement = connection.prepareStatement(
						"INSERT INTO 'products' (`categoryId`, `name`, `price`, `image`, `description`)"
								+ " VALUES (?, ?, ?, ?, ?)"
				)
		) {
			// get data from POJO and check null
			final Integer categoryId = product.getCategoryId();
			final String name = product.getName();
			final Integer price = product.getPrice();
			final String image = product.getImage();
			final String description = product.getDescription();
			if (categoryId == null || name == null || price == null || description == null) {
				LOGGER.error("Cannot add this Product: required value is null!");
				return false;
			}
			// set values for statement
			statement.setInt(1, categoryId);
			statement.setString(2, name);
			statement.setInt(3, price);
			statement.setString(4, image);
			statement.setString(5, description);
			LOGGER.info("Executing SQL statement: " + statement);
			if (statement.executeUpdate() != 1) {
				LOGGER.error("Cannot add this Product: nothing was updated!");
				return false;
			}
			// update generated values
			try (final ResultSet resultSet = statement.getGeneratedKeys()) {
				product.setId(resultSet.getInt("id"));
				product.setAvailable(resultSet.getBoolean("isAvailable"));
				product.setHidden(resultSet.getBoolean("isHidden"));
				product.setCreatedAt(resultSet.getTimestamp("createdAt"));
				product.setUpdatedAt(resultSet.getTimestamp("updatedAt"));
			}
			return true;
		} catch (final SQLException exception) {
			LOGGER.error("Cannot add this Product: Exception on execute!", exception);
			return false;
		}
	}

	public static boolean update(@NotNull Product2 product) {
		try (
				final Connection connection = DatabaseManagement.createConnection();
				final PreparedStatement statement = connection.prepareStatement(
						"UPDATE 'products' SET `categoryId` = ?, `name` = ?, `price` = ?, `image` = ?,"
								+ " `description` = ?, `isAvailable` = ?, `isHidden` = ?, `updatedAt` = DEFAULT"
								+ " WHERE `id` = ?"
				)
		) {
			// get data from POJO and check null
			final Integer id = product.getId();
			final Integer categoryId = product.getCategoryId();
			final String name = product.getName();
			final Integer price = product.getPrice();
			final String image = product.getImage();
			final String description = product.getDescription();
			final Boolean isAvailable = product.getAvailable();
			final Boolean isHidden = product.getHidden();
			if (id == null || categoryId == null || name == null || price == null
					|| description == null || isAvailable == null || isHidden == null) {
				LOGGER.error("Cannot add this Product: required value is null!");
				return false;
			}
			// set values for statement
			statement.setInt(1, categoryId);
			statement.setString(2, name);
			statement.setInt(3, price);
			statement.setString(4, image);
			statement.setString(5, description);
			statement.setBoolean(6, isAvailable);
			statement.setBoolean(7, isHidden);
			statement.setInt(8, id);
			LOGGER.info("Executing SQL statement: " + statement);
			if (statement.executeUpdate() != 1) {
				LOGGER.error("Cannot update this Product: nothing was updated!");
				return false;
			}
			// update generated values
			try (final ResultSet resultSet = statement.getGeneratedKeys()) {
				product.setUpdatedAt(resultSet.getTimestamp("updatedAt"));
			}
			return true;
		} catch (final SQLException exception) {
			LOGGER.error("Cannot update this Product: Exception on execute!", exception);
			return false;
		}
	}

	public static @Nullable Product2 findById(int id) {
		try (
				final Connection connection = DatabaseManagement.createConnection();
				final PreparedStatement statement = connection.prepareStatement(
						"SELECT `id`, `categoryId`, `name`, `price`, `image`, `description`,"
								+ " `isAvailable`, `isHidden`, `createdAt`, `updatedAt` FROM 'products'"
								+ " WHERE `id` = ?"
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
			LOGGER.error("Cannot find this Product: Exception on execute!", exception);
			return null;
		}
	}

	public static @NotNull List<@NotNull Product2> getAvailable() {
		return getAvailable(DatabaseManagement.DEFAULT_LIMIT);
	}

	public static @NotNull List<@NotNull Product2> getAvailable(int limit) {
		try (
				final Connection connection = DatabaseManagement.createConnection();
				final PreparedStatement statement = connection.prepareStatement(
						"SELECT `id`, `categoryId`, `name`, `price`, `image`, `description`,"
								+ " `isAvailable`, `isHidden`, `createdAt`, `updatedAt` FROM 'products'"
								+ " WHERE `isAvailable` = TRUE AND `isHidden` = FALSE LIMIT ?"
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
			LOGGER.error("Cannot get Product list: Exception on execute!", exception);
			return List.of();
		}
	}

	public static @NotNull List<@NotNull Product2> getVisible() {
		return getVisible(DatabaseManagement.DEFAULT_LIMIT);
	}

	public static @NotNull List<@NotNull Product2> getVisible(int limit) {
		try (
				final Connection connection = DatabaseManagement.createConnection();
				final PreparedStatement statement = connection.prepareStatement(
						"SELECT `id`, `categoryId`, `name`, `price`, `image`, `description`,"
								+ " `isAvailable`, `isHidden`, `createdAt`, `updatedAt` FROM 'products'"
								+ " WHERE `isHidden` = FALSE LIMIT ?"
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
			LOGGER.error("Cannot get Product list: Exception on execute!", exception);
			return List.of();
		}
	}

	public static @NotNull List<@NotNull Product2> getAll() {
		return getAll(DatabaseManagement.DEFAULT_LIMIT);
	}

	public static @NotNull List<@NotNull Product2> getAll(int limit) {
		try (
				final Connection connection = DatabaseManagement.createConnection();
				final PreparedStatement statement = connection.prepareStatement(
						"SELECT `id`, `categoryId`, `name`, `price`, `image`, `description`,"
								+ " `isAvailable`, `isHidden`, `createdAt`, `updatedAt` FROM 'products'"
								+ " LIMIT ?"
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
			LOGGER.error("Cannot get Product list: Exception on execute!", exception);
			return List.of();
		}
	}

	private static @NotNull Product2 createOne(@NotNull ResultSet resultSet) throws SQLException {
		final Product2 product = new Product2();
		product.setId(resultSet.getInt("id"));
		product.setCategoryId(resultSet.getInt("categoryId"));
		product.setName(resultSet.getString("name"));
		product.setPrice(resultSet.getInt("price"));
		product.setImage(resultSet.getString("image"));
		product.setDescription(resultSet.getString("description"));
		product.setAvailable(resultSet.getBoolean("isAvailable"));
		product.setHidden(resultSet.getBoolean("isHidden"));
		product.setCreatedAt(resultSet.getTimestamp("createdAt"));
		product.setUpdatedAt(resultSet.getTimestamp("updatedAt"));
		return product;
	}

	private static @NotNull List<@NotNull Product2> createMany(@NotNull ResultSet resultSet) throws SQLException {
		final List<Product2> products = new ArrayList<>();
		while (resultSet.next()) {
			products.add(createOne(resultSet));
		}
		return products;
	}
}
