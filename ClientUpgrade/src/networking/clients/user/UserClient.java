package networking.clients.user;

import communication.Response;
import communication.requests.user_requests.LoginRequest;
import communication.requests.user_requests.RegisterRequest;

import java.net.SocketTimeoutException;

public interface UserClient {
  Response login(LoginRequest loginRequest) throws SocketTimeoutException;
  Response register(RegisterRequest registerRequest) throws SocketTimeoutException;
}
