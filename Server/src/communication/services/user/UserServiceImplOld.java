package communication.services.user;

import communication.requests.user_requests.LoginRequest;
import communication.requests.user_requests.RegisterRequest;
import model.entities.user.User;
import persistence.user.UserDao;

import java.util.NoSuchElementException;

public class UserServiceImplOld implements UserServiceOld
{
  private final UserDao userDao;

  public UserServiceImplOld(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public User login(LoginRequest payload) throws NoSuchElementException {
    //User user = userDao.login(payload);

    // TODO More sophisticated error handling.
    //if (user == null) {
    //  throw new NoSuchElementException("Invalid login.");
    //}

    //return user;
    return null;
  }

  @Override
  public User register(RegisterRequest payload) {
    //User user = userDao.register(payload);

    //if (user == null) {
    //  throw new NoSuchElementException("Invalid login.");
    //}

    //return user;
    return null;
  }
}
