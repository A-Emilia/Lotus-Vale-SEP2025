package networking.user;

import communication.requests.user_requests.LoginRequest;
import communication.requests.user_requests.RegisterRequest;
import model.entities.User;

public interface UserClient {
  User login(LoginRequest loginRequest);
  boolean register(RegisterRequest registerRequest);
}
