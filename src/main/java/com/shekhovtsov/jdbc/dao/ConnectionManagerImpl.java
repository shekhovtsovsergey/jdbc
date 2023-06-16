package com.shekhovtsov.jdbc.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.util.List;
import java.util.Properties;


public class ConnectionManagerImpl implements ConnectionManager {

    private static ConnectionManagerImpl connectionManager;
    private List<Connection> connectionPool = new ArrayList<>();
    private String url;
    private String user;
    private String password;
    private int initialPoolSize;
    private String driver;
    private final String URL = "url";
    private final String USER = "user";
    private final String PASSWORD = "password";
    private final String INITIALPOOLSIZE = "initialPoolSize";
    private final String JDBC_PROPERTIES = "jdbc.properties";
    private final String DRIVER = "driver";


    public ConnectionManagerImpl() {
    }

    public static synchronized ConnectionManagerImpl getInstance() {
        if (connectionManager == null) {
            connectionManager = new ConnectionManagerImpl();
        }
        return connectionManager;
    }

    @Override
    public void initialize() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(JDBC_PROPERTIES)) {
            Properties prop = new Properties();
            prop.load(input);
            url = prop.getProperty(URL);
            user = prop.getProperty(USER);
            password = prop.getProperty(PASSWORD);
            initialPoolSize = Integer.parseInt(prop.getProperty(INITIALPOOLSIZE));
            driver = prop.getProperty(DRIVER);

            for (int i = 0; i < initialPoolSize; i++) {
                connectionPool.add(DriverManager.getConnection(url, user, password));
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized Connection getConnection() throws SQLException {
        if (connectionPool.isEmpty()) {
            connectionPool.add(DriverManager.getConnection(url, user, password));
        }
        return connectionPool.remove(0);
    }

    @Override
    public synchronized void releaseConnection(Connection connection) throws SQLException {
        if (connection != null) {
            connectionPool.add(connection);
        }
    }
}
