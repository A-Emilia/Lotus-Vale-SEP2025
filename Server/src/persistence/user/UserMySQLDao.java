package persistence.user;

import communication.requests.user_requests.LoginRequest;
import communication.requests.user_requests.RegisterRequest;
import model.entities.User;

public class UserMySQLDao implements UserDao {
  @Override
  public User login(LoginRequest payload) {
    // Establish connection to database and run query constructed via
    // the MySQLUserQuery class. Also run appropriate checks.
    // Purely testing as of right now.
    return new User.Builder(69).build();
  }

  @Override
  public boolean register(RegisterRequest payload) {
    return false;
  }
}
