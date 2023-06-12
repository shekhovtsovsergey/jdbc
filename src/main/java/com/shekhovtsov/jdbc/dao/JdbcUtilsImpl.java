package com.shekhovtsov.jdbc.dao;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtilsImpl implements JdbcUtils{

    @Override
    public Connection getConnection() throws SQLException, IOException {
        InputStream input = getClass().getClassLoader().getResourceAsStream("jdbc.properties");
        Properties props = new Properties();
        props.load(input);
        return DriverManager.getConnection(props.getProperty("url"), props);
    }

    @Override
    public void closeConnection(Connection connection) {
        if (connection == null) {
            return;
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public DataSource getDataSource() throws IOException {
        InputStream input = getClass().getClassLoader().getResourceAsStream("jdbc.properties");
        Properties props = new Properties();
        props.load(input);
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(props.getProperty("driver"));
        dataSource.setUrl(props.getProperty("url"));
        dataSource.setUsername(props.getProperty("user"));
        dataSource.setPassword(props.getProperty("password"));
        return dataSource;
    }

}
