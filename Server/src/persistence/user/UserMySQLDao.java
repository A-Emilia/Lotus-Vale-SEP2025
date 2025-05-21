package persistence.user;

import communication.requests.user_requests.LoginRequest;
import communication.requests.user_requests.RegisterRequest;
import model.entities.user.User;
import networking.DatabaseConnector;
import utilities.querying.user.MySQLRegisterQuery;
import utilities.querying.user.MySQLUserQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMySQLDao implements UserDao {
  @Override
  public User login(LoginRequest payload) {
    // Establish connection to database and run query constructed via
    // the MySQLUserQuery class. Also run appropriate checks.
    // Purely testing as of right now.

    MySQLUserQuery loginQuery = MySQLUserQuery.getUserLogin(payload);

    try (Connection con = DatabaseConnector.getConnection();
        PreparedStatement sqlStatement = loginQuery.build(con)) {

      ResultSet rs = sqlStatement.executeQuery();

      return User.sqlToUser(rs);
    }
    catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public User register(RegisterRequest payload) {

    try (Connection con = DatabaseConnector.getConnection();
        PreparedStatement registerQuery = MySQLRegisterQuery.build(con, payload)) {


      int affectedRows = registerQuery.executeUpdate();

      if (affectedRows == 0) {
        throw new IllegalStateException("Username already taken.");
      }

      try (ResultSet rs = registerQuery.getGeneratedKeys()) {
        if (rs.next()) {
          int id = rs.getInt(1);

          User user = new User.Builder(id)
              .username(payload.username())
              .build();

          System.out.println(user.toString());

          return user;
        }
      }
    }
    catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return null;
  }
}
