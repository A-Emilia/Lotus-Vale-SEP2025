package GUI.View.Login;

import communication.Response;
import communication.requests.user_requests.LoginRequest;
import communication.requests.user_requests.RegisterRequest;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import networking.clients.user.UserClient;

import java.net.SocketTimeoutException;

public class LoginVM {
  private final UserClient userClient;


  private final StringProperty usernameFieldProperty = new SimpleStringProperty();
  private final StringProperty passwordFieldProperty = new SimpleStringProperty();

  public LoginVM(UserClient userClient) {this.userClient = userClient;}

  public StringProperty usernameFieldProperty() {
    return usernameFieldProperty;
  }

  public StringProperty passwordFieldProperty() {
    return passwordFieldProperty;
  }

  public Response login() throws SocketTimeoutException {
    LoginRequest loginRequest = new LoginRequest(usernameFieldProperty.get(), passwordFieldProperty.get());
    return userClient.login(loginRequest);
  }

  public Response register() throws SocketTimeoutException {
    RegisterRequest registerRequest = new RegisterRequest(usernameFieldProperty.get(), passwordFieldProperty().get());
    return userClient.register(registerRequest);
  }
}
