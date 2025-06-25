package communication.services.user;

import communication.requests.user_requests.LoginRequest;
import communication.requests.user_requests.RegisterRequest;
import model.entities.user.User;

public interface UserServiceOld
{
  User login(LoginRequest payload);
  User register(RegisterRequest payload);
}
