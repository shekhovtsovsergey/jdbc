package com.shekhovtsov.jdbc.dao.hikari;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionManagerHikari {


    void initialize();

    Connection getConnection() throws SQLException;

    void releaseConnection(Connection connection) throws SQLException;

}
