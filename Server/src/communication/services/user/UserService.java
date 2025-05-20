package communication.services.user;

import communication.requests.user_requests.LoginRequest;
import communication.requests.user_requests.RegisterRequest;
import model.entities.user.User;

import javax.management.InstanceAlreadyExistsException;

public interface UserService {
  User login(LoginRequest payload);
  User register(RegisterRequest payload);
}
