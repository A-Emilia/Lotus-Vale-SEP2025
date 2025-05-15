package networking.user;

import communication.requests.user_requests.LoginRequest;
import communication.requests.user_requests.RegisterRequest;
import model.entities.User;

public class TCPUserClient implements UserClient {
  @Override
  public User login(LoginRequest loginRequest) {
    return null;
  }

  @Override
  public boolean register(RegisterRequest registerRequest) {
    return false;
  }
}
