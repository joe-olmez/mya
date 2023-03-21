package com.olmez.mya.utility;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;

public class DBMySql {
    private static final DataSource dataSource;

    static {
        MysqlDataSource db = new MysqlDataSource();
        db.setURL("jdbc:mysql://localhost:3306/core");
        db.setUser("root");
        db.setPassword("1234");
        dataSource = db;
    }

    private DBMySql() {
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}