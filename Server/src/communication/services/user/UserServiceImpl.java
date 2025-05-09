package communication.services.user;

import persistence.user.UserDao;

public class UserServiceImpl implements UserService {
  private UserDao userDao;

  public UserServiceImpl(UserDao userDao) {
    this.userDao = userDao;
  }
}
