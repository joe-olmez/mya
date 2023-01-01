package com.olmez.mya.temp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

  private String url = "jdbc:mysql://localhost:3306/car";
  private String username = "root";
  private String password = "1234";

  private Connection connection;
  private static DbConnection instance;

  private DbConnection() throws SQLException {
    this.connection = DriverManager.getConnection(url, username, password);
  }

  public static DbConnection getInstance() throws SQLException {
    if (instance == null || instance.getConnection().isClosed()) {
      instance = new DbConnection();
    }
    return instance;
  }

  public Connection getConnection() {
    return connection;
  }

}