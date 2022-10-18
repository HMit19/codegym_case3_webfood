package com.codegym.service;

import java.sql.ResultSet;

public interface IDatabaseHandle {
	boolean updateRecord(String table, String setValue, String condition);

	boolean deleteRecord(String table, String condition);

	boolean addRecord(String table, String values);

	boolean addRecord(String table, String columns, String values);

	ResultSet getRecords(String column, String table, String Connection);


}
