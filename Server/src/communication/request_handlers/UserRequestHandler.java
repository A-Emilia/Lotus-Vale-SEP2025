package communication.request_handlers;

import communication.requests.user_requests.LoginRequest;
import communication.requests.user_requests.RegisterRequest;
import communication.services.user.UserService;

public class UserRequestHandler implements RequestHandler {
  private UserService userService;

  public UserRequestHandler(UserService userService) {
    this.userService = userService;
  }

  @Override
  public Object handle(Object payload) {

    return switch (payload) {
      case LoginRequest req -> userService.login(req);
      case RegisterRequest req -> userService.register(req);

      default -> throw new IllegalStateException("Error");
    };
  }
}
