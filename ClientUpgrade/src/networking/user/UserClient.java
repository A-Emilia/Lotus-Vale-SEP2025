package networking.user;

import communication.requests.user_requests.LoginRequest;
import communication.requests.user_requests.RegisterRequest;

public interface UserClient {
  boolean login(LoginRequest loginRequest);
  boolean register(RegisterRequest registerRequest);
}
