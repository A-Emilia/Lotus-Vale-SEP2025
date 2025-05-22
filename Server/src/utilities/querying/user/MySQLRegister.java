package utilities.querying.user;

import communication.requests.user_requests.RegisterRequest;
import utilities.querying.QueryBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQLRegister implements QueryBuilder {
  private static final String sql =
          "INSERT INTO user (username, password)"     +
          "SELECT ?, ? FROM DUAL WHERE NOT EXISTS ("  +
          "SELECT 1 FROM user WHERE username = ?"    +
          ")";

  public static PreparedStatement build(Connection con, RegisterRequest request) throws SQLException {
    PreparedStatement res = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
    res.setString(1, request.username());
    res.setString(2, request.password());
    res.setString(3, request.username());

    return res;
  }
}
