package networking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
  // TODO I need to make this multithreadded or make a connection pool.
  private static final String URL = "jdbc:mysql://localhost:3306/mtg";
  private static final String USER = "root";
  private static final String PASSWORD = "2435Grfr1324Fede";

  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(URL, USER, PASSWORD);
  }
}
