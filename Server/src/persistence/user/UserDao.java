package persistence.user;

import communication.requests.user_requests.LoginRequest;
import communication.requests.user_requests.RegisterRequest;
import model.entities.User;

public interface UserDao {
  User login(LoginRequest payload);
  boolean register(RegisterRequest payload);
}
