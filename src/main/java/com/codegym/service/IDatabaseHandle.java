package com.codegym.service;

import java.sql.ResultSet;

public interface IDatabaseHandle {
    boolean updateRecord(String table, String setValue, String condition);

    public boolean deleteRecord(String table, String condition);

    boolean addRecord(String table, String values);

    public boolean addRecord(String table, String columns, String values);

    public ResultSet getRecords(String column, String table, String Connection);


}
