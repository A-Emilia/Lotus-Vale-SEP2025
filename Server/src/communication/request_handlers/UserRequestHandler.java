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
  public Object handle(String action, Object payload) {

    return switch(action) {
      case "login" -> userService.login((LoginRequest) payload);
      case "register" -> userService.register((RegisterRequest) payload);

      default -> throw new IllegalStateException("Unexpected value: " + action);
    };
  }
}
