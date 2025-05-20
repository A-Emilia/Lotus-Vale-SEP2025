package networking.user;

import communication.requests.user_requests.LoginRequest;
import communication.requests.user_requests.RegisterRequest;
import model.entities.user.User;

public interface UserClient {
  boolean login(LoginRequest loginRequest);
  boolean register(RegisterRequest registerRequest);
}
