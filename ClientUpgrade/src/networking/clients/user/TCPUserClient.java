package networking.clients.user;

import communication.Request;
import communication.RequestType;
import communication.Response;
import communication.ResponseType;
import communication.requests.user_requests.LoginRequest;
import communication.requests.user_requests.RegisterRequest;
import model.entities.user.User;
import networking.SocketClient;
import state.AppState;

import java.net.SocketTimeoutException;
import java.util.Objects;

public class TCPUserClient implements UserClient {
  @Override
  public Response login(LoginRequest loginRequest) throws SocketTimeoutException {
    Request request = new Request(RequestType.USER, loginRequest);
    return SocketClient.sendRequest(request);
  }

  @Override
  public Response register(RegisterRequest registerRequest) throws SocketTimeoutException {
    Request request = new Request(RequestType.USER, registerRequest);
    return SocketClient.sendRequest(request);
  }
}
