package communication.services.user;

import communication.Response;
import communication.ResponseType;
import communication.requests.user_requests.LoginRequest;
import communication.requests.user_requests.RegisterRequest;
import model.entities.user.User;
import persistence.user.UserDao;

import java.sql.SQLException;
import java.util.NoSuchElementException;

public class UserServiceImpl implements UserService {
  private final UserDao userDao;

  public UserServiceImpl(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public Response login(LoginRequest payload) throws NoSuchElementException {
    return userDao.login(payload);
  }

  @Override
  public Response register(RegisterRequest payload) {
    return userDao.register(payload);
  }
}
