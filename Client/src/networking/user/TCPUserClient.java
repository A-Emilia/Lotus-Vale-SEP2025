package networking.user;

import communication.Request;
import communication.RequestType;
import communication.requests.user_requests.LoginRequest;
import communication.requests.user_requests.RegisterRequest;
import model.entities.user.User;
import networking.SocketService;
import state.AppState;

public class TCPUserClient implements UserClient {
  @Override
  public boolean login(LoginRequest loginRequest) {
    Request request = new Request(RequestType.USER, "login", loginRequest);

    Object inc = SocketService.sendRequest(request);


    switch (inc) {
      case User res -> {
        AppState.setLoggedInUser(res);
        return true;
      }

      // TODO More sophisticated error handling.
      case null -> {
        return false;
      }

      default -> {
        return false;
      }
    }
  }

  @Override
  public boolean register(RegisterRequest registerRequest) {
    Request request = new Request(RequestType.USER, "register", registerRequest);

    Object inc = SocketService.sendRequest(request);

    switch (inc) {
      case User res -> {
        AppState.setLoggedInUser(res);
        return true;
      }

      // TODO More sophisticated error handling.
      case null -> {
        return false;
      }

      default -> {
        return false;
      }
    }
  }
}
