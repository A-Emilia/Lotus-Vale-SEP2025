package communication.request_handlers;

import communication.services.user.UserService;

public class UserRequestHandler implements RequestHandler {
  private UserService userService;

  public UserRequestHandler(UserService userService) {
    this.userService = userService;
  }

  @Override
  public Object handle(String action, Object payload) {
    return null;
  }
}
