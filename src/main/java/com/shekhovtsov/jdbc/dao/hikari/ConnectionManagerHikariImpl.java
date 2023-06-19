package com.shekhovtsov.jdbc.dao.hikari;

import com.shekhovtsov.jdbc.dao.connection.ConnectionManagerImpl;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.Properties;

public class ConnectionManagerHikariImpl implements ConnectionManagerHikari {

    private static ConnectionManagerImpl connectionManager;
    private HikariDataSource dataSource;
    private final String JDBC_PROPERTIES = "jdbc.properties";
    private final String POOL_NAME = "MyConnectionPool";
    private final String URL = "url";
    private final String USER = "user";
    private final String PASSWORD = "password";
    private final String INITIALPOOLSIZE = "initialPoolSize";
    private final String DRIVER = "driver";
    private static final String MAXIMUM_POOL_SIZE = "maximumPoolSize";
    private static final String MINIMUM_IDLE = "minimumIdle";
    private static final String IDLE_TIMEOUT = "idleTimeout";
    private static final String MAX_LIFETIME = "maxLifetime";
    private static final String CONNECTION_TIMEOUT = "connectionTimeout";


    public ConnectionManagerHikariImpl() {
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

            HikariConfig config = new HikariConfig();
            config.setPoolName(POOL_NAME);
            config.setJdbcUrl(prop.getProperty(URL));
            config.setUsername(prop.getProperty(USER));
            config.setPassword(prop.getProperty(PASSWORD));
            config.setMaximumPoolSize(Integer.parseInt(prop.getProperty(MAXIMUM_POOL_SIZE)));
            config.setMinimumIdle(Integer.parseInt(prop.getProperty(MINIMUM_IDLE)));
            config.setIdleTimeout(Long.parseLong(prop.getProperty(IDLE_TIMEOUT)));
            config.setMaxLifetime(Long.parseLong(prop.getProperty(MAX_LIFETIME)));
            config.setConnectionTimeout(Long.parseLong(prop.getProperty(CONNECTION_TIMEOUT)));

            dataSource = new HikariDataSource(config);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    public void releaseConnection(Connection connection) throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
