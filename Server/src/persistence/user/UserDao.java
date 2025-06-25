package persistence.user;

import communication.Response;
import communication.requests.user_requests.LoginRequest;
import communication.requests.user_requests.RegisterRequest;
import model.entities.user.User;

import java.sql.SQLException;

public interface UserDao {
  Response login(LoginRequest payload);
  Response register(RegisterRequest payload);
}
