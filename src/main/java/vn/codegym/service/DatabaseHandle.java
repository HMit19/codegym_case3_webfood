// tạo lớp sử lý các thao tác với database

package vn.codegym.service;

import vn.codegym.connect.DatabaseManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseHandle implements IDatabaseHandle {
	Connection connect = null;

	public DatabaseHandle() {
		connect = DatabaseManagement.createConnection();
	}

	// update record
	@Override
	public boolean updateRecord(String table, String setValue, String condition) {
		boolean checkUpdate = false;
		String sql = "UPDATE " + table + " SET " + setValue;
		sql += condition != "" ? (" WHERE " + condition) : "";
		System.out.println(sql);
		try {
			PreparedStatement sttm = connect.prepareStatement(sql);
			checkUpdate = sttm.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return checkUpdate;
	}

	// delete record
	@Override
	public boolean deleteRecord(String table, String condition) {
		boolean checkDelete = false;
		String sql = "DELETE FROM " + table;
		sql += condition != "" ? (" WHERE " + condition) : "";
		System.out.println(sql);
		try {
			PreparedStatement sttm = connect.prepareStatement(sql);
			checkDelete = sttm.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return checkDelete;
	}

	// add record
	@Override
	public boolean addRecord(String table, String values) {
		boolean checkAdd = false;
		String sql = "INSERT INTO " + table + " values(" + values + ")";
		System.out.println(sql);
		try {
			PreparedStatement sttm = connect.prepareStatement(sql);
			checkAdd = sttm.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return checkAdd;
	}

	@Override
	public boolean addRecord(String table, String columns, String values) {
		boolean checkAdd = false;
		String sql = "INSERT INTO " + table + " (" + columns + ") values(" + values + ")";
		System.out.println(sql);
		try {
			PreparedStatement sttm = connect.prepareStatement(sql);
			checkAdd = sttm.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return checkAdd;
	}

	// return records
	public ResultSet getRecords(String column, String table, String condition) {
		String sql = "SELECT " + column + " FROM " + table;
		sql += condition != "" ? (" WHERE " + condition) : "";
		System.out.println(sql);
		try {
			PreparedStatement sttm = connect.prepareStatement(sql);
			return sttm.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Test method manipulating database
	public static void main(String[] args) throws SQLException {
		DatabaseHandle db = new DatabaseHandle();
//        System.out.println("select all");
//        ResultSet rs = db.getRecords("*", "category", "");
//        while (rs.next()) {
//            System.out.println(rs.getString("id_category") + " " + rs.getString("name_category") + " " + rs.getString("exist"));
//        }
//
//        // xoa db
//        System.out.println("\ndelete");
//        System.out.println(db.deleteRecord("category", "id_category = 1"));

		// update db
//        System.out.println("\nupdate");
//        System.out.println(db.updateRecord("category", "exist = 1", ""));

		// add db
//        System.out.println("\nAdd");
//        System.out.println(db.addRecord("category", "name_category", "'name new'"));
	}
}
