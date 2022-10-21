package vn.codegym.database;

import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseManagement {
	private static final @NotNull String JDBC_URL = "jdbc:mysql://localhost:3306/web_food";
	private static final @NotNull String JDBC_USERNAME = "root";
	private static final @NotNull String JDBC_PASSWORD = "1905";

	private DatabaseManagement() {
	}

	public static @NotNull Connection createConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
		} catch (SQLException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	// test connect to mysql
	public static void main(@NotNull String @NotNull [] args) throws SQLException {
		try (final Connection connection = createConnection()) {
			System.out.println("Connected!");
			System.out.println(connection);
		}
	}
}
