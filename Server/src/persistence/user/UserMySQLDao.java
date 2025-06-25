package persistence.user;

import communication.Response;
import communication.ResponseType;
import communication.requests.user_requests.LoginRequest;
import communication.requests.user_requests.RegisterRequest;
import model.entities.user.User;
import networking.DatabaseConnector;
import utilities.querying.user.MySQLRegister;
import utilities.querying.user.MySQLLogin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMySQLDao implements UserDao {
  @Override
  public Response login(LoginRequest payload) {
    try (Connection con = DatabaseConnector.getConnection();
        ResultSet rs = getUserByUsername(con, payload.username())) {

      if (!rs.next()) {
        return new Response(ResponseType.ERROR, "Username does not exist.");
      }

      String storedPassword = rs.getString("password");
      if (!storedPassword.equals(payload.password())) {
        return new Response(ResponseType.ERROR, "Incorrect password.");
      }

      User user = User.sqlToUser(rs);
      return new Response(ResponseType.OK, user);

    } catch (SQLException e) {
      e.printStackTrace();
      return new Response(ResponseType.ERROR, "An unknown error has occurred.");
    }
  }

  @Override
  public Response register(RegisterRequest payload) {
    try (Connection con = DatabaseConnector.getConnection();
        ResultSet rs = getUserByUsername(con, payload.username())) {

      if (rs.next()) {
        return new Response(ResponseType.ERROR, "Username already taken.");
      }

      try (PreparedStatement registerQuery = MySQLRegister.build(con, payload)) {
        int affectedRows = registerQuery.executeUpdate();

        if (affectedRows == 0) {
          return new Response(ResponseType.ERROR, "Registration failed.");
        }

        try (ResultSet generatedKeys = registerQuery.getGeneratedKeys()) {
          if (generatedKeys.next()) {
            int id = generatedKeys.getInt(1);
            User user = new User.Builder(id)
                .username(payload.username())
                .build();

            return new Response(ResponseType.OK, user);
          }
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return new Response(ResponseType.ERROR, "An unknown error occurred during registration.");
    }
    return new Response(ResponseType.ERROR, "Unexpected failure.");
  }

  private ResultSet getUserByUsername(Connection con, String username) throws SQLException {
    String sql = "SELECT * FROM user WHERE username = ?";
    PreparedStatement res = con.prepareStatement(sql);
    res.setString(1, username);
    return res.executeQuery();
  }
}
