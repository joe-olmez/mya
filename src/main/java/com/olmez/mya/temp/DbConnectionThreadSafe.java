package com.olmez.mya.temp;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Database connection: Example of a singleton connection class (Thread Safe)
 */
public class DbConnectionThreadSafe {
  private static final String URL = "jdbc:mysql://localhost:3306/mya";
  private static final String USER = "root";
  private static final String PW = "1234";

  private static DbConnectionThreadSafe instance;
  private static Connection con;

  private DbConnectionThreadSafe() {
    try {
      con = DriverManager.getConnection(URL, USER, PW);
    } catch (Exception e) {
      // log.error("Failed to connect to database. {}", e.getMessage());
    }
  }

  public static synchronized Connection getConnection() {
    if (instance == null) {
      instance = new DbConnectionThreadSafe();
    }
    return con;
  }

}