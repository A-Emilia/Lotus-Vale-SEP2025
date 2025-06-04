package GUI.View.Login;

import communication.requests.user_requests.LoginRequest;
import communication.requests.user_requests.RegisterRequest;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import networking.user.UserClient;

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

  public void login() {
    LoginRequest loginRequest = new LoginRequest(usernameFieldProperty.get(), passwordFieldProperty.get());

    // TODO More logic

    userClient.login(loginRequest);
  }

  public void register() {
    RegisterRequest registerRequest = new RegisterRequest(usernameFieldProperty.get(), passwordFieldProperty().get());

    userClient.register(registerRequest);
  }
}
