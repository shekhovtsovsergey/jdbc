package com.shekhovtsov.jdbc.dao;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public interface JdbcUtils {

    Connection getConnection() throws SQLException, IOException;
    void closeConnection(Connection connection);
    DataSource getDataSource() throws IOException;
}
