package vn.codegym.database.dao;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.codegym.database.DatabaseManagement;
import vn.codegym.database.model.User2;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Users {
	private static final @NotNull Logger LOGGER = LoggerFactory.getLogger(Users.class);


	private Users() {
	}


	public static byte @NotNull [] hashPassword(@NotNull String password) {
		try {
			final MessageDigest digest = MessageDigest.getInstance("SHA3-256");
			final SecureRandom secureRandom = SecureRandom.getInstanceStrong();
			final byte[] bytes = new byte[64];
			secureRandom.nextBytes(bytes);
			digest.update(bytes, 0, 32);
			digest.update(password.getBytes(StandardCharsets.UTF_8));
			System.arraycopy(digest.digest(), 0, bytes, 32, 32);
			return bytes;
		} catch (final NoSuchAlgorithmException exception) {
			LOGGER.error("Unexpected error!", exception);
			throw new RuntimeException(exception);
		}
	}

	public static boolean checkPassword(@NotNull String password, byte @NotNull [] hashedPassword) {
		try {
			final MessageDigest digest = MessageDigest.getInstance("SHA3-256");
			digest.update(hashedPassword, 0, 32);
			digest.update(password.getBytes(StandardCharsets.UTF_8));
			return Arrays.equals(hashedPassword, 32, 64, digest.digest(), 0, 32);
		} catch (final NoSuchAlgorithmException exception) {
			LOGGER.error("Unexpected error!", exception);
			throw new RuntimeException(exception);
		}
	}

	public static boolean add(@NotNull User2 user) {
		try (
				final Connection connection = DatabaseManagement.createConnection();
				final PreparedStatement statement = connection.prepareStatement(
						"INSERT INTO 'users' (`email`, `password`, `name`, `phone`, `address`, `isAdmin`)"
								+ " VALUES (?, ?, ?, ?, ?, ?, ?)"
				)
		) {
			// get data from POJO and check null
			final String email = user.getEmail();
			final byte[] password = user.getPassword();
			final String name = user.getName();
			final String phone = user.getPhone();
			final String address = user.getAddress();
			final Boolean isAdmin = user.getAdmin();
			if (email == null || password == null || name == null
					|| phone == null || address == null || isAdmin == null) {
				LOGGER.error("Cannot add this User: required value is null!");
				return false;
			}
			// set values for statement
			statement.setString(1, email);
			statement.setBytes(2, password);
			statement.setString(3, name);
			statement.setString(4, phone);
			statement.setString(5, address);
			statement.setBoolean(6, isAdmin);
			LOGGER.info("Executing SQL statement: " + statement);
			if (statement.executeUpdate() != 1) {
				LOGGER.error("Cannot add this User: nothing was updated!");
				return false;
			}
			// update generated values
			try (final ResultSet resultSet = statement.getGeneratedKeys()) {
				user.setId(resultSet.getInt("id"));
				user.setActive(resultSet.getBoolean("isActive"));
				user.setCreatedAt(resultSet.getTimestamp("createdAt"));
				user.setUpdatedAt(resultSet.getTimestamp("updatedAt"));
			}
			return true;
		} catch (final SQLException exception) {
			LOGGER.error("Cannot add this User: Exception on execute!", exception);
			return false;
		}
	}

	public static boolean update(@NotNull User2 user) {
		try (
				final Connection connection = DatabaseManagement.createConnection();
				final PreparedStatement statement = connection.prepareStatement(
						"UPDATE 'users' SET `email` = ?, `password` = ?, `name` = ?, `phone` = ?, `address` = ?, "
								+ "`isAdmin` = ?, `isActive` = ?, `updatedAt` = DEFAULT WHERE `id` = ?"
				)
		) {
			// get data from POJO and check null
			final Integer id = user.getId();
			final String email = user.getEmail();
			final byte[] password = user.getPassword();
			final String name = user.getName();
			final String phone = user.getPhone();
			final String address = user.getAddress();
			final Boolean isAdmin = user.getAdmin();
			final Boolean isActive = user.getActive();
			if (id == null || email == null || password == null || name == null
					|| phone == null || address == null || isAdmin == null || isActive == null) {
				LOGGER.error("Cannot add this User: required value is null!");
				return false;
			}
			// set values for statement
			statement.setString(1, email);
			statement.setBytes(2, password);
			statement.setString(3, name);
			statement.setString(4, phone);
			statement.setString(5, address);
			statement.setBoolean(6, isAdmin);
			statement.setBoolean(7, isActive);
			statement.setInt(8, id);
			LOGGER.info("Executing SQL statement: " + statement);
			if (statement.executeUpdate() != 1) {
				LOGGER.error("Cannot update this User: nothing was updated!");
				return false;
			}
			// update generated values
			try (final ResultSet resultSet = statement.getGeneratedKeys()) {
				user.setUpdatedAt(resultSet.getTimestamp("updatedAt"));
			}
			return true;
		} catch (final SQLException exception) {
			LOGGER.error("Cannot update this User: Exception on execute!", exception);
			return false;
		}
	}

	public static @Nullable User2 findById(int id) {
		try (
				final Connection connection = DatabaseManagement.createConnection();
				final PreparedStatement statement = connection.prepareStatement(
						"SELECT `id`, `email`, `password`, `name`, `phone`, `address`, `isAdmin`, "
								+ "`isActive`, `createdAt`, `updatedAt` FROM 'users' WHERE `id` = ?"
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
			LOGGER.error("Cannot find this User: Exception on execute!", exception);
			return null;
		}
	}

	public static @Nullable User2 findByEmail(@NotNull String email) {
		try (
				final Connection connection = DatabaseManagement.createConnection();
				final PreparedStatement statement = connection.prepareStatement(
						"SELECT `id`, `email`, `password`, `name`, `phone`, `address`, `isAdmin`, "
								+ "`isActive`, `createdAt`, `updatedAt` FROM 'users' WHERE `email` = ?"
				)
		) {
			// set values for statement
			statement.setString(1, email);
			LOGGER.info("Executing SQL statement: " + statement);
			try (final ResultSet resultSet = statement.executeQuery()) {
				// check for empty return
				if (!resultSet.first()) return null;
				// return result value
				return createOne(resultSet);
			}
		} catch (final SQLException exception) {
			LOGGER.error("Cannot find this User: Exception on execute!", exception);
			return null;
		}
	}

	public static @NotNull List<@NotNull User2> getAll() {
		return getAll(DatabaseManagement.DEFAULT_LIMIT);
	}

	public static @NotNull List<@NotNull User2> getAll(int limit) {
		try (
				final Connection connection = DatabaseManagement.createConnection();
				final PreparedStatement statement = connection.prepareStatement(
						"SELECT `id`, `userId`, `details`, `onGoing`, `createdAt`, `updatedAt` FROM 'users' LIMIT ?"
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
			LOGGER.error("Cannot get User list: Exception on execute!", exception);
			return List.of();
		}
	}

	private static @NotNull User2 createOne(@NotNull ResultSet resultSet) throws SQLException {
		final User2 user = new User2();
		user.setId(resultSet.getInt("id"));
		user.setEmail(resultSet.getString("email"));
		user.setPassword(resultSet.getBytes("password"));
		user.setName(resultSet.getString("name"));
		user.setPhone(resultSet.getString("phone"));
		user.setAddress(resultSet.getString("address"));
		user.setAdmin(resultSet.getBoolean("isAdmin"));
		user.setActive(resultSet.getBoolean("isActive"));
		user.setCreatedAt(resultSet.getTimestamp("createdAt"));
		user.setUpdatedAt(resultSet.getTimestamp("updatedAt"));
		return user;
	}

	private static @NotNull List<@NotNull User2> createMany(@NotNull ResultSet resultSet) throws SQLException {
		final List<User2> bills = new ArrayList<>();
		while (resultSet.next()) {
			bills.add(createOne(resultSet));
		}
		return bills;
	}
}
