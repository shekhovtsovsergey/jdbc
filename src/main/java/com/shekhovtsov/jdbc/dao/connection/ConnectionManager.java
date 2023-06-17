package com.shekhovtsov.jdbc.dao.connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

public interface ConnectionManager {

    void initialize();

    Connection getConnection() throws SQLException;

    void releaseConnection(Connection connection) throws SQLException;

//    void execute(Callable task) throws Exception;

}
