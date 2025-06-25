package communication.services.user;

import communication.Response;
import communication.requests.user_requests.LoginRequest;
import communication.requests.user_requests.RegisterRequest;

import java.sql.SQLException;

public interface UserService {
  Response login(LoginRequest payload);
  Response register(RegisterRequest payload);
}
