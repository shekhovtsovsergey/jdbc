package com.shekhovtsov.jdbc.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionManager {

    void initialize();

    Connection getConnection() throws SQLException;

    void releaseConnection(Connection connection) throws SQLException;
}
