package utilities.querying.user;

import communication.requests.user_requests.LoginRequest;
import utilities.querying.QueryBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLLogin implements QueryBuilder {
  private final StringBuilder sql = new StringBuilder("SELECT u.* FROM user u WHERE 1=1");
  private final List<Object> userParam = new ArrayList<>();

  public static MySQLLogin getUserLogin(LoginRequest request) {
    return new MySQLLogin()
        .filterByUsername(request.username())
        .filterByPassword(request.password());
  }

  public MySQLLogin filterByUsername(String username) {
    if (username != null && !username.isEmpty()) {
      sql.append(" AND u.username = ?");
      userParam.add(username);
    }
    return this;
  }

  public MySQLLogin filterByPassword(String password) {
    if (password != null && !password.isEmpty()) {
      sql.append(" AND u.password = ?");
      userParam.add(password);
    }
    return this;
  }

  public PreparedStatement build(Connection con) throws SQLException {
    PreparedStatement res = con.prepareStatement(sql.toString());

    for (int i = 0; i < userParam.size() ; i++) {
      res.setString(i+1, (String) userParam.get(i));
    }

    return res;
  }
}
